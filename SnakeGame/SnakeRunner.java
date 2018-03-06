import javax.swing.JFrame;
/**
 * Write a description of class SnakeRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SnakeRunner
{
    public static void main(String[] args){
        SnakeGUI gui = new AIGUI(50);
        /*
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                try{
                    Thread.sleep(3000);
                }
                catch(Exception e){
                    //System.out.println(e.toString());
                }
                JFrame frame = new JFrame();
                frame.setSize(500, 500);
                frame.setVisible(true);
            }
        });
        thread.start();
        */
        gui.display();
        //thread.interrupt();
    }
}
