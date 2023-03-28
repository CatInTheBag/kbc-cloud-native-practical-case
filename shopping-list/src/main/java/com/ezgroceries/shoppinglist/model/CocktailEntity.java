package com.ezgroceries.shoppinglist.model;

import com.ezgroceries.shoppinglist.converter.StringSetConverter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cocktail")
public class CocktailEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "id_drink")
    private String cocktailId;

    @Column(name = "name")
    private String name;

    @Column(name = "glass")
    private String glass;

    @Column(name = "image")
    private String image;

    @Column(name = "ingredients")
    @Convert(converter = StringSetConverter.class)
    private Set<String> ingredients;

    public CocktailEntity(){};

    public CocktailEntity(String cocktailId, String name, String glass, String image, Set<String> ingredients) {
        this.cocktailId = cocktailId;
        this.name = name;
        this.glass = glass;
        this.image = image;
        this.ingredients = ingredients;
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

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    /*public static class CocktailBuilder {
        private String cocktailId;
        private String name;
        private String glass;
        private String instructions;
        private String image;
        private Set<String> ingredients;

        public CocktailBuilder setCocktailId(String cocktailId) {
            this.cocktailId = cocktailId;
            return this;
        }

        public CocktailBuilder setName(String name){
            this.name = name;
            return this;
        }

        public CocktailBuilder setGlass(String glass){
            this.glass = glass;
            return this;
        }

        public CocktailBuilder setImage(String image){
            this.image = image;
            return this;
        }

        public CocktailBuilder setIngredients(Set<String> ingredients){
            this.ingredients = ingredients;
            return this;
        }

        public Cocktail build(){
            return new Cocktail(cocktailId, name, glass, image, ingredients);
        }
    }*/
}
