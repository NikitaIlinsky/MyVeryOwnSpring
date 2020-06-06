package com.n_skiy.myspring;

public class ObjectFactory {

    private static ObjectFactory instance = new ObjectFactory();

    public static ObjectFactory getInstance() {
        return instance;
    }

    private ObjectFactory() {
    }

    public <T> T createObject(Class<T> type) {
        return null;
    }
}
