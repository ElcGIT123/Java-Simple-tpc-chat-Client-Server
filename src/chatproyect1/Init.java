package chatproyect1;

import java.util.Scanner;

import javax.swing.SwingUtilities;
import chatproyect1.gui.ChatWindow;

public class Init {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Ingresa tu username \n:");
        String username = sc.nextLine();
        
        SwingUtilities.invokeLater(() -> {
            ChatWindow.getInstance().setVisible(true);
        });
        
        new Thread(() -> {
            new ChatClient(username);
        }).start();
    } 
    
}
