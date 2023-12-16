package edu.hw10;

import edu.hw10.task1.RandomObjectGenerator;
import edu.hw10.testClasses.RogTest1;
import edu.hw10.testClasses.RogTest2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RandomObjectGeneratorTest {
    @Test
    @DisplayName("Test random object, class")
    void test1() {
        RogTest1 object = (RogTest1) RandomObjectGenerator.nextObject(RogTest1.class);
        assertDoesNotThrow(object::getInteger);
        assertDoesNotThrow(object::getString);
        Integer integer = object.getInteger();
        assertTrue(integer < 5 && integer >= 2);
        String string = object.getString();
        assertNotNull(string);
    }
    @Test
    @DisplayName("Test random object, create")
    void test2() {
        RogTest1 object = (RogTest1) RandomObjectGenerator.nextObject(RogTest1.class, "create");
        assertDoesNotThrow(object::getInteger);
        assertDoesNotThrow(object::getString);
        Integer integer = object.getInteger();
        assertTrue(integer < 5 && integer >= 2);
        String string = object.getString();
        assertNotNull(string);
    }

    @Test
    @DisplayName("Test random object, record")
    void test3() {
        RogTest2 object = (RogTest2) RandomObjectGenerator.nextObject(RogTest2.class);
        assertDoesNotThrow(object::integer);
        assertDoesNotThrow(object::string);
        Integer integer = object.integer();
        assertTrue(integer < -1);
        String string = object.string();
        assertNull(string);
    }
}

