package com.ezgroceries.shoppinglist.converter;

import com.ezgroceries.shoppinglist.model.CocktailEntity;
import com.ezgroceries.shoppinglist.model.Drink;
import com.ezgroceries.shoppinglist.model.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.ShoppingListRequest;
import org.springframework.stereotype.Component;

@Component
public class Converter {

    public CocktailEntity convertCocktailResponseToEntity(Drink drink){
        CocktailEntity cocktail = new CocktailEntity();
        cocktail.setCocktailId(drink.getIdDrink());
        cocktail.setName(drink.getStrDrink());
        cocktail.setGlass(drink.getStrGlass());
        cocktail.setImage(drink.getStrImageSource());
        cocktail.setIngredients(drink.getIngredients());
        return cocktail;
    }

    public ShoppingListEntity convertShoppingListRequestToEntity(ShoppingListRequest request){
        ShoppingListEntity entity = new ShoppingListEntity();
        entity.setName(request.getName());
        return entity;
    }
}
