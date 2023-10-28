package edu.hw4;

import java.util.*;
import java.util.stream.Collectors;

public class AnimalUtils {
    private AnimalUtils() {
    }

    /* Task1 */
    public static List<Animal> sortByHeight(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    /* Task2 */
    public static List<Animal> firstKHeaviest(List<Animal> animals, int k) {
        return animals.stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    /* Task3 */
    public static Map<Animal.Type, Long> countAllTypes(List<Animal> animals) {
        return animals.stream().
            collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    /* Task4 */
    public static String maxLengthName(List<Animal> animals) {
        return animals.stream()
            .max(Comparator.comparingInt(animal -> animal.name().length()))
            .orElseThrow()
            .name();
    }

    /* Task5 */
    public static Animal.Sex getMaxSex(List<Animal> animals) {
        return animals.stream()
            .map(Animal::sex)
            .reduce(0, (count, sex) -> {
                if (sex == Animal.Sex.M) {
                    return count + 1;
                } else {
                    return count - 1;
                }
            }, Integer::sum) > 0 ? Animal.Sex.M : Animal.Sex.F;
    }

    /* Task6 */
    public static Map<Animal.Type, Animal> getHeaviestOfTypes(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(
                Animal::type,
                Collectors.collectingAndThen(
                    Collectors.maxBy(Comparator.comparingInt(Animal::weight)),
                    optionalAnimal -> optionalAnimal.orElse(null)
                )
            ));
    }

    /* Task8 */
    public static Optional<Animal> getHeaviestLowerThanK(List<Animal> animals, int k) {
        return animals.stream()
            .filter(animal -> animal.height() < k)
            .max(Comparator.comparingInt(Animal::weight));
    }

    /* Task9 */
    public static Integer countPaws(List<Animal> animals) {
        return animals.stream()
            .mapToInt(Animal::paws)
            .sum();
    }

    /* Task10 */
    public static List<Animal> getWithDifferentPawsAndAge(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.age() != animal.paws())
            .toList();
    }

    /* Task11 */
    public static List<Animal> getAnimalsWhoCanBite(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.height() > 100 && (animal.bites() == null || animal.bites()))
            .toList();
    }

    /* Task12 */
    public static List<Animal> getWithWeightBiggerThanHeight(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.weight() > animal.height())
            .toList();
    }

    /* Task13 */
    public static List<Animal> getWithNameIsTwoWords(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.name().split("\\s+").length > 1)
            .toList();
    }

    /* Task14 */
    public static Boolean containsDogBiggerThanK(List<Animal> animals, int k) {
        return animals.stream()
            .anyMatch(animal -> animal.type() == Animal.Type.DOG && animal.height() > k);
    }

    /* Task15 */
    public static Map<Animal.Type, Integer> totalWeight(List<Animal> animals, int k, int l) {
        return animals.stream()
            .filter(animal -> animal.age() > k && animal.age() < l)
            .collect(Collectors
                .groupingBy(Animal::type, Collectors.summingInt(Animal::weight)));
    }

    /* Task16 */
    public static List<Animal> sortByTypeSexName(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .collect(Collectors.toList());
    }

    /* Task17 */
    public static Boolean isSpiderBitesMoreThanDogs(List<Animal> animals) {
        return animals.stream()
            .filter(animal -> animal.type() == Animal.Type.DOG || animal.type() == Animal.Type.SPIDER)
            .filter(Animal::bites)
            .map(Animal::type).reduce(0, (count, type) -> {
                if (type == Animal.Type.DOG) {
                    return count - 1;
                } else {
                    return count + 1;
                }
            }, Integer::sum) > 0;
    }

    /* Task18 */
    public static Animal getHeaviestFish(List<List<Animal>> animals) {
        return animals.stream()
            .flatMap(List::stream)
            .filter(animal -> animal.type() == Animal.Type.FISH)
            .max(Comparator.comparingInt(Animal::weight)).orElseThrow();
    }

    /* Task19 */
    public static Map<String, Set<ValidationError>> validateErrors(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors
                .toMap(
                    Animal::name,
                    animal -> new AnimalValidator(animal).validate()
                        .filter(Objects::nonNull)
                        .collect(Collectors.toSet())
                )
            );
    }
    /* Task20 */
  //  public static Map<String, String> validateErrorsToString(){}
}
