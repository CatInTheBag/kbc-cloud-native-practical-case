package com.ezgroceries.shoppinglist.model;

import java.util.List;

public class Cocktail {
    private String cocktailId;
    private String name;
    private String glass;
    private String instructions;
    private String image;
    private List<String> ingredients;

    public Cocktail(String cocktailId, String name, String glass, String instructions, String image, List<String> ingredients) {
        this.cocktailId = cocktailId;
        this.name = name;
        this.glass = glass;
        this.instructions = instructions;
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public static class CocktailBuilder {
        private String cocktailId;
        private String name;
        private String glass;
        private String instructions;
        private String image;
        private List<String> ingredients;

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

        public CocktailBuilder setInstructions(String instructions){
            this.instructions = instructions;
            return this;
        }

        public CocktailBuilder setImage(String image){
            this.image = image;
            return this;
        }

        public CocktailBuilder setIngredients(List<String> ingredients){
            this.ingredients = ingredients;
            return this;
        }

        public Cocktail build(){
            return new Cocktail(cocktailId, name, glass, instructions, image, ingredients);
        }
    }
}
