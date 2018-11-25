package simulator;

public class MainFormula {
    public static void main(String[] args) {
        MMS mms = new MMS();
        double lq = mms.calcLq(4,0.8,6);
        System.out.println(lq);
    }
}
