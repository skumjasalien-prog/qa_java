package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Test
    public void testGetKittensReturnsDefaultOne() {
        Feline feline = new Feline();
        int result = feline.getKittens();
        assertEquals("Должно вернуться 1 котенок по умолчанию", 1, result);
    }

    @Test
    public void testGetFamilyReturnsKoshachie() {
        Feline feline = new Feline();
        String family = feline.getFamily();
        assertEquals("Должно вернуться 'Кошачьи'", "Кошачьи", family);
    }

    @Test
    public void testEatMeatIsNotNull() throws Exception {
        Feline feline = new Feline();
        List<String> food = feline.eatMeat();
        assertNotNull("Еда не должна быть null", food);
    }

    @Test
    public void testEatMeatHasCorrectSize() throws Exception {
        Feline feline = new Feline();
        List<String> food = feline.eatMeat();
        assertEquals("Должно быть 3 вида еды", 3, food.size());
    }

    @Test
    public void testEatMeatContainsAnimals() throws Exception {
        Feline feline = new Feline();
        List<String> food = feline.eatMeat();
        assertTrue("Должна содержать 'Животные'", food.contains("Животные"));
    }

    @Test
    public void testEatMeatContainsBirds() throws Exception {
        Feline feline = new Feline();
        List<String> food = feline.eatMeat();
        assertTrue("Должна содержать 'Птицы'", food.contains("Птицы"));
    }

    @Test
    public void testEatMeatContainsFish() throws Exception {
        Feline feline = new Feline();
        List<String> food = feline.eatMeat();
        assertTrue("Должна содержать 'Рыба'", food.contains("Рыба"));
    }

    @Test
    public void testFelineIsInstanceOfAnimal() {
        Feline feline = new Feline();
        assertTrue("Feline должен наследоваться от Animal", feline instanceof Animal);
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
}