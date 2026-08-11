package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MathUtilsTestNG {

    @Test
    public void testFactorial() {
        Assert.assertEquals(MathUtils.factorial(0), 1L);
        Assert.assertEquals(MathUtils.factorial(1), 1L);
        Assert.assertEquals(MathUtils.factorial(3), 6L);
        Assert.assertEquals(MathUtils.factorial(5), 120L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialNegative() {
        MathUtils.factorial(-1);
    }

    @Test
    public void testTriangleArea() {
        Assert.assertEquals(MathUtils.triangleArea(5, 4), 10.0, 1e-9);
        Assert.assertEquals(MathUtils.triangleArea(1, 1), 0.5, 1e-9);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTriangleAreaNegativeBase() {
        MathUtils.triangleArea(-1, 2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testTriangleAreaZeroHeight() {
        MathUtils.triangleArea(2, 0);
    }

    @Test
    public void testArithmeticOperations() {
        Assert.assertEquals(MathUtils.add(3, 4), 7);
        Assert.assertEquals(MathUtils.subtract(3, 4), -1);
        Assert.assertEquals(MathUtils.multiply(3, 4), 12);
        Assert.assertEquals(MathUtils.divide(6, 3), 2);
    }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivideByZero() {
        MathUtils.divide(5, 0);
    }

    @Test
    public void testCompare() {
        Assert.assertEquals(MathUtils.compare(2, 5), -1);
        Assert.assertEquals(MathUtils.compare(3, 3), 0);
        Assert.assertEquals(MathUtils.compare(7, 3), 1);
    }
}