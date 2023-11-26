package edu.hw7.task3;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class PersonDataBaseSyncTest {
    private PersonDatabase personDatabase;

    @BeforeEach
    void setUp() {
        personDatabase = new PersonDatabaseSync();
        Thread thread1 = new Thread(() -> {
            personDatabase.add(new Person(1, "Name1", "Address1", "Phone1"));
            personDatabase.add(new Person(2, "Name2", "Address2", "Phone2"));
        });
        Thread thread2 = new Thread(() -> {
            personDatabase.add(new Person(3, "Name3", "Address3", "Phone3"));
            personDatabase.add(new Person(4, "Name4", "Address4", "Phone4"));
            personDatabase.delete(3);
        });
        thread1.start();
        thread2.start();
        assertDoesNotThrow(() -> {
            thread1.join();
            thread2.join();
        });
    }

    @Test
    @DisplayName("test findByName")
    void test1() {
        List<Person> expected = List.of(new Person(1, "Name1", "Address1", "Phone1"));
        assertThat(personDatabase.findByName("Name1")).isEqualTo(expected);
    }

    @Test
    @DisplayName("test findByAddress")
    void test2() {
        List<Person> expected = List.of(new Person(2, "Name2", "Address2", "Phone2"));
        assertThat(personDatabase.findByAddress("Address2")).isEqualTo(expected);
    }

    @Test
    @DisplayName("test findByPhone")
    void test3() {
        List<Person> expected = List.of(new Person(4, "Name4", "Address4", "Phone4"));
        assertThat(personDatabase.findByPhone("Phone4")).isEqualTo(expected);
    }

    @Test
    @DisplayName("test delete")
    void test4() {
        assertThat(personDatabase.findByPhone("Phone3")).isEqualTo(new ArrayList<>());
    }
}
