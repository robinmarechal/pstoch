package simulator;

public class MMS implements Solver
{

    private Simulation simulation;

    double calcQ0 (double lambda, double mu, int S) {
        double rho = lambda / (S * mu);
        double somme = 0;
        for (int i = 0; i <= S - 1; i++) {
            somme += Math.pow(rho * S, i) / MyMaths.facto(i);
        }
        return 1 / (somme + (Math.pow(rho * S, S)) / (MyMaths.facto(S) * (1 - rho)));
    }

    double calcLq (double lambda, double mu, int S) {
        double rho = lambda / (S * mu);
        double q0 = calcQ0(lambda, mu, S);
        return q0 * ((Math.pow(rho * S, S) * rho) / (MyMaths.facto(S) * Math.pow(1 - rho, 2)));
    }

    double calcL (double lambda, double mu, int S) {
        double lq = calcLq(lambda, mu, S);
        return lq + (lambda / mu);
    }

    double calcWq (double lambda, double mu, int S) {
        double lq = calcLq(lambda, mu, S);
        return lq / lambda;
    }

    double calcW (double lambda, double mu, int S) {
        double wq = calcWq(lambda, mu, S);
        return wq + (1 / mu);
    }

    int calcBottleNeck (double lambda, double mu) {
        int i = 1;
        while (lambda / (i * mu) > 1) {
            i++;
        }
        return i;
    }

    @Override
    public void setSimuation (Simulation simulation) {
        this.simulation = simulation;
    }

    @Override
    public double Q0 () {
        return calcQ0(simulation.LAMBDA, simulation.MU, simulation.S);
    }

    @Override
    public double L () {
        return calcL(simulation.LAMBDA, simulation.MU, simulation.S);
    }

    @Override
    public double Lq () {
        return calcLq(simulation.LAMBDA, simulation.MU, simulation.S);
    }

    @Override
    public double W () {
        return calcW(simulation.LAMBDA, simulation.MU, simulation.S);
    }

    @Override
    public double Wq () {
        return calcWq(simulation.LAMBDA, simulation.MU, simulation.S);
    }

    @Override
    public int bottleNeck () {
        return calcBottleNeck(simulation.LAMBDA, simulation.MU);
    }
}
