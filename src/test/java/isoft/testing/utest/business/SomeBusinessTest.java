package isoft.testing.utest.business;

import isoft.testing.utest.business.SomeBusinessImpl;
import isoft.testing.utest.business.SomeDataService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RunAs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author hornd
 */


//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SomeBusinessTest {

    @InjectMocks
    private SomeBusinessImpl foo = new SomeBusinessImpl();
    
    @Mock
    private SomeDataService mockDataService;
    //private SomeDataService mockDataService = mock(SomeDataService.class);

//    @BeforeEach
//    public void before() {
//        foo.setSomeDataService(mockDataService);
//    }

    @Test
    public void calculateSum_basic() {
        when(mockDataService.retriveAllData()).thenReturn(new int[]{1, 2, 3});
        Assertions.assertEquals(foo.calculateSumUsingDataService(), 6);
    }

    @Test
    public void calculateSum_empty() {
        when(mockDataService.retriveAllData()).thenReturn(new int[]{});
        Assertions.assertEquals(foo.calculateSumUsingDataService(), 0);
    }
    
    @Test
    public void testList() {
        List f = mock(List.class);
        when(f.get(anyInt())).thenReturn("foobar"); //argument matcher.
        Assertions.assertEquals("foobar", f.get(100));
    }

    @Test
    public void stupidTest() { //verification for methods that do not return a value  - but perform an action.
        List f = mock(List.class);
        when(f.get(anyInt())).thenReturn("foobar"); //argument matcher.
        
        f.get(0);
        f.get(1);
        
        verify(f).get(1);
        verify(f, Mockito.times(2)).get(anyInt());
        verify(f, Mockito.never()).get(2);
    }  
    
    
    @Test
    @SuppressWarnings("unchecked")
    public void argumentCapture() { //verification for methods that do not return a value  - but perform an action.
        List f = mock(List.class);
        f.add("s");
        f.add("t");
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(f, times(2)).add(captor.capture());
        
        Assertions.assertEquals("s", captor.getAllValues().get(0));
        Assertions.assertEquals("t", captor.getAllValues().get(1));
    }    

    @Test
    @SuppressWarnings("unchecked")
    public void spying() { 
        ArrayList f = spy(ArrayList.class); //gives the ability to create a "real" object - not simply a mock
                                            // object
        f.add("s");
        f.add("t");
        when(f.size()).thenReturn(5); 
        
        Assertions.assertEquals("s", f.get(0));
        Assertions.assertEquals("t", f.get(1));
        Assertions.assertEquals(5, f.size());

    }  
    
    
}
