package simulator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyMathsTest
{

    @Test
    public void facto () {
        assertEquals(1, MyMaths.facto(0));
        assertEquals(1, MyMaths.facto(1));
        assertEquals(120, MyMaths.facto(5));
    }
}