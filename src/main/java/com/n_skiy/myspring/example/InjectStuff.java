package com.n_skiy.myspring.example;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface InjectStuff {
    String value() default "";
}
