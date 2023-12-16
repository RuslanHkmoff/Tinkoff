package edu.hw10.testClasses;

import edu.hw10.task2.cache.Cache;

public interface PowCalculator {
    @Cache()
    Integer pow(int num);
}
