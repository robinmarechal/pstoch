public class Simulation
{
    // parameters
    public double LAMBDA;
    public double MU;
    public int K;
    public int S;

    private Solver solver;

    private Solver guessSolverInstance(){
        return solver;
    }

    public Solver getSolver () {
        return solver == null ? guessSolverInstance() : solver;
    }
}
