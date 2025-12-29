package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Test
    public void testGetKittens() {
        Feline feline = new Feline();
        int kittens = feline.getKittens();
        
        assertEquals("Должно вернуться 1 котенок по умолчанию", 1, kittens);
    }

    @Test
    public void testGetKittensWithCount() {
        Feline feline = new Feline();
        int kittens = feline.getKittens(5);
        
        assertEquals("Должно вернуться 5 котят", 5, kittens);
    }

    // Параметризованный тест для getKittens с разными значениями
    @RunWith(Parameterized.class)
    public static class GetKittensParameterizedTest {
        private final int input;
        private final int expected;

        public GetKittensParameterizedTest(int input, int expected) {
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

    @Test
    public void testGetFamily() {
        Feline feline = new Feline();
        String family = feline.getFamily();
        
        assertEquals("Должно вернуться 'Кошачьи'", "Кошачьи", family);
    }

    @Test
    public void testEatMeat() throws Exception {
        Feline feline = new Feline();
        List<String> food = feline.eatMeat();
        
        assertNotNull("Еда не должна быть null", food);
        assertEquals("Должно быть 3 вида еды", 3, food.size());
        assertTrue("Должна содержать 'Животные'", food.contains("Животные"));
        assertTrue("Должна содержать 'Птицы'", food.contains("Птицы"));
        assertTrue("Должна содержать 'Рыба'", food.contains("Рыба"));
    }

    @Test
    public void testFelineImplementsPredator() {
        Feline feline = new Feline();
        assertTrue("Feline должен реализовывать Predator", feline instanceof Predator);
    }

    @Test
    public void testFelineImplementsFelineBehavior() {
        Feline feline = new Feline();
        assertTrue("Feline должен реализовывать FelineBehavior", feline instanceof FelineBehavior);
    }


    @Test
    public void testEatMeatReturnsCorrectFood() throws Exception {
        Feline feline = new Feline();
        List<String> food = feline.eatMeat();
        
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        assertEquals("Должна вернуться правильная еда", expectedFood, food);
    }

    // Параметризованный тест для getFood с разными типами животных
    @RunWith(Parameterized.class)
    public static class GetFoodParameterizedTest {
        private final String animalKind;
        private final int expectedSize;
        private final List<String> expectedItems;
        private final boolean shouldThrowException;

        public GetFoodParameterizedTest(String animalKind, int expectedSize, List<String> expectedItems, boolean shouldThrowException) {
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
        public void testGetFoodWithDifferentAnimalKinds() throws Exception {
            Feline feline = new Feline();
            
            if (shouldThrowException) {
                try {
                    feline.getFood(animalKind);
                    fail("Должно было выброситься исключение для " + animalKind);
                } catch (Exception e) {
                    assertNotNull("Исключение должно быть выброшено", e);
                }
            } else {
                List<String> food = feline.getFood(animalKind);
                assertNotNull("Еда не должна быть null", food);
                assertEquals("Для " + animalKind + " должно быть " + expectedSize + " вида еды", expectedSize, food.size());
                for (String item : expectedItems) {
                    assertTrue("Должна содержать '" + item + "'", food.contains(item));
                }
            }
        }
    }

    @Test
    public void testFelineExtendsAnimal() {
        Feline feline = new Feline();
        assertTrue("Feline должен наследоваться от Animal", feline instanceof Animal);
    }
}

