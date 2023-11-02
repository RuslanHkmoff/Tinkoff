package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    Boolean bites
) {
    private static final int CATS_DOGS_PAWS = 4;
    private static final int SPIDER_PAWS = 8;

    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    public int paws() {
        return switch (type) {
            case CAT, DOG -> CATS_DOGS_PAWS;
            case BIRD -> 2;
            case FISH -> 0;
            case SPIDER -> SPIDER_PAWS;
        };
    }
}
