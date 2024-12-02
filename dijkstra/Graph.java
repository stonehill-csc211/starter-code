import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Graph{
    String[] nodes;
    int[][] edges;

    private class Record implements Comparable<Record>{
        int node;
        int distance;
        int previous;

        public Record(int node, int distance, int previous){
            this.node = node;
            this.distance = distance;
            this.previous = previous;
        }

        public int compareTo(Record other){
            return this.distance - other.distance;
        }
    }

    public Object[] shortestPath(String start, String end){
        // create an empty distance table
        Record[] table = new Record[nodes.length];
        // create a min-priority queue
        PriorityQueue<Record> queue = new PriorityQueue<>();
        // enqueue start with priority 0
        int s = -1;
        int e = -1;
        for(int i = 0; i < nodes.length; i++){
            if(nodes[i].equals(start)) s = i;
            if(nodes[i].equals(end)) e = i;
        }
        if(s == -1){
            System.out.println("Start node not found!");
            return null;
        }
        if(e == -1){
            System.out.println("End node not found!");
            return null;
        }
        queue.add(new Record(s, 0, -1));

        Record current;
        Record neighbor;
        int currentDistance,currentNode;
        // while the queue is not empty
        while(!queue.isEmpty()){
            // dequeue
            current = queue.poll();
            // if we've found the end, return something
            if(current.node == e){
                LinkedList<String> path = new LinkedList<>();
                // backtrack to the start
                while(current.node != s){
                    path.addFirst(nodes[current.node]);
                    current = table[current.previous];
                }
                return path.toArray();
                
            }
            // figure out this node's distance
            currentDistance = current.distance;
            currentNode = current.node;
            // put it in the table
            table[currentNode] = current;
            // enqueue its neighbors
            for(int i = 0; i < edges[currentNode].length; i++){
                if(edges[currentNode][i] == 0) continue;
                if(i == current.previous) continue;
                neighbor = new Record(i, currentDistance + edges[currentNode][i], currentNode);
                queue.add(neighbor);
            }
        }
        return null;
    }

    public Graph(String path) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(path));
        String head = sc.nextLine();
        nodes = head.substring(1).split(",");
        String line;
        String[] data;
        edges = new int[nodes.length][nodes.length];
        int i = 0;
        while(sc.hasNextLine()){
            line = sc.nextLine();
            data = line.split(",");
            for(int j = 0; j < edges[i].length; j++){
                edges[i][j] = Integer.parseInt(data[j+1]);
            }
            i++;
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(",");
        sb.append(String.join(",", this.nodes));
        for(int i = 0; i < this.edges.length; i++){
            sb.append(this.nodes[i]);
            for(int j = 0; j < this.edges[i].length; j++){
                sb.append("," + Integer.toString(this.edges[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args){
        Graph g;
        try {
            g = new Graph("ma_map.csv");
            Object[] path = g.shortestPath("Sharon", "Taunton");
            for(Object p : path){
                System.out.println(p);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}