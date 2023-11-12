package edu.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class AnimalUtilsTest {
    private List<Animal> animals;

    @BeforeEach
    void setUp() {
        animals = List.of(
            new Animal("Tom Cat", Animal.Type.CAT, Animal.Sex.F, 1, 1, 1, true),
            new Animal("Max", Animal.Type.CAT, Animal.Sex.F, 10, 15, 15, false),
            new Animal("AB BA", Animal.Type.BIRD, Animal.Sex.M, 5, 12, 12, false),
            new Animal("Jack", Animal.Type.BIRD, Animal.Sex.F, 2, 23, 23, false),
            new Animal("Rex", Animal.Type.DOG, Animal.Sex.F, 2, 140, 14, true),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 14, 50, false)
        );
    }

    @Test
    @DisplayName("Test sortByHeight")
    void test1() {
        List<Animal> expected = List.of(
            new Animal("Tom Cat", Animal.Type.CAT, Animal.Sex.F, 1, 1, 1, true),
            new Animal("AB BA", Animal.Type.BIRD, Animal.Sex.M, 5, 12, 12, false),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 14, 50, false),
            new Animal("Max", Animal.Type.CAT, Animal.Sex.F, 10, 15, 15, false),
            new Animal("Jack", Animal.Type.BIRD, Animal.Sex.F, 2, 23, 23, false),
            new Animal("Rex", Animal.Type.DOG, Animal.Sex.F, 2, 140, 14, true)
        );
        assertThat(AnimalUtils.sortByHeight(animals)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test firstKHeaviest")
    void test2() {
        List<Animal> expected = List.of(
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 14, 50, false),
            new Animal("Jack", Animal.Type.BIRD, Animal.Sex.F, 2, 23, 23, false)
        );
        assertThat(AnimalUtils.firstKHeaviest(animals, 2)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test countAllTypes")
    void test3() {
        Map<Animal.Type, Long> expected = Map.of(Animal.Type.FISH, 1L,
            Animal.Type.DOG, 1L,
            Animal.Type.BIRD, 2L,
            Animal.Type.CAT, 2L
        );
        assertThat(AnimalUtils.countAllTypes(animals)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test maxLengthName")
    void test4() {
        String expected = "Tom Cat";
        assertThat(AnimalUtils.maxLengthName(animals)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test getMaxSex")
    void test5() {
        Animal.Sex expected = Animal.Sex.F;
        assertThat(AnimalUtils.getMaxSex(animals)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test getHeaviestOfTypes")
    void test6() {
        Map<Animal.Type, Animal> expected = Map.of(
            Animal.Type.FISH, new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 14, 50, false),
            Animal.Type.DOG, new Animal("Rex", Animal.Type.DOG, Animal.Sex.F, 2, 140, 14, true),
            Animal.Type.BIRD, new Animal("Jack", Animal.Type.BIRD, Animal.Sex.F, 2, 23, 23, false),
            Animal.Type.CAT, new Animal("Max", Animal.Type.CAT, Animal.Sex.F, 10, 15, 15, false)
        );
        assertThat(AnimalUtils.getHeaviestOfTypes(animals)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test getHeaviestLowerThanK")
    void test8() {
        Optional<Animal> expectedAnimal = Optional.of(
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 14, 50, false)
        );
        assertThat(AnimalUtils.getHeaviestLowerThanK(animals, 16)).isEqualTo(expectedAnimal);
        Optional<Animal> expectedEmpty = Optional.empty();
        assertThat(AnimalUtils.getHeaviestLowerThanK(animals, 0)).isEqualTo(expectedEmpty);
    }

    @Test
    @DisplayName("Test countPaws")
    void test9() {
        Integer expected = 16;
        assertThat(AnimalUtils.countPaws(animals)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test getWithDifferentPawsAndAge")
    void test10() {
        List<Animal> expected = List.of(
            new Animal("Tom Cat", Animal.Type.CAT, Animal.Sex.F, 1, 1, 1, true),
            new Animal("Max", Animal.Type.CAT, Animal.Sex.F, 10, 15, 15, false),
            new Animal("AB BA", Animal.Type.BIRD, Animal.Sex.M, 5, 12, 12, false),
            new Animal("Rex", Animal.Type.DOG, Animal.Sex.F, 2, 140, 14, true),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 14, 50, false)
        );
        assertThat(AnimalUtils.getWithDifferentPawsAndAge(animals)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test getAnimalsWhoCanBite")
    void test11() {
        List<Animal> expected = List.of(
            new Animal("Rex", Animal.Type.DOG, Animal.Sex.F, 2, 140, 14, true)
        );
        assertThat(AnimalUtils.getAnimalsWhoCanBite(animals)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test getWithWeightBiggerThanHeight")
    void test12() {
        List<Animal> expected = List.of(
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 14, 50, false)
        );
        assertThat(AnimalUtils.getWithWeightBiggerThanHeight(animals)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test getWithNameIsTwoWords")
    void test13() {
        List<Animal> expected = List.of(
            new Animal("Tom Cat", Animal.Type.CAT, Animal.Sex.F, 1, 1, 1, true),
            new Animal("AB BA", Animal.Type.BIRD, Animal.Sex.M, 5, 12, 12, false)
        );
        assertThat(AnimalUtils.getWithNameIsTwoWords(animals)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test containsDogBiggerThanK")
    void test14() {
        assertTrue(AnimalUtils.containsDogBiggerThanK(animals, 3));
        assertFalse(AnimalUtils.containsDogBiggerThanK(animals, 200));
    }

    @Test
    @DisplayName("Test containsDogBiggerThanK")
    void test15() {
        Map<Animal.Type, Integer> expected = Map.of(
            Animal.Type.FISH, 50,
            Animal.Type.CAT, 16,
            Animal.Type.DOG, 14,
            Animal.Type.BIRD, 35
        );
        assertThat(AnimalUtils.totalWeight(animals, 0, 150)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test sortByTypeSexName")
    void test16() {
        List<Animal> expected = List.of(
            new Animal("Max", Animal.Type.CAT, Animal.Sex.F, 10, 15, 15, false),
            new Animal("Tom Cat", Animal.Type.CAT, Animal.Sex.F, 1, 1, 1, true),
            new Animal("Rex", Animal.Type.DOG, Animal.Sex.F, 2, 140, 14, true),
            new Animal("AB BA", Animal.Type.BIRD, Animal.Sex.M, 5, 12, 12, false),
            new Animal("Jack", Animal.Type.BIRD, Animal.Sex.F, 2, 23, 23, false),
            new Animal("Nemo", Animal.Type.FISH, Animal.Sex.M, 3, 14, 50, false)
        );
        assertThat(AnimalUtils.sortByTypeSexName(animals)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test isSpiderBitesMoreThanDogs")
    void test17() {
        assertFalse(AnimalUtils.isSpiderBitesMoreThanDogs(animals));
    }

    @Test
    @DisplayName("Test getHeaviestFish")
    void test18() {
        List<Animal> test2 = List.of(
            new Animal("Fish1", Animal.Type.FISH, Animal.Sex.M, 10, 1, 12, false),
            new Animal("Fish2", Animal.Type.FISH, Animal.Sex.M, 5, 40, 50, false),
            new Animal("Fish3", Animal.Type.FISH, Animal.Sex.F, 25, 100, 75, true)
        );
        Animal expected = new Animal("Fish3", Animal.Type.FISH, Animal.Sex.F, 25, 100, 75, true);
        assertThat(AnimalUtils.getHeaviestFish(List.of(animals, test2))).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test validateErrors")
    void test19() {
        List<Animal> animalsWithErrors = List.of(
            new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, -1, -1, 10, true),
            new Animal("Rex", Animal.Type.DOG, Animal.Sex.M, 12, -10, 10, true),
            new Animal("Alf", Animal.Type.SPIDER, Animal.Sex.M, 12, 10, 0, true),
            new Animal("John", Animal.Type.CAT, Animal.Sex.M, 12, 10, 10, true)
        );
        Map<String, Set<ValidationError>> expected = Map.of(
            "Tom", Set.of(
                new ValidationError("Age: age must be a positive number + actual: '-1'"),
                new ValidationError("Height: height must be a positive number + actual: '-1'")
            ),
            "Rex", Set.of(new ValidationError("Height: height must be a positive number + actual: '-10'")),
            "Alf", Set.of(new ValidationError("Weight: weight must be a positive number + actual: '0'"))

        );
        assertThat(AnimalUtils.validateErrors(animalsWithErrors)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test validateErrorsToString")
    void test20() {
        List<Animal> animalsWithErrors = List.of(
            new Animal("Tom", Animal.Type.CAT, Animal.Sex.M, -1, -1, 10, true),
            new Animal("John", Animal.Type.CAT, Animal.Sex.M, 12, 10, 10, true)
        );
        Map<String, String> expected = Map.of(
            "Tom",
            "Height: height must be a positive number + actual: '-1'; Age: age must be a positive number + actual: '-1'"
        );
        assertThat(AnimalUtils.validateErrorsToString(animalsWithErrors)).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test invalid name")
    void test21() {
        List<Animal> animalWithEmptyName = List.of(
            new Animal("", Animal.Type.CAT, Animal.Sex.M, -1, -1, 10, true));
        IllegalArgumentException thrown1 = assertThrows(
            IllegalArgumentException.class,
            () -> AnimalUtils.validateErrors(animalWithEmptyName),
            "Expected IllegalArgumentException, bit didn't"
        );
        String expected1 = "Name: name is empty";
        assertTrue(expected1.contains(thrown1.getMessage()));

        List<Animal> animalWithNullName = List.of(
            new Animal(null, Animal.Type.CAT, Animal.Sex.M, -1, -1, 10, true));
        IllegalArgumentException thrown2 = assertThrows(
            IllegalArgumentException.class,
            () -> AnimalUtils.validateErrors(animalWithNullName),
            "Expected IllegalArgumentException, bit didn't"
        );
        String expected2 = "Name: name is null";
        assertTrue(expected2.contains(thrown2.getMessage()));
    }
}
