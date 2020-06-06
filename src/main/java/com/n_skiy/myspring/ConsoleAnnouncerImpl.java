package com.n_skiy.myspring;

public class ConsoleAnnouncerImpl implements Announcer {

    public void announce(String announce) {
        System.out.println(announce);
    }
}
