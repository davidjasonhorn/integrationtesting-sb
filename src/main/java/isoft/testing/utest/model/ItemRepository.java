package isoft.testing.utest.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hornd
 */
public interface ItemRepository extends JpaRepository<Item, Integer>{
    
}
