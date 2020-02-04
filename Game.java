import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
//import java.awt.event.*;
//import javax.swing.event.*;

public class Game extends javax.swing.JFrame implements EventListener {
    boolean gameOn;
    boolean solo;
    boolean first;
    int gameSize;
    Board boardGame;
    JFrame frame;

    //Scale game on how late frame is.
    public static float timeScaler = 1;

    //game variables:
    int turn = 1;
    String p1Tile;
    String p2Tile;
    Player p1;
    Player p2;

    public Game(boolean soloQ, boolean firstQ, int gameBoardSize) {
        //making the window
        frame = new JFrame("Joust Game");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
        //frame.setUndecorated(true); //for making the stuff around the canvas invisible


        //adds a thing to listen for events
        WindowListener listener = new WindowListener();
        frame.addWindowListener(listener);

        this.gameOn = true;


        ////choosing gamemode
        solo = soloQ;
        first = firstQ;
        gameSize = gameBoardSize;

        //make popup

        //making the game
        if (solo) {
            if (first) {
                Human p1 = new Human(1, gameSize);
                Computer p2 = new Computer(2, gameSize);
            } else {
                Computer p1 = new Computer(1, gameSize);
                Human p2 = new Human(2, gameSize);
            }
        } else {
            Human p1 = new Human(1, gameSize);
            Human p2 = new Human(2, gameSize);
        }
        //making board
        boardGame = new Board(gameSize);
        //seting starting possitions
        p1Tile = "00";
        p2Tile = Integer.toString(gameSize - 1)+Integer.toString(gameSize - 1);
        boardGame.getTile(0, 0).setOccupied(true);
        boardGame.getTile(gameSize  - 1, gameSize - 1).setOccupied(true);

        frame.setLayout(new GridLayout(gameSize, gameSize, 1, 1));

        ////menu bar/////
        JMenuBar gameMenuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Menu");
        JMenuItem menuItemExit = new JMenuItem("Exit");
        menuItemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("game exited");
                new Window();
                frame.dispose();
            }
        });
        gameMenu.add(menuItemExit);
        gameMenuBar.add(gameMenu);
        // adds menu bar to the frame
        frame.setJMenuBar(gameMenuBar);

        ////making the buttons
        for (int i = 0; i < gameSize; i++) {
            for (int ii = 0; ii < gameSize; ii++) {
                //adds the tile at [i][ii]
                frame.add(boardGame.getTile(i, ii).getButton());
            }
        }

        frame.setVisible(true);//makes everything currently made visible
        //gameLoop();
    }

    public void gameLoop()
    {
        //Resources for making this better: http://www.java-gaming.org/index.php?topic=24220.0
        while(gameOn) //the loop
        {
            update();
            try {
                Thread.sleep(1);//the timing mechanism
            } catch(InterruptedException e) {
                System.out.println("thread got interrupted!");
            }
        }
    }


    public void update(){
        //set up some variables
        boolean turnChange = false;
        boolean gameEnd = false;
        int gameEndReason = 0;//0 = other, 1 = p1 loss, 2 = p2 loss
        ////computer move if it is computers turn
        boolean comp = true;

        ////computer move
        if(turn == 1){
            if(!p1.isHuman()){
                //if it is a computer
                comp = p1.compMove(boardGame.getBoard());
                if(!comp){
                    //if the computer can't find a move then the game ends
                    gameEnd = true;
                    gameEndReason = 1;
                }
            }
        } else {
            if(!p2.isHuman()){
                //if it is a computer
                comp = p2.compMove(boardGame.getBoard());
                if(!comp){
                    //if the computer can't find a move then the game ends
                    gameEnd = true;
                    gameEndReason = 2;
                }
            }
        }

        //human loss check
        boolean success = false;
        if (p1.isHuman()) {
            gameEndReason = 1;
            Tile[][] board = boardGame.getBoard();
            for(int i = 0; i < board[0].length; i++){
                for(int ii = 0; ii < board[0].length; ii++){
                    if(!board[i][ii].occupied){
                        //if tile is not occupied check if it is a possible move
                        if ((Math.abs(i - p1.getXPos()) == 1 && Math.abs(ii - p1.getYPos()) == 2)
                                || (Math.abs(i - p1.getXPos()) == 2 && Math.abs(ii - p1.getYPos()) == 1)) {
                            success = true;
                            gameEndReason = 0;
                        }
                    }
                }
            }
        } else if(p2.isHuman()){
            gameEndReason = 2;
            Tile[][] board = boardGame.getBoard();
            for(int i = 0; i < board[0].length; i++){
                for(int ii = 0; ii < board[0].length; ii++){
                    if(!board[i][ii].occupied){
                        //if tile is not occupied check if it is a possible move
                        if(success){
                            //to make the editor not annoy me with the duplicate code thing
                        }
                        if ((Math.abs(i - p2.getXPos()) == 1 && Math.abs(ii - p2.getYPos()) == 2)
                                || (Math.abs(i - p2.getXPos()) == 2 && Math.abs(ii - p2.getYPos()) == 1)) {
                            success = true;
                            gameEndReason = 0;
                        }
                    }
                }
            }
        }
        //if success is false game ends because of player
        if(!success){
            gameEnd = true;
        }

        if (gameEnd){
            //end game screen
            System.out.println("game over | reason: " + gameEndReason);
        }



        ////remake the newly updated board ///////////////////////////
        // clear screen

        ////remake the buttons
        for (int i = 0; i < gameSize; i++) {
            for (int ii = 0; ii < gameSize; ii++) {
                if (boardGame.getTile(i, ii).occupied) {
                    //if the tile has been clicked
                    if (p1Tile.equals(Integer.toString(i)+Integer.toString(ii))) {
                        //if the tile has been clicked and is the actively occupied tile by the white piece
                        boardGame.getTile(i, ii).setText("♘");
                        System.out.println("white move");

                    } else if(p2Tile.equals(Integer.toString(i)+Integer.toString(ii))) {
                        //if the tile has been clicked and is the actively occupied tile by the black piece
                        boardGame.getTile(i, ii).setText("♞");
                        System.out.println("black move");

                    } else {
                        //if the tile has been clicked and is not the actively occupied tile
                        boardGame.getTile(i, ii).setText("⦻");
                    }

                    System.out.println("tile " + i + ", " + ii + " is pressed");
                } else {
                    //if the tile has not been clicked
                    boardGame.getTile(i, ii).setText("_");
                }
                //                frame.add(boardGame.getTile(i, ii).getButton());
            }
        }
        //        frame.setVisible(true);

        ////

        ////turn changer //needs to check if the person whose turn it is has moved yet
//        if (turnChange) {
//            if(turn == 1){
//                turn = 2;
//            } else {
//                turn = 1;
//            }
//        }
    }


}