package com.ezgroceries.shoppinglist.model;

import com.ezgroceries.shoppinglist.converter.StringSetConverter;
import com.ezgroceries.shoppinglist.dto.CocktailDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cocktail")
public class CocktailEntity {

    @Id
    @Column(updatable = false)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "id_drink")
    private String cocktailId;

    private String name;

    private String glass;

    private String image;

    private String instructions;

    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    @OneToMany(
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            mappedBy = "cocktail"
    )
    @JsonIgnoreProperties("cocktail")
    private List<CocktailShoppingListEntity> cocktailShoppingLists;

    public CocktailEntity(){};

    public CocktailEntity(String cocktailId, String name, String glass, String image, Set<String> ingredients) {
        this.cocktailId = cocktailId;
        this.name = name;
        this.glass = glass;
        this.image = image;
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCocktailId() {
        return cocktailId;
    }

    public void setCocktailId(String cocktailId) {
        this.cocktailId = cocktailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGlass() {
        return glass;
    }

    public void setGlass(String glass) {
        this.glass = glass;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    /*public List<CocktailShoppingListEntity> getCocktailShoppingList() {
        return cocktailShoppingLists;
    }

    public void setCocktailShoppingList(List<CocktailShoppingListEntity> cocktailShoppingList) {
        this.cocktailShoppingLists = cocktailShoppingList;
    }

    public void addCocktailToShoppingList(CocktailShoppingListEntity cocktailShoppingListEntity){
        if(!this.getCocktailShoppingList().contains(cocktailShoppingListEntity)){
            this.cocktailShoppingLists.add(cocktailShoppingListEntity);
        }
    }

    public void removeCocktailFromShoppingList(CocktailShoppingListEntity cocktailShoppingListEntity){
        this.cocktailShoppingLists.remove(cocktailShoppingListEntity);
    }*/

    public CocktailDTO toDto(){
        return new CocktailDTO.Builder()
                .withCocktailId(this.getCocktailId())
                .withName(this.getName())
                .withGlass(this.getGlass())
                .withImage(this.getImage())
                .withIngredients(this.getIngredients())
                .build();
    }
}
