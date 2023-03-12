package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.model.Cocktail;
import com.ezgroceries.shoppinglist.model.ShoppingList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class) // WebMvcTest = MockMvc, @MockBean
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCocktailsSuccess() throws Exception {

        mockMvc.perform(get("/cocktails")
                        .param("search","Russian")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].cocktailId").value("23b3d85a-3928-41c0-a533-6538a71e17c4"));
    }

    @Test
    void createShoppingListSuccess() throws Exception {

        mockMvc.perform(post("/shopping-lists")
                .content("{\n" + "  \"name\": \"Stephanie's birthday\"\n" + "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location","http://localhost/shopping-lists/2222"));
    }

    @Test
    void addCocktailToShoppingListSuccess() throws Exception {

        mockMvc.perform(post("/shopping-lists/{shoppingListId}/cocktails","222")
                .content("{\n" + "  \"cocktailId\": \"23b3d85a-3928-41c0-a533-6538a71e17c4\"\n" + "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location","http://localhost/shopping-lists/222/cocktails222"));
    }

    @Test
    void getShoppingList() throws Exception{

        mockMvc.perform(get("/shopping-lists/{shoppingListId}","90689338-499a-4c49-af90-f1e73068ad4f"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("90689338-499a-4c49-af90-f1e73068ad4f"));

    }

    @Test
    void getShoppingListSuccess() throws Exception {
        Cocktail margerita = new Cocktail("23b3d85a-3928-41c0-a533-6538a71e17c4","Margerita","Cocktail glass","Rub the rim of the glass with the lime slice to make the salt stick to it. Take care to moisten..",
                "https://www.thecocktaildb.com/images/media/drink/wpxpvu1439905379.jpg",List.of("Tequila","Triple sec","Lime juice","Salt"));
        var shoppingLists = new ArrayList<ShoppingList>();
        var shopList1 = new ShoppingList("90689338-499a-4c49-af90-f1e73068ad4f","Stephanie's birthday", margerita.getIngredients());
        var shopList2 = new ShoppingList("6c7d09c2-8a25-4d54-a979-25ae779d2465", "My Birthday", margerita.getIngredients());
        shoppingLists.add(shopList1);
        shoppingLists.add(shopList2);

        mockMvc.perform(get("/shopping-lists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(shoppingLists)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("90689338-499a-4c49-af90-f1e73068ad4f"))
                .andExpect(jsonPath("$[1].id").value("6c7d09c2-8a25-4d54-a979-25ae779d2465"));

    }

    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}