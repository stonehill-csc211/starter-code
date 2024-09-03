public class MysteryFunction2 implements MysteryFunction{
    public MysteryFunction2(){}
    public double call(int n){
        return Math.pow(n,3) + Math.sin(n);
    }
}