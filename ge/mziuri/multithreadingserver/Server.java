
package ge.mziuri.multithreadingserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    
    private static List<ServerThread> clientList = new ArrayList<>();
    
    private static int idGenerator = 1;
    
    public static void main(String[] args) {
        
        try {
            ServerSocket server = new ServerSocket(8659);
            while (true) {
                Socket socket = server.accept();
                ServerThread serverThread = new ServerThread(socket, idGenerator);
                idGenerator++;
                serverThread.start();
                clientList.add(serverThread);
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public static void sendMessageToAllClient(String msg, int authorId) {
        for (ServerThread st : clientList) {
            if (st.getClientId() != authorId) {
                st.sendMessage(msg);
            }
        }
    }
    
    public static void deleteClientFromList(int id) {
        for (ServerThread st : clientList) {
            if (st.getClientId() == id) {
                clientList.remove(st);
                break;
            }
        }
    }
    
}
