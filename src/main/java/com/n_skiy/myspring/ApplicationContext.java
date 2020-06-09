package com.n_skiy.myspring;

import com.n_skiy.myspring.example.CatSpookier;
import com.n_skiy.myspring.example.SprinklerCatSpookierImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ApplicationContext {

    private ObjectFactory objectFactory;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();

    private Config config;

    public ApplicationContext() {
        HashMap<Class, Class> interface2ImplClass = new HashMap<>();
        interface2ImplClass.put(CatSpookier.class, SprinklerCatSpookierImpl.class);
        config = new ObjectConfig("com.n_skiy.myspring", interface2ImplClass);

        objectFactory = new ObjectFactory(this);
    }

    public Config getConfig() {
        return config;
    }

    public <T> T getObject(Class<T> type) {
        if(cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }
        T t = objectFactory.createObject(implClass);

        if (implClass.isAnnotationPresent(SingletonStuff.class)) {
            cache.put(type, t);
        }

        return t;
    }
}
