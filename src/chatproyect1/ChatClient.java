package chatproyect1;

import java.util.Scanner;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import chatproyect1.gui.ChatWindow;

public class ChatClient {
    
    private String username;
    
    public ChatClient(String username) {
        this.username = username;
        
        Scanner sc = new Scanner(System.in);
        
        try {
            Socket socket = new Socket("127.0.0.1", 5000);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            System.out.println("Enviar mensajes");
  
            output.writeUTF(username);
            output.flush();
            
            new Thread(() -> {
                try {
                    while(true) {
                        String clientMsg = input.readUTF();   // Espera mensaje del servidor
                        ChatWindow.getInstance().addWindowMessage(clientMsg);
                    }
                
                } catch(Exception e) {
                   ChatWindow.getInstance().addWindowMessage(this.username + " Se ha desconectado");
                }
              
            }).start();
            
            while(true) {
                System.out.print("[]: ");
                String clientMsg = sc.nextLine();
                output.writeUTF(this.username + ": " + clientMsg);
                output.flush();
            }
                
        } catch (IOException ex) {
            System.getLogger(ChatClient.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    } 
   
}
