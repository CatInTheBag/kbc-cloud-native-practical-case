package com.ezgroceries.shoppinglist.service.impl;

import com.ezgroceries.shoppinglist.converter.Converter;
import com.ezgroceries.shoppinglist.dto.ShoppingListWithIngredientsDTO;
import com.ezgroceries.shoppinglist.model.CocktailEntity;
import com.ezgroceries.shoppinglist.model.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.ShoppingListRequest;
import com.ezgroceries.shoppinglist.repository.ShoppingListRepository;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    @Autowired
    private ShoppingListRepository repository;
    @Autowired
    private Converter converter;

    @Override
    public ShoppingListEntity createShoppingList(ShoppingListRequest shoppingListRequest) {

        var shoppingListEntity = repository.findByName(shoppingListRequest.getName());

        return shoppingListEntity.orElseGet(() -> repository.save(converter.convertShoppingListRequestToEntity(shoppingListRequest)));
    }

    @Override
    public Optional<ShoppingListEntity> findShoppingListById(String id) {
        return repository.findById(id);
    }

    @Override
    public ShoppingListEntity updateShoppingListWithIngredients(ShoppingListEntity entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<ShoppingListEntity> getShoppingList(String shoppingListId) {
        return repository.findById(shoppingListId);
    }

    @Override
    public List<ShoppingListEntity> getAllShoppingLists() {
        return repository.findAll();
    }

    @Override
    public void save(ShoppingListEntity entity) {
        repository.save(entity);
    }

    @Override
    public ShoppingListWithIngredientsDTO convertShoppingList(String shoppingListName, List<CocktailEntity> cocktailEntityList) {
        ShoppingListWithIngredientsDTO dto = converter.convertShoppingListAndCocktails(shoppingListName, cocktailEntityList);
        return dto;
    }


}
