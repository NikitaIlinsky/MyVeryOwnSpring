package com.n_skiy.myspring;

import com.n_skiy.myspring.example.Room;
import com.n_skiy.myspring.example.RoomCleaner;


public class Main {

    public static void main(String[] args) {
        RoomCleaner roomCleaner = new RoomCleaner();
        Room room = new Room();

        roomCleaner.startCleaning(room);
    }

}
