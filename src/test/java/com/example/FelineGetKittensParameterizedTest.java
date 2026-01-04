package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FelineGetKittensParameterizedTest {
    private final int input;
    private final int expected;

    public FelineGetKittensParameterizedTest(int input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "getKittens({0}) = {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 0},
                {1, 1},
                {5, 5},
                {10, 10},
                {100, 100},
                {-5, -5},
                {-1, -1}
        });
    }

    @Test
    public void testGetKittensWithDifferentValues() {
        Feline feline = new Feline();
        int result = feline.getKittens(input);
        assertEquals("Для входного значения " + input + " должно вернуться " + expected, expected, result);
    }
}

