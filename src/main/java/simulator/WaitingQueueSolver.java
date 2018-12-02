package simulator;

abstract public class WaitingQueueSolver implements Solver
{
    protected Simulation simulation;

    @Override
    public void setSimuation (Simulation simulation) {
        this.simulation = simulation;
    }

    public double convertLambdaToMuTimeUnit () {
        return simulation.getLambdaTimeUnit().convertFrequencyTo(simulation.lambda(), simulation.getMuTimeUnit());
    }
}
