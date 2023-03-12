package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class Controller {

    private final Logger logger = LoggerFactory.getLogger(Controller.class);

    Cocktail margerita = new Cocktail("23b3d85a-3928-41c0-a533-6538a71e17c4","Margerita","Cocktail glass","Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
            "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",List.of("Tequila","Triple sec","Lime juice","Salt"));

    Cocktail blueMargerita = new Cocktail("d615ec78-fe93-467b-8d26-5d26d8eab073","Blue Margerita","Cocktail glass","Rub rim of cocktail glass with lime juice. Dip rim in coarse salt..",
            "https://www.thecocktaildb.com/images/media/drink/qtvvyq1439905913.jpg",List.of("Tequila","Blue Curacao","Lime juice","Salt"));

    List<Cocktail> dummyListOfTwoCocktails = new ArrayList<>(Arrays.asList(margerita,blueMargerita));


    @GetMapping("cocktails")
    public ResponseEntity<List<Cocktail>> getCocktails(@RequestParam("search") String search){

        Cocktail margerita = new Cocktail("23b3d85a-3928-41c0-a533-6538a71e17c4","Margerita","Cocktail glass","Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",List.of("Tequila","Triple sec","Lime juice","Salt"));

        List<Cocktail> dummyListOfTwoCocktails = new ArrayList<>(Arrays.asList(margerita));

        logger.info("Calling endpoint api/v1/cocktails with search argument " + search);

        return new ResponseEntity<>(dummyListOfTwoCocktails, HttpStatus.OK);
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

        var shoppingList = new ShoppingList(id,"Stephanie's birthday", margerita.getIngredients());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("" + id)
                .build().toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/shopping-lists/{shoppingListId}")
    public ResponseEntity<ShoppingList> getShoppingList(@PathVariable("shoppingListId") String shoppingListId){

        var shoppingList = new ShoppingList("90689338-499a-4c49-af90-f1e73068ad4f","Stephanie's birthday", margerita.getIngredients());

        return new ResponseEntity<>(shoppingList,HttpStatus.OK);
    }

    @GetMapping("/shopping-lists")
    public ResponseEntity<List<ShoppingList>> getShoppingList(){

        var shoppingLists = new ArrayList<ShoppingList>();
        var shopList1 = new ShoppingList("90689338-499a-4c49-af90-f1e73068ad4f","Stephanie's birthday", margerita.getIngredients());
        var shopList2 = new ShoppingList("6c7d09c2-8a25-4d54-a979-25ae779d2465", "My Birthday", margerita.getIngredients());

        shoppingLists.add(shopList1);
        shoppingLists.add(shopList2);

        return new ResponseEntity<>(shoppingLists,HttpStatus.OK);
    }
}
