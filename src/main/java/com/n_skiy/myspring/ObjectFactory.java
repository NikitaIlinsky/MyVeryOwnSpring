package com.n_skiy.myspring;

import java.lang.reflect.InvocationTargetException;


public class ObjectFactory {

    private static ObjectFactory instance = new ObjectFactory();

    private ObjectConfig objectConfig = new ObjectConfig("com.n_skiy.myspring");


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
            return implClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
