package com.ezgroceries.shoppinglist.dto;

import java.util.Set;

public class CocktailDTO {
    private String cocktailId;
    private String name;
    private String glass;
    private String instructions;
    private String image;
    private Set<String> ingredients;

    private String errorMessage;

    private CocktailDTO() {
    }

    public static class Builder {

        private CocktailDTO cocktailDTO = new CocktailDTO();

        public Builder withCocktailId(String cocktailId) {
            cocktailDTO.setCocktailId(cocktailId);
            return this;
        }

        public Builder withName(String name) {
            cocktailDTO.setName(name);
            return this;
        }

        public Builder withGlass(String glass) {
            cocktailDTO.setGlass(glass);
            return this;
        }

        public Builder withInstructions(String instructions) {
            cocktailDTO.setInstructions(instructions);
            return this;
        }

        public Builder withImage(String image) {
            cocktailDTO.setImage(image);
            return this;
        }

        public Builder withIngredients(Set<String> ingredients) {
            cocktailDTO.setIngredients(ingredients);
            return this;
        }

        public Builder withErrorMessage(String errorMessage){
            cocktailDTO.setErrorMessage(errorMessage);
            return this;
        }

        public CocktailDTO build() {
            return cocktailDTO;
        }
    }

    public static Builder newBuilder() {
        return new Builder();
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

    public Set<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
