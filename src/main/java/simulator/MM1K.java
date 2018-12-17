package simulator;

import simulator.lib.exception.QueueingException;
import simulator.lib.exception.enums.QueueingExceptionType;

public class MM1K extends WaitingQueueSolver {

    double calcRho (double lambda, double mu) {
        return lambda/mu;
    }

    double calcQ0 (double lambda, double mu, int K){
        return calcQi(lambda,mu,K,0);
    }

    double calcQi (double lambda, double mu, int K, int i){
        double rho = this.calcRho(lambda, mu);
        if(rho == 1){
            return 1/(K+1);
        }
        else{
            return (1-rho)*Math.pow(rho,i)/(1-Math.pow(rho,K+1));
        }
    }

    double calcLq (double lambda, double mu, int K) {
        double L = calcL(lambda,mu,K);
        double q0 = calcQ0(lambda,mu,K);
        return L - (1-q0);
    }

    double calcL (double lambda, double mu, int K) {
        double rho = this.calcRho(lambda, mu);
        if(rho == 1) {
            return K/2;
        }
        else {
            return rho*(1-(K+1)*Math.pow(rho,K)+K*Math.pow(rho,K+1))/((1-rho)*(1-Math.pow(rho,K+1)));
        }
    }

    double calcWq (double lambda, double mu, int K) {
        double lq = calcLq(lambda, mu, K);
        double Wq = lq / (lambda*(1-calcQi(lambda,mu,K,K)));
        return Wq;
    }

    double calcW (double lambda, double mu, int K) {
        double l = calcL(lambda,mu,K);
        double W = l/(lambda*(1-calcQi(lambda,mu,K,K)));
        return W;
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
