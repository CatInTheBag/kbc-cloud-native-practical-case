package com.ezgroceries.shoppinglist.service.impl;

import com.ezgroceries.shoppinglist.api.response.CocktailDBResponse;
import com.ezgroceries.shoppinglist.converter.Converter;
import com.ezgroceries.shoppinglist.dto.CocktailDTO;
import com.ezgroceries.shoppinglist.model.CocktailEntity;
import com.ezgroceries.shoppinglist.api.response.Drink;
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

        CocktailDBResponse response = client.searchCocktails(search);

        if(response == null){
            return null;
        }

        persistCocktails(response);

        return response;
    }

    public void persistCocktails(CocktailDBResponse response){

        for (Drink drink : response.getDrinks()){
            if(repository.findByCocktailId(drink.getIdDrink()).isEmpty()){
                repository.save(converter.convertCocktailPublicAPIResponseToEntity(drink));
            }
        }
    }

    @Override
    public Optional<CocktailEntity> getCocktailByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public void save(CocktailEntity cocktailEntity) {
        repository.save(cocktailEntity);
    }
}
