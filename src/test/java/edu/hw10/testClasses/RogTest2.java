package edu.hw10.testClasses;

import edu.hw10.task1.annotations.Max;

public record RogTest2(String string, @Max(-1) Integer integer) {
}
