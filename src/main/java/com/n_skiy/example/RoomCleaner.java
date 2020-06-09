package com.n_skiy.example;


import com.n_skiy.myspring.InjectStuffByType;


public class RoomCleaner {

    @InjectStuffByType
    private Announcer announcer;

    @InjectStuffByType
    private CatSpookier catSpookier;

    public void startCleaning(Room room) {
        announcer.announce("Preparing for cleaning");
        catSpookier.spookCats();
        clean(room);
        announcer.announce(room + " had being cleaned");
    }

    private void clean(Room room) {
        System.out.println("Cleaning " + room + ": wshoh-wshoh-wshoh");
    }
}
