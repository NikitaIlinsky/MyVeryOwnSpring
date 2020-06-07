package com.n_skiy.myspring;

import org.reflections.Reflections;


public interface Config {

    <T> Class<? extends T> getImplClass(Class<T> interfaceType);

    Reflections getScanner();
}
