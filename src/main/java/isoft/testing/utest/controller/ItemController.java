package isoft.testing.utest.controller;

import isoft.testing.utest.business.ItemBusinessService;
import isoft.testing.utest.model.Item;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hornd
 */
@RestController()
public class ItemController {
    
    @Autowired
    private ItemBusinessService itemBusinessService;
    
    @GetMapping("/item")
    public Item getItem() { 
        return new Item(1, "foo", 100, 10);
    }

    @GetMapping("/itemById")
    public Item getItemById(@RequestParam(name = "id") Integer id) { 
        Item i = itemBusinessService.getItemById(id);
        return i;
    }

    @GetMapping("/item-service")
    public Item getItemFromService() { 
        return itemBusinessService.retrieveItem(0);
    }
    
    @GetMapping("/item-sall")
    public List<Item> getAllItems() { 
        return itemBusinessService.retrieveAllItems();
    }
    
    @PostMapping("/item")
    public Item postItem(@RequestBody Item it) { 
       Item it2 = itemBusinessService.postItem(it);
       return it2;
    }
    
}
