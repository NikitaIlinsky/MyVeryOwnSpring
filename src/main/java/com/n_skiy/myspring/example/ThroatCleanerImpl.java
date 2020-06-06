package com.n_skiy.myspring.example;

public class ThroatCleanerImpl implements ThroatCleaner {

    @InjectStuff
    private String coughType;

    @Override
    public void throatClean() {
        System.out.println(coughType + " " + coughType);
    }
}
