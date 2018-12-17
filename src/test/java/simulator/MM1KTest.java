package simulator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MM1KTest
{

    @Test
    public void lq () {
        MM1K mm1k = new MM1K();
        assertEquals(mm1k.calcLq(4, 5, 4), 0.860, 0.01);
        assertEquals(mm1k.calcLq(10, 10, 10), 4.0, 0.01);
    }

    @Test
    public void l () {
        MM1K mm1k = new MM1K();
        assertEquals(mm1k.calcL(4, 5, 4), 1.56, 0.01);
        assertEquals(mm1k.calcL(10, 10, 10), 5, 0.01);
    }

    @Test
    public void wq () {
        MM1K mm1k = new MM1K();
        assertEquals(mm1k.calcWq(4, 5, 4), 0.245, 0.01);
        assertEquals(mm1k.calcWq(10, 10, 10), 0.437, 0.01);
    }

    @Test
    public void w () {
        MM1K mm1k = new MM1K();
        assertEquals(mm1k.calcW(4, 5, 4), 0.445, 0.01);
        assertEquals(mm1k.calcW(10, 10, 10), 0.537, 0.01);
    }

}