package com.ifmo.lesson7;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Библиотека помогает вести учет книг: какие книги и сколько в ней хранятся.
 * Библиотека ограничена по числу типов книг, это ограничение задается аргументом
 * конструктора maxBookKinds. Например, если библиотека ограничена числом 10,
 * то это означает, что она может хранить 10 разных книг, но любое их количество.
 * <p>
 * Если из библиотеки убираются все книги одного типа, то освобождается место,
 * на которое можно добавить книгу другого типа.
 * Например:
 * <pre>
 *     Library library = new Library(2);
 *     library.put(new Book("Stephen King", "Shining"), 2); // return true
 *     library.put(new Book("Stephen King", "Dark Tower"), 3); // return true
 *
 *     // Эту книгу добавить не можем, т.к. лимит 2
 *     library.put(new Book("Tolstoy", "War and peace"), 6); // return false
 *
 *     // Забираем все книги Тёмной башни, чтобы освободить место.
 *     library.take(new Book("Stephen King", "Dark Tower"), 3) // return 3
 *
 *     // Теперь мы можем успешно добавить "Войну и мир".
 *     library.put(new Book("Tolstoy", "War and peace"), 6); // return true
 * </pre>
 * <p>
 * Если попытаться взять из библиотеки больше книг, чем у нее есть, то она
 * должна вернуть только число книг, которые в ней находились и освободить место.
 * Например:
 *
 * <pre>
 *     Library library = new Library(2);
 *     library.put(new Book("Stephen King", "Shining"), 2); // return true
 *
 *     // Все равно вернет 2, т.к. больше нет.
 *     library.take(new Book("Stephen King", "Shining"), 10) // return 2
 * </pre>
 */
public class Library {
    //Shelf[] shelves;
    HashMap<Integer, LinkedList<Shelf>> hashMap = new HashMap<>();
    int maxQuantity;
    int curQuantity;


    public Library(int maxBookKinds) {
        //shelves = new Shelf[maxBookKinds];
        maxQuantity = maxBookKinds;
    }

    /**
     * Add books to library.
     *
     * @param book     Book to add.
     * @param quantity How many books to add.
     * @return {@code True} if book successfully added, {@code false} otherwise.
     */
    public boolean put(Book book, int quantity) {
        if (hashMap.containsKey(book.hashCode())) {
            for (Shelf shelf : hashMap.get(book.hashCode())) {
                if (shelf.book.equals(book)) {
                    shelf.quantity += quantity;
                    return true; //хотели положить уже существующую книгу - увеличили количество экземпляров
                }
            }
            if (curQuantity >= maxQuantity)
                return false; //свободных полок нет
            LinkedList<Shelf> findList = hashMap.get(book.hashCode());
            findList.add(new Shelf(book, quantity));
            hashMap.put(book.hashCode(), findList);
            curQuantity++; //увеличиваем количество занятых полок
            return true; //одинаковый хешкод, добавили новую книгу
        }
        if (curQuantity >= maxQuantity)
            return false; //свободных полок нет
        LinkedList<Shelf> newShelf = new LinkedList<>();
        newShelf.add(new Shelf(book, quantity));
        hashMap.put(book.hashCode(), newShelf); //
        curQuantity++;
        return true;
    }

    /**
     * Take books from library.
     *
     * @param book     Book to take.
     * @param quantity How many books to take.
     * @return Actual number of books taken.
     */
    public int take(Book book, int quantity) {
        if (hashMap.containsKey(book.hashCode())) {
            List<Shelf> shelves = hashMap.get(book.hashCode());
            for (Shelf shelf : shelves) {
                if (shelf.book.equals(book)) {
                    //нашли книгу
                    shelf.quantity -= quantity;
                    if (shelf.quantity <= 0) {
                        if (shelves.size()==1) { //на выбранном хешкоде нет других книг-авторы
                            hashMap.remove(book.hashCode()); //забрали все книги, удалили запись
                        }else { //на выбранном хешкоде есть другие книги-авторы
                            shelves.remove(shelf);
                        }
                        curQuantity--;
                        return shelf.quantity + quantity;
                    }
                    return quantity; //забрали не все книги
                }

            }
        }
        return 0;
    }


}
