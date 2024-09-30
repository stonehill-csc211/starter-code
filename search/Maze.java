package search;

import java.util.LinkedList;
import java.util.Scanner;

import DataStructuresLib.ArrayStack;
import DataStructuresLib.LinkedQueue;

import java.io.File;
import java.io.FileNotFoundException;

public class Maze {

    Square[][] squares;
    Square start, end;
    
    private class Square{
        int x;
        int y;
        boolean wall;
        Square previous;

        private Square(){
            this.x = -1;
            this.y = -1;
            this.wall = false;
            this.previous = null;
        }

        private Square(int x, int y, boolean wall){
            this.x = x;
            this.y = y;
            this.wall = wall;
            this.previous = null;
        }

        public String toString(){
            return "Square(" + this.x + " " + this.y + ")";
        }
    }
    public Maze(String filename) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(filename));
        int length = 1;
        int width = (sc.nextLine().length() / 2) + 1;
        while(sc.hasNextLine()){sc.nextLine();length++;}
        sc.close();
        sc = new Scanner(new File(filename));
        
        this.squares = new Square[length][width];
        int i = 0;
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            String[] cells = line.split(" ");
            for(int j = 0; j < cells.length; j++){
                if(cells[j].equals("s")){
                    this.squares[i][j] = new Square(i,j, false);
                    this.start = this.squares[i][j];
                } else if(cells[j].equals("e")){
                    this.squares[i][j] = new Square(i,j, false);
                    this.end = this.squares[i][j];
                } else {
                    boolean wall = (cells[j].equals("1"));
                    this.squares[i][j] = new Square(i,j, wall);
                }
            }
            i++;
        }
        sc.close();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < this.squares.length; i++){

            for(int j = 0; j < this.squares[i].length; j++){
                if(this.squares[i][j] == this.start) {sb.append("s");}
                else if(this.squares[i][j] == this.end) {sb.append("e");}
                else {sb.append(this.squares[i][j].wall ? "1" : "0");}
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    public Square[] solveBFS(){
        /*
         * Use a search algorithm to solve the maze
         * Returns an array of squares from start to end
         * This solution is DFS using a stack. Try it with a queue!
         * O( )
         */
        LinkedQueue<Square> queue = new LinkedQueue<Square>();
        queue.enqueue(this.start);
        Square current, up, left, right, down;
        while(queue.size() > 0){
            System.out.println(queue);
            current = queue.dequeue();
            // explore up
            up = this.squares[current.x-1][current.y];
            if(!up.wall && up.previous == null){
                up.previous = current;
                queue.enqueue(up);
            }
            down = this.squares[current.x+1][current.y];
            if(!down.wall && down.previous == null){
                down.previous = current;
                queue.enqueue(down);
            }
            left = this.squares[current.x][current.y-1];
            if(!left.wall && left.previous == null){
                left.previous = current;
                queue.enqueue(left);
            }
            right = this.squares[current.x][current.y+1];
            if(!right.wall && right.previous == null){
                right.previous = current;
                queue.enqueue(right);
            }
            // if we've found the end return something probably
            if(current.equals(this.end)){
                LinkedList<Square> solution = new LinkedList<Square>();
                while(!current.equals(this.start)){
                    solution.addFirst(current);
                    current = current.previous;
                }
                Square[] arr = new Square[solution.size()];
                for(int i = 0; i < arr.length; i++){
                    arr[i] = (Square)solution.get(i);
                }
                return arr;
            }
        }
        return null;
    }

    public Square[] solveDFS(){
        /*
         * Use a search algorithm to solve the maze
         * Returns an array of squares from start to end
         * This solution is DFS using a stack. Try it with a queue!
         * O( )
         */
        ArrayStack<Square> stack = new ArrayStack<Square>();
        stack.push(this.start);
        Square current, up, left, right, down;
        while(stack.size() > 0){
            System.out.println(stack);
            current = stack.pop();
            // explore up
            up = this.squares[current.x-1][current.y];
            if(!up.wall && up.previous == null){
                up.previous = current;
                stack.push(up);
            }
            down = this.squares[current.x+1][current.y];
            if(!down.wall && down.previous == null){
                down.previous = current;
                stack.push(down);
            }
            left = this.squares[current.x][current.y-1];
            if(!left.wall && left.previous == null){
                left.previous = current;
                stack.push(left);
            }
            right = this.squares[current.x][current.y+1];
            if(!right.wall && right.previous == null){
                right.previous = current;
                stack.push(right);
            }
            // if we've found the end return something probably
            if(current.equals(this.end)){
                LinkedList<Square> solution = new LinkedList<Square>();
                while(!current.equals(this.start)){
                    solution.addFirst(current);
                    current = current.previous;
                }
                Square[] arr = new Square[solution.size()];
                for(int i = 0; i < arr.length; i++){
                    arr[i] = (Square)solution.get(i);
                }
                return arr;
            }
        }
        return null;
    }

    public static void main(String[] args){
        try{
            Maze m = new Maze("C:\\Users\\sgoree\\csc211\\starter-code\\search\\maze1.txt");
            Square[] solution = m.solveBFS();
            System.out.println("Solution:");
            for(int i = 0; i < solution.length; i++){
                System.out.println(solution[i]);
            }
        } catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
}
