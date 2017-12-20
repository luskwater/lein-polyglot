package {{namespace}};

import clojure.java.api.Clojure;
import clojure.lang.IFn;


class {{java-class-name}} {
    private static volatile {{java-class-name}} instance = null;
    private static Object mutex = new Object();
    private static IFn clojureRequire;
    private static IFn {{name}}Fn;
    private {{java-class-name}}() {
        clojureRequire = Clojure.var("clojure.core", "require");
        clojureRequire.invoke(Clojure.read("{{namespace}}"));
        {{name}}Fn = Clojure.var("{{namespace}}", "foo");
    }
    private static void callFoo(String s) {
        {{name}}Fn.invoke(s);
    }
    public static void run() {
        System.out.println("Hello, World! (from Java)");
        callFoo("Java calling!");
    }
    public static {{java-class-name}} getInstance() {
        {{java-class-name}} result = instance;
        if (result == null) {
            synchronized(mutex) {
                result = instance;
                if (result == null)
                    instance = result = new {{java-class-name}}();
            }
        }
        return instance;
    }
    public static void main(String[] args) {
        {{java-class-name}} me = getInstance();
        me.run();
    }
}
