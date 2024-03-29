package com.ifmo.lesson11;

import com.ifmo.lesson11.inner.Message;
import com.ifmo.lesson11.inner.MessageGenerator;
import com.ifmo.lesson11.inner.MessagePriority;

import java.util.*;

/**
 * Created by xmitya on 17.10.16.
 */
public class Tasks1 {

    public static void main(String[] args) {
        MessageGenerator generator = new MessageGenerator();

        List<Message> messages = generator.generate(100);

        countEachPriority(messages);
        countCountEachCode(messages);
        countUniqueMessages(messages);

        System.out.println("Genuine messages in natural order: \n" + genuineMessagesInOriginalOrder(messages));

        removeEach(generator.generate(100), MessagePriority.LOW);
        removeOther(generator.generate(100), MessagePriority.URGENT);
    }

    private static void countEachPriority(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого приоритета.
        // Ответ необходимо вывести в консоль.
        Map<MessagePriority, Integer> map = new HashMap<>();
        for (Message msg : messages) {
            if(map.containsKey(msg.getPriority())){
                map.put(msg.getPriority(), map.get(msg.getPriority())+1);
            }else {
                map.put(msg.getPriority(), 1);
            }
        }
        map.forEach((e1, e2) -> System.out.print(e1 +": " + e2 + "\n"));
    }

    private static void countCountEachCode(List<Message> messages) {
        // Сосчитайте количество сообщений для каждого кода сообщения.
        // Ответ необходимо вывести в консоль.
        Map<Integer, Integer> map = new HashMap<>();
        for (Message msg : messages) {
            if(map.containsKey(msg.getCode())){
                map.put(msg.getCode(), map.get(msg.getCode())+1);
            }else {
                map.put(msg.getCode(), 1);
            }
        }
        map.forEach((e1, e2) -> System.out.print(e1 +": " + e2 + "\n"));
    }

    private static void countUniqueMessages(List<Message> messages) {

        // Сосчитайте количество уникальных сообщений.
        // Ответ необходимо вывести в консоль.
        Set<Message> set = new HashSet<>(messages);
        set.forEach(System.out::println);
    }

    private static List<Message> genuineMessagesInOriginalOrder(List<Message> messages) {
        // Здесь необходимо вернуть только неповторяющиеся сообщения и в том порядке, в котором
        // они встречаются в первоначальном списке. Например, мы на входе имеем такие сообщения:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}, {HIGH, 9}],
        // то на выходе должны получить:
        // [{URGENT, 4}, {HIGH, 9}, {LOW, 3}].
        // Т.е. остались только уникальные значения, и порядок их поступления сохранен.
        Set<Message> set = new LinkedHashSet<>(messages);
        set.forEach(System.out::println);

        return messages;
    }

    private static void removeEach(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции каждое сообщение с заданным приоритетом.
        System.out.printf("Before remove each: %s, %s\n", priority, messages);
        Collection<Message> del = new ArrayList<>();
        for (Message msg : messages) {
            if (msg.getPriority()==priority){
                del.add(msg);
            }
        }
        for (Message msg : del) {
            messages.remove(msg);
        }

        System.out.printf("After remove each: %s, %s\n", priority, messages);
    }

    private static void removeOther(Collection<Message> messages, MessagePriority priority) {
        // Удалить из коллекции все сообщения, кроме тех, которые имеют заданный приоритет.
        System.out.printf("Before remove other: %s, %s\n", priority, messages);

        Collection<Message> del = new ArrayList<>();
        for (Message msg : messages) {
            if (msg.getPriority()!=priority){
                del.add(msg);
            }
        }
        for (Message msg : del) {
            messages.remove(msg);
        }

        System.out.printf("After remove other: %s, %s\n", priority, messages);
    }
}
