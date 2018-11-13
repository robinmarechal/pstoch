public class MM1K {

    public double Q0(double lambda, double mu, int K){
        double rho = lambda/mu;
        if(rho == 1){
            return 1/(K-1);
        }
        else{
            return (1-rho)/(1-Math.pow(rho,K+1));
        }
    }
}
