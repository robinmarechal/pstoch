package simulator;

import simulator.lib.QueueingException;

public class MM1K implements Solver {

    private Simulation simulation;

    public double calcQ0 (double lambda, double mu, int K){
        double rho = lambda/mu;
        if(rho == 1){
            return 1/(K-1);
        }
        else{
            return (1-rho)/(1-Math.pow(rho,K+1));
        }
    }

    double calcLq (double lambda, double mu, int K) {
        return 0;
    }

    double calcL (double lambda, double mu, int K) {
        return 0;
    }

    double calcWq (double lambda, double mu, int K) {
        return 0;
    }

    double calcW (double lambda, double mu, int K) {
        return 0;
    }

    @Override
    public void setSimuation (Simulation simulation) {
        this.simulation= simulation;
    }

    @Override
    public double Q0 () {
        return this.calcQ0(simulation.LAMBDA, simulation.MU, simulation.K);
    }

    @Override
    public double L () {
        return this.calcL(simulation.LAMBDA, simulation.MU, simulation.K);
    }

    @Override
    public double Lq () {
        return this.calcLq(simulation.LAMBDA, simulation.MU, simulation.K);
    }

    @Override
    public double W () {
        return this.calcW(simulation.LAMBDA, simulation.MU, simulation.K);
    }

    @Override
    public double Wq () {
        return this.calcWq(simulation.LAMBDA, simulation.MU, simulation.K);
    }

    @Override
    public int bottleNeck () {
        throw new QueueingException("No bottleneck possibility with this kind of queues.");
    }
}
