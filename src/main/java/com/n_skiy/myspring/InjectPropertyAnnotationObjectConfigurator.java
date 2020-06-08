package com.n_skiy.myspring;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class InjectPropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    private Map<String, String> propertiesMap;

    public InjectPropertyAnnotationObjectConfigurator() {
        try {
            String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
            Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
            propertiesMap = lines.map(line -> line.split("="))
                    .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void configure(Object t) {
        Class<?> implClass = t.getClass();

        for(Field field : implClass.getDeclaredFields()) {
            InjectStuff annotation = field.getAnnotation(InjectStuff.class);
            if(annotation != null) {
                String value;
                if(annotation.value().isEmpty()) {
                    value = propertiesMap.get(field.getName());
                } else {
                    value = propertiesMap.get(annotation.value());
                }

                injectValueToField(t, field, value);
            }
        }
    }

    private void injectValueToField(Object obj, Field field, String value) {
        try {
            boolean isAccessible = field.isAccessible();
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(isAccessible);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
