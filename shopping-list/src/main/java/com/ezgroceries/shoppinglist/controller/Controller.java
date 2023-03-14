package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.exceptions.CocktailNotFoundException;
import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.Drink;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.ezgroceries.shoppinglist.repository.CocktailDBClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private CocktailDBClient client;

    @GetMapping("cocktails")
    public ResponseEntity<CocktailDBResponse> getCocktails(@RequestParam("search") String search){

        logger.info("Calling endpoint cocktails with search argument " + search);

        var response = client.searchCocktails(search);

        if(null == response.getDrinks()){
            throw new CocktailNotFoundException("Cocktail not found!");
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("shopping-lists")
    public ResponseEntity<Void> createShoppingList(@RequestBody ShoppingList shoppingList){

        logger.info("Calling endpoint shopping-lists with shopping list name " + shoppingList.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/2222")
                .build()
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("shopping-lists/{shoppingListId}/cocktails")
    public ResponseEntity<Void> addCocktailToShoppingList(@PathVariable("shoppingListId") String id, @RequestBody Cocktail cocktail ){

        logger.info("Calling endpoint shopping-lists/{shoppingListId}/cocktails with shopping list name ");
        var response = client.searchCocktails(cocktail.getName());

        if(null == response.getDrinks()){
            throw new CocktailNotFoundException("Cocktail " + cocktail.getName() + " not found!");
        }

        Optional<Drink> drink = response.getDrinks().stream()
                .filter(d -> d.getStrDrink().equalsIgnoreCase(cocktail.getName()))
                .findFirst();

        if(drink.isEmpty()){
            throw new CocktailNotFoundException("Cocktail " + cocktail.getName() + " not found!");
        }

        var shoppingList = new ShoppingList(id,"Stephanie's birthday", drink.get().getIngredients());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("" + id)
                .build().toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/shopping-lists/{shoppingListId}")
    public ResponseEntity<ShoppingList> getShoppingList(@PathVariable("shoppingListId") String shoppingListId){

        logger.info("Calling endpoint /shopping-lists/{shoppingListId} with shopping list name ");

        var response = client.searchCocktails("Mojito");

        if(null == response.getDrinks()){
            throw new CocktailNotFoundException("Cocktail Mojito not found!");
        }

        Optional<Drink> drink = response.getDrinks().stream()
                .filter(d -> d.getStrDrink().equalsIgnoreCase("Mojito"))
                .findFirst();

        if(drink.isEmpty()){
            throw new CocktailNotFoundException("Cocktail Mojito not found!");
        }

        var shoppingList = new ShoppingList(shoppingListId,"Stephanie's birthday", drink.get().getIngredients());

        return new ResponseEntity<>(shoppingList,HttpStatus.OK);
    }

    @GetMapping("/shopping-lists")
    public ResponseEntity<List<ShoppingList>> getShoppingList(){

        logger.info("Calling endpoint /shopping-lists");

        var responseMojito = client.searchCocktails("Mojito");

        if(null == responseMojito.getDrinks()){
            throw new CocktailNotFoundException("Cocktail Mojito not found!");
        }

        Optional<Drink> drinkMojito = responseMojito.getDrinks().stream()
                .filter(d -> d.getStrDrink().equalsIgnoreCase("Mojito"))
                .findFirst();

        if(drinkMojito.isEmpty()){
            throw new CocktailNotFoundException("Cocktail Mojito not found!");
        }

        var responseMargerita = client.searchCocktails("Margarita");

        if(null == responseMargerita.getDrinks()){
            throw new CocktailNotFoundException("Cocktail response Margarita not found!");
        }

        Optional<Drink> drinkMargerita = responseMargerita.getDrinks().stream()
                .filter(d -> d.getStrDrink().equalsIgnoreCase("Margarita"))
                .findFirst();

        if(drinkMargerita.isEmpty()){
            throw new CocktailNotFoundException("Cocktail Margarita not found!");
        }

        var shoppingLists = new ArrayList<ShoppingList>();
        var shopList1 = new ShoppingList("90689338-499a-4c49-af90-f1e73068ad4f","Stephanie's birthday", drinkMojito.get().getIngredients());
        var shopList2 = new ShoppingList("6c7d09c2-8a25-4d54-a979-25ae779d2465", "My Birthday", drinkMargerita.get().getIngredients());

        shoppingLists.add(shopList1);
        shoppingLists.add(shopList2);

        return new ResponseEntity<>(shoppingLists,HttpStatus.OK);
    }
}
