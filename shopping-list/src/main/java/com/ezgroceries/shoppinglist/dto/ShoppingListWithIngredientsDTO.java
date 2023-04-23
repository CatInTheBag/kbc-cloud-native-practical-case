package com.ezgroceries.shoppinglist.dto;

import java.util.Set;

public class ShoppingListWithIngredientsDTO {
    private String shoppingListName;
    private Set<String> ingredients;

    public String getShoppingListName() {
        return shoppingListName;
    }

    public void setShoppingListName(String shoppingListName) {
        this.shoppingListName = shoppingListName;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
