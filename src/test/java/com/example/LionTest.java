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
public class LionTest {

    @Mock
    private FelineBehavior felineBehavior;

    // Параметризованный тест для проверки наличия гривы у львов разного пола
    @RunWith(Parameterized.class)
    public static class LionManeParameterizedTest {
        private final String sex;
        private final boolean expectedHasMane;

        public LionManeParameterizedTest(String sex, boolean expectedHasMane) {
            this.sex = sex;
            this.expectedHasMane = expectedHasMane;
        }

        @Parameterized.Parameters(name = "Lion({0}) hasMane = {1}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"Самец", true},
                    {"Самка", false}
            });
        }

        @Test
        public void testLionMane() throws Exception {
            FelineBehavior mockFeline = mock(FelineBehavior.class);
            Lion lion = new Lion(sex, mockFeline);
            assertEquals("Для пола " + sex + " наличие гривы должно быть " + expectedHasMane, 
                    expectedHasMane, lion.doesHaveMane());
        }
    }

    @Test
    public void testLionMaleHasMane() throws Exception {
        Lion lion = new Lion("Самец", felineBehavior);
        assertTrue("Самец должен иметь гриву", lion.doesHaveMane());
    }

    @Test
    public void testLionFemaleNoMane() throws Exception {
        Lion lion = new Lion("Самка", felineBehavior);
        assertFalse("Самка не должна иметь гриву", lion.doesHaveMane());
    }

    @Test(expected = Exception.class)
    public void testLionInvalidSexThrowsException() throws Exception {
        new Lion("Неизвестный", felineBehavior);
    }

    @Test
    public void testGetKittens() throws Exception {
        when(felineBehavior.getKittens()).thenReturn(5);
        
        Lion lion = new Lion("Самец", felineBehavior);
        int kittens = lion.getKittens();
        
        assertEquals("Должно вернуться 5 котят", 5, kittens);
        verify(felineBehavior, times(1)).getKittens();
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(felineBehavior.getFood("Хищник")).thenReturn(expectedFood);
        
        Lion lion = new Lion("Самка", felineBehavior);
        List<String> food = lion.getFood();
        
        assertEquals("Должна вернуться правильная еда", expectedFood, food);
        verify(felineBehavior, times(1)).getFood("Хищник");
    }

    @Test
    public void testGetFoodThrowsException() throws Exception {
        when(felineBehavior.getFood("Хищник")).thenThrow(new Exception("Ошибка получения еды"));
        
        Lion lion = new Lion("Самец", felineBehavior);
        
        try {
            lion.getFood();
            fail("Должно было выброситься исключение");
        } catch (Exception e) {
            assertEquals("Ошибка получения еды", e.getMessage());
        }
        
        verify(felineBehavior, times(1)).getFood("Хищник");
    }

    @Test
    public void testGetKittensThrowsException() throws Exception {
        when(felineBehavior.getKittens()).thenThrow(new RuntimeException("Ошибка получения котят"));
        
        Lion lion = new Lion("Самка", felineBehavior);
        
        try {
            lion.getKittens();
            fail("Должно было выброситься исключение");
        } catch (RuntimeException e) {
            assertEquals("Ошибка получения котят", e.getMessage());
        }
        
        verify(felineBehavior, times(1)).getKittens();
    }

    // Параметризованный тест для Lion с реальным Feline
    @RunWith(Parameterized.class)
    public static class LionWithRealFelineParameterizedTest {
        private final String sex;
        private final boolean expectedHasMane;

        public LionWithRealFelineParameterizedTest(String sex, boolean expectedHasMane) {
            this.sex = sex;
            this.expectedHasMane = expectedHasMane;
        }

        @Parameterized.Parameters(name = "Lion({0}) with real Feline")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"Самец", true},
                    {"Самка", false}
            });
        }

        @Test
        public void testLionWithRealFeline() throws Exception {
            Feline realFeline = new Feline();
            Lion lion = new Lion(sex, realFeline);
            
            assertEquals("Для пола " + sex + " наличие гривы должно быть " + expectedHasMane, 
                    expectedHasMane, lion.doesHaveMane());
            assertEquals("Должно вернуться 1 котенок", 1, lion.getKittens());
            
            List<String> food = lion.getFood();
            assertNotNull("Еда не должна быть null", food);
            assertTrue("Должна содержать 'Животные'", food.contains("Животные"));
        }
    }

}

