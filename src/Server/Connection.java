package Server;

/**
 *
 * @author Erick Pereira, Felipe Campos, Guilherme Rodrigues, Lucas Campanelli, Paulo Silveira e Ronaldo Arley
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {
    
    public static String receive(Socket socket) throws IOException {
        InputStream message = socket.getInputStream();
        
        byte messageBytes[] = new byte[500];
        
        int readBytes = message.read(messageBytes);
        
        if(readBytes > 0){
            return new String(messageBytes);
        }
        else {
            return "";
        }
    }
    
    public static void send(Socket socket, String message) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(message.getBytes());
    }
}