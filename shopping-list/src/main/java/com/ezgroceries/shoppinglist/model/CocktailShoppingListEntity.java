package com.ezgroceries.shoppinglist.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "cocktail_shopping_list")
public class CocktailShoppingListEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "cocktail_id")
    @JsonIgnoreProperties({"shoppingList","cocktail"})
    private CocktailEntity cocktail;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shopping_list_id")
    @JsonIgnoreProperties({"shoppingList","cocktail"})
    private ShoppingListEntity shoppingList;

    public CocktailShoppingListEntity(){}

    public CocktailShoppingListEntity(CocktailEntity cocktail, ShoppingListEntity shoppingList) {
        this.cocktail = cocktail;
        this.shoppingList = shoppingList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CocktailEntity getCocktail() {
        return cocktail;
    }

    public void setCocktail(CocktailEntity cocktail) {
        this.cocktail = cocktail;
    }

    public ShoppingListEntity getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingListEntity shoppingList) {
        this.shoppingList = shoppingList;
    }
}
