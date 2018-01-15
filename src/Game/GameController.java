package Game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable, Listener {
    @FXML
    private HBox root;
    @FXML
    private Board board;
    @FXML
    private GameProcessInfo pro;
    private GameLogic gameLogic;
    private Players currentPlayer, nextPlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.board = new Board(8);
        this.board.setPrefHeight(400);
        this.board.setPrefWidth(400);
        root.getChildren().add(0, board);
        this.gameLogic = new GameLogic(this.board);
        this.board.addListener(this);

        this.currentPlayer = new HumanPlayer('X');
        this.nextPlayer = new HumanPlayer('O');
        this.board.draw(this.gameLogic.generateMoves(this.currentPlayer.getSign()), Color.BLACK, Color.WHITE);
    }

    @Override
    public void click(Cell cell) {
        this.gameLogic.makeMove(cell, this.currentPlayer.getSign());

        Players temp = this.currentPlayer;
        this.currentPlayer = this.nextPlayer;
        this.nextPlayer = temp;
        this.board.draw(this.gameLogic.generateMoves(this.currentPlayer.getSign()), Color.BLACK, Color.WHITE);
    }
}
