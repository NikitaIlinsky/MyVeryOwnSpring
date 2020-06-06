package com.n_skiy;


public class RoomCleaner {

    private Announcer announcer = new ConsoleAnnouncerImpl();
    private CatSpookier catSpookier = new CatSpookierImpl();

    public void start(Room room) {
        announcer.announce("Preparing for cleaning");
        catSpookier.spookCats();
        clean(room);
        announcer.announce("Room " + room + " had being cleaned");
    }

    private void clean(Room room) {
        System.out.println("Cleaning room: " + room + " - wshoh-wshoh-wshoh");
    }
}
