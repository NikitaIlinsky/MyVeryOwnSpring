package com.n_skiy.myspring;

import com.n_skiy.myspring.ApplicationContext;
import com.n_skiy.myspring.ObjectConfigurator;
import com.n_skiy.myspring.annotation.InjectStuffByType;

import java.lang.reflect.Field;


public class InjectStuffByTypeAnnotationObjectConfigurator implements ObjectConfigurator {

    @Override
    public void configure(Object t, ApplicationContext context) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(InjectStuffByType.class)) {
                injectToField(t, field, context);
            }
        }
    }

    private void injectToField(Object t, Field field, ApplicationContext context) {
        try {
            Class<?> type = field.getType();
            field.setAccessible(true);
            Object object = context.getObject(type);
            field.set(t, object);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
