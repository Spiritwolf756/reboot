package com.ifmo.lesson23;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class TopWords {
    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        File text = new File("E:\\Java\\REBOOT\\Lesson1.1\\src\\main\\resources\\wap.txt");

        // Вычитываем все строки из файла
        List<String> linesWords = Files.readAllLines(text.toPath());

        // Создаем пустую коллекцию для слов.
        List<String> words = new ArrayList<>();

        for (String line : linesWords) {
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


        int cpus = Runtime.getRuntime().availableProcessors();

        ExecutorService pool = Executors.newFixedThreadPool(cpus);

        List<Future<Map<String, Integer>>> futures = new ArrayList<>(cpus);

//определяем оптимальную длину списка
        int eachLenght = words.size() / cpus;

        for (int i = 0; i < cpus; i++) {
            int j;
            if (i == cpus-1) {
                j = words.size();
            } else {
                j = eachLenght * (i + 1);
            }
            List<String> lines = new ArrayList<>(words.subList(eachLenght * i, j));

            Future<Map<String, Integer>> future =
                    pool.submit(() -> countWords(lines));

            futures.add(future);
        }

        List<Map<String, Integer>> counts = new ArrayList<>(cpus);
//собираем результаты выполнения
        for (Future<Map<String, Integer>> future : futures) {
            Map<String, Integer> map = future.get();

            counts.add(map);
        }
//объединяем все счетчики и находим 10 наиболее часто встречающихся слов
        List<String> top10Words = top10(counts);

        System.out.println(top10Words);

        pool.shutdown();
    }

    private static Map<String, Integer> countWords(List<String> lines) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : lines) {
            if (map.get(word) == null) {
                map.put(word, 1);
            } else {
                int count = map.get(word);
                map.put(word, count + 1);
            }
        }
        return map;
    }

    private static List<String> top10(Collection<Map<String, Integer>> counters) {
        Map<String, Integer> bigMap = new HashMap<>();

        for (Map<String, Integer> map : counters) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (bigMap.get(entry.getKey()) == null) {
                    bigMap.put(entry.getKey(), entry.getValue());
                } else {
                    int count = bigMap.get(entry.getKey());
                    bigMap.put(entry.getKey(), count + entry.getValue());
                }
            }

        }

        List<Map.Entry> list = new ArrayList<>(bigMap.entrySet());

        list.sort(new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return (int) o2.getValue() - (int) o1.getValue();
            }
        });
        List<String> ret = new ArrayList<>(10);
        int i = 0;
        for (Map.Entry item : list) {
            ret.add(item.getKey().toString());
            i++;
            if (i == 10) break;
        }


        return ret;
    }
}