
public class main{

    public static int largestN(int k, MysteryFunction f){
        /*
         * Returns the largest integer value of N such that f.call(N) < k
         */
        long upperBound = 1;
        while(f.call((int)upperBound) < k){
            upperBound*=2;
        }
        long lowerBound = upperBound / 2;
        while( upperBound - lowerBound > 1){
            System.out.println(lowerBound + " " + upperBound);
            long middle = (lowerBound + upperBound) / 2;
            if(f.call((int)middle) < k){
                lowerBound = middle;
            } else if(f.call((int)middle) == k){
                break;
            } else {
                upperBound = middle;
            }
        }
        return (int)lowerBound;
    }

    public static void main(String[] args){
        System.out.println("Testing on MysteryFunction1:");
        MysteryFunction1 f = new MysteryFunction1();
        int k = 27;
        int n = largestN(k, f);
        System.out.println("Output: " + n);

        System.out.println("Testing on MysteryFunction3:");
        MysteryFunction3 g = new MysteryFunction3();
        int k_3 = 10000;
        int n_3 = largestN(k_3, g);
        System.out.println("Output: " + n_3);
    }
}