package edu.hw3.Task5;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        String surname1 = person1.surname();
        String surname2 = person2.surname();
        if (surname1 == null) {
            if (surname2 == null) {
                return 0;
            }
            return -1;
        }
        int surnameComp = surname1.compareTo(surname2);
        if (surnameComp == 0) {
            String name1 = person1.name();
            String name2 = person2.name();
            return name1.compareTo(name2);
        }
        return surnameComp;
    }
}
