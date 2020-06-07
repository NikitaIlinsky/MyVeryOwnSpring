package com.n_skiy.myspring;

import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;


public class ObjectConfig implements Config {

    private Reflections scanner;
    private Map<Class, Class> interface2ImplClass;

    public ObjectConfig(String packageToScan, Map<Class, Class> interface2ImplClass) {
        this.interface2ImplClass = interface2ImplClass;
        this.scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> interfaceType) {
        return interface2ImplClass.computeIfAbsent(interfaceType, aClass -> {
            Set<Class<? extends T>> classes = scanner.getSubTypesOf(interfaceType);
            if (classes.size() != 1) {
                throw new RuntimeException(interfaceType + "has 0 or more than one impl please update your config");
            }

            return classes.iterator().next();
        });
    }

    @Override
    public Reflections getScanner() {
        return scanner;
    }
}
