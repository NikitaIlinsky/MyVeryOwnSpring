package com.n_skiy.myspring.example;

import com.n_skiy.myspring.InjectStuff;


public class ThroatCleanerImpl implements ThroatCleaner {

    @InjectStuff                 // Inject from application.property by field name
    private String coughType1;

    @InjectStuff("coughType2")   // Inject from application.property by property
    private String coughEnding;

    @Override
    public void throatClean() {
        System.out.println(coughType1 + " " + coughEnding);
    }
}
