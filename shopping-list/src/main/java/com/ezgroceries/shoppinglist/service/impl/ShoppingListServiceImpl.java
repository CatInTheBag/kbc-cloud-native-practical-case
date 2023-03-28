package com.ezgroceries.shoppinglist.service.impl;

import com.ezgroceries.shoppinglist.converter.Converter;
import com.ezgroceries.shoppinglist.model.ShoppingListEntity;
import com.ezgroceries.shoppinglist.model.ShoppingListRequest;
import com.ezgroceries.shoppinglist.repository.ShoppingListRepository;
import com.ezgroceries.shoppinglist.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    @Autowired
    private ShoppingListRepository repository;
    @Autowired
    private Converter converter;

    @Override
    public ShoppingListEntity createShoppingList(ShoppingListRequest shoppingListRequest) {

        return repository.save(converter.convertShoppingListRequestToEntity(shoppingListRequest));
    }

    @Override
    public Optional<ShoppingListEntity> findShoppingListById(String id) {
        return repository.findById(id);
    }

    @Override
    public ShoppingListEntity updateShoppingListWithIngredients(ShoppingListEntity entity) {
        return repository.save(entity);
    }
}
