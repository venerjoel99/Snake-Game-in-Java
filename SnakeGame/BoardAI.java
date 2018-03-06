import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class BoardAI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BoardAI extends AI
{
    // instance variables - replace the example below with your own
    private Board game;
    private boolean reachable = true;
    private Node head;
    private Node tail;
    /**
     * Constructor for objects of class BoardAI
     */
    public BoardAI(Board game)
    {
        // initialise instance variables
        this.game = game;
    }
    private List<Node> makeGraph(){
        List<Node> graph = new ArrayList<Node>();
        for (int i = 0; i < game.gameBoardSize(); i++){
            for (int j = 0; j < game.gameBoardSize(); j++){
                Node n = new Node(i, j);
                if (!game.getSnake().snakePresent(n)){
                    graph.add(n);
                }
            }
        }
        Pixel source = game.getSnake().getPixels().get(0);
        this.head = new Node(source.getRow(), source.getCol());
        graph.add(head);
        return graph;
    }
    public List<Direction> navigate(){
        List<Node> graph = makeGraph();
        Node start = this.head;
        Node end = new Node(game.pelletRow(), game.pelletCol());
        List<Node> path = this.path(start, end, graph);
        reachable = (path.get(path.size()-1).equals(end));
        if (!reachable){
            int size = game.getSnake().getSize();
            Pixel tail = game.getSnake().getPixels().get(size - 1);
            int endX = tail.getRow();
            int endY = tail.getCol();
            end = new Node(endX, endY);
            path = this.path(start, end, graph);
        }
        List<Direction> result = new ArrayList<Direction>();
        while (path.size() > 1){
            Node n1 = path.get(0);
            Node n2 = path.get(1);
            int x = n2.getRow() - n1.getRow();
            int y = n2.getCol() - n1.getCol();
            if (x==0){
                result.add(y==1 ? Direction.RIGHT : Direction.LEFT);
            }
            else if (y==0){
                result.add(x==1 ? Direction.DOWN : Direction.UP);
            }
            path.remove(0);
        }
        return result;
    }
    public boolean canReach(){
        return this.reachable;
    }
}
