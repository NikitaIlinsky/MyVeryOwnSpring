package com.n_skiy.example;

public class Room {

    private static int roomCount = 0;
    private final int roomId = ++roomCount;

    @Override
    public String toString() {
        return "Room #" + roomId;
    }
}
