package edu.hw10.task2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class CacheProxy {
    private CacheProxy() {
    }

    public static <T> T create(Object target, Class<T> clazz) {
        ClassLoader targetLoader = target.getClass().getClassLoader();
        Class<?>[] interfaces = {clazz};
        InvocationHandler handler = new CacheInvocationHandler(target);
        return (T) Proxy.newProxyInstance(targetLoader, interfaces, handler);
    }
}
