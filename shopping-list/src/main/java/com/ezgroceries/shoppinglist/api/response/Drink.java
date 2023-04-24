package com.ezgroceries.shoppinglist.api.response;

import java.util.HashSet;
import java.util.Set;

public class Drink {

    public Drink(){}

    public Drink(String idDrink, String strDrink) {
        this.idDrink = idDrink;
        this.strDrink = strDrink;
    }

    public Drink(String idDrink, String strDrink, String strIngredient1, String strIngredient2, String strIngredient3) {
        this(idDrink,strDrink);
        this.strIngredient1 = strIngredient1;
        this.strIngredient2 = strIngredient2;
        this.strIngredient3 = strIngredient3;
    }

    private String idDrink;
    private String strDrink;

    private String strGlass;

    private String strIngredient1;
    private String strIngredient2;
    private String strIngredient3;
    private String strIngredient4;
    private String strIngredient5;

    private String strImageSource;
    private String strInstructions;
    private String dateModified;

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public String getStrGlass() {
        return strGlass;
    }

    public void setStrGlass(String strGlass) {
        this.strGlass = strGlass;
    }


    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient4() {
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public String getStrIngredient5() {
        return strIngredient5;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public String getStrImageSource() {
        return strImageSource;
    }

    public void setStrImageSource(String strImageSource) {
        this.strImageSource = strImageSource;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getDateModified() {
        return dateModified;
    }

    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }

    public Set<String> getIngredients(){
        var ingredients = new HashSet<String>();

        if (this.getStrIngredient1() != null) {
            ingredients.add(this.getStrIngredient1());
        }
        if (this.getStrIngredient2() != null) {
            ingredients.add(this.getStrIngredient2());
        }
        if (this.getStrIngredient3() != null) {
            ingredients.add(this.getStrIngredient3());
        }
        if (this.getStrIngredient4() != null) {
            ingredients.add(this.getStrIngredient4());
        }
        if (this.getStrIngredient5() != null) {
            ingredients.add(this.getStrIngredient5());
        }
        return ingredients;
    }
}
