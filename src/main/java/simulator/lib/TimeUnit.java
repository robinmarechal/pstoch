package simulator.lib;

import java.util.Arrays;
import java.util.List;

public enum TimeUnit
{
    SECOND("seconde", "sec", 1),
    MINUTE("minute", "min", 60),
    HOUR("heure", "h", 60),
    DAY("jour", "j", 24),
    WEEK("semaine", "sem", 7),
    MONTH("mois", "mois", 4.35),
    YEAR("année", "a", 12);

    public final String textValue;
    public final String shortValue;
    public final double multiplicator;
    public final String value;

    /**
     * @param textValue
     * @param multiplicator
     */
    TimeUnit (String textValue, String shortValue, double multiplicator) {
        this.shortValue = shortValue;
        this.multiplicator = multiplicator;
        this.textValue = textValue;
        this.value = textValue;
    }

    public static TimeUnit fromValue (String value) {
        for (TimeUnit timeUnit : TimeUnit.values()) {
            if (timeUnit.value.equals(value)) {
                return timeUnit;
            }
        }

        throw new RuntimeException("Can't find corresponding TimeUnit enum from string");
    }

    public double calculateRatio (TimeUnit wantedTimeUnit) {
        List<TimeUnit> values = Arrays.asList(TimeUnit.values());
        int thisIndex = values.indexOf(this);
        int targetIndex = values.indexOf(wantedTimeUnit);

        double ratio = 1;

        if (thisIndex > targetIndex) {
            for (int i = thisIndex; i > targetIndex; i--) {
                ratio *= values.get(i).multiplicator;
            }
        }
        else if (thisIndex < targetIndex) {
            for (int i = thisIndex + 1; i <= targetIndex; i++) {
                ratio /= values.get(i).multiplicator;
            }
        }

        return ratio;
    }

    public double convertFrequencyTo (double nbUnits, TimeUnit wantedTimeUnit) {
        return wantedTimeUnit.convertTimeTo(nbUnits, this);
    }

    public double convertTimeTo (double nbUnits, TimeUnit wantedTimeUnit) {
        return nbUnits * this.calculateRatio(wantedTimeUnit);
    }

    @Override
    public String toString () {
        return this.value;
    }
}
