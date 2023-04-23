package com.ezgroceries.shoppinglist.service;


import com.ezgroceries.shoppinglist.dto.CocktailDTO;
import com.ezgroceries.shoppinglist.model.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.CocktailEntity;

import java.util.Optional;

public interface CocktailService {

    CocktailDTO getCocktail(String search);
    Optional<CocktailEntity> getCocktailByName(String name);
    void save(CocktailEntity cocktailEntity);
}
