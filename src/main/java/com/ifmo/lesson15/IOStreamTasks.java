package com.ifmo.lesson15;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
    Реализуйте все методы с использованием потоков ввода-вывода.
 */
public class IOStreamTasks {
    public static void main(String[] args) throws IOException {
        File srcFile = new File("E:\\Java\\REBOOT\\Lesson1.1\\src\\main\\resources\\psw.txt");
        File dstFile = new File("E:\\Java\\REBOOT\\Lesson1.1\\src\\main\\resources\\copyWap.txt");
        File dstFile2 = new File("E:\\Java\\REBOOT\\Lesson1.1\\src\\main\\resources\\ex2");
        File endFile = new File("E:\\Java\\REBOOT\\Lesson1.1\\src\\main\\resources\\endWap.txt");
   /*     try (InputStream src = new FileInputStream(dstFile)) {
            try (OutputStream dst = new FileOutputStream(endFile)) {
                //            copy(src, dst);
                encrypt(src, dst, "текст");
            }
        }

    */


        // assembly(split(dstFile, dstFile2, 10240), endFile);
        //System.out.println(getFreeDir(dstFile, dstFile2));
        encrypt(endFile, dstFile, srcFile);
    }

    /**
     * Полностью копирует один поток в другой.
     *
     * @param src Входящий поток.
     * @param dst Выходящий поток.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void copy(InputStream src, OutputStream dst) throws IOException {
        try (ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = src.read(buf)) > 0) {
                bout.write(buf, 0, len);
            }
            try (dst) {
                dst.write(bout.toByteArray());
            }
        }
    }

    /**
     * Последовательно разбивает файл на несколько более мелких, равных
     * указанному размеру. Последний файл может быть меньше задданого
     * размера.
     *
     * @param file   Файл, который будет разбит на несколько.
     * @param dstDir Директория, в которой будут созданы файлы меньшего размера.
     * @param size   Размер каждого файла-части, который будет создан.
     * @return Список файлов-частей в том порядке, в котором они должны считываться.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static List<File> split(File file, File dstDir, int size) throws IOException {
        //чтение
        try (InputStream in = new FileInputStream(file);
             ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
            byte[] buf = new byte[size];
            int len;
            int i = 0;
            List<File> files = new ArrayList<>();
            while ((len = in.read(buf)) > 0) {
                File newFile = getFreeDir(file, dstDir);
                bout.write(buf, 0, len);
                try (OutputStream out = new FileOutputStream(newFile)) {
                    out.write(bout.toByteArray(), i, len);
                    i += size;
                    files.add(newFile);
                }
            }
            //запись

            return files;
            //  }

        }
    }

    public static File getFreeDir(File oldFile, File newFile) {
        String fullName = oldFile.getName();
        int point = fullName.lastIndexOf(".");
        String name1 = "\\" + fullName.substring(0, point);
        String name2 = fullName.substring(point);
        if (!newFile.exists())
            newFile.mkdir();
        File ret = new File(newFile + name1 + name2);
        int i = 1;
        while (ret.exists()) {
            ret = new File(newFile + name1 + "(" + i + ")" + name2);
            i++;
        }
        return ret;
        // File ret = new File(newFile.toString() + "/" + name)
        // if ()
    }

    /**
     * Собирает из частей один файл.
     *
     * @param files Список файлов в порядке чтения.
     * @param dst   Файл, в который будут записаны все части.
     * @throws IOException Будет выброшен в случае ошибки.
     */

    public static void assembly(List<File> files, File dst) throws IOException {
        try (OutputStream out = new FileOutputStream(dst, true)) {
            for (File file : files) {
                try (InputStream in = new FileInputStream(file);
                     ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) > 0) {
                        bout.write(buf, 0, len); //запись в буфер из открытого файла
                    }
                    out.write(bout.toByteArray()); //запись в конечный файл из буфера

                }
            }
        }
    }

    /**
     * Шифрует/дешифрует поток с помощью XOR. В качестве ключа используется пароль.
     *
     * @param src        Входящий поток, данные которого будут зашифрованы/расшифрованы.
     * @param dst        Выходящий поток, куда будут записаны зашифрованные/расшифрованные данные.
     * @param passphrase Пароль.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void encrypt(InputStream src, OutputStream dst, String passphrase) throws IOException {
        try (src;
             ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = src.read(buf)) > 0) {
                bout.write(buf, 0, len);
            }
            //шифрование
            byte[] data = bout.toByteArray();
            byte[] psw = passphrase.getBytes();
            int j = 0;
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte) (data[i] ^ psw[j]);
                j++;
                if (j == psw.length)
                    j = 0;
            }
            //запись в итоговый файл
            try (dst) {
                dst.write(data);
            }


        }
    }

    /**
     * Шифрует/дешифрует файл с помощью файла-ключа.
     *
     * @param src Файл, который должен быть зашифрован/расшифрован.
     * @param dst Файл, куда будут записаны зашифрованные/расшифрованные данные.
     * @param key Файл-ключ.
     * @throws IOException Будет выброшен в случае ошибки.
     */
    public static void encrypt(File src, File dst, File key) throws IOException {
        byte[] psw;
        byte[] data;
        try (InputStream in = new FileInputStream(key);
             ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                bout.write(buf, 0, len);
            }
            psw = new byte[bout.toByteArray().length];
            System.arraycopy(bout.toByteArray(), 0, psw, 0, bout.toByteArray().length);
        }
        try (InputStream in = new FileInputStream(src);
             ByteArrayOutputStream bout = new ByteArrayOutputStream()) {
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                bout.write(buf, 0, len);
            }
            //шифрование
            data = new byte[bout.toByteArray().length];
            System.arraycopy(bout.toByteArray(), 0, data, 0, bout.toByteArray().length);
        }
        int j = 0;
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (data[i] ^ psw[j]);
            j++;
            if (j == psw.length)
                j = 0;
        }
        //запись в итоговый файл
        try (OutputStream out = new FileOutputStream(dst)) {
            out.write(data);
        }


    }
}
