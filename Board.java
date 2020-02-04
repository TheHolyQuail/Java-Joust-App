import java.util.*;
import java.util.EventListener;

public class Board implements EventListener{
    // Instance Variables
    int P1X;
    int P1Y;
    int P2X;
    int P2Y;
    int size;
    Tile[][] board;

    // Constructor Declaration of Class
    public Board(int size)
    {
        board = new Tile[size][size];
        for(int i = 0; i < size;i++){
            for(int ii = 0; ii < size;ii++){
                board[i][ii] =  new Tile(Integer.toString(i)+Integer.toString(ii), size);
            }
        }
        System.out.println(this.board[0][0].getColor());
    }

    //Methods
    public int getP1XPos()
    {
        return P1X;
    }
    public int getP1YPos()
    {
        return P1Y;
    }
    public int getP2XPos()
    {
        return P2X;
    }
    public int getP2YPos()
    {
        return P2Y;
    }
    public int getSize()
    {
        return size;
    }
    public Tile getTile(int i,  int ii)
    {
        return board[i][ii];
    }
    public Tile[][] getBoard(){
        return board;
    }

    public boolean checkMove(int x, int y) {
        boolean verdict = false;
        if(this.board[x][y].isOpen()){
            verdict = true;
        }

        return verdict;
    }


    public void setP1XY(int x, int y){
        this.P1X = x;
        this.P1Y = y;
        this.board[x][y].close();
    }

    public void setP2XY(int x, int y){
        this.P2X = x;
        this.P2Y = y;
        this.board[x][y].close();
    }
}
