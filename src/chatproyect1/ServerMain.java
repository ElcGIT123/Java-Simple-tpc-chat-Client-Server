    package chatproyect1;

import javax.swing.SwingUtilities;
import chatproyect1.gui.ConsoleWindow;

public class ServerMain {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConsoleWindow.getInstance().setVisible(true);
            ConsoleWindow.getInstance().addWindowMessage("Server ON");
        });
        
        Server server = Server.getInstance();
        Thread thread = new Thread(server);
        thread.start();
        
        System.out.println("Server: ON");
   
    }
    
}
