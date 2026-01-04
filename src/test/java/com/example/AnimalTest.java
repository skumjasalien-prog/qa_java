package com.example;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AnimalTest {

    @Test
        public void testGetFoodHerbivore_NotNull() throws Exception {
            Animal animal = new Animal();
            List<String> food = animal.getFood("Травоядное");
            assertNotNull("Еда не должна быть null", food);
        }

        @Test
        public void testGetFoodHerbivore_SizeIsTwo() throws Exception {
            Animal animal = new Animal();
            List<String> food = animal.getFood("Травоядное");
            assertEquals("Должно быть 2 вида еды", 2, food.size());
        }

        @Test
        public void testGetFoodHerbivore_ContainsTrava() throws Exception {
            Animal animal = new Animal();
            List<String> food = animal.getFood("Травоядное");
            assertTrue("Должна содержать 'Трава'", food.contains("Трава"));
        }

        @Test
        public void testGetFoodHerbivore_ContainsVegetation() throws Exception {
            Animal animal = new Animal();
            List<String> food = animal.getFood("Травоядное");
            assertTrue("Должна содержать 'Различные растения'", food.contains("Различные растения"));
        }

        @Test
        public void testGetFoodPredator_NotNull() throws Exception {
            Animal animal = new Animal();
            List<String> food = animal.getFood("Хищник");
            assertNotNull("Еда не должна быть null", food);
        }

        @Test
        public void testGetFoodPredator_SizeIsThree() throws Exception {
            Animal animal = new Animal();
            List<String> food = animal.getFood("Хищник");
            assertEquals("Должно быть 3 вида еды", 3, food.size());
        }

        @Test
        public void testGetFoodPredator_ContainsAnimals() throws Exception {
            Animal animal = new Animal();
            List<String> food = animal.getFood("Хищник");
            assertTrue("Должна содержать 'Животные'", food.contains("Животные"));
        }

        @Test
        public void testGetFoodPredator_ContainsBirds() throws Exception {
            Animal animal = new Animal();
            List<String> food = animal.getFood("Хищник");
            assertTrue("Должна содержать 'Птицы'", food.contains("Птицы"));
        }

        @Test
        public void testGetFoodPredator_ContainsFish() throws Exception {
            Animal animal = new Animal();
            List<String> food = animal.getFood("Хищник");
            assertTrue("Должна содержать 'Рыба'", food.contains("Рыба"));
        }

        @Test(expected = Exception.class)
        public void testGetFoodUnknownKindThrowsException() throws Exception {
            Animal animal = new Animal();
            animal.getFood("Неизвестный");
        }

        @Test
        public void testGetFamily_NotNull() {
            Animal animal = new Animal();
            String family = animal.getFamily();
            assertNotNull("Семейство не должно быть null", family);
        }

        @Test
        public void testGetFamily_NotEmpty() {
            Animal animal = new Animal();
            String family = animal.getFamily();
            assertTrue("Должно содержать информацию о семействах", family.length() > 0);
        }
    }