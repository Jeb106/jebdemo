package com.example.designmodel.proxy.gupao;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 文件名：ExProxy
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-03 21:25
 */
public class ExProxy implements Serializable {

    private static String ln = "\r\n";

    public static Object newProxyInstance(ExClassLoader loader,
                                          Class<?>[] interfaces,
                                          ExInvocationHandler h)
            throws IllegalArgumentException
    {
        try {
            //1生成源代码
            String generateSrc = generateSrc(interfaces[0]);

            //2将源代码生成到磁盘
            String filePath = ExProxy.class.getResource("").getPath();
            File file = new File(filePath + "$Proxy0.java");

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(generateSrc);
            fileWriter.flush();
            fileWriter.close();
            //3编译源码，生成。class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
            Iterable iterable = manager.getJavaFileObjects(file);


            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, iterable);
            task.call();
            manager.close();
            //4 将class文件中到内容，动态添加到jvm中
            //5 返回被代理后到对象
            Class<?> proxyClass = loader.findClass("$Proxy0");
            Constructor<?> c = proxyClass.getConstructor(ExInvocationHandler.class);
            file.delete();
            return  c.newInstance(h);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return  null;
    }
    public static String generateSrc(Class<?> interfaces) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.example.designmodel.proxy.gupao;"+ln);
        sb.append("import java.lang.reflect.Method;" + ln);
        sb.append("import com.example.designmodel.proxy.gupao.*;" + ln);
        sb.append("public class $Proxy0 implements " +interfaces.getSimpleName() + "{" +ln);

        sb.append("private ExInvocationHandler h;" + ln);
        sb.append("public $Proxy0(ExInvocationHandler h){");
        sb.append("this.h = h;");
        sb.append("}");

        for (Method method :interfaces.getMethods()) {
            sb.append("public " + method.getReturnType() +"  "+ method.getName() + "(){" + ln);
            sb.append("try{" + ln);
            sb.append("Method m = "+interfaces.getSimpleName() +".class.getMethod(\""+method.getName()+"\",new Class[] { });"+ln);
            sb.append("this.h.invoke(this,m,null);"+ln);
            sb.append("}catch(Throwable e){e.printStackTrace();}" + ln);
            sb.append("}");

            sb.append("}" + ln);

        }
        return  sb.toString();

    }
}
