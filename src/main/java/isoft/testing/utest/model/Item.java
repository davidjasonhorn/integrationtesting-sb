package isoft.testing.utest.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;


/**
 *
 * @author hornd
 */
@Entity()
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private  int id; 
    
    private   String name;
    private   int price;
    private  int quantity; 
    
    @Transient
    private int value; 
    
    protected Item() {

    }

    
    public Item(int id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    
    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name=" + name + ", price=" + price + ", quantity=" + quantity + '}';
    }
    
    
    
          
    
}
