public interface Solver
{
    void setSimuation(Simulation simulation);

    double Q0();
    double L();
    double Lq();
    double W();
    double Wq();
    int engorgement();
}
