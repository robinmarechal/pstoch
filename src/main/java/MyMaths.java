public class MyMaths {
    public static int facto(int n){
        int facto = 1;
        if(n == 0)
            return 1;
        for(int i=1; i<n; i++){
            facto = facto*i;
        }
        return facto;
    }
}
