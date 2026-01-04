package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LionWithRealFelineParameterizedTest {
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
    public void testLionHasMane() throws Exception {
        Feline realFeline = new Feline();
        Lion lion = new Lion(sex, realFeline);
        assertEquals("Для пола " + sex + " наличие гривы должно быть " + expectedHasMane,
                expectedHasMane, lion.doesHaveMane());
    }

    @Test
    public void testLionGetKittens() throws Exception {
        Feline realFeline = new Feline();
        Lion lion = new Lion(sex, realFeline);
        assertEquals("Должно вернуться 1 котенок", 1, lion.getKittens());
    }

    @Test
    public void testLionGetFoodNotNull() throws Exception {
        Feline realFeline = new Feline();
        Lion lion = new Lion(sex, realFeline);
        List<String> food = lion.getFood();
        assertNotNull("Еда не должна быть null", food);
    }

    @Test
    public void testLionGetFoodContainsAnimals() throws Exception {
        Feline realFeline = new Feline();
        Lion lion = new Lion(sex, realFeline);
        List<String> food = lion.getFood();
        assertTrue("Должна содержать 'Животные'", food.contains("Животные"));
    }
}

