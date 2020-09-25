package isoft.testing.utest.business;

import isoft.testing.utest.model.Item;
import isoft.testing.utest.model.ItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author hornd
 */
@Service
public class ItemBusinessService {
    
    @Autowired
    private ItemRepository repository;
    
    public Item retrieveItem(int id) { 
        return new Item(id, "ball", 10, 100);
    }
    
    public List<Item> retrieveAllItems() { 
        List<Item> items =  repository.findAll();
        
        items.forEach((i) -> { 
            i.setValue(i.getPrice()*i.getQuantity());
        });
        
        return items;
    }

    public Item postItem(Item it) {
        
        repository.save(it);
        
        return it;
        
    }

    public Item getItemById(Integer id) {
        Optional<Item> item = repository.findById(id); 
        if (item.isPresent()) { 
            return item.get();
        } else { 
            return null;
        }
        
    }
}
