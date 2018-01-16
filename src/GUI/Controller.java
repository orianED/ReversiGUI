package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    AnchorPane background;
    @FXML
    private Button start;
    @FXML
    private Button settings;
    @FXML
    private Button exit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        BufferedImage img = null;
//        try {
//            img = ImageIO.read(new File("../Image/reversi.jpg"));
//            background.setBackground(new BackgroundImage(img, 0, 0,0,400));
//        } catch (IOException e) {
//        }
    }

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
