package simulator;

import simulator.lib.QueueingException;

public class Simulation
{
    public static final Simulation instance = new Simulation();

    // parameters
    public double LAMBDA;
    public double MU;
    public int K;
    public int S;

    private double Q0;
    private double L;
    private double Lq;
    private double W;
    private double Wq;
    private int bottleNeck;

    private Solver solver;

    private Simulation () {
    }

    private Solver guessSolverInstance () {
        if (K == 0) {
            this.solver = new MMS();
        }
        else if (S == 1) {
            this.solver = new MM1K();
        }
        else {
            throw new QueueingException("Multiple servers with limited queue's size has not been implemented yet in this simulator");
        }

        this.solver.setSimuation(this);
        return this.solver;
    }

    public Solver getSolver () {
        return solver == null ? guessSolverInstance() : solver;
    }

    public void solve () {
        Solver solv = getSolver();

        Q0 = solv.Q0();
        L = solv.L();
        Lq = solv.Lq();
        W = solv.W();
        Wq = solv.Wq();

        try {
            bottleNeck = solv.bottleNeck();
        }
        catch (QueueingException e) {
            bottleNeck = -1;
        }
    }
}
