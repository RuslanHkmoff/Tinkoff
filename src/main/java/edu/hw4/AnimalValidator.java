package edu.hw4;

import java.util.stream.Stream;

public class AnimalValidator {
    private final Animal animal;

    public AnimalValidator(Animal animal) {
        this.animal = animal;
    }

    public Stream<ValidationError> validate() {
        return Stream.of(
            validateName(),
            validateAge(),
            validateHeight(),
            validateWeight()
        );
    }

    public ValidationError validateName() {
        if (animal.name() == null) {
            throw new IllegalArgumentException("Name: name is null");
        }
        if (animal.name().isEmpty()) {
            throw new IllegalArgumentException("Name: name is empty");
        }
        return null;
    }

    public ValidationError validateAge() {
        int age = animal.age();
        if (age <= 0) {
            return new ValidationError("Age: age must be a positive number + actual: '" + age + "'");
        }
        return null;
    }

    public ValidationError validateHeight() {
        int height = animal.height();
        if (height <= 0) {
            return new ValidationError("Height: height must be a positive number + actual: '" + height + "'");
        }
        return null;
    }

    public ValidationError validateWeight() {
        int weight = animal.weight();
        if (weight <= 0) {
            return new ValidationError("Weight: weight must be a positive number + actual: '" + weight + "'");
        }
        return null;
    }
}
