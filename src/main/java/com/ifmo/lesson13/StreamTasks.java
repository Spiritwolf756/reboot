package com.ifmo.lesson13;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTasks {

    private static class Person {
        private final String name;
        private final int age;
        private final String country;

        public Person(String name, int age, String country) {
            this.name = name;
            this.age = age;
            this.country = country;
        }
        String getCountry(){
            return country;
        }
        int getAge(){
            return age;
        }
    }

    public static void main(String[] args) {
        List<Person> people = generatePeople(100).collect(Collectors.toList());

        List<String> countries = countriesSortedByTheirPopulationDescending(people.stream());
        String countryThatHasMostKids = countryThatHasMostKids(people.stream());
        Map<String, Long> populationByCountry = populationByCountry(people.stream());

        System.out.println(countries);
        System.out.println(countryThatHasMostKids);
        System.out.println(populationByCountry);

        List<String> words = List.of("the", "hello", "approximation", "greetings", "java", "war");

        Map<Integer, Set<String>> wordsByLength = groupWordsByLength(words);
        int averageWordLength = averageWordLength(words);
        Set<String> longestWords = longestWords(words);

        System.out.println(wordsByLength);
        System.out.println(averageWordLength);
        System.out.println(longestWords);
    }

    // Метод возвращает страны в порядке убывания их населения.
    public static List<String> countriesSortedByTheirPopulationDescending(Stream<Person> people) {
        List<String> result = populationByCountry(people)
                .entrySet()
                .stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return result;
    }

    // Метод находит страну (или одну из стран), в которых больше всего человек в возрасте
    // до 18 лет.
    public static String countryThatHasMostKids(Stream<Person> people) {
        String result = people
                .filter(Person -> Person.age<18)
                .collect(Collectors.groupingBy(Person::getCountry, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .map(Map.Entry::getKey).orElse("Unknown");
        return result;
    }

    // Метод возвращает карту стран их населения.
    public static Map<String, Long> populationByCountry(Stream<Person> people) {
        Map<String, Long> result = people
                .collect(Collectors.groupingBy(Person::getCountry, Collectors.counting()));
        return result;
    }

    // Метод генерирует случайных людей в ограниченном наборе стран.
    // number - число желаемых людей.
    public static Stream<Person> generatePeople(int number) {
        Random rnd = new Random(0);
        Stream<Person> str = Stream.generate(()->new Person("Name"+rnd.nextInt(500), rnd.nextInt(100), "Country"+rnd.nextInt(50)))
                .limit(number);
        return str;
    }

    // Метод возвращает карту сгруппированных слов по их длине. Например, для
    // трёхбуквенных слов будет:
    // 3 -> "the", "map", "got", "war"...
    public static Map<Integer, Set<String>> groupWordsByLength(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()));
    }

    // Метод находит среднюю длину слов в списке.
    public static int averageWordLength(List<String> words) {
        return (int) Math.round(words.stream().mapToInt(String::length).average().getAsDouble());
    }

    // Метод находит самое длинное слово или слова, если таких несколько.
    public static Set<String> longestWords(List<String> words) {
        return words.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.toSet()))
                .entrySet()
                .stream()
                .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
                .max(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getValue).orElse(Set.of());
    }


}
