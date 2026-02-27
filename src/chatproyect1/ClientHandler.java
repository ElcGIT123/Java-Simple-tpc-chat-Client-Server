package chatproyect1;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import chatproyect1.gui.ConsoleWindow;

public class ClientHandler implements Runnable {
    
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    
    private String username;
    public List<ClientHandler> clients;
    
    public ClientHandler(Socket socket, List<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;
        
        try {
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            
            username = input.readUTF();
            ConsoleWindow.getInstance().addWindowMessage("[ServerLog]: "+ username + " Se ha conectado al servidor");
            Server.sendMessageToAll(username + " Se ha conectado!");
            
        } catch (IOException ex) {
            System.getLogger(ClientHandler.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    @Override 
    public void run() {
        try {
            while (true) {
                String message = input.readUTF();
                
                ConsoleWindow.getInstance().addWindowMessage("[ServerLog]: " + message);
                Server.sendMessageToAll(message);
            }
        } catch (IOException e) {
            clients.remove(this);
            Server.sendMessageToAll(username + " Se ha desconectado");
            ConsoleWindow.getInstance().addWindowMessage("[ServerLog]: " + username + " Se ha desconectado");
        }
    }
    
    public void sendMessage(String message) {
         try {
            output.writeUTF(message);
            output.flush();
        } catch (IOException e) {
            System.getLogger(ClientHandler.class.getName()).log(System.Logger.Level.ERROR, (String) null, e);
        }
    }

}

