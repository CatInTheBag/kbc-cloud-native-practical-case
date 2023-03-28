package com.ezgroceries.shoppinglist.model;

import java.util.List;

public class CocktailDBResponse {
    private List<Drink> drinks;
    private String errorMessage;

    public List<Drink> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Drink> drinks) {
        this.drinks = drinks;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
