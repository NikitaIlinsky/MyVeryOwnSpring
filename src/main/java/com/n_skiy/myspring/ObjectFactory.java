package com.n_skiy.myspring;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


public class ObjectFactory {

    private ApplicationContext context;

    private List<ObjectConfigurator> configurators = new ArrayList<>();

    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends ObjectConfigurator> aClass : this.context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(instantiateObject(aClass));
        }
    }

    public <T> T createObject(Class<T> type) {
        T t = instantiateObject(type);
        configurators.forEach(conf -> conf.configure(t, context));
        return t;
    }

    private <T> T instantiateObject(Class<? extends T> aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
