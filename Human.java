public class Human extends Player {
    public Human(int num, int gameSize) {
        super(num, true, gameSize);

    }

    public boolean checkMove(int x, int y){
        if((Math.abs(x - this.X) == 1 && Math.abs(y - this.Y) == 2) || (Math.abs(x - this.X) == 2 && Math.abs(y - this.Y) == 1)){
            this.setPos(x, y);
            return true;
        }
        return false;
    }
}
