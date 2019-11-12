package com.ifmo.lesson11;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.*;

public class TopWords {
    public static void main(String[] args) throws IOException {
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

        System.out.println(top10Words(words));
        System.out.println(top10Phrases(words));
        System.out.println(charactersFrequency(words));
    }

    public static Map<String, Integer> top10Words(List<String> words) {
        Map<String, Integer> bigMap = new HashMap<>();
        for (String word : words) {
            changeBigMap(bigMap, word);
        }
        return getTop10(bigMap); //топ 10 слов по частоте встречаемости (вернуть слово и количество повторений)
    }

    public static Map<String, Integer> top10Phrases(List<String> words) {
        ListIterator<String> listIterator = words.listIterator();
        Map<String, Integer> bigMap = new HashMap<>();

        String preWord = null;
        String curWord;
        while (listIterator.hasNext()){
            if (preWord==null){
                preWord=listIterator.next();
                continue;
            }
            curWord=listIterator.next();
            changeBigMap(bigMap, preWord + " " + curWord);
            preWord=curWord;
        }
        return getTop10(bigMap); //вернуть топ 10 фраз с количеством повторений
    }

    public static Map<Character, Integer> charactersFrequency(List<String> words) {
        Map<Character, Integer> bigMap = new HashMap<>();
        for (String word:words){
        /*    if (word.length()==0) {
                continue;
            }
            if (word.length()==1){
                changeBigMap(bigMap, word);
                continue;
            }

         */
            for (int i = 0; i<word.length(); i++){
                changeBigMap(bigMap, word.charAt(i));
            }
        }
        return bigMap; //вернуть все символы с количеством повторений
    }
    private static void changeBigMap(Map map, String word) {
            if (map.get(word) == null) {
                map.put(word, 1);
            } else {
                int count = (int) map.get(word);
                map.put(word, count + 1);
            }
        }
    private static void changeBigMap(Map map, Character ch) {
        if (map.get(ch) == null) {
            map.put(ch, 1);
        } else {
            int count = (int) map.get(ch);
            map.put(ch, count + 1);
        }
    }
    private static Map<String, Integer> getTop10(Map<String, Integer> bigMap){
        List<Map.Entry> list = new ArrayList<>(bigMap.entrySet());

        list.sort(new Comparator<Map.Entry>() {
            @Override
            public int compare(Map.Entry o1, Map.Entry o2) {
                return (int) o2.getValue() - (int) o1.getValue();
            }
        });
        Map<String, Integer> map = new HashMap<>();
        int i=0;
        for (Map.Entry item : list){
            map.put(item.getKey().toString(), (int) item.getValue());
            i++;
            if (i==10) break;
        }
        return map;
    }
}
