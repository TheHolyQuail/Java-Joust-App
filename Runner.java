import java.util.Scanner;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Runner{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();//.setVisible(true);
            }
        });
    }

}