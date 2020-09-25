/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isoft.testing.utest.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import isoft.testing.utest.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author hornd
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemControllerIT {
    
    @Autowired
    private TestRestTemplate testRestTemplate; 
    
    //Use @MockBean to mock interfaces with external systems.
    
    @Test
    public void get_item() { 
        Item i = this.testRestTemplate.getForObject("/item", Item.class);
        Assertions.assertEquals(1,i.getId());
    }
    
    @Test
    public void post_item() { 
        Item i = new Item(0,"test100", 100, 100);

        ResponseEntity<Item> re = testRestTemplate.postForEntity("/item", i, Item.class);
        
        Assertions.assertNotNull(re.getBody());
        Assertions.assertEquals(HttpStatus.OK, re.getStatusCode());

        System.out.println(re.getBody());

//        Item i2 = this.testRestTemplate.getForObject("/itemById", Item.class, re.getBody().getId());
//        Assertions.assertEquals(re.getBody().getId(),i2.getId());
//        Assertions.assertEquals("test100",i2.getName());
//        Assertions.assertEquals(100,i2.getPrice());
//        Assertions.assertEquals(100,i2.getQuantity());
    }    
    
}
