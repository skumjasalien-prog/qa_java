package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class LionManeParameterizedTest {
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

