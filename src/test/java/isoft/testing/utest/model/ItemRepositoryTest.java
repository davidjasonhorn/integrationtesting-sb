package isoft.testing.utest.model;

import isoft.testing.utest.model.ItemRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 *
 * @author hornd
 */
@DataJpaTest
public class ItemRepositoryTest {
    
    @Autowired
    private ItemRepository repository;
    
    @Test
    public void find_all_items() { 
        Assertions.assertTrue(repository.findAll().size() ==3);
    }
}
