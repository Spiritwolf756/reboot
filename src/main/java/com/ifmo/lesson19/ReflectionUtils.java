package com.ifmo.lesson19;

import com.ifmo.lesson5.Oval;

import java.io.File;
import java.lang.reflect.Field;

public class ReflectionUtils {
    //вывести перечень всех полей и их значение: ClassName(...:...; ...:...)
    //рекурсией
    public static String toString(Object obj) {
        StringBuilder str = new StringBuilder();
        Class aClass = obj.getClass();
        str.append(aClass.getSimpleName());
        Field[] fields = aClass.getDeclaredFields();
        if (fields.length > 0) {
            int i = 1;
            int j = 0;
            for (Field field : fields){
                final Exclude exclude = field.getAnnotation(Exclude.class);
                if (exclude == null)
                    j++;
            }
            for (Field field : fields) {
                try {
                    final Exclude exclude = field.getAnnotation(Exclude.class);
                    if (exclude == null) {
                        if (i == 1)
                            str.append("={");
                        Field publicFiled = aClass.getDeclaredField(field.getName());
                        publicFiled.setAccessible(true);
//                        aClass.getDeclaredField(field.getName()).setAccessible(true);
                        str.append(field.getName()).append("=");
                        str.append(publicFiled.get(obj));
                        if (i < j)
                            str.append("; ");
                        i++;
                    }
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            if (j > 0)
                str.append("}");
        }
        return str.toString();
    }

    //создать копию объекта. Рекурсией. Импломентирует ли клонабл?
    public static Object deepClone(Object obj) {

        return new Object();
    }
}
