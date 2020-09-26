/*
 * Copyright 2020 hornd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package isoft.testing.utest.product.service;

import isoft.testing.utest.product.conversion.InventoryTransactionConverter;
import isoft.testing.utest.product.conversion.ProductConverter;
import isoft.testing.utest.product.domain.InventoryTransaction;
import isoft.testing.utest.product.domain.Product;
import isoft.testing.utest.product.domain.ProductRepository;
import isoft.testing.utest.product.external.ExchangeRateHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hornd
 */
@Service
public class ProductServiceImpl implements ProductService {

    //Managed with Dependency Injection
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private InventoryTransactionConverter inventoryTransactionConverter;

    @Autowired
    private ExchangeRateHelper exchangeRateHelper;


    @Override
    public void addProduct(ProductTO product) {

        //convert product to domain
        Product p = productConverter.toDomain(product);
        //store product
        productRepository.save(p);
    }

    @Override
    public void performTransaction(InventoryTransactionTO inventoryTransaction) {
        
        //convert transaction to a domain
        InventoryTransaction inv = inventoryTransactionConverter.toDomain(inventoryTransaction);

        //load the product
        Product p = productRepository.findByProductId(inventoryTransaction.getProductId());
        //add inventory to the product
        p.addInventoryTransaction(inv);
        //store product
        productRepository.save(p);
    }

    @Override
    public BigDecimal getSalePrice(InventoryTransactionTO inventoryTransaction, String currencyCode) {
        
        Product p = productRepository.findByProductId(inventoryTransaction.getProductId());
        //calculate the sale price
        BigDecimal salePrice = exchangeRateHelper.calculateSalePriceForCurrency(inventoryTransaction, currencyCode, p);
        return salePrice;
    }

    @Override
    public ProductTO loadProductByProductId(String productId) {

        //load the product by id
        Product product = productRepository.findByProductId(productId);

        ProductTO p = productConverter.toTransferObject(product);
        return p;
    }

    @Override
    public ProductListTO loadAllProducts() {

        @SuppressWarnings("unchecked")
        List<Product> products = toList(productRepository.findAll());

        ProductListTO p = productConverter.toTransferObject(products);

        return p;

    }

    @Override
    public InventoryTransactionListTO loadTransactionsForProductId(String productId) {
        Product p = productRepository.findByProductId(productId);

        List<InventoryTransaction> inventoryTransactions = p.getInventoryTransactions();

        InventoryTransactionListTO invList = inventoryTransactionConverter.toTransferObject(inventoryTransactions);

        return invList;
    }

    private List<Product> toList(Iterable<Product> findAll) {
        List<Product> actualList = new ArrayList<>();
        findAll.forEach(actualList::add);
        return actualList;
    }
    
}
