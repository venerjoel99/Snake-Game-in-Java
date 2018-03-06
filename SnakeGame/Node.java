class Node extends Pixel{
    private int cost = -1;
    private Node parent = null;
    private boolean visited = false;
    public Node(int row, int col){
        super(row, col);
    }
    public void setParent(Node n){
        this.parent = n;
    }
    public Node getParent(){
        return this.parent;
    }
    public void visit(){
        this.visited = true;
    }
    public boolean isVisited(){
        return this.visited;
    }
    public void setCost(int cost){
        this.cost = cost;
    }
    public int getCost(){
        return this.cost;
    }
}