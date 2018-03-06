import javax.swing.JOptionPane;
import java.util.List;
/**
 * Write a description of class AIGUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class AIGUI extends SnakeGUI
{
    // instance variables - replace the example below with your own
    private int x;
    private int waitTime = 50;
    /**
     * Constructor for objects of class AIGUI
     */
    public AIGUI(int numRow)
    {
        super(numRow);
    }
    public void display(){
        newGame();
        setVisible(true);
        boolean moving = true;
        while(isVisible()){
            BoardAI ai = new BoardAI(game);
            List<Direction> directions = null;
            game.changeDirection(Direction.NONE);
            while(moving){
                if (directions==null || directions.size() == 0 || !ai.canReach()){
                    directions = ai.navigate();
                }
                if (directions.size() > 0){
                    game.setDirection(directions.remove(0));
                }
                moving = game.move();
                repaint();
                try{
                    Thread.sleep(waitTime);
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
            }
            JOptionPane.showMessageDialog(null,"Score: " + game.getSnake().getSize());
            while (!moving){
                moving = game.move();
                try{
                    Thread.sleep(waitTime);
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
            }
        }
    }
}
