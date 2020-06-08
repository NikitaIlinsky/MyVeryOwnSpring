package com.n_skiy.myspring.example;

public class Main {

    public static void main(String[] args) {
        RoomCleaner roomCleaner = new RoomCleaner();
        Room room = new Room();

        roomCleaner.startCleaning(room);
    }

}
