import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowListener extends WindowAdapter {

    public void windowClosing(WindowEvent event) {
        System.out.println("About to close the window");
    }
}

