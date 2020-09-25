/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isoft.testing.utest.business;

/**
 *
 * @author hornd
 */
public class SomeBusinessImpl {
    private SomeDataService someDataService;

    public void setSomeDataService(SomeDataService someDataService) {
        this.someDataService = someDataService;
    }
    
    
    public int calculateSum (int...data) { 
        int sum = 0; 
        for (int i : data) { 
            sum+=i;
        }
        
        return sum;
    }
    
    public int calculateSumUsingDataService () { 
        int sum = 0; 
        for (int i : someDataService.retriveAllData()) { 
            sum+=i;
        }
        
        return sum;
    }    
}
