package com.example;

import java.util.List;

public interface FelineBehavior {
    
    int getKittens();
    
    List<String> getFood(String animalKind) throws Exception;
    
}

