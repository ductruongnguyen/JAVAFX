package bussiness;

import com.entity.Client;
import com.entity.Server;
import controller.ServerBoxController;
import javafx.collections.FXCollections;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerThread extends Thread {

    private Server chatServer;
    private ServerSocket server;
    private Socket socket;

    public ServerThread(Server chatServer) {
        this.chatServer = chatServer;
        try {
            server = new ServerSocket(chatServer.getPort());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                socket = server.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                String username = dis.readUTF();
                Client c = new Client();
                if(username != null) {
                    String x = username.substring(username.indexOf(":") + 1);
                    c.setUsername(x);
                    c.setSocket(socket);
                    ServerBoxController.clients.setItems(FXCollections.observableArrayList(c));
                    ClientHandler ch = new ClientHandler(socket, c);
                    clients.put(x, ch);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static HashMap<String, ClientHandler> clients = new HashMap<>();
}


