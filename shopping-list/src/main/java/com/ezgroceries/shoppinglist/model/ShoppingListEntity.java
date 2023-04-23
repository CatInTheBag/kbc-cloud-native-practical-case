package com.ezgroceries.shoppinglist.model;

import com.ezgroceries.shoppinglist.converter.StringSetConverter;
import com.ezgroceries.shoppinglist.dto.ShoppingListDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shopping_list")
public class ShoppingListEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "name")
    private String name;

    /*@Column(name = "ingredients")
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;*/

    @OneToMany(
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            mappedBy = "shoppingList"
    )
    @JsonIgnoreProperties("shoppingList")
    private List<CocktailShoppingListEntity> cocktailShoppingLists;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShoppingListDTO toDTO(){
        return new ShoppingListDTO.Builder()
                .withName(this.getName())
                .build();
    }

    /*public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }*/

    /*public List<CocktailShoppingListEntity> getCocktailShoppingList() {
        return cocktailShoppingLists;
    }

    public void addCocktail(CocktailShoppingListEntity cocktailShoppingList) {
        if(!this.cocktailShoppingLists.contains(cocktailShoppingList)){
            this.cocktailShoppingLists.add(cocktailShoppingList);
        }
    }

    public void removeCocktail(CocktailShoppingListEntity cocktailShoppingListEntity){
        this.cocktailShoppingLists.remove(cocktailShoppingListEntity);
    }*/
}
