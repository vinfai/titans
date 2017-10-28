package java.util;

import sun.applet.AppletClassLoader;

/**
 * @author vinfai
 * @since 2017-10-24
 */
public class MyClazz {

    private HashMap<String, String> map = new HashMap<>();

    public void doTest() {
        final HashMap.Node<String, String> node = map.getNode(1, 2);
    }

    public static void main(String[] args) {
        //java.lang.SecurityException: Prohibited package name: java.util

        new MyClazz().doTest();
        //new ExtentionClassLoader

    }

}
