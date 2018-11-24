public class MMS {

    public double Q0(double lambda, double mu, int S) {
        double rho = lambda/(S*mu);
        double somme = 0;
        for(int i=0; i <= S-1;i++){
            somme += Math.pow(rho*S,i)/MyMaths.facto(i);
        }
        return 1/(somme + (Math.pow(rho*S,S))/(MyMaths.facto(S)*(1-rho)));
    }

    public double Lq(double lambda, double mu, int S){
        double rho = lambda/(S*mu);
        double q0 = Q0(lambda, mu, S);
        return q0*((Math.pow(rho*S,S)*rho)/(MyMaths.facto(S)*Math.pow(1-rho,2)));
    }

    public double L(double lambda, double mu, int S){
        double lq = Lq(lambda,mu,S);
        return lq+(lambda/mu);
    }

    public double Wq(double lambda, double mu, int S){
        double lq = Lq(lambda,mu,S);
        return lq/lambda;
    }

    public double W(double lambda, double mu, int S){
        double wq = Wq(lambda,mu,S);
        return wq + (1/mu);
    }

    public int engorgement(double lambda, double mu){
        int i = 1;
        while(lambda/(i*mu)>1)
            i++;
        return i;
    }
}
