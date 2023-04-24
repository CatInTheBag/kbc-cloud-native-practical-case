package com.ezgroceries.shoppinglist.api.response;

import com.ezgroceries.shoppinglist.api.response.Drink;

import java.util.List;

public class CocktailDBResponse {
    private List<Drink> drinks;

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }
}
