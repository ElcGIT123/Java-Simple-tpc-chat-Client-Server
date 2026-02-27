package chatproyect1.gui;

import javax.swing.*;
import java.awt.*;

public abstract class Window extends JFrame {

    public JTextArea chatArea;
    protected String title;
    
    public Window(String title, int sizeX, int sizeY) {
        this.title = title;
        setTitle(this.title);
        setSize(sizeX, sizeY); // 400, 500
        setLocationRelativeTo(null); // Centrar ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear área de texto
        chatArea = new JTextArea();
        chatArea.setEditable(false); // Solo lectura
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        
    }
    
   /* protected void addMessage(String message) {
        chatArea.append(message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength()); // Auto-scroll abajo
    } */

}