package chatproyect1;

import java.net.Socket;
import java.io.DataOutputStream;
import java.io.IOException;

public class Client implements Runnable{

    private String host;
    private int port;
    private String message;
    
    public Client(String host, int port, String message) {
        this.host = host;
        this.port = port;
        this.message = message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public void run() {
        DataOutputStream outputStream;
        Socket socket;    
        try {
            socket = new Socket(host, port);
            outputStream = new DataOutputStream(socket.getOutputStream());
            
            outputStream.writeUTF(this.message);
           
            socket.close();
            
        } catch (IOException ex) {
            System.getLogger(Client.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
}
