import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Tile extends JFrame implements ActionListener{
    // Instance Variables
    String num;
    String color;
    boolean open = true;
    JButton button = new JButton();
    boolean occupied = false;
    int occupiedBy = 0;

    // Constructor Declaration of Class
    public Tile(String num, int boardSize)
    {
        this.num = num;
        this.color = "GREEN";
        button.setText("_");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg) {
                if(occupied != true) {
                    System.out.println(num);//print which button was clicked
                    occupied = true; //make the button register itself as clicked so the game can see it has happened
                    //close();//closing the button makes it unable to have text... not sure why
                    //button.setBackground(Color.RED); coloring the buttons also seems to not work for ome reason...
                    //button.setOpaque(true);                ^
                    //button.setBorderPainted(false);        ^
                    button.setText("♘⦻♞_"); //sets the text of the button to a default of all possible text values
                    //if the tile has already been occupied then it will do nothing when clicked
                }
            }
        });

        //this.button.addActionListener(this);
    }

    //Methods
    public boolean isOpen(){
        return this.open;
    }
    public JButton getButton(){
        return this.button;
    }
    public String getColor()
    {
        return color;
    }
    public boolean Occupied(){
        return this.occupied;
    }

    public void close(){
        this.open = false;
        this.color = "red";
        button.setEnabled(false);
    }
    public void setOccupied(boolean x){
        this.occupied = x;
    }
    public void setText(String text){
        button.setText(text);
    }

    public void actionPerformed(ActionEvent event) {
        System.out.println("tile clicked");
    }

}

