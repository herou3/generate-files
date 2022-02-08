package com.utils;

import java.util.HashMap;
import java.util.Map;

public class AppContext {
    private static AppContext instance;
    private final static Map<String, Object> context = new HashMap<>();
    private AppContext() {
    }

    public static AppContext get(String login) {
        if (instance == null) {
            instance = new AppContext();
        }
        return instance;
    }

    public static void clear() {
        context.clear();
    }

    public static <T> void put(String key, T object) {
        context.put(key, object);
    }

    public static <T> T get(String key, Class<T> userClass) {
        Object object;
        try {
            object = context.get(key);
        } catch (NullPointerException e) {
            throw new AssertionError(String.format("Object with key %s doesn't exist!", key));
        }
        return userClass.cast(object);
    }
}
