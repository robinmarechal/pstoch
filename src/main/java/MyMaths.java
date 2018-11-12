public class MyMaths {
    public static int facto(int n){
        int facto = 0;
        for(int i=1; i<n; i++){
            facto = facto*i;
        }
        return facto;
    }
}
