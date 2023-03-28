package com.ezgroceries.shoppinglist.model;

import java.util.Set;


public class ShoppingListRequest {
    private String id;
    private String name;
    private Set<String> ingredients;

    public ShoppingListRequest(String id, String name, Set<String> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }
}
