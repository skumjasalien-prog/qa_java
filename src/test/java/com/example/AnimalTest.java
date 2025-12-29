package com.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AnimalTest {

    @Test
    public void testGetFoodHerbivore() throws Exception {
        Animal animal = new Animal();
        List<String> food = animal.getFood("Травоядное");
        
        assertNotNull("Еда не должна быть null", food);
        assertEquals("Должно быть 2 вида еды", 2, food.size());
        assertTrue("Должна содержать 'Трава'", food.contains("Трава"));
        assertTrue("Должна содержать 'Различные растения'", food.contains("Различные растения"));
    }

    @Test
    public void testGetFoodPredator() throws Exception {
        Animal animal = new Animal();
        List<String> food = animal.getFood("Хищник");
        
        assertNotNull("Еда не должна быть null", food);
        assertEquals("Должно быть 3 вида еды", 3, food.size());
        assertTrue("Должна содержать 'Животные'", food.contains("Животные"));
        assertTrue("Должна содержать 'Птицы'", food.contains("Птицы"));
        assertTrue("Должна содержать 'Рыба'", food.contains("Рыба"));
    }

    @Test(expected = Exception.class)
    public void testGetFoodUnknownKindThrowsException() throws Exception {
        Animal animal = new Animal();
        animal.getFood("Неизвестный");
    }

    @Test
    public void testGetFamily() {
        Animal animal = new Animal();
        String family = animal.getFamily();
        
        assertNotNull("Семейство не должно быть null", family);
        assertTrue("Должно содержать информацию о семействах", family.length() > 0);
    }
}

