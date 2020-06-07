package com.n_skiy.myspring;

import com.n_skiy.myspring.example.CatSpookier;
import com.n_skiy.myspring.example.SprinklerCatSpookierImpl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ObjectFactory {

    private static ObjectFactory instance = new ObjectFactory();

    private Config config;
    private List<ObjectConfigurator> configurators = new ArrayList<>();


    public static ObjectFactory getInstance() {
        return instance;
    }

    private ObjectFactory() {
        HashMap<Class, Class> interface2ImplClass = new HashMap<>();
        interface2ImplClass.put(CatSpookier.class, SprinklerCatSpookierImpl.class);

        config = new ObjectConfig("com.n_skiy.myspring", interface2ImplClass);
        for (Class<? extends ObjectConfigurator> aClass : config.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(instantiateObject(aClass));
        }
    }

    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = config.getImplClass(type);
        }

        T t = instantiateObject(implClass);
        configurators.forEach(conf -> conf.configure(t));

        return t;
    }

    private <T> T instantiateObject(Class<? extends T> aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException();
        }
    }
}
