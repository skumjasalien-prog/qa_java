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
    public void testGetSound_ReturnsМяу() {
        Cat cat = new Cat(new Feline());
        assertEquals("Должно вернуться 'Мяу'", "Мяу", cat.getSound());
    }

    @Test
    public void testGetFoodWithMock_ReturnsExpectedFood() throws Exception {
        List<String> expectedFood = List.of("Мясо", "Рыба");
        when(predator.eatMeat()).thenReturn(expectedFood);

        Cat cat = new Cat(new Feline());
        // Используем рефлексию для внедрения мока
        java.lang.reflect.Field field = Cat.class.getDeclaredField("predator");
        field.setAccessible(true);
        field.set(cat, predator);

        List<String> food = cat.getFood();
        assertEquals("Должна вернуться правильная еда", expectedFood, food);
    }

    @Test
    public void testGetFoodWithMock_CallsEatMeat() throws Exception {
        when(predator.eatMeat()).thenReturn(List.of("Мясо"));

        Cat cat = new Cat(new Feline());
        java.lang.reflect.Field field = Cat.class.getDeclaredField("predator");
        field.setAccessible(true);
        field.set(cat, predator);

        cat.getFood();
        verify(predator, times(1)).eatMeat();
    }

    @Test
    public void testGetFoodWithMock_ThrowsException() throws Exception {
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
    }

    @Test
    public void testGetFoodWithRealFeline_NotNull() throws Exception {
        Cat cat = new Cat(new Feline());
        List<String> food = cat.getFood();

        assertNotNull("Еда не должна быть null", food);
    }

    @Test
    public void testGetFoodWithRealFeline_NotEmpty() throws Exception {
        Cat cat = new Cat(new Feline());
        List<String> food = cat.getFood();

        assertFalse("Список еды не должен быть пустым", food.isEmpty());
    }

    @Test
    public void testGetFoodWithRealFeline_SizeIsThree() throws Exception {
        Cat cat = new Cat(new Feline());
        List<String> food = cat.getFood();
        assertEquals("Должно быть 3 вида еды", 3, food.size());
    }

    @Test
    public void testGetSoundSecondTime_ReturnsМяу() {
        Cat cat = new Cat(new Feline());
        cat.getSound(); // Первый вызов
        assertEquals("Должен возвращать 'Мяу' при повторных вызовах", "Мяу", cat.getSound());
    }

    @Test
    public void testConstructor_CreatesCat() {
        Feline feline = new Feline();
        Cat cat = new Cat(feline);
        assertNotNull("Cat должен быть создан", cat);
    }
}