package entities;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
 @Test
    public void freteCalcTesteRetorna100(){
  assertEquals(100, Product.frete(1000));
 }

 @Test
    public void freteCalcTesteRetorna105(){
        assertEquals(140, Product.frete(2000));
    }
}
