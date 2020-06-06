package com.n_skiy.myspring;

public class Room {

    private static int roomCount = 0;
    private final int roomId = ++roomCount;

    @Override
    public String toString() {
        return "Room #" + roomId;
    }
}
