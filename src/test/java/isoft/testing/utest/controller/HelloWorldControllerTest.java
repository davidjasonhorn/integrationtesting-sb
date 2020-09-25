package isoft.testing.utest.controller;

import isoft.testing.utest.controller.HelloWorldController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author hornd
 */
//@ExtendWith(SpringExtension.class)
@WebMvcTest(HelloWorldController.class) //Loads specific classes annotatned for instance by @Controller
//@SpringBootTest(classes = HelloWorldController.class)                                        //to load the entire spring context, then @SpringBootTest
public class HelloWorldControllerTest {
    
    @Autowired
    private MockMvc mockmvc;
    
    @Test
    public void helloWorld_basic() throws Exception { 
        RequestBuilder request = MockMvcRequestBuilders
                .get("/hello-world")
                .accept(MediaType.APPLICATION_JSON);
        
        MvcResult result =  mockmvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("hello world"))
                .andReturn();
        
//Assertions.assertEquals("hello world", result.getResponse().getContentAsString());
    }
}
