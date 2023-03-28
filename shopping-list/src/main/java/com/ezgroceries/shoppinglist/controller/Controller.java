package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.exceptions.CocktailNotFoundException;
import com.ezgroceries.shoppinglist.model.CocktailEntity;
import com.ezgroceries.shoppinglist.model.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.Drink;
import com.ezgroceries.shoppinglist.model.ShoppingListRequest;
import com.ezgroceries.shoppinglist.repository.CocktailDBClient;
import com.ezgroceries.shoppinglist.service.CocktailService;
import com.ezgroceries.shoppinglist.service.ShoppingListCocktailService;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
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
    @Autowired
    private CocktailService cocktailService;
    @Autowired
    private ShoppingListService shoppingListService;
    @Autowired
    private ShoppingListCocktailService shoppingListCocktailService;

    @GetMapping("cocktails")
    public ResponseEntity<CocktailDBResponse> getCocktails(@RequestParam("search") String search){

        logger.info("Calling endpoint cocktails with search argument " + search);

        var response = cocktailService.getCocktail(search);

        if(null != response.getErrorMessage()){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("shopping-lists")
    public ResponseEntity<Void> createShoppingList(@RequestBody ShoppingListRequest shoppingListRequest){

        logger.info("Calling endpoint shopping-lists with shopping list name " + shoppingListRequest.getName());

        var shoppingListEntity = shoppingListService.createShoppingList(shoppingListRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{itemId}")
                .buildAndExpand(shoppingListEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("shopping-lists/{shoppingListId}/cocktails")
    public ResponseEntity<Void> addCocktailToShoppingList(@PathVariable("shoppingListId") String id, @RequestBody CocktailEntity cocktail ){

        logger.info("Calling endpoint shopping-lists/{shoppingListId}/cocktails with shopping list name ");

        var shoppingListEntity = shoppingListCocktailService.addCocktailToShoppingList(id, cocktail.getName());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/itemId}")
                .buildAndExpand(shoppingListEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/shopping-lists/{shoppingListId}")
    public ResponseEntity<ShoppingListRequest> getShoppingList(@PathVariable("shoppingListId") String shoppingListId){

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

        var shoppingList = new ShoppingListRequest(shoppingListId,"Stephanie's birthday", drink.get().getIngredients());

        return new ResponseEntity<>(shoppingList,HttpStatus.OK);
    }

    @GetMapping("/shopping-lists")
    public ResponseEntity<List<ShoppingListRequest>> getShoppingList(){

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

        var shoppingLists = new ArrayList<ShoppingListRequest>();
        var shopList1 = new ShoppingListRequest("90689338-499a-4c49-af90-f1e73068ad4f","Stephanie's birthday", drinkMojito.get().getIngredients());
        var shopList2 = new ShoppingListRequest("6c7d09c2-8a25-4d54-a979-25ae779d2465", "My Birthday", drinkMargerita.get().getIngredients());

        shoppingLists.add(shopList1);
        shoppingLists.add(shopList2);

        return new ResponseEntity<>(shoppingLists,HttpStatus.OK);
    }
}
