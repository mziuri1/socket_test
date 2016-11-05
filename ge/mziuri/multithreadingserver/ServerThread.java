
package ge.mziuri.multithreadingserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
    
    private int clientId;
    
    private Socket Socket;
    
    private DataInputStream in;
    
    private DataOutputStream out;
    
    public ServerThread(Socket socket) {
        this.Socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ServerThread(Socket socket, int clientId) {
        this(socket);
        this.clientId = clientId;
    }
    
    public int getClientId() {
        return clientId;
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                String text = in.readUTF();
                System.out.println("სერვერმა მიიღო " + text);
                Server.sendMessageToAllClient(text, clientId);
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void closeConnection() {
        try {
            in.close();
            out.close();
            Socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
