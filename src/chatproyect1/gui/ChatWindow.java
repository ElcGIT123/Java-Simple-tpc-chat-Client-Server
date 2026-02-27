package chatproyect1.gui;

import javax.swing.SwingUtilities;

public class ChatWindow extends Window {
    
    public static final ChatWindow instance = new ChatWindow();
    
    public ChatWindow() {
        super("Chat Global", 400, 500);
    }
    
    public static ChatWindow getInstance() {
        return ChatWindow.instance;
    }

    public void addWindowMessage(String message) {
        SwingUtilities.invokeLater(() -> {
           chatArea.append(message + "\n");
        });
    }
    
}
