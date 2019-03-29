package com.udemyMasterJavaUnitTest.MasterJavaUnitTestingwithSpringBootMockito.controller;

import com.udemyMasterJavaUnitTest.MasterJavaUnitTestingwithSpringBootMockito.model.Item;
import com.udemyMasterJavaUnitTest.MasterJavaUnitTestingwithSpringBootMockito.servis.ItemBusinessService;
import org.hamcrest.Matchers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemController.class)
public class ItemControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    ItemBusinessService itemBusinessService;

    @Test
    public void dummyItem_basic() throws Exception {

        RequestBuilder request = MockMvcRequestBuilders
                .get("/dummy-item")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{id: 1,name:Ball,price:10,quantity:100}"))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Ball")))
                .andExpect(jsonPath("$.price", Matchers.is(10)))
                .andExpect(jsonPath("$.quantity", Matchers.is(100)))


                .andReturn();

        String expected = "{id: 1,name:Ball,price:10,quantity:100}";

        System.out.println("testim" + result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }

    @Test
    public void itemFromBusinessService_test() throws Exception {
        when(itemBusinessService.retreiveHardcodedItem()).thenReturn(new Item(1, "Ball", 10, 100));
        RequestBuilder request = MockMvcRequestBuilders
                .get("/item-from-business-service")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("{id: 1,name:Ball,price:10,quantity:100}"))
                .andReturn();

        String expected = "{id: 1,name:Ball,price:10,quantity:100}";

        System.out.println("testim" + result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);


    }

    @Test
    public void retrieveAllItems_test() throws Exception {
        when(itemBusinessService.retrieveAllItems()).thenReturn(
                Arrays.asList(new Item(1001, "Item1", 10, 20))

        );
        RequestBuilder request = MockMvcRequestBuilders
                .get("/all-items-from-database")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().json("[{id: 1,name:Ball,price:10,quantity:100}]"))
                //burda cok json olabilir[{},{}] şeklinde
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Ball")))
                .andExpect(jsonPath("$.price", Matchers.is(10)))
                .andExpect(jsonPath("$.quantity", Matchers.is(100)))


                .andReturn();

        String expected = "{id: 1,name:Ball,price:10,quantity:100}";

        System.out.println("testim" + result.getResponse().getContentAsString());
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);


    }
}
