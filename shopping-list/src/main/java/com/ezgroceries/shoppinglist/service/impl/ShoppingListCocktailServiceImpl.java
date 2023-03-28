package com.ezgroceries.shoppinglist.service.impl;

import com.ezgroceries.shoppinglist.model.CocktailEntity;
import com.ezgroceries.shoppinglist.model.ShoppingListEntity;
import com.ezgroceries.shoppinglist.service.CocktailService;
import com.ezgroceries.shoppinglist.service.ShoppingListCocktailService;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingListCocktailServiceImpl implements ShoppingListCocktailService {
    @Autowired
    private CocktailService cocktailService;
    @Autowired
    private ShoppingListService shoppingListService;

    @Override
    public ShoppingListEntity addCocktailToShoppingList(String shoppingListId, String cocktailName) {
        cocktailService.getCocktail(cocktailName);

        Optional<CocktailEntity> cocktail = cocktailService.getCocktailByName(cocktailName);

        Optional<ShoppingListEntity> shoppingListEntity = shoppingListService.findShoppingListById(shoppingListId);
        if(shoppingListEntity.isEmpty()){
            return null;
        }

        shoppingListEntity.get().setIngredients(cocktail.get().getIngredients());
        return shoppingListService.updateShoppingListWithIngredients(shoppingListEntity.get());
    }


}
