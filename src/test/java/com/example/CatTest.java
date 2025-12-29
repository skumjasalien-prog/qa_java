package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    private Predator predator;

    @Test
    public void testGetSound() {
        Cat cat = new Cat(new Feline());
        assertEquals("Должно вернуться 'Мяу'", "Мяу", cat.getSound());
    }

    @Test
    public void testGetFoodWithMock() throws Exception {
        List<String> expectedFood = List.of("Мясо", "Рыба");
        when(predator.eatMeat()).thenReturn(expectedFood);
        
        // Используем рефлексию для установки мока
        Cat cat = new Cat(new Feline());
        java.lang.reflect.Field field = Cat.class.getDeclaredField("predator");
        field.setAccessible(true);
        field.set(cat, predator);
        
        List<String> food = cat.getFood();
        
        assertEquals("Должна вернуться правильная еда", expectedFood, food);
        verify(predator, times(1)).eatMeat();
    }

    @Test
    public void testGetFoodThrowsException() throws Exception {
        when(predator.eatMeat()).thenThrow(new Exception("Ошибка получения мяса"));
        
        Cat cat = new Cat(new Feline());
        java.lang.reflect.Field field = Cat.class.getDeclaredField("predator");
        field.setAccessible(true);
        field.set(cat, predator);
        
        try {
            cat.getFood();
            fail("Должно было выброситься исключение");
        } catch (Exception e) {
            assertEquals("Ошибка получения мяса", e.getMessage());
        }
        
        verify(predator, times(1)).eatMeat();
    }

    @Test
    public void testGetFoodWithRealFeline() throws Exception {
        // Тест с реальным объектом Feline (без мока)
        Cat cat = new Cat(new Feline());
        List<String> food = cat.getFood();
        
        assertNotNull("Еда не должна быть null", food);
        assertFalse("Список еды не должен быть пустым", food.isEmpty());
        assertTrue("Должна содержать 'Животные'", food.contains("Животные"));
        assertTrue("Должна содержать 'Птицы'", food.contains("Птицы"));
        assertTrue("Должна содержать 'Рыба'", food.contains("Рыба"));
        assertEquals("Должно быть 3 вида еды", 3, food.size());
    }

    @Test
    public void testGetSoundMultipleTimes() {
        Cat cat = new Cat(new Feline());
        assertEquals("Должно вернуться 'Мяу'", "Мяу", cat.getSound());
        assertEquals("Должно вернуться 'Мяу' при повторном вызове", "Мяу", cat.getSound());
    }

    @Test
    public void testCatConstructor() {
        Feline feline = new Feline();
        Cat cat = new Cat(feline);
        
        assertNotNull("Cat должен быть создан", cat);
        assertEquals("Должно вернуться 'Мяу'", "Мяу", cat.getSound());
    }
}

