package com.n_skiy.myspring.example;

import com.n_skiy.myspring.ObjectFactory;


public class Main {

    public static void main(String[] args) {
        RoomCleaner roomCleaner = ObjectFactory.getInstance().createObject(RoomCleaner.class);
        Room room = new Room();

        roomCleaner.startCleaning(room);
    }

}
