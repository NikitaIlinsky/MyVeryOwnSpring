package com.n_skiy.myspring;

public class ObjectFactory {

    private static ObjectFactory instance = new ObjectFactory();

    private ObjectConfig objectConfig;


    public static ObjectFactory getInstance() {
        return instance;
    }

    private ObjectFactory() {
    }

    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if(type.isInterface()) {
            implClass = objectConfig.getImplClass(type);
        }

        try {
            return implClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
