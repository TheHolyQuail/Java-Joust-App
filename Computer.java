public class Computer extends Player {
    public Computer(int num, int gameSize) {
        super(num, false, gameSize);
    }

    public boolean compMove(Tile[][] board){
        boolean sucess = false;
        for(int i = 0; i < board[0].length; i++){
            for(int ii = 0; ii < board[0].length; ii++){
                if(!board[i][ii].occupied){
                    //if tile is not occupied check if it is a possible move
                    if((Math.abs(i - this.X) == 1 && Math.abs(ii - this.Y) == 2) || (Math.abs(i - this.X) == 2 && Math.abs(ii - this.Y) == 1)){
                        this.setPos(i, ii); //moves to the first space it finds
                        sucess = true;
                    }
                }
            }
        }
        return sucess;
    }
}
