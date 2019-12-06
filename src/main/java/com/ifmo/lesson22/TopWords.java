package com.ifmo.lesson22;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TopWords {
    private static String STOPWORD = "QWERTY";

    public static void main(String[] args) throws IOException, InterruptedException {
        LinkedBlockingQueue<String> lbqIn;
        LinkedBlockingQueue<Map> lbqOut;
        // Создаем файл, указывая путь к текстовому файлу на диске
        File text = new File("E:\\Java\\REBOOT\\Lesson1.1\\src\\main\\resources\\wap.txt");

        // Вычитываем все строки из файла
        List<String> lines = Files.readAllLines(text.toPath());

        // Создаем пустую коллекцию для слов.
        List<String> words = new ArrayList<>();

        for (String line : lines) {
            // Для каждой строки
            String[] wordSplit =
                    line.toLowerCase() // Переводим в нижний регистр
                            .replaceAll("\\p{Punct}", " ") // Заменяем все знаки на пробел
                            .trim() // Убираем пробелы в начале и конце строки.
                            .split("\\s"); // Разбиваем строки на слова

            for (String s : wordSplit) {
                // Выбираем только непустые слова.
                if (s.length() > 0) {
                    words.add(s.trim());
                }
            }
        }
        lbqIn = new LinkedBlockingQueue<>();
        lbqOut = new LinkedBlockingQueue<>();
        Producer producer = new Producer(words, lbqIn);
        List<Worker> workers = new ArrayList<>(4);
        producer.start();
        for (int i = 0; i < 4; i++) {
            new Worker(lbqIn, lbqOut).start();
        }
        new Finisher(lbqOut).start();
    }

    private static class Finisher extends Thread {
        Map<String, Integer> bigMap = new HashMap<>();
        Map<String, Integer> map = new HashMap<>();
        LinkedBlockingQueue<Map> lbqOut;

        Finisher(LinkedBlockingQueue<Map> lbqOut) {
            this.lbqOut = lbqOut;
        }

        @Override
        public void run() {
            int i = 0;
            while (i < 4) {

                try {
                    map = lbqOut.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    if (bigMap.get(entry.getKey()) == null) {
                        bigMap.put(entry.getKey(), entry.getValue());
                    } else {
                        int count = bigMap.get(entry.getKey());
                        bigMap.put(entry.getKey(), count+entry.getValue());
                    }
                }
                i++;
            }
            System.out.println(getTop10(bigMap));
        }
    }

    private static class Worker extends Thread {
        Map<String, Integer> map = new HashMap<>();
        LinkedBlockingQueue<String> lbqIn;
        LinkedBlockingQueue<Map> lbqOut;

        Worker(LinkedBlockingQueue<String> lbqIn, LinkedBlockingQueue<Map> lbqOut) {
            this.lbqIn = lbqIn;
            this.lbqOut = lbqOut;
        }

        @Override
        public void run() {
            String word;
            try {
                while (!((word = lbqIn.take()).equals(STOPWORD))) {

                    if (map.get(word) == null) {
                        map.put(word, 1);
                    } else {
                        int count = map.get(word);
                        map.put(word, count + 1);
                    }
                }
                lbqIn.put(STOPWORD);
                lbqOut.put(map);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Producer extends Thread {
        List<String> words;
        LinkedBlockingQueue<String> lbqIn;

        Producer(List<String> words, LinkedBlockingQueue<String> lbqIn) {
            this.words = words;
            this.lbqIn = lbqIn;
        }

        @Override
        public void run() {
            for (String word : words) {
                try {
                    lbqIn.put(word);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                lbqIn.put(STOPWORD);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static Map<String, Integer> getTop10(Map<String, Integer> bigMap) {
        List<Map.Entry> list = new ArrayList<>(bigMap.entrySet());

        list.sort(new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return (int) o2.getValue() - (int) o1.getValue();
            }
        });
        Map<String, Integer> map = new HashMap<>();
        int i = 0;
        for (Map.Entry item : list) {
            map.put(item.getKey().toString(), (int) item.getValue());
            i++;
            if (i == 10) break;
        }
        return map;
    }
}
