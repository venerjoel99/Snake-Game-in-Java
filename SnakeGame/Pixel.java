
/**
 * Write a description of class PIxel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pixel
{
    protected int row;
    protected int col;
    /**
     * Private constructor for location class
     */
    public Pixel(int row, int col){
        this.row = row;
        this.col = col;
    }
    /**
     * Does this location equal other location
     */
    public boolean equals(Pixel other){
        return (this.getRow() == other.getRow()) && (this.getCol()==other.getCol());
    }
    public int getRow(){return row;}
    public int getCol(){return col;}
    public void setRow(int row){this.row = row;}
    public void setCol(int col){this.col = col;}
}
