package com.ezgroceries.shoppinglist.dto;

public class ShoppingListDTO {
    private String name;

    private ShoppingListDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class Builder{
        private ShoppingListDTO shoppingListDTO = new ShoppingListDTO();

        public Builder withName(String name){
            shoppingListDTO.setName(name);
            return this;
        }

        public ShoppingListDTO build(){
            return shoppingListDTO;
        }
    }

    public static Builder newBuilder(){
        return new Builder();
    }
}
