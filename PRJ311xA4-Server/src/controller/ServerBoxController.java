package controller;

import com.entity.Client;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ServerBoxController {

    @FXML
    public static ListView<Client> clients;

    public void lstClientMouseClicked(MouseEvent evt) {
        try {
            if (evt.getClickCount() == 2) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/ChatBox.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                String clientName = (((Client) clients.getSelectionModel().getSelectedItems()).getUsername());
                stage.setTitle("Chat with: " + clientName);
                stage.show();

                ChatBoxController controller =  loader.getController();
                controller.setUserName(clientName);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
