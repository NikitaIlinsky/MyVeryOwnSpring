package com.n_skiy.example;

import com.n_skiy.myspring.annotation.InjectStuffFromProperty;
import com.n_skiy.myspring.annotation.SingletonStuff;


@SingletonStuff
public class ThroatCleanerImpl implements ThroatCleaner {

    @InjectStuffFromProperty                 // Inject from application.property by field name
    private String coughType1;

    @InjectStuffFromProperty("coughType2")   // Inject from application.property by property
    private String coughEnding;

    @Override
    public void throatClean() {
        System.out.println(coughType1 + " " + coughEnding);
    }
}
