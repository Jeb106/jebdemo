package com.example.designmodel.proxy.gupao;

import java.io.*;

/**
 * 文件名：ExClassLoader
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-03 21:27
 */
public class ExClassLoader extends  ClassLoader {

    private File baseDir;


    public ExClassLoader() {
        String path = ExClassLoader.class.getResource("").getPath();
        this.baseDir = new File(path);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String className = ExClassLoader.class.getPackage().getName() + "." + name;
        FileInputStream in= null;
        ByteArrayOutputStream out=null;
        if (baseDir != null) {
            File classFile = new File(baseDir, name.replaceAll("\\.", "/")+".class");
            if (classFile.exists()) {
                try {
                    in = new FileInputStream(classFile);
                    out = new ByteArrayOutputStream();
                    byte[] buff = new byte[1024];
                    int len;
                    while ((len = in.read(buff)) != -1){
                        out.write(buff,0,len);

                    }
                    return defineClass(className,out.toByteArray(),0,out.size());



                } catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    if(null != in){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(null != out){
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    classFile.delete();
                }


            }
        }



        return null;
    }
}
