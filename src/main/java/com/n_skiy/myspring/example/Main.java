package com.n_skiy.myspring.example;

import com.n_skiy.myspring.ApplicationContext;


public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext();
        RoomCleaner roomCleaner = context.getObject(RoomCleaner.class);
        Room room = new Room();

        roomCleaner.startCleaning(room);
    }

}
