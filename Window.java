import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends javax.swing.JFrame {
    public Window(){
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

        JButton onePlayer = new JButton("One Player");
        onePlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("one player");
                makeOptions(true, true);
                frame.dispose();
            }
        });
        frame.add(onePlayer);

        JButton twoPlayer = new JButton("Two Player");
        twoPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("one player");
                makeOptions(false, true);
                frame.dispose();
            }
        });
        frame.add(twoPlayer);


        frame.setVisible(true);//makes everything currently made visible
    }

    public void makeOptions(boolean solo, boolean first){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Options(solo, first);//.setVisible(true);
            }
        });
    }
}
