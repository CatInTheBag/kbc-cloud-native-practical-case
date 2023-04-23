package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.dto.ShoppingListWithIngredientsDTO;
import com.ezgroceries.shoppinglist.model.CocktailEntity;
import com.ezgroceries.shoppinglist.model.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.ShoppingListRequest;

import java.util.List;
import java.util.Optional;

public interface ShoppingListService {

    ShoppingListEntity createShoppingList(ShoppingListRequest shoppingListRequest);
    Optional<ShoppingListEntity> findShoppingListById(String id);
    ShoppingListEntity updateShoppingListWithIngredients(ShoppingListEntity entity);

    Optional<ShoppingListEntity> getShoppingList(String shoppingListId);

    List<ShoppingListEntity> getAllShoppingLists();

    void save(ShoppingListEntity entity);

    ShoppingListWithIngredientsDTO convertShoppingList(String shoppingListName, List<CocktailEntity> cocktailEntityList);
}
