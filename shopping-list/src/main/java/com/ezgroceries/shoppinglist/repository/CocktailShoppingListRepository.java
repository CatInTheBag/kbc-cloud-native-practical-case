package com.ezgroceries.shoppinglist.repository;

import com.ezgroceries.shoppinglist.model.CocktailEntity;
import com.ezgroceries.shoppinglist.model.CocktailShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CocktailShoppingListRepository extends JpaRepository<CocktailShoppingListEntity,String> {
    Optional<CocktailShoppingListEntity> findByCocktailIdAndShoppingListId(String cocktailId, String shoppingListId);

    Optional<List<CocktailShoppingListEntity>> findByShoppingListId(String cocktailId);
}
