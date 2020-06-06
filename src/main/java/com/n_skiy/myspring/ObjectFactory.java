package com.n_skiy.myspring;

import com.n_skiy.myspring.example.CatSpookier;
import com.n_skiy.myspring.example.InjectStuff;
import com.n_skiy.myspring.example.SprinklerCatSpookierImpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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
            T t = implClass.getDeclaredConstructor().newInstance();

            String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
            Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
            Map<String, String> propertiesMap = lines.map(line -> line.split("="))
                    .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));

            for(Field field : implClass.getDeclaredFields()) {
                InjectStuff annotation = field.getAnnotation(InjectStuff.class);


                if(annotation != null) {
                    String value;
                    if(annotation.value().isEmpty()) {
                        value = propertiesMap.get(field.getName());
                    } else {
                        value = propertiesMap.get(annotation.value());
                    }

                    boolean isAccessible = field.isAccessible();
                    field.setAccessible(true);
                    field.set(t, value);
                    field.setAccessible(isAccessible);
                }
            }

            return t;
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
