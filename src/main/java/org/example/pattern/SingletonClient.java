package org.example.pattern;

import java.util.function.Function;

class LazySingleObject{
    private LazySingleObject INSTANCE;

    public LazySingleObject getInstance() {
        if (INSTANCE == null) {
            synchronized (LazySingleObject.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazySingleObject();
                }
            }
        }
        return INSTANCE;
    }
}

enum EagerSingleton{
    EAGER_SINGLETON("ff", (ss)->{
        return true;
    });
    final String value;
    EagerSingleton(String s, Function<String, Boolean> function){
        this.value=s;
    }

}

public class SingletonClient {
    public static void main(String[] args) {}
}
