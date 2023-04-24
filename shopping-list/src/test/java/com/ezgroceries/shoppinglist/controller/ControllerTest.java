package com.ezgroceries.shoppinglist.controller;

import com.ezgroceries.shoppinglist.api.response.CocktailDBResponse;
import com.ezgroceries.shoppinglist.api.response.Drink;
import com.ezgroceries.shoppinglist.model.ShoppingListRequest;
import com.ezgroceries.shoppinglist.repository.CocktailDBClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class) // WebMvcTest = MockMvc, @MockBean
class ControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CocktailDBClient client;

    @Test
    public void getCocktailsSuccess() throws Exception {
        var response = new CocktailDBResponse();
        response.setDrinks(List.of(new Drink("11102","Black Russian")));

        given(client.searchCocktails("Russian")).willReturn(response);

        mockMvc.perform(get("/cocktails")
                        .param("search","Russian")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.drinks[0].idDrink").value("11102"));

        verify(client).searchCocktails(anyString());
    }

    @Test
    @Disabled
    public void getCocktailsFailure() throws Exception {
        var response = new CocktailDBResponse();
        //response.setDrinks(List.of(new Drink("11102","Black Russian","Ordinary Drink")));

        given(client.searchCocktails(anyString())).willReturn(response);

        mockMvc.perform(get("/cocktails")
                        .param("search","Cartman")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isInternalServerError());
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
        var response = new CocktailDBResponse();
        response.setDrinks(List.of(new Drink("11000","Mojito")));

        given(client.searchCocktails("Mojito")).willReturn(response);

        mockMvc.perform(post("/shopping-lists/{shoppingListId}/cocktails","222")
                .content("{\n" + "  \"name\": \"Mojito\"\n" + "}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location","http://localhost/shopping-lists/222/cocktails222"));
    }

    @Test
    void getShoppingList() throws Exception{
        var response = new CocktailDBResponse();
        response.setDrinks(List.of(new Drink("11000","Mojito")));

        given(client.searchCocktails("Mojito")).willReturn(response);

        mockMvc.perform(get("/shopping-lists/{shoppingListId}","90689338-499a-4c49-af90-f1e73068ad4f"))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("90689338-499a-4c49-af90-f1e73068ad4f"));

    }

    @Test
    void getShoppingListSuccess() throws Exception {
        var response = new CocktailDBResponse();
        var drinkMojito = new Drink("11000","Mojito","Light rum","Lime","Sugar");
        var drinkMargarita = new Drink( "11007","Margarita","Tequila","Triple sec","Lime juice");
        response.setDrinks(List.of(drinkMojito,drinkMargarita));

        given(client.searchCocktails(anyString())).willReturn(response);

        var shoppingLists = new ArrayList<ShoppingListRequest>();
        var shopList1 = new ShoppingListRequest("90689338-499a-4c49-af90-f1e73068ad4f","Stephanie's birthday", drinkMojito.getIngredients());
        var shopList2 = new ShoppingListRequest("6c7d09c2-8a25-4d54-a979-25ae779d2465", "My Birthday", drinkMargarita.getIngredients());
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