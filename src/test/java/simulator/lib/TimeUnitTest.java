package simulator.lib;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeUnitTest
{

    @Test
    public void calculateRatioWhenTargetIsLower () {
        assertEquals(60 * 60, TimeUnit.HOUR.calculateRatio(TimeUnit.SECOND), 0.001);
        assertEquals(24 * 60, TimeUnit.DAY.calculateRatio(TimeUnit.MINUTE), 0.001);
        assertEquals(7 * 24 * 60, TimeUnit.WEEK.calculateRatio(TimeUnit.MINUTE), 0.001);
    }

    @Test
    public void calculateRatioWhenTargetIsGreater () {
        assertEquals(1 / 60 / 60, TimeUnit.SECOND.calculateRatio(TimeUnit.HOUR), 0.001);
        assertEquals(1 / 24 / 60, TimeUnit.MINUTE.calculateRatio(TimeUnit.DAY), 0.001);
    }

    @Test
    public void calculateRatioWhenTargetIsEqual () {
        assertEquals(1, TimeUnit.SECOND.calculateRatio(TimeUnit.SECOND), 0.001);
        assertEquals(1, TimeUnit.YEAR.calculateRatio(TimeUnit.YEAR), 0.001);
        assertEquals(1, TimeUnit.MINUTE.calculateRatio(TimeUnit.MINUTE), 0.001);
    }

    @Test
    public void realCaseConversionWithCalculateRatio () {
        assertEquals(2 * 60 * 60, 2 * TimeUnit.HOUR.calculateRatio(TimeUnit.SECOND), 0.001);
        assertEquals(3 * 7 * 24 * 60, 3 * TimeUnit.WEEK.calculateRatio(TimeUnit.MINUTE), 0.001);

        assertEquals(2 / 60 / 60, 2 * TimeUnit.SECOND.calculateRatio(TimeUnit.HOUR), 0.001);
        assertEquals(3 / 7 / 24 / 60, 3 * TimeUnit.MINUTE.calculateRatio(TimeUnit.WEEK), 0.001);
    }


    @Test
    public void convertTimeTo () {
        assertEquals(2 * 60 * 60, TimeUnit.HOUR.convertTimeTo(2, TimeUnit.SECOND), 0.001);
        assertEquals(3 * 7 * 24 * 60, TimeUnit.WEEK.convertTimeTo(3, TimeUnit.MINUTE), 0.001);

        assertEquals(2 / 60 / 60, TimeUnit.SECOND.convertTimeTo(2, TimeUnit.HOUR), 0.001);
        assertEquals(3 / 7 / 24 / 60, TimeUnit.MINUTE.convertTimeTo(3, TimeUnit.WEEK), 0.001);
    }

    @Test
    public void convertFrequencyTo(){
        // 120 times an hour = twice a minute
        assertEquals(2, TimeUnit.HOUR.convertFrequencyTo(120, TimeUnit.MINUTE), 0.001);

        // Twice a minute = 120 times an hour
        assertEquals(120, TimeUnit.MINUTE.convertFrequencyTo(2, TimeUnit.HOUR), 0.001);
    }
}