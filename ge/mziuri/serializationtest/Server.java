
package ge.mziuri.serializationtest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    
    public static void main(String[] args) {
        
        try {
            ServerSocket server = new ServerSocket(8080);
            Socket socket = server.accept();
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            while (true) {
                Student student = (Student)in.readObject();
                System.out.println("სტუდენტი " + student.getFirstName() + " " + 
                        student.getSecondName() + " დარეგისტრირდა!");
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
