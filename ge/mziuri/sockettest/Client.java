
package ge.mziuri.sockettest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) {
        
        try {
            Socket socket = new Socket("localhost", 8080);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                dos.writeUTF(text);
                System.out.println(dis.readUTF());
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
