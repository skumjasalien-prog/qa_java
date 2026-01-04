package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CatGetFoodWithRealFelineParameterizedTest {
    private final String expectedItem;

    public CatGetFoodWithRealFelineParameterizedTest(String expectedItem) {
        this.expectedItem = expectedItem;
    }

    @Parameterized.Parameters(name = "contains \"{0}\"")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Животные"},
                {"Птицы"},
                {"Рыба"}
        });
    }

    @Test
    public void testGetFoodContainsItem() throws Exception {
        Cat cat = new Cat(new Feline());
        List<String> food = cat.getFood();
        assertTrue("Должна содержать '" + expectedItem + "'", food.contains(expectedItem));
    }
}

