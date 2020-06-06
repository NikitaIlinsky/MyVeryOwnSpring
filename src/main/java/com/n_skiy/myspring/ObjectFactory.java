package com.n_skiy.myspring;

import com.n_skiy.myspring.example.CatSpookier;
import com.n_skiy.myspring.example.SprinklerCatSpookierImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;


public class ObjectFactory {

    private static ObjectFactory instance = new ObjectFactory();

    private ObjectConfig objectConfig;


    public static ObjectFactory getInstance() {
        return instance;
    }

    private ObjectFactory() {
        HashMap<Class, Class> interface2ImplClass = new HashMap<>();
        interface2ImplClass.put(CatSpookier.class, SprinklerCatSpookierImpl.class);

        objectConfig = new ObjectConfig("com.n_skiy.myspring.example", interface2ImplClass);
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
