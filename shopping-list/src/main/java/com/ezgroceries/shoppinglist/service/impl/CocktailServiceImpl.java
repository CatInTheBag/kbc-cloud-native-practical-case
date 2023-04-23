package com.ezgroceries.shoppinglist.service.impl;

import com.ezgroceries.shoppinglist.converter.Converter;
import com.ezgroceries.shoppinglist.dto.CocktailDTO;
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
    public CocktailDTO getCocktail(String search) {

        var cocktailEntity = repository.findByName(search);

        if(cocktailEntity.isEmpty()){

            addCocktailToDB(search);

            cocktailEntity = repository.findByName(search);

            if(cocktailEntity.isEmpty()) {
                return new CocktailDTO.Builder()
                        .withErrorMessage("Cocktail not found")
                        .build();
            }
        }

        return cocktailEntity.get().toDto();
    }

    public void addCocktailToDB(String search){
        var response =  client.searchCocktails(search);

        if(null == response.getDrinks()){
            return;
        }

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
