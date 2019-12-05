package com.ifmo.lesson21;

import jdk.jshell.tool.JavaShellToolBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class TopWords {
    public static void main(String[] args) throws IOException, InterruptedException {
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
                if (s.length() > 0)
                    words.add(s.trim());
            }
        }
        threadsTop10Words(words);
    }

    public static void threadsTop10Words(List<String> words) throws InterruptedException {
        Map<String, Integer> totalWordCount = new HashMap<>();
        int eachLenght;
        eachLenght = words.size() / 4;

        List<WordCountThread> threads = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            int j;
            if (i == 3) {
                j = words.size();
            } else {
                j = eachLenght * (i + 1);
            }
            threads.add(new WordCountThread(new ArrayList<>(words.subList(eachLenght * i, j))));
            threads.get(i).createMap();
        }

        for (WordCountThread thread : threads) {
            thread.join();
        }

        //собираем мапы
        for (WordCountThread thread : threads) {
            Map<String, Integer> map = thread.getMap();
            //for (Map.Entry<String, Integer> entry : map.entrySet()) {

            //changeBigMap(totalWordCount, entry.getKey(), entry.getValue());
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (totalWordCount.get(entry.getKey()) == null) {
                    totalWordCount.put(entry.getKey(), entry.getValue());
                } else {
                    int count = totalWordCount.get(entry.getKey());
                    totalWordCount.put(entry.getKey(), count + entry.getValue());
                }
            }
        }

        System.out.println(getTop10(totalWordCount));
    }

    private static void changeBigMap(Map map, String word) {
        if (map.get(word) == null) {
            map.put(word, 1);
        } else {
            int count = (int) map.get(word);
            map.put(word, count + 1);
        }
    }

    private static void changeBigMap(Map map, String word, int count1) {
        if (map.get(word) == null) {
            map.put(word, 1);
        } else {
            int count = (int) map.get(word);
            map.put(word, count + count1);
        }
    }

    static class WordCountThread {

        private List<String> words;
        Thread t;

        Map<String, Integer> map = new HashMap<>();

        WordCountThread(List<String> words) {
            this.words = words;
        }

        public void createMap() {
            t = new Thread(() -> {
                for (String word : words) {
                    changeBigMap(map, word);
                }
            }
            );
            t.start();
        }

        public void join() throws InterruptedException {
            t.join();
        }

        public Map<String, Integer> getMap() {
            return map;
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
