package com.n_skiy.myspring;

import com.n_skiy.myspring.annotation.SingletonStuff;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ApplicationContext {

    private ObjectFactory objectFactory;

    private Map<Class, Object> cache = new ConcurrentHashMap<>();
    private Config config;

    ApplicationContext(Config config) {
        this.config = config;
    }

    void setObjectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    Config getConfig() {
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
