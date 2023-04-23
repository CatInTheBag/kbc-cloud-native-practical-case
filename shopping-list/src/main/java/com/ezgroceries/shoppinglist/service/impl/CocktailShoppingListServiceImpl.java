package com.ezgroceries.shoppinglist.service.impl;

import com.ezgroceries.shoppinglist.model.CocktailEntity;
import com.ezgroceries.shoppinglist.model.CocktailShoppingListEntity;
import com.ezgroceries.shoppinglist.model.ShoppingListEntity;
import com.ezgroceries.shoppinglist.repository.CocktailShoppingListRepository;
import com.ezgroceries.shoppinglist.service.CocktailService;
import com.ezgroceries.shoppinglist.service.CocktailShoppingListService;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CocktailShoppingListServiceImpl implements CocktailShoppingListService {
    @Autowired
    private CocktailService cocktailService;
    @Autowired
    private ShoppingListService shoppingListService;
    @Autowired
    private CocktailShoppingListRepository cocktailShoppingListRepository;

    @Override
    public CocktailShoppingListEntity addCocktailToShoppingList(String shoppingListId, String cocktailName) {
        cocktailService.getCocktail(cocktailName);

        Optional<CocktailEntity> cocktailOptional = cocktailService.getCocktailByName(cocktailName);

        if(cocktailOptional.isEmpty()){
            return null;
        }

        Optional<ShoppingListEntity> shoppingListOptional = shoppingListService.findShoppingListById(shoppingListId);

        if(shoppingListOptional.isEmpty()){
            return null;
        }

        Optional<CocktailShoppingListEntity> cocktailShoppingListEntityOptional = cocktailShoppingListRepository.findByCocktailIdAndShoppingListId(cocktailOptional.get().getId(), shoppingListId);

        if (cocktailShoppingListEntityOptional.isPresent()) {
            return cocktailShoppingListEntityOptional.get();
        }

        var cocktailShoppingListEntity = new CocktailShoppingListEntity(cocktailOptional.get(),shoppingListOptional.get());

        /*shoppingListOptional.get().addCocktail(cocktailShoppingListEntity);
        cocktailOptional.get().addCocktailToShoppingList(cocktailShoppingListEntity);*/

        return cocktailShoppingListRepository.save(cocktailShoppingListEntity);
    }

    @Override
    public List<CocktailEntity> findCocktailsWithShoppingListId(String cocktailId) {
        Optional<List<CocktailShoppingListEntity>> cocktailShoppingListEntityList = cocktailShoppingListRepository.findByShoppingListId(cocktailId);

        if(cocktailShoppingListEntityList.isEmpty()){
            return null;
        }

        List<CocktailEntity> cocktailEntityList = new ArrayList<>();

        for(CocktailShoppingListEntity entity : cocktailShoppingListEntityList.get()){
            cocktailEntityList.add(entity.getCocktail());
        }

        return cocktailEntityList;
    }
}
