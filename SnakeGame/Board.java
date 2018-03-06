import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board
{
    // instance variables - replace the example below with your own
    private Pixel pellet;
    private Snake snake;
    private int gameBoardSize = 50;
    private boolean gameOver = false;
    private int score = 0;
    private boolean snakePresent[][];
    //Direction of the snake movement
    private Direction direction = Direction.NONE;
    private List<Direction> queue;
    /**
     * Constructor for objects of class Snake
     */
    public Board(int boardSize)
    {
        // initialise instance variables
        this.gameBoardSize = boardSize;
        int startX = (int)(Math.random()*gameBoardSize);
        int startY = (int)(Math.random()*gameBoardSize);
        this.snake = new Snake(startX, startY);
        this.pellet = new Pixel((int)(Math.random()*gameBoardSize),
                            (int)(Math.random()*gameBoardSize));  
        this.snakePresent = new boolean[boardSize][boardSize];
        this.queue = new ArrayList<Direction>();
    }
    /**
     * Move the snake one pixel in a certain direction
     * @return whether the snake successfully moved
     * and game is not over
     */
    public boolean move(){
        if (queue.size() > 0){
            direction = queue.remove(0);
        }
        Pixel head = snake.move(direction);
        if (wall(head) || 
            snake.collided()){
                gameOver = true;
                direction = Direction.NONE;
                return false;
            }
        else if (head.equals(pellet)){
            snake.grow(3);
            this.score+=3;
            do{
                pellet.setRow((int)(Math.random()*gameBoardSize));
                pellet.setCol((int)(Math.random()*gameBoardSize));
            }while (snake.snakePresent(pellet));
        }
        for(int i = 0; i < gameBoardSize; i++){
            for(int j = 0; j < gameBoardSize; j++){
                snakePresent[i][j]=false;
            }
        }
        for(Pixel part : this.snake.getPixels()){
            snakePresent[part.getRow()][part.getCol()]=true;
        }
        return true;
    }
    /**
     * Returns whether or not the pixel location
     * is beyond game boundaries
     */
    private boolean wall(Pixel pixel){
        return (pixel.getRow() < 0 || pixel.getCol() < 0 || 
                    pixel.getRow() >= gameBoardSize ||
                    pixel.getCol() >= gameBoardSize);
    }
    /**
     * Returns the row of the pellet
     */
    public int pelletRow(){
        return pellet.getRow();
    }
    /**
     * Returns the column of the pellet
     */
    public int pelletCol(){
        return pellet.getCol();
    }
    /**
     * Return the number of rows/columns the game board has
     */
    public int gameBoardSize(){
        return gameBoardSize;
    }
    public Snake getSnake(){
        return this.snake;
    }
    /**
     * Change the direction of the snake
     */
    public void changeDirection(Direction direction){
        this.queue.add(direction);
    }
    public void setDirection(Direction direction){
        this.direction = direction;
    }
    /**
     * Return the current direction of the snake
     */
    public Direction getDirection(){
        return this.direction;
    }
    public int getScore(){
        return this.score;
    }
    public boolean[][] getPresence(){
        return this.snakePresent;
    }
}
