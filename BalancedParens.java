public class BalancedParens {
    public static boolean balancedParens(String s){
        int depth = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') depth++;
            else if(s.charAt(i) == ')') depth--;
            if(depth < 0) return false;
        }
        return (depth == 0);
    }

    public static void main(String[] args) {
        String test1 = "(()())";
        String test2 = "(()()))";
        String test3 = ")()(";
        String test4 = "(hello)";
        System.out.println(balancedParens(test1));
        System.out.println(balancedParens(test2));
        System.out.println(balancedParens(test3));
        System.out.println(balancedParens(test4));
    }
}
