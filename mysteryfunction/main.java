
public class main{

    public static int largestN(int k, MysteryFunction f){
        /*
         * Returns the largest integer value of N such that f.call(N) < k
         */
        // TODO
        return 0;
    }

    public static void main(String[] args){
        System.out.println("Testing on MysteryFunction1:");
        MysteryFunction1 f = new MysteryFunction1();
        int k = 27;
        int n = largestN(k, f);
        System.out.println("Output: " + n);
    }
}