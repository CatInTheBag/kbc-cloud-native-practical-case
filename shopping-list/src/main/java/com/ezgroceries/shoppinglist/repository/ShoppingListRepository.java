package com.ezgroceries.shoppinglist.repository;

import com.ezgroceries.shoppinglist.model.ShoppingListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingListEntity, String> {
    Optional<ShoppingListEntity> findByName(String name);
}
