import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class AI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AI
{
    // instance variables - replace the example below with your own
    private Node start = null;
    private Node dest = null;
    private List<Node> graph = null;
    /**
     * Constructor for objects of class AI
     */
    public AI()
    {
        
    }
    private double length(Node a, Node b){
        double x = Math.pow(Math.abs(a.getRow() - b.getRow()), 2);
        double y = Math.pow(Math.abs(a.getCol() - b.getCol()), 2);
        return Math.sqrt(x + y);
    }    
    private Node minCost(){
        Node result = null;
        double min = -1.0;
        for (Node n : graph){
            if (!n.isVisited() && n.getCost() != -1){
                double f_cost = n.getCost()/* + length(dest, n)*/;
                if (min == -1.0 || f_cost < min){
                    min = f_cost;
                    result = n;
                }
            }
        }
        //System.out.println(result==null ? "Null" : "Not");
        return result;
    }
    private boolean exists(Node current, Node[] list){
        for (Node n : list){
            if (current.equals(n)){
                return true;
            }
        }
        return false;
    }
    private boolean emptyGraph(){
        for (Node n : this.graph){
            if (!n.isVisited()){
                return false;
            }
        }
        return true;
    }
    private List<Node> neighbors(Node current){
        Node up = new Node(current.getRow() - 1, current.getCol());
        Node down = new Node(current.getRow() + 1, current.getCol());
        Node left = new Node(current.getRow(), current.getCol() - 1);
        Node right = new Node(current.getRow(), current.getCol() + 1);
        List<Node> results = new ArrayList<Node>();
        Node tests[] = {up, down, left, right};
        for (Node n : this.graph){
            if (exists(n, tests)){
                results.add(n);
            }
        }
        return results;
    }
    private Node closest(){
        double min = -1.0;
        Node result = null;
        for (Node n : graph){
            double dist = length(n, dest);
            if (n.isVisited() && (min == -1.0 || dist < min)){
                result = n;
                min = dist;
            }
        }
        return result;
    }
    public List<Node> path(Node start, Node end, List<Node> graph){
        List<Node> result = new ArrayList<Node>();
        this.start = start;
        this.dest = end;
        this.graph = graph;
        Node current = this.start;
        for (Node n : this.graph){
            if (this.start.equals(n)){
                this.start = n;
                current = n;
                current.setCost(0);
                break;
            }
        }
        while(!emptyGraph() && !current.equals(this.dest)){
            current = minCost();
            if (current==null) break;
            for (Node n : neighbors(current)){
                int cost = current.getCost() + 1;
                if (n.getCost() == -1 || cost < n.getCost()){
                    n.setCost(cost);
                    n.setParent(current);
                    //System.out.print("Yes");
                }
            }
            current.visit();
        }
        if (current==null || !current.equals(this.dest)){
            current = closest();
        }
        while(!current.equals(this.start)){
            result.add(0, current);
            current = current.getParent();
        }
        result.add(0, start);
        return result;
    }
}
