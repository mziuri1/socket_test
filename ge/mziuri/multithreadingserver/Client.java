
package ge.mziuri.multithreadingserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) {
        
        try {
            Socket socket = new Socket("localhost", 8080);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            Thread thread = new Thread(){
                public void run() {
                    try {
                        while (true) {
                            String text = in.readUTF();
                            System.out.println(text);
                        }
                    } catch(IOException ex) {
                        System.out.println(ex.getMessage());
                    }   
                }  
            };
            thread.start();
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                out.writeUTF(text);
            }
            in.close();
            out.close();
            socket.close();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
