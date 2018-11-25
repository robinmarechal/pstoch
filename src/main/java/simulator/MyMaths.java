package simulator;

public class MyMaths
{
    public static int facto (int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be a positive number");
        }

        return n == 0 || n == 1 ? 1 : n * facto(n - 1);
    }
}
