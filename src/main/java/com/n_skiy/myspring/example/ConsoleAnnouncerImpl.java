package com.n_skiy.myspring.example;

public class ConsoleAnnouncerImpl implements Announcer {

    @Override
    public void announce(String announce) {
        System.out.println(announce);
    }
}