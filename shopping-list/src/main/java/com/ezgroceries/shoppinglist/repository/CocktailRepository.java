package com.ezgroceries.shoppinglist.repository;

import com.ezgroceries.shoppinglist.model.CocktailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CocktailRepository extends JpaRepository<CocktailEntity,String> {
    Optional<CocktailEntity> findByCocktailId(String id);
    Optional<CocktailEntity> findByName(String name);
}
