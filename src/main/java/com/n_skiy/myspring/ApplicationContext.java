package com.n_skiy.myspring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class ApplicationContext {

    ObjectFactory objectFactory;
    private Map<Class, Object> cache = new ConcurrentHashMap<>();


    public <T> T getObject(Class<T> type) {
        return null;
    }
}
