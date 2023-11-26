package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDatabaseSync implements PersonDatabase {
    private final Map<String, List<Person>> nameMap;
    private final Map<String, List<Person>> adressMap;
    private final Map<String, List<Person>> phoneMap;
    private final Map<Integer, Person> idMap;

    public PersonDatabaseSync() {
        this.nameMap = new HashMap<>();
        this.adressMap = new HashMap<>();
        this.phoneMap = new HashMap<>();
        this.idMap = new HashMap<>();
    }

    @Override
    public synchronized void add(Person person) {
        nameMap.computeIfAbsent(person.name(), k -> new ArrayList<>()).add(person);
        adressMap.computeIfAbsent(person.address(), k -> new ArrayList<>()).add(person);
        phoneMap.computeIfAbsent(person.phoneNumber(), k -> new ArrayList<>()).add(person);
        idMap.put(person.id(), person);

    }

    @Override
    public synchronized void delete(int id) {
        Person person = idMap.get(id);
        if (person == null) {
            return;
        }
        nameMap.computeIfPresent(person.name(), (k, list) -> {
            list.remove(person);
            return list;
        });
        adressMap.computeIfPresent(person.address(), (k, list) -> {
            list.remove(person);
            return list;
        });
        phoneMap.computeIfPresent(person.phoneNumber(), (k, list) -> {
            list.remove(person);
            return list;
        });
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return nameMap.getOrDefault(name, new ArrayList<>());
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return adressMap.getOrDefault(address, new ArrayList<>());
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return phoneMap.getOrDefault(phone, new ArrayList<>());
    }
}
