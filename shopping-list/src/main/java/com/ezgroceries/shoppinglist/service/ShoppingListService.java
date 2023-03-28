package com.ezgroceries.shoppinglist.service;

import com.ezgroceries.shoppinglist.model.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.ShoppingListRequest;

import java.util.Optional;

public interface ShoppingListService {

    ShoppingListEntity createShoppingList(ShoppingListRequest shoppingListRequest);
    Optional<ShoppingListEntity> findShoppingListById(String id);
    ShoppingListEntity updateShoppingListWithIngredients(ShoppingListEntity entity);
}
