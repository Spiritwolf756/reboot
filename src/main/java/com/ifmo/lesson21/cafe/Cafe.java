package com.ifmo.lesson21.cafe;

public class Cafe {
   /* public static void main(String[] args) {
        Cafe cafe = new Cafe();
        Waiter waiter = cafe.new Waiter();
        Client client = cafe.new Client();
        //Order order = new Order();
        waiter.start();
        createOrder(waiter, client);


    }

    private static void createOrder(Waiter waiter, Client client) {
        //waiter.takeOrder(new Order(), client);
        waiter.notify();
    }

    private class Client {

    }

    private class Waiter extends Thread {
        Client client;
        Order order;
        Dish dish;

        @Override
        public void run() {
            while (!isInterrupted()) {
                synchronized (this) {
                    try {
                        System.out.println("Засыпаем");

                        this.wait();
                        System.out.println("Просыпаемся");

                        if (order != null) {
                            //идем к повару
                            System.out.println("Идем к повару");
                        }
                        if (dish != null) {
                            //идем к клиенту
                            System.out.println("Идем к клиенту");
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }

        public void takeOrder(Order order, Client client) {
            System.out.println("Устанавливаем значения");

            this.order = order;
            this.client = client;
        }
    }

    */
}
