package simulator;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import simulator.lib.TimeUnit;
import simulator.lib.exception.NotImplementedException;

public class Simulation
{
    public static final TimeUnit DEFAULT_LAMBDA_TIME_UNIT = TimeUnit.HOUR;
    public static final TimeUnit DEFAULT_MU_TIME_UNIT = TimeUnit.HOUR;

    // parameters
    private DoubleProperty LAMBDA = new SimpleDoubleProperty(this, "LAMBDA", 1);
    private DoubleProperty MU = new SimpleDoubleProperty(this, "MU", 1);
    private IntegerProperty K = new SimpleIntegerProperty(this, "K", 0);
    private IntegerProperty S = new SimpleIntegerProperty(this, "S", 1);

    private TimeUnit lambdaTimeUnit = DEFAULT_LAMBDA_TIME_UNIT;
    private TimeUnit muTimeUnit = DEFAULT_MU_TIME_UNIT;

    private double Q0;
    private double L;
    private double Lq;
    private double W;
    private double Wq;
    private int bottleNeck;

    private Solver solver;

    public static final Simulation instance = new Simulation();

    private Simulation () {
    }

    public Solver guessSolverInstance () {
        int kValue = K.intValue();
        int sValue = S.intValue();

        if (kValue == 0) {
            this.solver = new MMS();
        }
        else if (sValue == 1) {
            this.solver = new MM1K();
        }
        else {
            throw new NotImplementedException("Multiple servers with limited queue's size has not been implemented yet in this simulator");
        }

        this.solver.setSimuation(this);
        return this.solver;
    }

    public Solver getSolver () {
        return solver == null ? guessSolverInstance() : solver;
    }

    public double lambda () {
        return LAMBDA.doubleValue();
    }

    public double mu () {
        return MU.doubleValue();
    }

    public int s () {
        return S.intValue();
    }

    public int k () {
        return K.intValue();
    }

    public DoubleProperty lambdaProperty () {
        return this.LAMBDA;
    }

    public DoubleProperty muProperty () {
        return this.MU;
    }

    public IntegerProperty kProperty () {
        return this.K;
    }

    public IntegerProperty sProperty () {
        return this.S;
    }

    public TimeUnit getLambdaTimeUnit () {
        return lambdaTimeUnit;
    }

    public TimeUnit getMuTimeUnit () {
        return muTimeUnit;
    }

    public void setMuTimeUnit (TimeUnit muTimeUnit) {
        this.muTimeUnit = muTimeUnit;
    }

    public void setLambdaTimeUnit (TimeUnit lambdaTimeUnit) {
        this.lambdaTimeUnit = lambdaTimeUnit;
    }

    public double convertMuTimeUnit (TimeUnit wantedTimeUnit) {
        double newValue = muTimeUnit.convertFrequencyTo(mu(), wantedTimeUnit);
        muProperty().setValue(newValue);
        return newValue;
    }
}
