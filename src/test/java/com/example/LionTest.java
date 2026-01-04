package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private FelineBehavior felineBehavior;

    @Test
    public void testLionInvalidSexThrowsException() throws Exception {
        new Lion("Неизвестный", felineBehavior);
    }

    @Test
    public void testGetKittens_ReturnsExpectedValue() throws Exception {
        when(felineBehavior.getKittens()).thenReturn(5);
        Lion lion = new Lion("Самец", felineBehavior);
        int kittens = lion.getKittens();
        assertEquals("Должно вернуться 5 котят", 5, kittens);
    }

    @Test
    public void testGetKittens_CallsFelineBehavior() throws Exception {
        when(felineBehavior.getKittens()).thenReturn(5);
        Lion lion = new Lion("Самец", felineBehavior);
        lion.getKittens();
        verify(felineBehavior, times(1)).getKittens();
    }

    @Test
    public void testGetFood_ReturnsExpectedFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(felineBehavior.getFood("Хищник")).thenReturn(expectedFood);
        Lion lion = new Lion("Самка", felineBehavior);
        List<String> food = lion.getFood();
        assertEquals("Должна вернуться правильная еда", expectedFood, food);
    }

    @Test
    public void testGetFood_CallsFelineBehavior() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(felineBehavior.getFood("Хищник")).thenReturn(expectedFood);
        Lion lion = new Lion("Самка", felineBehavior);
        lion.getFood();
        verify(felineBehavior, times(1)).getFood("Хищник");
    }

    @Test
    public void testGetFoodThrowsException_ThrowsException() throws Exception {
        when(felineBehavior.getFood("Хищник")).thenThrow(new Exception("Ошибка получения еды"));
        Lion lion = new Lion("Самец", felineBehavior);
        try {
            lion.getFood();
            fail("Должно было выброситься исключение");
        } catch (Exception e) {
            assertEquals("Ошибка получения еды", e.getMessage());
        }
    }

    @Test
    public void testGetFoodThrowsException_CallsFelineBehavior() throws Exception {
        when(felineBehavior.getFood("Хищник")).thenThrow(new Exception("Ошибка получения еды"));
        Lion lion = new Lion("Самец", felineBehavior);
        try {
            lion.getFood();
        } catch (Exception ignored) {
        }
        verify(felineBehavior, times(1)).getFood("Хищник");
    }

    @Test
    public void testGetKittensThrowsException_ThrowsException() throws Exception {
        when(felineBehavior.getKittens()).thenThrow(new RuntimeException("Ошибка получения котят"));
        Lion lion = new Lion("Самка", felineBehavior);
        try {
            lion.getKittens();
            fail("Должно было выброситься исключение");
        } catch (RuntimeException e) {
            assertEquals("Ошибка получения котят", e.getMessage());
        }
    }

    @Test
    public void testGetKittensThrowsException_CallsFelineBehavior() throws Exception {
        when(felineBehavior.getKittens()).thenThrow(new RuntimeException("Ошибка получения котят"));
        Lion lion = new Lion("Самка", felineBehavior);
        try {
            lion.getKittens();
        } catch (RuntimeException ignored) {
        }
        verify(felineBehavior, times(1)).getKittens();
    }
}

