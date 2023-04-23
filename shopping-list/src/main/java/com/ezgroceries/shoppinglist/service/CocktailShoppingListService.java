package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.model.CocktailEntity;
import com.ezgroceries.shoppinglist.model.CocktailShoppingListEntity;
import com.ezgroceries.shoppinglist.model.ShoppingListEntity;
import org.postgresql.util.PSQLException;

import java.util.List;
import java.util.Optional;

public interface CocktailShoppingListService {
    CocktailShoppingListEntity addCocktailToShoppingList(String shoppingListId, String cocktailName);

    List<CocktailEntity> findCocktailsWithShoppingListId(String cocktailId);
}
