package controller;

import com.entity.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ServerBoxController {

    public static ObservableList<Client> lstClients = FXCollections.observableArrayList();

    @FXML
    public ListView<Client> clients = new ListView<Client>();

    @FXML
    void initialize() {
        clients.setCellFactory(new Callback<ListView<Client>, ListCell<Client>>() {
            @Override
            public ListCell<Client> call(ListView<Client> param) {
                ListCell<Client> cell = new ListCell<Client>() {
                    @Override
                    protected void updateItem(Client item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                            setText(item.getUsername());
                        } else {
                            setText("");
                        }
                    }
                };
                return cell;
            }
        });
        clients.setItems(lstClients);
    }

    public void lstClientMouseClicked(MouseEvent evt) {
        try {
            if (evt.getClickCount() == 2) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/ChatBox.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                String clientName = (((Client) clients.getSelectionModel().getSelectedItem()).getUsername());
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
