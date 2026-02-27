package chatproyect1.gui;

import javax.swing.SwingUtilities;

public class ConsoleWindow extends Window {
    
    public static final ConsoleWindow instance = new ConsoleWindow();
    
    public ConsoleWindow() {
        super("ConsoleLog", 500, 250);
    }
    
    public static ConsoleWindow getInstance() {
        return ConsoleWindow.instance;
    }
    
    public void addWindowMessage(String message) {
          SwingUtilities.invokeLater(() -> {
             chatArea.append(message + "\n");
        });
    }
    
}