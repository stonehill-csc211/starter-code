
import DataStructuresLib.ArrayStack;

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

    public static boolean balancedParens2(String s){
        // make a stack
        ArrayStack<String> stack = new ArrayStack<String>();
        String open = "([{\"<";
        String closed = ")]}\">";
        String current, popped;
        for(int i = 0; i < s.length(); i++){
            // get the current character
            current = s.substring(i,i+1);
            //System.out.println(i +" "+  current +" " + stack);
            // if it's an open bracket, push
            if(open.contains(current)){
                stack.push(current);
            // if it's a closed bracket, pop and compare
            } else if(closed.contains(current)){
                try{
                    popped = stack.pop();
                } catch(ArrayIndexOutOfBoundsException e){
                    return false;
                }
                // get the current bracket type
                int bracketType = closed.indexOf(current);
                // if we DON'T have the opposite bracket at the top of the stack
                if(!popped.equals(open.substring(bracketType, bracketType+1))){
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        String test1 = "(()())";
        String test2 = "(()()))";
        String test3 = ")()(";
        String test4 = "(hello)";
        String test5 = "<()()>";
        String test6 = "[()}";
        String test7 = "(\")";
        System.out.println(test1 + " " + balancedParens2(test1));
        System.out.println(test2 + " " + balancedParens2(test2));
        System.out.println(test3 + " " + balancedParens2(test3));
        System.out.println(test4 + " " + balancedParens2(test4));
        System.out.println(test5 + " " + balancedParens2(test5));
        System.out.println(test6 + " " + balancedParens2(test6));
        System.out.println(test7 + " " + balancedParens2(test7));
    }
}
