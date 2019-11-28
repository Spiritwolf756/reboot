package com.ifmo.lesson19.injector;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Injector {
    private Map<Class<?>, Object> singletons = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T create(String className) {

        try {
            final Class<?> cls = Class.forName(className);

            final Constructor<?> constructor = cls.getDeclaredConstructor();
            final Object obj = constructor.newInstance();

            inject(obj);

            return (T) obj;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private void inject(Object obj) throws ReflectiveOperationException {
        Class<?> cls = obj.getClass();

        for (Field field : cls.getDeclaredFields()) {
            final Inject an = field.getAnnotation(Inject.class);

            if (an != null) {
                Class<?> depType = field.getType();

                if (an.type() != DefaultType.class) {
                    depType = an.type();
                }

                Object dep = createDependency(depType, an.singleton());

                setDependency(obj, field, dep);
            }
        }
    }

    private void setDependency(Object obj, Field field, Object dep) throws IllegalAccessException {
        field.setAccessible(true);
        field.set(obj, dep);
        field.setAccessible(false);
    }

    private Object createDependency(Class<?> depType, boolean singleton) throws ReflectiveOperationException {
        Object dep = singletons.get(depType);

        if (dep != null && singleton)
            return dep;

        dep = depType.getDeclaredConstructor().newInstance();

        singletons.putIfAbsent(depType, dep);

        inject(dep);

        return dep;
    }
}
