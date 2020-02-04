import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Options {
    public Options(boolean solo, boolean first){
        //making the window
        JFrame frame = new JFrame("Joust Game");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.BLACK);
//        //frame.setUndecorated(true); //for making the stuff around the canvas invisible
        frame.setLayout(new GridLayout(1, 2, 5, 1));


        //adds a thing to listen for events
        WindowListener listener = new WindowListener();
        frame.addWindowListener(listener);

        JButton onePlayer = new JButton("8x8");
        onePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("one player");
                makeGame(solo, first, 8);
                frame.dispose();
            }
        });
        frame.add(onePlayer);

        JButton twoPlayer = new JButton("20x20");
        twoPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("one player");
                makeGame(solo, first, 20);
                frame.dispose();
            }
        });
        frame.add(twoPlayer);


        frame.setVisible(true);//makes everything currently made visible
    }

    public void makeGame(boolean solo, boolean first, int size){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game(solo, first, size);//.setVisible(true);
            }
        });
    }
}
