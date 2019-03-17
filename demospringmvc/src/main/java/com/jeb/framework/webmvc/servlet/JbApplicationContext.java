package com.jeb.framework.webmvc.servlet;

import com.jeb.framework.annotation.JebAutowired;
import com.jeb.framework.annotation.JebController;
import com.jeb.framework.annotation.JebService;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 文件名：JbApplicationContext
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-16 15:21
 */
public class JbApplicationContext {

    //类似于内部的配置信息，我们在外面是看不到的
    //我们能够看到的只有ioc容器  getBean方法来间接调用的
    private List<String> classCache = new ArrayList<String>();

    private Properties config = new Properties();

    Map<String, Object> instanceMapping = new ConcurrentHashMap<String, Object>();




    public JbApplicationContext(String context) {
        System.out.println("context：" + context);
        InputStream is = null;
        try {
            //1定位
            is = this.getClass().getClassLoader().getResourceAsStream(context);
            //2载入
            config.load(is);
            //3注册 把所有class找出来保存下来
            String scanPackage = config.getProperty("scanPackage");
            doRegister(scanPackage);

            //4实例化需要ioc的对象（@Service @Controller） 循环class
            doCreateBean();

            //5 注入
            populate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void populate() {
        if (instanceMapping.isEmpty()) {
            return;
        }
        Set<Map.Entry<String, Object>> entries = instanceMapping.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();

            for (Field field : fields) {
                if (field.isAnnotationPresent(JebAutowired.class)) {
                    JebAutowired annotation = field.getAnnotation(JebAutowired.class);
                    String id = annotation.value().trim();
                    //如果id为空，也就是说，自己没有设置，默认根据类型来注入
                    if (StringUtils.isEmpty(id)) {
                        id = lowerFirstChar(field.getType().getSimpleName());
                    }

                    field.setAccessible(true);
                    try {
                        field.set(entry.getValue(),instanceMapping.get(id));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("sss");

    }

    private void doCreateBean() {
        //检查看有没有注册信息,注册信息里面保存了所有的class名字
        //BeanDefinition 保存了类的名字，也保存类和类之间的关系(Map/list/Set/ref/parent)
        //processArray
        if (classCache.size() == 0) {
            return;
        }

        try {
            for (String className : classCache) {
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(JebController.class)) {
                    String id = lowerFirstChar(clazz.getSimpleName());
                    instanceMapping.put(id, clazz.newInstance());

                } else if (clazz.isAnnotationPresent(JebService.class)) {
                    JebService service = clazz.getAnnotation(JebService.class);
                    // 如果设置了名字 就是自定义的名字
                    String id = service.value();
                    if (!"".equals(id)) {
                        instanceMapping.put(id, clazz.newInstance());

                    }

                    //如果是空的，就用默认规则
                    //1、类名首字母小写
                    //如果这个类是接口
                    //2、可以根据类型类匹配
                    Class<?>[] interfaces = clazz.getInterfaces();
                    if (interfaces.length > 0) {
                        for (Class<?> anInterface : interfaces) {
                            instanceMapping.put(lowerFirstChar(anInterface.getSimpleName()), clazz.newInstance());

                        }
                    } else {
                        String insName = lowerFirstChar(clazz.getSimpleName());
                        instanceMapping.put(insName, clazz.newInstance());
                    }
                }else {
                    continue;
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String lowerFirstChar(String name) {
        char[] chars = name.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);

    }

    private void doRegister(String scanPackage) {
        // 获取 绝对路径  url  = file:/Users/hujinbiao/workspace/IdeaProjects/demo/demospringmvc/target/classes/com/jeb
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File dir = new File(url.getFile());
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                //递归调用
                doRegister(scanPackage + "." + file.getName());
            } else {
                classCache.add(scanPackage + "." + file.getName().replaceAll(".class", "").trim());
            }
        }


    }

    public Map<String,Object> getAll(){
        return instanceMapping;
    }


    public Properties getConfig() {
        return config;
    }
}
