package isoft.testing.utest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hornd
 */
@RestController
public class HelloWorldController {
    
    @GetMapping("/hello-world")
    public String helloWorld() { 
        return "hello world";
    }
}
