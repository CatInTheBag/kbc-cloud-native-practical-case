package com.ezgroceries.shoppinglist.repository;

import com.ezgroceries.shoppinglist.api.response.CocktailDBResponse;
import com.ezgroceries.shoppinglist.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "cocktailDBClient", url = "https://www.thecocktaildb.com/api/json/v1/1" , fallback = CocktailDBClient.CocktailDBClientFallback.class)
public interface CocktailDBClient {

    @GetMapping(value = "search.php")
    CocktailDBResponse searchCocktails(@RequestParam("s") String search);

    class CocktailDBClientFallback implements CocktailDBClient {

        @Autowired
        private CocktailRepository cocktailRepository;
        @Autowired
        private Converter converter;

        @Override
        public CocktailDBResponse searchCocktails(String search) {
            var response = new CocktailDBResponse();

            var cocktailEntity = cocktailRepository.findByNameContainingIgnoreCase(search);

            if(cocktailEntity.isEmpty()){
                return null;
            }

            response.setDrinks(converter.convertEntitiesToListDrink(cocktailEntity.get()));

            return response;
        }
    }
}
