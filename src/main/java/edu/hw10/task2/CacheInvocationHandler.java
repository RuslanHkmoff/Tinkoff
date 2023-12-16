package edu.hw10.task2;

import edu.hw10.task2.cache.AbstractCache;
import edu.hw10.task2.cache.Cache;
import edu.hw10.task2.cache.FileCache;
import edu.hw10.task2.cache.MapCache;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class CacheInvocationHandler implements InvocationHandler {
    private final Object target;
    private AbstractCache cache;

    public CacheInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache annotation = method.getAnnotation(Cache.class);
        Object result = method.invoke(target, args);
        if (annotation == null) {
            return result;
        }

        createCache(annotation);

        String key = method.getName() + "#" + Arrays.toString(args);
        String got = cache.get(key);
        if (got == null) {
            cache.save(key, result.toString());
            return result;
        }
        Method valueOf = getValueOfMethod(method);

        return valueOf.invoke(null, got);
    }

    private Method getValueOfMethod(Method method) {
        Method need;
        try {
            need = method.getReturnType().getMethod("valueOf", String.class);
            if (!Modifier.isStatic(need.getModifiers())) {
                throw new NoSuchMethodException();
            }
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(
                "Возвращаемый тип должен поддерживать статический метод valueOf(String)"
            );
        }
        return need;
    }

    private void createCache(Cache annotation) throws IOException {
        if (cache == null) {
            if (annotation.persist()) {
                cache = new FileCache(annotation.pathToDir());
            } else {
                cache = new MapCache();
            }
        }
    }
}
