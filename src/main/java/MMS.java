public class MMS {

    public double P0(double lambda, double mu, int S) {
        double rho = lambda/(S*mu);
        double somme = 0;
        for(int i=0; i <S-1;i++){
            somme += Math.pow(rho,i)/ MyMaths.facto(i);
        }
        return 1/(somme + (Math.pow(rho,S)/ MyMaths.facto(S))*(1/(1-rho/S)));
    }

    public double Pa(double lambda, double mu, int S){
        double rho = lambda/(S*mu);
        double P0 = P0(lambda, mu, S);
        return P0*(Math.pow(rho,S)/(MyMaths.facto(S-1)*(S-rho)));
    }

    public double N(double lambda, double mu, int S){
        double Pa = Pa(lambda, mu, S);
        double rho = lambda/(S*mu);
        return rho*(1+(Pa/(S-rho)));
    }

    public double Na(double lambda, double mu, int S){
        double Pa = Pa(lambda, mu, S);
        double rho = lambda/(S*mu);
        return rho*(Pa/(S-rho));
    }

    public double T(double lambda, double mu, int S){
        double Pa = Pa(lambda, mu, S);
        double rho = lambda/(S*mu);
        return (1/mu)*(1+(Pa/(S-rho)));
    }

    public double Ta(double lambda, double mu, int S){
        double Pa = Pa(lambda, mu, S);
        double rho = lambda/(S*mu);
        return Pa/(mu*(S-rho));
    }

    public boolean engorgement(double lambda, double mu, int S){
        if(lambda/(S*mu) < 1)
            return true;
        else return false;
    }
}
