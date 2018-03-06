import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Snake here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Snake
{
    // instance variables - replace the example below with your own
    private List<Pixel> snakePixels;
    private int growPixels = 0;
    private int size = 0;
    private boolean collision = false;
    /**
     * Constructor for objects of class Snake
     */
    public Snake(int startX, int startY)
    {
        // initialise instance variables
        this.snakePixels = new ArrayList<Pixel>();
        snakePixels.add(0, new Pixel(startX, startY));
        this.size++;
    }
    public boolean snakePresent(Pixel pixel){
        for(Pixel part : this.snakePixels){
            if (part.equals(pixel)){
                return true;
            }
        }
        return false;
    }
    /**
     * Move the snake one pixel in a certain direction
     * @return whether the snake successfully moved
     * and game is not over
     */
    public Pixel move(Direction direction){
        Pixel currentHead = snakePixels.get(0);
        int newX = currentHead.getCol();
        int newY = currentHead.getRow();
        switch (direction){
            case LEFT:
                //System.out.println("L");
                newX--;
                break;
            case RIGHT:
                //System.out.println("R");
                newX++;
                break;
            case UP:
                //System.out.println("U");
                newY--;
                break;
            case DOWN:
                //System.out.println("D");
                newY++;
                break;
            default:
                return new Pixel(newY, newX);
            }
        Pixel newHead = new Pixel(newY, newX);
        this.collision = this.snakePresent(newHead);
        snakePixels.add(0, newHead);
        if(growPixels>0){
             growPixels--;
             size++;
        }
        else if (snakePixels.size()>1){
            snakePixels.remove(snakePixels.size()-1);
        }
        return snakePixels.get(0);
    }
    public void grow(int num){
        this.growPixels += num;
    }
    public List<Pixel> getPixels(){
        return this.snakePixels;
    }
    public int getSize(){
        return this.size;
    }
    public boolean collided(){
        return this.collision;
    }
}
