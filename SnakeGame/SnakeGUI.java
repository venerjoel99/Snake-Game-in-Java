import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Container;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.util.List;
/**
 * Write a description of class SnakeGUI here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeGUI extends JFrame implements KeyListener, MouseListener
{
    private final int windowLength = 1500;
    private int waitTime = 50;
    protected Board game = null;
    private JPanel[][] pixels = null;
    private Container pane = null;
    private boolean playing = true;
    private int x1, x2, y1, y2;
    /**
     * Constructor for the  game GUI
     */
    public SnakeGUI(int numRow){
        super();
        pane = this.getContentPane();
        pane.setLayout(new GridLayout(numRow, numRow));
        pixels = new JPanel[numRow][numRow];
        this.setTitle("");
        this.setSize(windowLength, windowLength);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.addMouseListener(this);
        for (int i = 0; i < pixels.length; i++){
            for (int j = 0; j < pixels[0].length; j++){
                pixels[i][j] = new JPanel();
                pane.add(pixels[i][j]);
            }
        }
    }
    /**
     * Start a new game
     */
    protected void newGame(){
        game = new Board(pixels.length);
        this.playing = true;
        repaint();
    }
    /**
     * Display the GUI
     */
    public void display(){
        newGame();
        setVisible(true);
        while(isVisible()){
            while(game.move()){
                repaint();
                try{
                    Thread.sleep(waitTime);
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
            }
            playing = false;
            JOptionPane.showMessageDialog(null,"Score: " + game.getScore());
            while (!playing){
                try{
                    Thread.sleep(waitTime);
                }
                catch(Exception e){
                    System.out.println(e.toString());
                }
            }
        }
    }
    /**
     * Update the GUI
     */
    @Override
    public void repaint(){
        boolean presence[][] = game.getPresence();
        for (int i = 0; i < pixels.length; i++){
            for (int j = 0; j < pixels[0].length; j++){
                if(i==game.pelletRow() && j==game.pelletCol()){
                    pixels[i][j].setBackground(Color.RED);
                }
                else{
                    pixels[i][j].setBackground(presence[i][j] ? Color.BLUE : Color.GRAY);
                }
            }
        }
    }
    public void mouseReleased(MouseEvent e){
        boolean tailPresent = (game.getSnake().getSize()>1);
        int x2 = e.getX();
        int y2 = e.getY();
        int x = x2 - x1;
        int y = y2 - y1;
        if (Math.abs(x) > Math.abs(y)){
            if (x<0){
                if (tailPresent && game.getDirection()==Direction.RIGHT) return;
                game.changeDirection(Direction.LEFT);
            }
            else {
                if (tailPresent && game.getDirection()==Direction.LEFT) return;
                game.changeDirection(Direction.RIGHT);
            }
        }
        else if (Math.abs(y) > Math.abs(x)){
            if (y<0){
                if (tailPresent && game.getDirection()==Direction.DOWN) return;
                game.changeDirection(Direction.UP);
            }
            else {
                if (tailPresent && game.getDirection()==Direction.UP) return;
                game.changeDirection(Direction.DOWN);
            }
        }
    }
    public void mousePressed(MouseEvent e){
        x1 = e.getX();
        y1 = e.getY();
    }
    public void mouseExited(MouseEvent e){
        return;
    }
    public void mouseEntered(MouseEvent e){
        return;
    }
    public void mouseClicked(MouseEvent e){
        return;
    }
    public void keyPressed(KeyEvent e){
        boolean tailPresent = (game.getSnake().getSize()>1);
        int keyCode = e.getKeyCode();
        char keyChar = e.getKeyChar();
        if (keyChar=='n'){
            newGame();
        }
        else if(!playing){
            return;
        }
        switch(keyCode){
            case KeyEvent.VK_UP:
            case KeyEvent.VK_I:
                if (tailPresent && game.getDirection()==Direction.DOWN) return;
                game.changeDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_K:
                if (tailPresent && game.getDirection()==Direction.UP) return;
                game.changeDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_L:
                if (tailPresent && game.getDirection()==Direction.LEFT) return;
                game.changeDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_J:
                if (tailPresent && game.getDirection()==Direction.RIGHT) return;
                game.changeDirection(Direction.LEFT);
                break;
            default:
                if (e.getKeyChar()=='p'){
                    game.changeDirection(Direction.NONE);
                }
                break;
        }
    }
    public void keyTyped(KeyEvent e){
        return;
    }
    public void keyReleased(KeyEvent e){
        return;
    }
}
