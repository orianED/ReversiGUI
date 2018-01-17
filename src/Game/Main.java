package Game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    /**
    start function initialize the game menu screen.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
        primaryStage.setTitle("Reversi");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    /**
    main of the game.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
