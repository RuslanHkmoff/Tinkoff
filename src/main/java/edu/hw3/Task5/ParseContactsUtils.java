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
        if (ASC_TYPE.equals(type)) {
            Arrays.sort(persons, new PersonComparator());
        } else if (DESC_TYPE.equals(type)) {
            Arrays.sort(persons, new PersonComparator().reversed());
        } else {
            throw new IllegalArgumentException("Invalid type of sort, expected ASC or DESC, actual '" + type + "'");
        }
        return persons;
    }

    private static Person[] getPersons(String[] input) {
        Person[] result = new Person[input.length];
        for (int i = 0; i < input.length; i++) {
            String[] parsedElement = input[i].split(" ");
            if (parsedElement.length == 2) {
                result[i] = new Person(parsedElement[0], parsedElement[1]);
            } else if (parsedElement.length == 1) {
                result[i] = new Person(parsedElement[0], "");
            } else {
                throw new IllegalArgumentException();
            }
        }
        return result;
    }
}
