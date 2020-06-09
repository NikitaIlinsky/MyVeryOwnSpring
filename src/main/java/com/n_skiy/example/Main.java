package com.n_skiy.example;

import com.n_skiy.myspring.Application;
import com.n_skiy.myspring.ApplicationContext;

import java.util.HashMap;
import java.util.Map;


public class Main {

    public static void main(String[] args) {
        ApplicationContext context = Application.run("com.n_skiy.myspring", getInterfaceToImplMap());

        RoomCleaner roomCleaner = context.getObject(RoomCleaner.class);
        Room room = new Room();

        roomCleaner.startCleaning(room);
    }

    private static Map<Class, Class> getInterfaceToImplMap() {
        // TODO add conflict resolver for multiple implementation instead of this
        HashMap<Class, Class> interface2ImplClass = new HashMap<>();
        interface2ImplClass.put(CatSpookier.class, SprinklerCatSpookierImpl.class);
        return interface2ImplClass;
    }

}
