public class Player {
    // Instance Variables
    int X;
    int Y;
    boolean type;
    int P;
    String color;

    // Constructor Declaration of Class
    public Player(int num, boolean type, int gameSize)
    {
        this.P = num;
        if(P == 1){
            this.X = 0;
            this.Y = 0;
            this.color = "♘";
        } else if(P == 2){
            this.X = gameSize - 1;
            this.Y = gameSize - 1;
            this.color = "♞";
        }
        this.type = type;
    }

    //Methods
    public int getXPos()
    {
        return X;
    }
    public int getYPos()
    {
        return Y;
    }
    public boolean isHuman()
    {
        return type;
    }
    public int getP()
    {
        return P;
    }
    public String getColor()
    {
        return color;
    }


    public void setPos(int x, int y){
        this.X = x;
    }

    public boolean compMove(Tile[][] board){
        //placeholder for the computer player
        return false;
    }
    public boolean checkMove(int x, int y){
        //placeholder for the computer player
        return false;
    }


}
