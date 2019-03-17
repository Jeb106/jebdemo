package com.jeb.framework.webmvc.servlet;

import com.jeb.framework.annotation.JebController;
import com.jeb.framework.annotation.JebRequestMapping;
import com.jeb.framework.annotation.JebRequestParam;
import com.jeb.framework.webmvc.bean.JebModelAndView;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件名：JbDispatchServlet
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-16 14:57
 */
public class JbDispatchServlet extends HttpServlet {
    private static final String LOCATION = "contextConfigLocation";
    List<Handler> handleMapping = new ArrayList<Handler>();
    private Map<Handler,HandlerAdapter> adapterMapping = new HashMap<Handler, HandlerAdapter>();
    private List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>();
    @Override
    public void init(ServletConfig config) throws ServletException {
        //ioc容器必须要先初始化  假设容器以及启动
        JbApplicationContext context = new JbApplicationContext(config.getInitParameter(LOCATION));
        //请求解析
        initMultipartResolver(context);
        //多语言、国际化
        initLocaleResolver(context);
        //主题View层的
        initThemeResolver(context);

        //============== 重要 ================
        //解析url和Method的关联关系
        initHandlerMappings(context);
        //适配器（匹配的过程）
        initHandlerAdapters(context);
        //============== 重要 ================


        //异常解析
        initHandlerExceptionResolvers(context);
        //视图转发（根据视图名字匹配到一个具体模板）
        initRequestToViewNameTranslator(context);

        //解析模板中的内容（拿到服务器传过来的数据，生成HTML代码）
        initViewResolvers(context);

        initFlashMapManager(context);

    }

    private void initFlashMapManager(JbApplicationContext context) {
    }

    private void initViewResolvers(JbApplicationContext context) {
        //模板一般是不会放到WebRoot下的，而是放在WEB-INF下，或者classes下
        //这样就避免了用户直接请求到模板
        //加载模板的个数，存储到缓存中
        //检查模板中的语法错误
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String rootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File rootDir = new File(rootPath);
        for (File template : rootDir.listFiles()) {
            viewResolvers.add(new ViewResolver(template.getName(),template));
        }
    }

    private void initRequestToViewNameTranslator(JbApplicationContext context) {
    }

    private void initHandlerExceptionResolvers(JbApplicationContext context) {
    }

    private class ViewResolver {
        protected String viewName;
        protected File file;

        protected ViewResolver(String viewName, File file) {
            this.file = file;
            this.viewName = viewName;
        }

        protected String parse(JebModelAndView mv) throws FileNotFoundException {
            StringBuffer sb = new StringBuffer();
            RandomAccessFile ra = new RandomAccessFile(this.file, "r");

            try {
                //模板框架的语法是非常复杂，但是，原理是一样的
                //无非都是用正则表达式来处理字符串而已
                //就这么简单，不要认为这个模板框架的语法是有多么的高大上
                //来我现在来做一个最接地气的模板

                String line = null;
                while (null != (line = ra.readLine())) {
                    Matcher m = matcher(line);
                    //循环查找
                    while (m.find()) {
                        for (int i = 0; i < m.groupCount(); i++) {
                           String paramName =  m.group(i).replaceAll("@\\{|\\}","");
                            Object paramValue = mv.getModel().get(paramName);
                            if (null == paramValue) {
                                continue;
                            }
                            line = line.replaceAll("@\\{" + paramName + "\\}", paramValue.toString());
                        }
                    }
                        sb.append(line);
                }

            } catch (Exception e) {
            }
            return  sb.toString();
        }

    }

    private Matcher matcher(String line) {
        //分组匹配 非贪婪模式
        Pattern p = Pattern.compile("@\\{(.+?)\\}",Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(line);
        return m;
    }

    //适配器（匹配的过程）
    //主要是用来动态匹配我们参数的
    private void initHandlerAdapters(JbApplicationContext context) {

        if (handleMapping.isEmpty()) {
            return;
        }
        Map<String, Integer> paramMapping = new HashMap<String, Integer>();

        for (Handler handler : handleMapping) {
            Class<?>[] parameterTypes = handler.method.getParameterTypes();
            //有顺序，但是通过反射，没法拿到我们参数名字
            //匹配自定参数列表
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> type = parameterTypes[i];
                if (type == HttpServletRequest.class || type == HttpServletResponse.class) {
                    paramMapping.put(type.getSimpleName(), i);

                }
            }
            Annotation[][] pa = handler.method.getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation annotation : pa[i]) {
                    if (annotation instanceof JebRequestParam) {
                        String paramName = ((JebRequestParam) annotation).value();
                        if (StringUtils.isNotEmpty(paramName)) {
                            paramMapping.put(paramName, i);
                        }
                    }
                }
            }

            adapterMapping.put(handler, new HandlerAdapter(paramMapping));
        }




    }

    private void initHandlerMappings(JbApplicationContext context) {

        Map<String, Object> ioc = context.getAll();
        if (ioc.isEmpty()) {
            return;
        }

        //只要是由Cotroller修饰类，里面方法全部找出来
        //而且这个方法上应该要加了RequestMaping注解，如果没加这个注解，这个方法是不能被外界来访问的

        Set<Map.Entry<String, Object>> entries = ioc.entrySet();

        for (Map.Entry<String, Object> entry : entries) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(JebController.class)) {
                continue;
            }
            String url = "";
            if (clazz.isAnnotationPresent(JebRequestMapping.class)) {
                url = clazz.getAnnotation(JebRequestMapping.class).value();
            }
            //扫描Controller下面的所有的方法
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(JebRequestMapping.class)) {
                    continue;
                }
                String methodUrl = method.getAnnotation(JebRequestMapping.class).value();
                String regex = (url + methodUrl).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
                handleMapping.add(new Handler(pattern, entry.getValue(), method));

            }


        }
    }

    private void initThemeResolver(JbApplicationContext context) {
    }

    private void initLocaleResolver(JbApplicationContext context) {
    }

    private void initMultipartResolver(JbApplicationContext context) {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //先取出来一个Handler，从HandlerMapping取
        Handler handler = getHandle(req);
        if (null == handler) {
            resp.getWriter().write("404");
            return;
        }
        //再取出来一个适配器
        //再由适配去调用我们具体的方法
        HandlerAdapter handlerAdapter = getHandleAdapter(handler);
        JebModelAndView mv = handlerAdapter.handle(req, resp, handler);
        applyDefaultViewName(resp, mv);

    }

    private void applyDefaultViewName(HttpServletResponse resp, JebModelAndView mv) {
        if (null == mv) {
            return;
        }
        for (ViewResolver viewResolver : viewResolvers) {
            if (!mv.getView().equals(viewResolver.viewName)) {
                continue;
            }
            try {
                String parse = viewResolver.parse(mv);

                if (parse != null) {
                    resp.getWriter().write(parse);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private HandlerAdapter getHandleAdapter(Handler handler) {
        if(adapterMapping.isEmpty()){return null;}
        return adapterMapping.get(handler);
    }

    private Handler getHandle(HttpServletRequest req) {

        if (handleMapping.isEmpty()) {
            return null;
        }

        String uri = req.getRequestURI();
        String contextPath = req.getContextPath();
        uri = uri.replaceAll(contextPath,"").replaceAll("/+","/");
        for (Handler handler : handleMapping) {
            Matcher matcher = handler.pattern.matcher(uri);

            if (!matcher.matches()) {
                continue;
            }
            return handler;
        }
         return null;
    }

    /**
     * handleMapping 定义
     */
    private class Handler {

        protected Object controller;
        protected Method method;
        protected Pattern pattern;

        protected Handler(Pattern pattern, Object controller, Method method) {
            this.pattern = pattern;
            this.controller = controller;
            this.method = method;
        }


    }

    private class HandlerAdapter {

        private Map<String, Integer> paramMapping;
        public HandlerAdapter(Map<String, Integer> paramMapping) {
            this.paramMapping = paramMapping;
        }

        //主要目的是用反射调用url对应的method
        public JebModelAndView handle(HttpServletRequest request, HttpServletResponse response,Handler handler) {
            Class<?>[] parameterTypes = handler.method.getParameterTypes();
            //要想给参数赋值，只能通过索引号来找到具体的某个参数
            Object[] paramValue = new Object[parameterTypes.length];

            Map<String,String[]> parameterMap = request.getParameterMap();

            for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
                String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", ",");
                if (!this.paramMapping.containsKey(param.getKey())) {
                    return null;
                }
                int index = paramMapping.get(param.getKey());
                paramValue[index] = castStringValue(value, parameterTypes[index]);


            }

            String reqName = HttpServletRequest.class.getSimpleName();
            if (paramMapping.containsKey(reqName)) {
                Integer reqIndex = paramMapping.get(reqName);
                paramValue[reqIndex] = request;
            }
            String resqName = HttpServletResponse.class.getSimpleName();
            if(this.paramMapping.containsKey(resqName)){
                int respIndex = this.paramMapping.get(resqName);
                paramValue[respIndex] = response;
            }
            try {
                Object r = handler.method.invoke(handler.controller, paramValue);
                boolean isModeAndView = handler.method.getReturnType() == JebModelAndView.class;
                if (isModeAndView) {
                    return (JebModelAndView) r;
                } else {
                    return  null;
                }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            return  null;
        }

    }

    private Object castStringValue(String value, Class<?> clazz) {

        if (clazz == String.class) {
            return value;
        } else if (clazz == Integer.class) {
            return Integer.valueOf(value);
        } else if (clazz == int.class) {
            return Integer.valueOf(value).intValue();
        } else {
            return null;
        }
    }

}
