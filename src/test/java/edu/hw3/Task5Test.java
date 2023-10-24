package edu.hw3;

import edu.hw3.Task5.Person;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static edu.hw3.Task5.ParseContactsUtils.parseContacts;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("Test ASC")
    void test1() {
        String[] test = new String[] {"John Locke", "Thomas Aquinas", "Alexander", "David Hume", "Rene Descartes"};
        Person[] expected = new Person[] {
            new Person("Alexander", ""),
            new Person("Thomas", "Aquinas"),
            new Person("Rene", "Descartes"),
            new Person("David", "Hume"),
            new Person("John", "Locke"),
        };
        assertThat(parseContacts(test, "ASC")).isEqualTo(expected);
    }

    @Test
    @DisplayName("Test DESC")
    void test2() {
        String[] test = new String[] {"Paul Erdos", "Leonhard Euler", "Alexander", "Carl Gauss"};
        Person[] expected = new Person[] {
            new Person("Carl", "Gauss"),
            new Person("Leonhard", "Euler"),
            new Person("Paul", "Erdos"),
            new Person("Alexander", "")
        };
        assertThat(parseContacts(test, "DESC")).isEqualTo(expected);
    }
}
