package com.onightperson.hearken.javacode;


import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by liubaozhu on 17/8/14.
 */

public class MainClass extends ClassLoader {
    private String root;



    public MainClass(ClassLoader loader) {
       super(loader);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("findClass");
        byte[] classData = loadClassData(name);
        if (classData == null) {
            throw new ClassNotFoundException();
        } else {
            return defineClass(name, classData, 0, classData.length);
        }
    }

    private byte[] loadClassData(String name) {
        System.out.println("loadClassData");
        String fileName = root + File.separatorChar + name.replace('.', File.separatorChar) + ".class";

        InputStream is = null;
        try {
            is = new FileInputStream(fileName);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int bufferSize = 1024;
            byte[] buffer = new byte[bufferSize];
            int length;

            while ((length = is.read(buffer)) != -1) {
                baos.write(buffer, 0, length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void setRoot(String root) {
        this.root = root;
    }

    public static void main(String[] args) {
//        ClassLoader loader = Test.class.getClassLoader();
//        while (loader != null) {
//            System.out.println("curLoader: " + loader);
//            loader = loader.getParent();
//        }
//
//        try {
//            Class.forName("com.onightperson.hearken.javatest.Test", true, Test.class.getClassLoader().getParent());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        MainClass myLoader = new MainClass(ClassLoader.getSystemClassLoader().getParent());

//        MainClass myLoader2 = new MainClass(ClassLoader.getSystemClassLoader().getParent());
        myLoader.setRoot("/Users/baidu/ONPApplication/Hearken/app/src/main/java");
//        myLoader2.setRoot("/Users/baidu/ONPApplication/Hearken/app/src/main/java");
        Class<?> testClass;
        Class<?> testClass2;

        try {
            testClass = Class.forName("com.onightperson.hearken.javatest.Test", true, myLoader);
            Object o1 = testClass.newInstance();
//            testClass2 = Class.forName("com.onightperson.hearken.javatest.Test", true, myLoader2);
//            Object o2 = testClass2.newInstance();
//            Method setTestMethod = testClass.getDeclaredMethod("setTest", java.lang.Object.class);
//            setTestMethod.invoke(o1, o2);
            System.out.println("loader: " + o1.getClass().getClassLoader());

            testBoolean();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void testBoolean() {
        ResultItem success = new ResultItem();
        anotherMethod(success);
        System.out.println("success: " + success.success);

    }

    private static void anotherMethod(ResultItem result) {
        for (int i = 0; i < 2; i++) {
            result.success = true;
        }
    }
}
