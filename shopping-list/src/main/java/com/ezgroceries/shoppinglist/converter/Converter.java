package com.ezgroceries.shoppinglist.converter;

import com.ezgroceries.shoppinglist.dto.ShoppingListWithIngredientsDTO;
import com.ezgroceries.shoppinglist.model.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Converter {

    //TODO: Could be improved with Builder Pattern and streams
    public CocktailEntity convertCocktailPublicAPIResponseToEntity(Drink drink){
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

    public ShoppingListWithIngredientsDTO convertShoppingListAndCocktails(String shoppingListName, List<CocktailEntity> cocktailList){
        //TODO: improve with streams
        var shoppingListWithIngredientsDTO = new ShoppingListWithIngredientsDTO();
        shoppingListWithIngredientsDTO.setShoppingListName(shoppingListName);

        for(CocktailEntity entity : cocktailList){
            shoppingListWithIngredientsDTO.setIngredients(entity.getIngredients());
        }

        return shoppingListWithIngredientsDTO;
    }
}
