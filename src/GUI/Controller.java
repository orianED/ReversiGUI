package GUI;

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

    @FXML
    protected void start(ActionEvent event) throws IOException {
        AnchorPane game = FXMLLoader.load(getClass().getResource("../Game/Game.fxml"));
        Scene g = new Scene(game);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(g);
        stage.show();
    }

    @FXML
    protected void settings(ActionEvent event) throws IOException {
        AnchorPane Settings = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Scene set = new Scene(Settings);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(set);
        stage.show();
    }

    @FXML
    protected void exit() {
        System.exit(1);
    }
}
