package edu.hw10.testClasses;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;

public class RogTest1 {
    private final String string;
    private final Integer integer;

    public RogTest1(@NotNull String string, @Min(2) @Max(5) Integer integer) {
        this.string = string;
        this.integer = integer;
    }

    public static RogTest1 create(@NotNull String string, @Min(2) @Max(5) Integer integer) {
        return new RogTest1(string, integer);
    }

    public String getString() {
        return string;
    }

    public Integer getInteger() {
        return integer;
    }

}

