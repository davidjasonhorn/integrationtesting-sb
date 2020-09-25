package isoft.testing.utest.jsonpath;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author hornd
 */
public class JsonPathTest {
  
    
    @Test
    public void testFatures() { 
        
        String jsonString = "[" +
"{\"id\": 1, \"name\": \"Pencil\", \"quantity\" : 5 }," +
"{\"id\": 2, \"name\": \"Pen\", \"quantity\" : 15 }, " +
"{\"id\": 3, \"name\": \"Eraser\", \"quantity\" : 10 }" +
" ]";
        DocumentContext ctx = JsonPath.parse(jsonString); 
        int length = ctx.read("$.length()"); 
        Assertions.assertThat(length).isEqualTo(3);
        List<Integer> ids = ctx.read("$..id");
        Assertions.assertThat(ids).contains(1,2,3);
        Assertions.assertThat(ids).containsExactly(1,2,3);
        System.out.println(ctx.read("$.[0]").toString());
        System.out.println(ctx.read("$.[0:1]").toString());
        System.out.println(ctx.read("$.[?(@.name=='Eraser')]").toString());
        System.out.println(ctx.read("$.[?(@.quantity==5)]").toString());
 
    }
}
