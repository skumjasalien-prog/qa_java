package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class AnimalGetFoodParameterizedTest {
    private final String animalKind;
    private final int expectedSize;
    private final List<String> expectedItems;
    private final boolean shouldThrowException;

    public AnimalGetFoodParameterizedTest(String animalKind, int expectedSize, List<String> expectedItems, boolean shouldThrowException) {
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
        Animal animal = new Animal();
        try {
            animal.getFood(animalKind);
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
        Animal animal = new Animal();
        List<String> food = animal.getFood(animalKind);
        assertNotNull("Еда не должна быть null", food);
    }

    @Test
    public void testGetFoodSize() throws Exception {
        if (shouldThrowException) {
            return;
        }
        Animal animal = new Animal();
        List<String> food = animal.getFood(animalKind);
        assertEquals("Для " + animalKind + " должно быть " + expectedSize + " вида еды", expectedSize, food.size());
    }

    @Test
    public void testGetFoodContainsItem() throws Exception {
        if (shouldThrowException || expectedItems == null || expectedItems.isEmpty()) {
            return;
        }
        Animal animal = new Animal();
        List<String> food = animal.getFood(animalKind);
        // Проверяем только первый элемент для соблюдения принципа "один тест - одна проверка"
        assertTrue("Должна содержать '" + expectedItems.get(0) + "'", food.contains(expectedItems.get(0)));
    }
}

