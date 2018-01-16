package Game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
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
    private Color pX, pO;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String line;
        BufferedReader br;
        try {
            FileReader fileReader = new FileReader("game_settings.txt");

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            if ((line = bufferedReader.readLine()) != null) {
                this.board = new Board(Integer.parseInt(line));
            }
            if ((line = bufferedReader.readLine()) != null) {
                if (line == "Player X") {
                    line = bufferedReader.readLine();
                    this.currentPlayer = new HumanPlayer('X', Color.valueOf(line));
                    this.pX = this.currentPlayer.getColor();
                    line = bufferedReader.readLine();
                    this.nextPlayer = new HumanPlayer('O', Color.valueOf(line));
                    this.pO = this.nextPlayer.getColor();
                } else {
                    line = bufferedReader.readLine();
                    this.nextPlayer = new HumanPlayer('X', Color.valueOf(line));
                    this.pX = this.nextPlayer.getColor();
                    line = bufferedReader.readLine();
                    this.currentPlayer = new HumanPlayer('O', Color.valueOf(line));
                    this.pO = this.currentPlayer.getColor();
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            this.board = new Board(8);
            this.currentPlayer = new HumanPlayer('X', Color.BLACK);
            this.nextPlayer = new HumanPlayer('O', Color.WHITE);
            System.out.println(
                    "Unable to open file '" +
                            "game_settings.txt" + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + "game_settings.txt" + "'");
        }
        this.board.setPrefHeight(400);
        this.board.setPrefWidth(400);
        root.getChildren().add(0, board);
        this.gameLogic = new GameLogic(this.board);
        this.board.addListener(this);


        this.board.draw(this.gameLogic.generateMoves(this.currentPlayer.getSign()), this.pX, this.pO);

    }

    @Override
    public void click(Cell cell) {
        this.gameLogic.makeMove(cell, this.currentPlayer.getSign());

        Players temp = this.currentPlayer;
        this.currentPlayer = this.nextPlayer;
        this.nextPlayer = temp;
        this.board.draw(this.gameLogic.generateMoves(this.currentPlayer.getSign()), this.pX, this.pO);
    }
}
