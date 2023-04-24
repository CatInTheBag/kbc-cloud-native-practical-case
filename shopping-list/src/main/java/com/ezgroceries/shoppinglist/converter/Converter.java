package com.ezgroceries.shoppinglist.converter;

import com.ezgroceries.shoppinglist.api.response.CocktailDBResponse;
import com.ezgroceries.shoppinglist.api.response.Drink;
import com.ezgroceries.shoppinglist.dto.ShoppingListWithIngredientsDTO;
import com.ezgroceries.shoppinglist.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    public List<Drink> convertEntitiesToListDrink(List<CocktailEntity> cocktailEntityList){
        var drinks = new ArrayList<Drink>();

        for(CocktailEntity entity : cocktailEntityList) {
            Drink drink = new Drink();
            drink.setIdDrink(entity.getCocktailId());
            drink.setStrDrink(entity.getName());
            drink.setStrGlass(entity.getGlass());
            drink.setStrImageSource(entity.getImage());
            drink.setStrInstructions(entity.getInstructions());

            String[] ingredientsArray = entity.getIngredients().toArray(new String[0]);
            for (int i = 0; i <= ingredientsArray.length; i++) {
                String ingredient = ingredientsArray[i];
                switch (i) {
                    case 0:
                        drink.setStrIngredient1(ingredient);
                        break;
                    case 1:
                        drink.setStrIngredient2(ingredient);
                        break;
                    case 2:
                        drink.setStrIngredient3(ingredient);
                        break;
                    case 3:
                        drink.setStrIngredient4(ingredient);
                        break;
                    case 4:
                        drink.setStrIngredient5(ingredient);
                        break;
                    default:
                        break;
                }
            }
            drinks.add(drink);
        }
        return drinks;
    }
}
