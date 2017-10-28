package com.titans.octopus.classloader;

import sun.misc.Launcher;

/**
 * @author vinfai
 * @since 2017-10-24
 */
public class ClassLoaderTest {

    public static void main(String[] args) throws Exception{
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
      /*  System.out.println(classLoader);
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
*/
        /*try {
            classLoader.loadClass("java.util.HashSet");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
//        Launcher.getLauncher()

        test3();
    }

    public static void test2() throws Exception{
        MyClassLoader loader = new MyClassLoader();
        Class<?> c = loader.loadClass("com.titans.octopus.classloader.HighRichHandsome");
        System.out.println("loaded by " + c.getClassLoader());

        Person p = (Person)c.newInstance();
        p.say();

        HighRichHandsome highRichHandsome = (HighRichHandsome) c.newInstance();
        highRichHandsome.say();
    }


    public static void test3() throws Exception{
        MyClassLoader loader = new MyClassLoader();
        Class<?> c = loader.findClass("com.titans.octopus.classloader.HighRichHandsome");
        System.out.println("loaded by " + c.getClassLoader());
        //loaded by com.titans.octopus.classloader.MyClassLoader@61bbe9ba
        Person p = (Person)c.newInstance();
        p.say();

        HighRichHandsome highRichHandsome = (HighRichHandsome) c.newInstance();
        highRichHandsome.say();
    }
}
