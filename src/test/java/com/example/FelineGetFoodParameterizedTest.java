package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class FelineGetFoodParameterizedTest {
    private final String animalKind;
    private final int expectedSize;
    private final List<String> expectedItems;
    private final boolean shouldThrowException;

    public FelineGetFoodParameterizedTest(String animalKind, int expectedSize, List<String> expectedItems, boolean shouldThrowException) {
        this.animalKind = animalKind;
        this.expectedSize = expectedSize;
        this.expectedItems = expectedItems;
        this.shouldThrowException = shouldThrowException;
    }

    @Parameterized.Parameters(name = "getFood(\"{0}\")")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Хищник", 3, Arrays.asList("Животные", "Птицы", "Рыба"), false},
                {"Травоядное", 2, Arrays.asList("Трава", "Различные растения"), false},
                {"Неизвестный", 0, null, true}
        });
    }

    @Test
    public void testGetFoodThrowsException() throws Exception {
        if (!shouldThrowException) {
            return;
        }
        Feline feline = new Feline();
        try {
            feline.getFood(animalKind);
            fail("Должно было выброситься исключение для " + animalKind);
        } catch (Exception e) {
            assertNotNull("Исключение должно быть выброшено", e);
        }
    }

    @Test
    public void testGetFoodNotNull() throws Exception {
        if (shouldThrowException) {
            return;
        }
        Feline feline = new Feline();
        List<String> food = feline.getFood(animalKind);
        assertNotNull("Еда не должна быть null", food);
    }

    @Test
    public void testGetFoodSize() throws Exception {
        if (shouldThrowException) {
            return;
        }
        Feline feline = new Feline();
        List<String> food = feline.getFood(animalKind);
        assertEquals("Для " + animalKind + " должно быть " + expectedSize + " вида еды", expectedSize, food.size());
    }

    @Test
    public void testGetFoodContainsItems() throws Exception {
        if (shouldThrowException || expectedItems == null) {
            return;
        }
        Feline feline = new Feline();
        List<String> food = feline.getFood(animalKind);
        for (String item : expectedItems) {
            assertTrue("Должна содержать '" + item + "'", food.contains(item));
        }
    }
}

