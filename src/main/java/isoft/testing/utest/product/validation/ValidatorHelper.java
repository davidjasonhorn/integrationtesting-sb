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
package isoft.testing.utest.product.validation;

import isoft.testing.utest.product.service.InventoryTransactionTO;
import isoft.testing.utest.product.service.ProductTO;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 *
 * @author hornd
 */
public class ValidatorHelper {
    
    public static Set<ConstraintViolation<ProductTO>> validateProduct(ProductTO product) throws ValidationError { 
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
        Set<ConstraintViolation<ProductTO>> violations = validator.validate(product);
        return violations;
    }
    
    public static Set<ConstraintViolation<InventoryTransactionTO>> validateInventoryTransaction(InventoryTransactionTO invt) throws ValidationError  { 
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
        Set<ConstraintViolation<InventoryTransactionTO>> violations = validator.validate(invt);
        return violations;
    }    
    
    
}
