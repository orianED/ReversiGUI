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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
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
    ObservableList<String> sizes = FXCollections.observableArrayList("4", "6", "8", "10", "12", "14", "16", "18", "20");

    @FXML
    ColorPicker pXColor;

    @FXML
    ColorPicker pOColor;

    @FXML
    Button back;

    /*
    backAction function set the main menu as the screen again.
     */
    @FXML
    protected void backAction(ActionEvent event) throws IOException {
        AnchorPane menu = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        Scene men = new Scene(menu);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(men);
        stage.show();
    }

    /*
    save function save the setting in file and back to the main menu.
     */
    @FXML
    protected void save(ActionEvent event) throws Exception {
        PrintWriter writer = new PrintWriter("game_settings.txt", "UTF-8");
        writer.println(this.board_size.getValue());
        writer.println(this.first_player.getValue());
        writer.println(this.pXColor.getValue().toString());
        writer.println(this.pOColor.getValue().toString());
        writer.close();
        backAction(event);
    }

    /*
    initialize the settings screen.
     */
    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        first_player.setValue("Player X");
        first_player.setItems(players);
        pXColor.setValue(Color.BLACK);
        board_size.setValue("8");
        board_size.setItems(sizes);
    }
}
