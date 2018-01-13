package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class SettingsController implements Initializable {
    @FXML
    ChoiceBox<String> first_player;

    @FXML
    ObservableList<String> players = FXCollections.observableArrayList("Player X", "Player O");

    @FXML
    ChoiceBox<String> board_size;

    @FXML
    ObservableList<String> sizes = FXCollections.observableArrayList("4x4", "6x6", "8x8", "10x10", "12x12", "14x14", "16x16", "18x18", "20x20");

    @FXML
    ColorPicker pX;

    @FXML
    ColorPicker pO;
    
    @FXML
    Button back;

    @FXML
    protected void backAction(ActionEvent event) throws IOException {
        AnchorPane menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene men = new Scene(menu);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(men);
        stage.show();
    }

    @FXML
    protected void save() {

    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        first_player.setValue("Player X");
        first_player.setItems(players);
        board_size.setValue("8x8");
        board_size.setItems(sizes);
    }
}
