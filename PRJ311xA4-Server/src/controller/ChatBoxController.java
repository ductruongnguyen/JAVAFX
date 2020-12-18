package controller;

import bussiness.ClientHandler;
import bussiness.ServerThread;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatBoxController {
    @FXML
    public TextArea txtContent;
    @FXML
    public TextField txtMessage;

    private ClientHandler cs;
    private String username;

    public void setUserName(String username) {
        this.username = username;
        cs = ServerThread.clients.get(username);
        cs.setTxtContent(txtContent);
        new Thread(cs).start();
    }


    public void btnSendActionPerformed(ActionEvent evt) {
        try {
            cs.send(txtMessage.getText());
            txtMessage.clear();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
