package com.n_skiy.myspring;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Application {

    public static ApplicationContext run(String packageToScan, Map<Class, Class> interface2ImplClass) {
        Config config = new ObjectConfig(getPackagesToScan(packageToScan), interface2ImplClass);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setObjectFactory(objectFactory);
        return context;
    }

    private static Set<String> getPackagesToScan(String userPackage){
        Set<String> packagesToScan = new HashSet<>();
        String frameworkPackage = Application.class.getPackage().getName();
        packagesToScan.add(frameworkPackage);
        packagesToScan.add(userPackage);
        return packagesToScan;
    }
}
