package simulator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MMSTest
{
    @Test
    public void q0 () {
        MMS mms = new MMS();
        assertEquals(mms.calcQ0(32, 8, 12), 0.02, 0.01);
        assertEquals(mms.calcQ0(4, 0.8, 6), 0.0045, 0.01);
    }

    @Test
    public void lq () {
        MMS mms = new MMS();
        assertEquals(mms.calcLq(32, 8, 12), 0.0005, 0.0001);
        assertEquals(mms.calcLq(4, 0.8, 6), 2.94, 0.01);
    }

    @Test
    public void l () {
        MMS mms = new MMS();
        assertEquals(mms.calcL(32, 8, 12), 4.00, 0.01);
        assertEquals(mms.calcL(4, 0.8, 6), 7.94, 0.01);
    }

    @Test
    public void wq () {
        MMS mms = new MMS();
        assertEquals(mms.calcWq(32, 8, 12), 0.0, 0.01);
        assertEquals(mms.calcWq(4, 0.8, 6), 0.73, 0.01);
    }

    @Test
    public void w () {
        MMS mms = new MMS();
        assertEquals(mms.calcW(32, 8, 12), 0.13, 0.01);
        assertEquals(mms.calcW(4, 0.8, 6), 1.98, 0.01);
    }

    @Test
    public void bottleNeck () {
    }
}