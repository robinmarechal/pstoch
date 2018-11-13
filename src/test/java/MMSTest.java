import org.junit.Test;

import static org.junit.Assert.*;

public class MMSTest {

    @Test
    public void p0() {
        MMS mms = new MMS();
        assertEquals(mms.P0(10,12,2),0.41, 0.0001);
        assertEquals(mms.P0(4,0.8,6),0.0045,0.0001);
    }

    @Test
    public void pa() {
    }

    @Test
    public void n() {
    }

    @Test
    public void na() {
    }

    @Test
    public void t() {
    }

    @Test
    public void ta() {
    }

    @Test
    public void engorgement() {
    }
}