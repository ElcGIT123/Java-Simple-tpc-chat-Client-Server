package chatproyect1;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import chatproyect1.gui.ConsoleWindow;

public class Server implements Runnable {

    public static Server instance;
    private int port;
    private static List<ClientHandler> clients = new ArrayList<>();
    
    
    public Server(int port) {
        this.port = port;
    }
    
    public static Server getInstance() {
        if(Server.instance == null) {
            Server.instance = new Server(5000);
        }
        return Server.instance;
    }
   
    @Override
    public void run() {
        ServerSocket server = null;
        Socket socket = null;
        DataInputStream inputStream;
        
        try {
            server = new ServerSocket(port);
            ConsoleWindow.getInstance().addWindowMessage("[servidor]: Servidor inicializado correctamente");
            ConsoleWindow.getInstance().addWindowMessage("[servidor]: Servidor escuchando en el puerto 5000");
            
            while(true) {
                socket = server.accept();
                ClientHandler handler = new ClientHandler(socket, Server.clients);
                Server.clients.add(handler);
                new Thread(handler).start();
            }
            
        } catch (IOException ex) {
            System.getLogger(Server.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
       public static void sendMessageToAll(String message) {         
           for (ClientHandler client : Server.clients) {
               client.sendMessage(message);
           }
      }
                
}
  