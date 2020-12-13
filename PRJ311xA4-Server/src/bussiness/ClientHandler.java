package bussiness;

import com.entity.Client;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;
    private Client client;
    DataInputStream dis;
    DataOutputStream dos;
    private TextArea txtContent;

    public ClientHandler(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;
    }

    public void setTxtContent(TextArea txtContent) {
        this.txtContent = txtContent;
    }

    @Override
    public void run() {
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            while (true) {
                Object line = dis.readUTF();
                if (line != null) {
                    txtContent.appendText("\n" + client.getUsername() + ":" + line);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void send(Object line) throws Exception {
        dos.writeUTF(line.toString());
        txtContent.appendText("\nMe" + line);
    }
}
