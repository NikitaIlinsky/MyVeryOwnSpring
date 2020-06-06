package com.n_skiy.myspring;

import org.reflections.Reflections;

import java.util.Set;


public class ObjectConfig {

    private Reflections scanner;

    public ObjectConfig(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    public <T> Class<? extends T> getImplClass(Class<T> interfaceType) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(interfaceType);
        if (classes.size() != 1) {
            throw new RuntimeException(interfaceType + "has 0 or more than one impl");
        }

        return classes.iterator().next();
    }
}
