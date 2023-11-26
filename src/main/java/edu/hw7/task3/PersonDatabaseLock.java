package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonDatabaseLock implements PersonDatabase {
    private final ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final Map<String, List<Person>> nameMap;
    private final Map<String, List<Person>> adressMap;
    private final Map<String, List<Person>> phoneMap;
    private final Map<Integer, Person> idMap;

    public PersonDatabaseLock() {
        this.nameMap = new HashMap<>();
        this.adressMap = new HashMap<>();
        this.phoneMap = new HashMap<>();
        this.idMap = new HashMap<>();
    }

    @Override
    public void add(Person person) {
        lock.writeLock().lock();
        try {
            nameMap.computeIfAbsent(person.name(), k -> new ArrayList<>()).add(person);
            adressMap.computeIfAbsent(person.address(), k -> new ArrayList<>()).add(person);
            phoneMap.computeIfAbsent(person.phoneNumber(), k -> new ArrayList<>()).add(person);
            idMap.put(person.id(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        lock.writeLock().lock();
        try {
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
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        lock.readLock().lock();
        try {
            return nameMap.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        lock.readLock().lock();
        try {
            return adressMap.get(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        lock.readLock().lock();
        try {
            return phoneMap.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
