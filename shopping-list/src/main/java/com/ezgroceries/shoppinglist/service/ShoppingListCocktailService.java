package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.model.ShoppingListEntity;

public interface ShoppingListCocktailService {
    ShoppingListEntity addCocktailToShoppingList(String shoppingListId, String cocktailName);
}
