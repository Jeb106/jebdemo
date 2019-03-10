package com.jeb.framework.webmvc.servlet;

import com.jeb.framework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jinBiaoHu
 * @date 2019-01-13 17:06
 */
public class JebDispatchServlet extends HttpServlet {
	Map<String, Object> ioc = new HashMap<String, Object>();

	private Properties contextConfig = new Properties();

	private List<String> classNames = new ArrayList<String>();

	//	private Map<String, Object> handleMapping = new HashMap<String, Object>();
	private List<Handler> handleMapping = new ArrayList<Handler>();

	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//6 等待请求
		doDispatch(req, resp);
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		// 从这里开始启动
		//1 加载配置文件
		doLoadConfig(config.getInitParameter("contextConfigLocation"));
		//2 扫描所有相关类
		doScan(contextConfig.getProperty("scanPackage"));
		//3 初始化所有相关类
		doInstance();
		//4 自动注入
		doAutowired();
		//=================spring 初始化完成===============

		//5 初始化handlermapping
		initHandlerMapping();
		System.out.println("Jeb Spring init....");

	}
	private Handler getHandler(HttpServletRequest req) {
		if (handleMapping.isEmpty()) {
			return null;
		}
		String url = req.getRequestURI();
		String contextPath = req.getContextPath();
		url = url.replace(contextPath, "").replaceAll("/+", "/");
		for (Handler handler : handleMapping) {
			Matcher matcher = handler.pattern.matcher(url);
			if (!matcher.matches()) {
				continue;
			}
			return handler;
		}
		return null;
	}

	private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
		try {
			Handler handler = getHandler(req);

			if (null == handler) {
				try {
					resp.getWriter().write("404");
					return;
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			//
			Class<?>[] parameterTypes = handler.method.getParameterTypes();
			Object[] paramValues = new Object[parameterTypes.length];

			Map<String, String[]> parameterMap = req.getParameterMap();
			for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
				String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s", "");
				if (handler.paramIndexMapping.containsKey(param.getKey())) {
					int index = handler.paramIndexMapping.get(param.getKey());
					paramValues[index] = convert(parameterTypes[index], value);
				}
			}

			int reqIndex = handler.paramIndexMapping.get(HttpServletRequest.class.getName());
			paramValues[reqIndex] = req;
			int respIndex = handler.paramIndexMapping.get(HttpServletResponse.class.getName());
			paramValues[respIndex] = resp;

			handler.method.invoke(handler.controler, paramValues);
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	private Object convert(Class<?> type, String value) {
		if (Integer.class == type) {
			return Integer.valueOf(value);
		}
		return value;
	}



	private void doScan(String scanPackage) {
		try {
			URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
			File classDir = new File(url.getFile());
			for (File file : classDir.listFiles()) {
				if (file.isDirectory()) {
					doScan(scanPackage + "." + file.getName());
				}
				else {
					String className = scanPackage + "." + file.getName().replace(".class", "");
					classNames.add(className);
				}
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doInstance() {
		if (classNames.isEmpty()) {
			return;
		}
		for (String classs : classNames) {
			try {
				Class<?> className = Class.forName(classs);
				if (className.isAnnotationPresent(JebController.class)) {
					String beanName = lowerFirstWord(className.getSimpleName());
					ioc.put(beanName, className.newInstance());

				}
				else if (className.isAnnotationPresent(JebService.class)) {
					JebService annotation = className.getAnnotation(JebService.class);
					String beanName = annotation.value();
					if ("".equals(beanName.trim())) {
						beanName = lowerFirstWord(className.getSimpleName());
					}
					Object instance = className.newInstance();
					ioc.put(beanName, instance);

					for (Class<?> i : className.getInterfaces()) {
						try {
							beanName = lowerFirstWord(i.getSimpleName());
							ioc.put(beanName, instance);
						}
						catch (Exception e) {
							System.out.println("================bean已经存在=================");
						}
					}

				}
				else {
					continue;
				}

			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (InstantiationException e) {
				e.printStackTrace();
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private String lowerFirstWord(String name) {
		char[] chars = name.toCharArray();
		chars[0] += 32;
		return String.valueOf(chars);
	}

	private void doAutowired() {
		if (ioc.isEmpty()) {
			return;
		}
		for (Map.Entry<String, Object> entry : ioc.entrySet()) {
			Object instance = entry.getValue();
			Field[] declaredFields = instance.getClass().getDeclaredFields();
			for (Field field : declaredFields) {
				if (field.isAnnotationPresent(JebAutowired.class)) {
					JebAutowired jebAutowired = field.getAnnotation(JebAutowired.class);
					String autoName = jebAutowired.value().trim();
					if ("".equals(autoName)) {
						autoName = field.getName();

					}
					field.setAccessible(true);
					Object obj = ioc.get(autoName);
					try {
						field.set(instance, obj);
					}
					catch (IllegalAccessException e) {
						e.printStackTrace();
						continue;
					}
				}
				else {
					continue;
				}

			}
		}

	}

	private void doLoadConfig(String contextConfigLocation) {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
		try {
			contextConfig.load(is);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (null != is) {
				try {
					is.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private void initHandlerMapping() {
		if (ioc.isEmpty()) {
			return;
		}
		for (Map.Entry<String, Object> entry : ioc.entrySet()) {
			Object instance = entry.getValue();
			String baseUrl = "";
			if (instance.getClass().isAnnotationPresent(JebRequestMapping.class)) {
				JebRequestMapping jebRequestMapping = instance.getClass().getAnnotation(JebRequestMapping.class);
				baseUrl = jebRequestMapping.value();
			}
			Method[] methods = instance.getClass().getMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(JebRequestMapping.class)) {
					JebRequestMapping jebRequestMapping = method.getAnnotation(JebRequestMapping.class);
					String regex = ("/" + baseUrl + "/" + jebRequestMapping.value().trim()).replaceAll("/+", "/");
					Pattern pattern = Pattern.compile(regex);

					handleMapping.add(new Handler(pattern, entry.getValue(), method));
					//handleMapping.put(url,method);
					System.out.println("handleMapping:" + method);
				}
			}

		}

	}

	private class Handler {
		protected Object controler;

		protected Method method;

		protected Pattern pattern;

		protected Map<String, Integer> paramIndexMapping;

		protected Handler(Pattern pattern, Object controler, Method method) {
			this.controler = controler;
			this.method = method;
			this.pattern = pattern;
			paramIndexMapping = new HashMap<String, Integer>();
			putParamIndexMapping(method);
		}

		private void putParamIndexMapping(Method method) {
			Annotation[][] parameterAnnotations = method.getParameterAnnotations();
			for (int i = 0; i < parameterAnnotations.length; i++) {
				for (Annotation a : parameterAnnotations[i]) {
					if (a instanceof JebRequestParam) {
						String paramName = ((JebRequestParam) a).value();
						if (!"".equals(paramName.trim())) {
							paramIndexMapping.put(paramName, i);
						}
					}
				}
			}

			//
			Class<?>[] parameterTypes = method.getParameterTypes();
			for (int i = 0; i < parameterTypes.length; i++) {
				Class<?> parameterType = parameterTypes[i];
				if (parameterType == HttpServletRequest.class || parameterType == HttpServletResponse.class) {
					paramIndexMapping.put(parameterType.getName(), i);
				}
			}

		}

	}
}
