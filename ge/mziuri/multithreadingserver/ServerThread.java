package ge.mziuri.multithreadingserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {

    private int clientId;

    private Socket socket;

    private DataInputStream in;

    private DataOutputStream out;

    public ServerThread(Socket socket) {
        this.socket = socket;
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
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
        while (socket.isConnected()) {
            try {
                String text = in.readUTF();
                if (text.equals("exit")) {
                    closeConnection();
                    break;
                }
                Server.sendMessageToAllClient(text, clientId);
            } catch(IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void closeConnection() {
        try {
            Server.deleteClientFromList(clientId);
            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
