package isoft.testing.utest.controller;

import isoft.testing.utest.controller.ItemController;
import isoft.testing.utest.business.ItemBusinessService;
import isoft.testing.utest.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 *
 * @author hornd
 */
//@ExtendWith(SpringExtension.class)
@WebMvcTest(ItemController.class) //Loads specific classes annotatned for instance by @Controller
                                        //to load the entire spring context, then @SpringBootTest
public class ItemControllerTest {
    
    @Autowired
    private MockMvc mockmvc;
    
    @MockBean
    private ItemBusinessService itemBusinessService;
    
    @Test
    public void item_basic() throws Exception { 
        RequestBuilder request = MockMvcRequestBuilders
                .get("/item")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result =  mockmvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{id:1,name:foo,price:100,quantity:10}"))
                .andReturn();
        
//Assertions.assertEquals("hello world", result.getResponse().getContentAsString());
    }
    
    @Test
    public void itemService_basic() throws Exception {
        
        Mockito.when(itemBusinessService.retrieveItem(0)).thenReturn(new Item(2, "test",10,20));
        
        RequestBuilder request = MockMvcRequestBuilders
                .get("/item-service")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result =  mockmvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().json("{id:2,name:test,price:10,quantity:20}"))
                .andReturn();

        
//Assertions.assertEquals("hello world", result.getResponse().getContentAsString());
    }    
}
