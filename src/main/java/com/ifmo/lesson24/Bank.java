package com.ifmo.lesson24;

import com.ifmo.lesson16.print.Commands.Ban;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bank {
    private Map<Long, User> users = new ConcurrentHashMap<>();
    private List<Account> accounts = new CopyOnWriteArrayList<>();

    private class User {
        private final long id;
        private final String name;

        private User(long id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private class Account {
        private final long id;
        private final long userId;
        private long amount;

        private Account(long id, long userId, long amount) {
            this.id = id;
            this.userId = userId;
            this.amount = amount;
        }
    }

    private class Transaction {
        private final BigInteger transactionId;
        private final long fromAccountId;
        private final long toAccountId;
        private final long amount;
        private final boolean success;

        private Transaction(long fromAccountId, long toAccountId, long amount, boolean success) {
            this.success = success;
            this.transactionId = new BigInteger("" + System.currentTimeMillis() + fromAccountId + toAccountId + amount);
            this.fromAccountId = fromAccountId;
            this.toAccountId = toAccountId;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "transactionId=" + transactionId +
                    ", fromAccountId=" + fromAccountId +
                    ", toAccountId=" + toAccountId +
                    ", amount=" + amount +
                    ", success=" + success +
                    '}';
        }
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        // 1. Сгенерируйте пользователей и их аккаунты (все идентификаторы должны быть уникальны).
        List<User> users = new ArrayList<>(10);
        List<Account> accounts = new ArrayList<>(10);
        Random rnd = new Random((System.currentTimeMillis()));
        for (int i = 0; i < 10; i++) {
            users.add(bank.new User(i, "Name" + i));
            accounts.add(bank.new Account(i, i, new Random(System.currentTimeMillis()).nextInt(100000)));
        }
        // 2. Переводите деньги со случайного аккаунта на случайный в 100 потоках.
        //случайный id
        int randomId1 = new Random().nextInt(accounts.size());
        int randomId2 = new Random().nextInt(accounts.size());
//        int randomId2 = (int) Math.floor(Math.random() * users.size());
        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            pool.submit(() -> {bank.transferMoney(accounts.get(randomId1), accounts.get(randomId2), new Random(System.currentTimeMillis()).nextInt(100000));
            });
        }
        pool.shutdown();
        // Другими словами, создайте 100 потоков или пул из 100 потоков, в которых
        // выполните перевод вызовом метода transferMoney().


    }

    // TODO Самая главная часть работы!
    public void transferMoney(Account from, Account to, long amount) {
    /*    System.out.println("FROM: " + from.id);
        System.out.println("TO: " + to.id);
        System.out.println("AMOUNT: " + amount);
        
     */
        //попытка перевести на тот же аккаунт
        if (from.id == to.id)
            return;
        //недостаточно денег
        if (from.amount < amount) {
            new Transaction(from.id, to.id, amount, false);
            return;
        }
        //совершаем перевод
        Thread thread = new Thread() {
            @Override
            public void run() {
                synchronized (from) {
                    synchronized (to) {
                        from.amount -= amount;
                        to.amount += amount;
                    }
                }
                new Transaction(from.id, to.id, amount, true);
            }
        };

        // 1. Атомарно и потокобезопасно перевести деньги в количестве 'amount' со счёта 'from' на счёт 'to'.
        // 2. Создать объект Transaction, содержащий информацию об операции и отправить в очередь
        // потоку Logger, который проснётся и напечатает её.
    }
}
