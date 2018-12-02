package simulator;

import simulator.lib.exception.QueueingException;
import simulator.lib.exception.enums.QueueingExceptionType;

public class MM1K extends WaitingQueueSolver {

    double calcRho (double lambda, double mu) {
        return lambda/mu;
    }

    double calcQ0 (double lambda, double mu, int K){
        double rho = this.calcRho(lambda, mu);
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
    public double rho () {
        return calcRho(this.convertLambdaToMuTimeUnit(), simulation.mu());
    }

    @Override
    public double Q0 () {
        return calcQ0(this.convertLambdaToMuTimeUnit(), simulation.mu(), simulation.k());
    }

    @Override
    public double L () {
        return calcL(this.convertLambdaToMuTimeUnit(), simulation.mu(), simulation.k());
    }

    @Override
    public double Lq () {
        return calcLq(this.convertLambdaToMuTimeUnit(), simulation.mu(), simulation.k());
    }

    @Override
    public double W () {
        return calcW(this.convertLambdaToMuTimeUnit(), simulation.mu(), simulation.k());
    }

    @Override
    public double Wq () {
        return calcWq(this.convertLambdaToMuTimeUnit(), simulation.mu(), simulation.k());
    }

    @Override
    public int bottleNeck () {
        throw new QueueingException("No bottleneck possibility with this kind of queues.", QueueingExceptionType.NO_POSSIBLE_BOTTLENECK);
    }
}
