package simulator;

public interface Solver
{
    void setSimuation(Simulation simulation);

    double rho();
    double Q0();
    double L();
    double Lq();
    double W();
    double Wq();
    int bottleNeck();
}
