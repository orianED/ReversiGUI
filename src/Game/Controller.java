package Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    AnchorPane background;

    /**
    start function initialize the game screen.
     */
    @FXML
    protected void start(ActionEvent event) throws IOException {
        AnchorPane game = FXMLLoader.load(getClass().getResource("Game.fxml"));
        Scene g = new Scene(game);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(g);
        stage.show();
    }

    /**
    settings function initialize the settings screen.
     */
    @FXML
    protected void settings(ActionEvent event) throws IOException {
        AnchorPane Settings = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Scene set = new Scene(Settings);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(set);
        stage.show();
    }

    /**
    exit function exit from the game.
     */
    @FXML
    protected void exit() {
        System.exit(1);
    }
}
