package com.n_skiy.myspring;

import java.lang.reflect.Field;


public class InjectStuffByTypeAnnotationObjectConfigurator implements ObjectConfigurator {

    @Override
    public void configure(Object t) {
        for (Field field : t.getClass().getDeclaredFields()) {
            if(field.isAnnotationPresent(InjectStuffByType.class)) {
                injectToField(t, field);
            }
        }
    }

    private void injectToField(Object t, Field field) {
        try {
            Class<?> type = field.getType();
            field.setAccessible(true);
            Object object = ObjectFactory.getInstance().createObject(type);
            field.set(t, object);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
