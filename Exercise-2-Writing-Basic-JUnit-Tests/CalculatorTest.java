import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(30, calculator.add(10, 20));
    }

    @Test
    public void testSubtract() {
        assertEquals(10, calculator.subtract(20, 10));
    }

    @Test
    public void testMultiply() {
        assertEquals(200, calculator.multiply(20, 10));
    }

    @Test
    public void testDivide() {
        assertEquals(2, calculator.divide(20, 10));
    }
}
