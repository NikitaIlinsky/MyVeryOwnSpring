package com.n_skiy.myspring.example;

import com.n_skiy.myspring.ObjectFactory;


public class ConsoleAnnouncerImpl implements Announcer {

    private ThroatCleaner throatCleaner = ObjectFactory.getInstance().createObject(ThroatCleaner.class);

    @Override
    public void announce(String announce) {
        throatCleaner.throatClean();
        System.out.println(announce);
    }
}
