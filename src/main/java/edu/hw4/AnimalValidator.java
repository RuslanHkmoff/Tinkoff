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
        if (animal.name().isEmpty()) {
            return new ValidationError("Name: is empty, ");
        }
        return null;
    }

    public ValidationError validateAge() {
        int age = animal.age();
        if (age <= 0) {
            return new ValidationError("Age: must be a positive number + actual: '" + age + "', ");
        }
        return null;
    }

    public ValidationError validateHeight() {
        int height = animal.height();
        if (height <= 0) {
            return new ValidationError("Height: must be a positive number + actual: '" + height + "', ");
        }
        return null;
    }

    public ValidationError validateWeight() {
        int weight = animal.weight();
        if (weight <= 0) {
            return new ValidationError("Weight: must be a positive number + actual: " + weight + "', ");
        }
        return null;
    }
}
