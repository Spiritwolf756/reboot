package com.ifmo.lesson24;

import com.ifmo.lesson16.print.Commands.Ban;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

public class Bank {
    private Map<Long, User> users = new ConcurrentHashMap<>();
    private List<Account> accounts = new CopyOnWriteArrayList<>();
    private LinkedBlockingQueue<Transaction> lbq = new LinkedBlockingQueue<>();
    private boolean bankExist = true;
    private boolean isInterrapted = false;

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
        List<User> users = new ArrayList<>(20);
        List<Account> accounts = new ArrayList<>(20);
        //Random rnd = new Random((System.currentTimeMillis()));
        for (int i = 0; i < 10; i++) {
            users.add(bank.new User(i, "Name" + i));
            accounts.add(bank.new Account(i, i, new Random(System.currentTimeMillis()).nextInt(100000)));
        }
        // 2. Переводите деньги со случайного аккаунта на случайный в 100 потоках.
        //случайный id


        Thread logger = new Thread(() -> {
            while (!bank.isInterrapted) {
                try {
                    System.out.println(bank.lbq.take());
                } catch (InterruptedException e) {
                    bank.isInterrapted=true;
                    System.out.println("Логгер прерван");
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        });
        logger.setDaemon(true);
        logger.start();
        Random rnd = new Random();
        ExecutorService pool = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            pool.submit(() -> {
                bank.transferMoney(accounts.get(rnd.nextInt(accounts.size())), accounts.get(rnd.nextInt(accounts.size())), rnd.nextInt(100000));
            });
        }
        pool.shutdown();
        // Другими словами, создайте 100 потоков или пул из 100 потоков, в которых
        // выполните перевод вызовом метода transferMoney().
/*        try {
            logger.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bank.closeBank();
    }

    public void closeBank() {
        System.out.println("Закрываем банк");
        bankExist = !bankExist;
    }

    public void checkAndTransfer(Account from, Account to, long amount) {
        //недостаточно денег
        if (from.amount < amount) {
            try {
                lbq.put(new Transaction(from.id, to.id, amount, false));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        from.amount -= amount;
        to.amount += amount;
    }

    // TODO Самая главная часть работы!
    public void transferMoney(Account from, Account to, long amount) {
        //попытка перевести на тот же аккаунт
        if (from.id == to.id)
            return;
        if (from.id < to.id) {
            synchronized (from) {
                synchronized (to) {
                    checkAndTransfer(from, to, amount);
                }
            }
        } else {
            synchronized (to) {
                synchronized (from) {
                    checkAndTransfer(from, to, amount);
                }
            }
        }

        try {
            lbq.put(new Transaction(from.id, to.id, amount, true));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // 1. Атомарно и потокобезопасно перевести деньги в количестве 'amount' со счёта 'from' на счёт 'to'.
        // 2. Создать объект Transaction, содержащий информацию об операции и отправить в очередь
        // потоку Logger, который проснётся и напечатает её.
    }
}
