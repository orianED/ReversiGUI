package Game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private HBox root;
    @FXML
    private Board board;
    @FXML
    private GameProcessInfo pro;
    private GameLogic gameLogic;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.board = new Board(8);
        this.board.setPrefHeight(400);
        this.board.setPrefWidth(400);
        root.getChildren().add(0, board);
        this.gameLogic = new GameLogic(this.board);

        root.widthProperty().addListener((observable, oldValue, newValue) -> {
            double boardNewWidth = newValue.doubleValue();
            this.board.setPrefWidth(boardNewWidth);
            this.board.draw(this.gameLogic.generateMoves('X'), Color.BLACK, Color.WHITE);
        });

        root.heightProperty().addListener((observable, oldValue, newValue) -> {
            this.board.setPrefHeight(newValue.doubleValue());
            this.board.draw(this.gameLogic.generateMoves('X'), Color.BLACK, Color.WHITE);
        });
    }
}
