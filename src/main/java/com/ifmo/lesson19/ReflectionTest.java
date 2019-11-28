package com.ifmo.lesson19;

import java.lang.reflect.Method;

public class ReflectionTest {
    public static void main(String[] args) throws ClassNotFoundException {
        final Class<?> cls = Class.forName("com.ifmo.lesson19.MyBean");

        final Version verAn = cls.getAnnotation(Version.class);

        if (verAn != null) {
            final String majorVersion = verAn.value();
            final int minorVersion = verAn.minor();

            System.out.printf("%s.%s\n", majorVersion, minorVersion);
        }

        for (Method declaredMethod : cls.getDeclaredMethods()) {
            final Version verAn2 = declaredMethod.getAnnotation(Version.class);

            if (verAn2 != null) {
                System.out.printf("Method name = %s, version = %s.%s\n",
                        declaredMethod.getName(), verAn2.value(), verAn2.minor());
            }
        }
    }


}
