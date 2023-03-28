package com.ezgroceries.shoppinglist.service.impl;

import com.ezgroceries.shoppinglist.converter.Converter;
import com.ezgroceries.shoppinglist.model.CocktailDBResponse;
import com.ezgroceries.shoppinglist.model.CocktailEntity;
import com.ezgroceries.shoppinglist.model.Drink;
import com.ezgroceries.shoppinglist.repository.CocktailDBClient;
import com.ezgroceries.shoppinglist.repository.CocktailRepository;
import com.ezgroceries.shoppinglist.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CocktailServiceImpl implements CocktailService {

    @Autowired
    CocktailDBClient client;
    @Autowired
    CocktailRepository repository;
    @Autowired
    Converter converter;

    @Override
    public CocktailDBResponse getCocktail(String search) {

        var response =  client.searchCocktails(search);

        if(null == response.getDrinks()){
            //TODO:throw error??
            //throw new CocktailNotFoundException("Cocktail not found!");
            response.setErrorMessage("Cocktail not found!");
            return response;
        }

        for (Drink drink : response.getDrinks()){
            if(repository.findByCocktailId(drink.getIdDrink()).isEmpty()){
                repository.save(converter.convertCocktailResponseToEntity(drink));
            }
        }

        return response;
    }

    @Override
    public Optional<CocktailEntity> getCocktailByName(String name) {
        return repository.findByName(name);
    }
}
