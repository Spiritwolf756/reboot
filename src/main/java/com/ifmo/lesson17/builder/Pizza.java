package com.ifmo.lesson17.builder;

public class Pizza {
    final String dough;
    final String cheese;
    final int catchup;
    final int tomatoes;
    final int papperoni;

    public static class Builder {
        private final String dough;
        private final String cheese;
        private int catchup = 0;
        private int tomatoes = 0;
        private int papperoni = 0;

        public Builder(String dough, String cheese) {
            this.dough = dough;
            this.cheese = cheese;
        }

        public Builder catchup(int catchup) {
            this.catchup = catchup;
            return this;
        }

        public Builder tomatoes(int tomatoes) {
            this.tomatoes = tomatoes;
            return this;
        }

        public Builder papperoni(int papperoni) {
            this.papperoni = papperoni;
            return this;
        }

        public Pizza build() {
            return new Pizza(this);
        }
    }

    private Pizza(Builder builder) {
        dough = builder.dough;
        cheese = builder.cheese;
        catchup = builder.catchup;
        tomatoes = builder.tomatoes;
        papperoni = builder.papperoni;
    }
    @Override
    public String toString(){
        return "Пицца\nТесто: " +dough + "\nСыр: " + cheese + "\nкетчуп: " + catchup + "\nпомидоры: " + tomatoes + "\nпапперони: " +papperoni;
    }

}
