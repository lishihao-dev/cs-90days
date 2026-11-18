/**
 * Day 1 / 程序 0：HelloWorld
 *
 * 目的：把 .java -> .class -> JVM 这条链跑通两遍（IDEA 一遍，命令行一遍）。
 *
 * 命令行执行：
 *   javac -d out HelloWorld.java
 *   java -cp out HelloWorld
 */
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello, Java.");
        System.out.println("java.version = " + System.getProperty("java.version"));
        System.out.println("java.home    = " + System.getProperty("java.home"));

        // 当前类的加载器：AppClassLoader 说明这个类是从 -classpath 上找到的
        ClassLoader loader = HelloWorld.class.getClassLoader();
        System.out.println("classloader  = " + loader);

        // 打印入参，用来观察 main(String[] args) 里的数组到底装了什么
        if (args.length > 0) {
            System.out.println("args.length  = " + args.length);
            for (int i = 0; i < args.length; i++) {
                System.out.println("  args[" + i + "] = " + args[i]);
            }
        } else {
            System.out.println("args.length  = 0（没传参数）");
        }
    }
}
