package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;


public class SettingsController implements Initializable {
    @FXML
    ChoiceBox<String> first_player;

    @FXML
    ObservableList<String> players = FXCollections.observableArrayList("Player X", "Player O");

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        first_player.setValue("Player X");
        first_player.setItems(players);
    }
}
