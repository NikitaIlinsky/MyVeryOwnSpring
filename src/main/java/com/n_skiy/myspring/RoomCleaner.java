package com.n_skiy.myspring;


public class RoomCleaner {

    private Announcer announcer = ObjectFactory.getInstance().createObject(Announcer.class);
    private CatSpookier catSpookier = ObjectFactory.getInstance().createObject(CatSpookier.class);

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
