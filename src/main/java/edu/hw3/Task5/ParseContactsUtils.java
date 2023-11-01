package edu.hw3.Task5;

import java.util.Arrays;

public class ParseContactsUtils {
    private static final String ASC_TYPE = "ASC";
    private static final String DESC_TYPE = "DESC";

    private ParseContactsUtils() {
    }

    public static Person[] parseContacts(String[] input, String type) {
        if (input == null || input.length == 0) {
            return new Person[0];
        }
        Person[] persons = getPersons(input);
        switch (type) {
            case ASC_TYPE -> Arrays.sort(persons, new PersonComparator());
            case DESC_TYPE -> Arrays.sort(persons, new PersonComparator().reversed());
            default ->
                throw new IllegalArgumentException("Invalid type of sort, expected ASC or DESC, actual '" + type + "'");

        }
        return persons;
    }

    private static Person[] getPersons(String[] input) {
        Person[] result = new Person[input.length];
        for (int i = 0; i < input.length; i++) {
            String[] parsedElement = input[i].split(" ");
            result[i] = switch (parsedElement.length) {
                case 2 -> new Person(parsedElement[0], parsedElement[1]);
                case 1 -> new Person(parsedElement[0], "");
                default -> throw new IllegalArgumentException();
            };
        }
        return result;
    }
}
