
package ge.mziuri.multithreadingserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    
    private static int idGenerator = 1;
    
    private static List<ServerThread> clientList = new ArrayList<>();
    
    public static void main(String[] args) {
        
        try {
            ServerSocket server = new ServerSocket(8080);
            while (true) {
                Socket socket = server.accept();
                ServerThread serverThread = new ServerThread(socket, idGenerator);
                idGenerator++;
                clientList.add(serverThread);
                serverThread.start();
                System.out.println("სერვერს დაუკავშირდა კლიენტი id_თ " + serverThread.getClientId());
                System.out.println("კავშირზეა " + clientList.size() + " კლიენტი!" + System.lineSeparator());
            }
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public static void sendMessageToAllClient(String msg, int authorId) {
        System.out.println(clientList.size());
        System.out.println(authorId);
        System.out.println("------");
        for (ServerThread st : clientList) {
            System.out.println(st.getClientId());
            if (st.getClientId() != authorId) {
                st.sendMessage(msg);
            }
        }
    }
    
}
