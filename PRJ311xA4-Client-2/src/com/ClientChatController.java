package com;

import business.ClientThread;
import com.entity.Client;
import com.entity.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientChatController {

    @FXML
    private TextArea txtContent;
    @FXML
    private TextField txtUsername, txtHostIP, txtPort, txtMessage;
    @FXML
    private Button btnSend, btnConnect;

    ClientThread clientThread = null;

    public void btnConnectActionPerformed(ActionEvent actionEvent) {
        if(clientThread == null) {
            try {
                Client c = new Client(txtUsername.getText(), "");
                Server server = new Server(txtHostIP.getText(), Integer.parseInt(txtPort.getText()));
                clientThread = new ClientThread(server, txtContent);
                Thread t = new Thread(clientThread);
                t.start();
                clientThread.send(":" + c.getUsername());
                txtContent.appendText("Connected to server");
                btnConnect.setDisable(true);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    public void onEnterConnect(ActionEvent actionEvent) {
        btnConnectActionPerformed(actionEvent);
    }

    @FXML
    public void onEnter(ActionEvent actionEvent) {
        btnSendActionPerformed(actionEvent);
    }

    public void btnSendActionPerformed(ActionEvent actionEvent) {
        try {
            clientThread.send(txtMessage.getText());
            txtMessage.clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
