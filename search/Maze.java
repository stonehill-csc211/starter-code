package search;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Maze {

    Square[][] squares;
    Square start, end;
    
    private class Square{
        int x;
        int y;
        boolean wall;

        private Square(){
            this.x = -1;
            this.y = -1;
            this.wall = false;
        }

        private Square(int x, int y, boolean wall){
            this.x = x;
            this.y = y;
            this.wall = wall;
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

    public Square[] solve(){
        /*
         * Use a search algorithm to solve the maze
         * Returns an array of squares from start to end
         * O( )
         */

        // TODO

        return null;
    }

    public static void main(String[] args){
        try{
            Maze m = new Maze("/Users/sgoree/csc_211_repos/csc211_prep/search/maze1.txt");
            System.out.println(m);
        } catch(FileNotFoundException e){
            System.out.println(e);
        }
    }
}
