package isoft.testing.utest.resource;

import isoft.testing.utest.product.service.InventoryTransactionListTO;
import isoft.testing.utest.product.service.InventoryTransactionTO;
import isoft.testing.utest.product.service.ProductListTO;
import isoft.testing.utest.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import isoft.testing.utest.product.service.ProductTO;
import java.math.BigDecimal;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Rest API for managing attachments.
 *
 * @author hornd
 */
@RestController()
@RequestMapping(value = "/product")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void postProduct(@RequestBody ProductTO att) {
//        try {
        System.out.println(att.toString());
        productService.addProduct(att);
//            return Response.status(Response.Status.CREATED).build();
//        } catch (BusinessException be) {
//            return Response.status(Response.Status.BAD_REQUEST).entity(be.getErrorsList()).build();
//        }
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductTO loadProductByProductId(@PathVariable(name = "id") String productId) {

        System.out.println("attachmentId is " + productId);
        ProductTO pr = productService.loadProductByProductId(productId);
        return pr;

    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductListTO loadAllProducts() {
        ProductListTO pl = productService.loadAllProducts();
        return pl;
    }

    @GetMapping(path = "/salesprice", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getSalePrice(@RequestParam(name = "productId") String productId,
            @RequestParam(name = "quantity") Integer quantity,
            @RequestParam(name = "currency") String currency) {
        InventoryTransactionTO it = new InventoryTransactionTO(java.time.LocalDateTime.now(), quantity, productId);
        BigDecimal salsesprice = productService.getSalePrice(it, currency);
        return salsesprice.toString();
    }

    @GetMapping(path = "/{id}/inv")
    public InventoryTransactionListTO loadTransactionsForProductId(@PathVariable(value = "id") String productId) {
        InventoryTransactionListTO to = productService.loadTransactionsForProductId(productId);
        return to;
    }

    @PostMapping(path = "/trans", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void performTransaction(@Valid @RequestBody InventoryTransactionTO inventoryTransaction) {

        productService.performTransaction(inventoryTransaction);
    }

}
