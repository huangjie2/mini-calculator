package com.kent;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for Calculator.
 */
public class CalculatorTest {

    @Test
    public void testCaculator() {
        Calculator calc = new Calculator();

        calc.add(10);
        Assert.assertEquals(10.0, calc.getCurrentValue(), 1.0);

        calc.subtract(2);
        Assert.assertEquals(8.0, calc.getCurrentValue(), 0.0);

        calc.multiply(2);
        Assert.assertEquals(16.0, calc.getCurrentValue(), 0.0);

        calc.divide(4);
        Assert.assertEquals(4.0, calc.getCurrentValue(), 0.0);

        calc.undo();
        Assert.assertEquals(16.0, calc.getCurrentValue(), 0.0);

        calc.redo();
        Assert.assertEquals(4.0, calc.getCurrentValue(), 0.0);

    }
}
