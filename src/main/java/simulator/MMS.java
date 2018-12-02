package simulator;

import simulator.lib.exception.QueueingException;
import simulator.lib.exception.enums.QueueingExceptionType;

public class MMS extends WaitingQueueSolver
{
    private double calcRho (double lambda, double mu, int S) {
        return lambda / (S * mu);
    }

    double calcQ0 (double lambda, double mu, int S) {
        double rho = this.calcRho(lambda, mu, S);
        if (rho >= 1) {
            throw new QueueingException("lambda can't be greater or equal to s*mu", QueueingExceptionType.RHO_GT_1);
        }

        double somme = 0;
        for (int i = 0; i <= S - 1; i++) {
            somme += Math.pow(rho * S, i) / MyMaths.facto(i);
        }
        double q0 = 1 / (somme + (Math.pow(rho * S, S)) / (MyMaths.facto(S) * (1 - rho)));

        return q0;
    }

    double calcLq (double lambda, double mu, int S) {
        double rho = this.calcRho(lambda, mu, S);
        double q0 = calcQ0(lambda, mu, S);
        double Lq = q0 * ((Math.pow(rho * S, S) * rho) / (MyMaths.facto(S) * Math.pow(1 - rho, 2)));

        return Lq;
    }

    double calcL (double lambda, double mu, int S) {
        double lq = calcLq(lambda, mu, S);
        double L = lq + (lambda / mu);
        return L;
    }

    double calcWq (double lambda, double mu, int S) {
        double lq = calcLq(lambda, mu, S);
        double Wq = lq / lambda;
        return Wq;
    }

    double calcW (double lambda, double mu, int S) {
        double wq = calcWq(lambda, mu, S);
        double W = wq + (1 / mu);
        return W;
    }

    int calcBottleNeck (double lambda, double mu) {
        int s = 1;
        while (calcRho(lambda, mu, s) >= 1) {
            s++;
        }
        return s;
    }

    @Override
    public double rho () {
        return calcRho(this.convertLambdaToMuTimeUnit(), simulation.mu(), simulation.s());
    }

    @Override
    public double Q0 () {
        return calcQ0(this.convertLambdaToMuTimeUnit(), simulation.mu(), simulation.s());
    }

    @Override
    public double L () {
        return calcL(this.convertLambdaToMuTimeUnit(), simulation.mu(), simulation.s());
    }

    @Override
    public double Lq () {
        return calcLq(this.convertLambdaToMuTimeUnit(), simulation.mu(), simulation.s());
    }

    @Override
    public double W () {
        return calcW(this.convertLambdaToMuTimeUnit(), simulation.mu(), simulation.s());
    }

    @Override
    public double Wq () {
        return calcWq(this.convertLambdaToMuTimeUnit(), simulation.mu(), simulation.s());
    }

    @Override
    public int bottleNeck () {
        return calcBottleNeck(this.convertLambdaToMuTimeUnit(), simulation.mu());
    }
}
