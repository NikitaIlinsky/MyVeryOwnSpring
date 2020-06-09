package com.n_skiy.myspring.example;

import com.n_skiy.myspring.InjectStuffByType;


public class ConsoleAnnouncerImpl implements Announcer {

    @InjectStuffByType
    private ThroatCleaner throatCleaner;

    @Override
    public void announce(String announce) {
        throatCleaner.throatClean();
        System.out.println(announce);
    }
}
