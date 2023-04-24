package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.api.response.CocktailDBResponse;
import com.ezgroceries.shoppinglist.dto.ShoppingListWithIngredientsDTO;
import com.ezgroceries.shoppinglist.model.*;
import com.ezgroceries.shoppinglist.repository.CocktailDBClient;
import com.ezgroceries.shoppinglist.service.CocktailService;
import com.ezgroceries.shoppinglist.service.CocktailShoppingListService;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    private CocktailShoppingListService cocktailShoppingListService;

    @GetMapping("cocktails")
    public ResponseEntity<CocktailDBResponse> getCocktails(@RequestParam("search") String search){

        logger.info("Calling endpoint /cocktails with search argument " + search);

        var response = cocktailService.getCocktail(search);

        if(response == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("shopping-lists")
    public ResponseEntity<Void> createShoppingList(@RequestBody ShoppingListRequest shoppingListRequest){

        logger.info("Calling endpoint /shopping-lists with shopping list name " + shoppingListRequest.getName());

        var shoppingListEntity = shoppingListService.createShoppingList(shoppingListRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{itemId}")
                .buildAndExpand(shoppingListEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping("shopping-lists/{shoppingListId}/cocktails")
    public ResponseEntity<Void> addCocktailToShoppingList(@PathVariable("shoppingListId") String id, @RequestBody CocktailEntity cocktail ) {

        logger.info("Calling endpoint /shopping-lists/" + id + "/cocktails/" + cocktail.getName());

        var cocktailShoppingListEntity = cocktailShoppingListService.addCocktailToShoppingList(id, cocktail.getName());

        if(cocktailShoppingListEntity == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{itemId}")
                .buildAndExpand(cocktailShoppingListEntity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/shopping-lists/{shoppingListId}")
    public ResponseEntity<ShoppingListWithIngredientsDTO> getShoppingListDistinctIngredients(@PathVariable("shoppingListId") String shoppingListId){

        logger.info("Calling endpoint /shopping-lists/" + shoppingListId);

        var shoppingList = shoppingListService.getShoppingList(shoppingListId);

        if(shoppingList.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        var cocktailList = cocktailShoppingListService.findCocktailsWithShoppingListId(shoppingList.get().getId());

        if(cocktailList.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        var shoppingListWithIngredientsDto = shoppingListService.convertShoppingList(shoppingList.get().getName(),cocktailList);

        //TODO:: error when returning
        return new ResponseEntity<>(shoppingListWithIngredientsDto,HttpStatus.OK);
    }

    @GetMapping("/shopping-lists")
    public ResponseEntity<List<ShoppingListEntity>> getAllShoppingList(){

        logger.info("Calling endpoint /shopping-lists");

        List<ShoppingListEntity> list = shoppingListService.getAllShoppingLists();

        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
