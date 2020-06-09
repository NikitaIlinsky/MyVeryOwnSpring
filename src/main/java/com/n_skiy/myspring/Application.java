package com.n_skiy.myspring;

import java.util.Map;


public class Application {

    public static ApplicationContext run(String packageToScan, Map<Class, Class> interface2ImplClass) {
        Config config = new ObjectConfig(packageToScan, interface2ImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setObjectFactory(objectFactory);
        return context;
    }
}
