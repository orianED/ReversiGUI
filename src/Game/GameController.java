package Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable, Listener {
    @FXML
    private HBox root;
    @FXML
    private Board board;
    @FXML
    private HBox current_player_root;
    @FXML
    private HBox x_color_root;
    @FXML
    private HBox o_color_root;
    @FXML
    private HBox x_score_root;
    @FXML
    private HBox o_score_root;
    @FXML
    private Pane winner;
    private GameLogic gameLogic;
    private Players currentPlayer, nextPlayer;
    private Color pXColor, pOColor;

    /**
     * initialize the Game.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String line;
        try {
            FileReader fileReader = new FileReader("game_settings.txt");

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            if ((line = bufferedReader.readLine()) != null) {
                this.board = new Board(Integer.parseInt(line));
            }
            if ((line = bufferedReader.readLine()) != null) {
                if (line.equals("Player X")) {
                    line = bufferedReader.readLine();
                    this.currentPlayer = new HumanPlayer('X', Color.valueOf(line));
                    this.pXColor = this.currentPlayer.getColor();
                    line = bufferedReader.readLine();
                    this.nextPlayer = new HumanPlayer('O', Color.valueOf(line));
                    this.pOColor = this.nextPlayer.getColor();
                } else {
                    line = bufferedReader.readLine();
                    this.nextPlayer = new HumanPlayer('X', Color.valueOf(line));
                    this.pXColor = this.nextPlayer.getColor();
                    line = bufferedReader.readLine();
                    this.currentPlayer = new HumanPlayer('O', Color.valueOf(line));
                    this.pOColor = this.currentPlayer.getColor();
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            //default settings
            this.board = new Board(8);
            this.currentPlayer = new HumanPlayer('X', Color.BLACK);
            this.nextPlayer = new HumanPlayer('O', Color.WHITE);

            System.out.println("Unable to open file 'game_settings.txt'");
        } catch (IOException ex) {
            System.out.println("Error reading file 'game_settings.txt'");
        }
        this.board.setPrefHeight(400);
        this.board.setPrefWidth(400);
        root.getChildren().add(0, board);
        this.gameLogic = new GameLogic(this.board);
        this.board.addListener(this);

        this.board.draw(this.gameLogic.generateMoves(this.currentPlayer.getSign()), this.pXColor, this.pOColor);
        this.drawInfo();
    }

    /**
     * implements the listener that notify on click and call to the draw.
     */
    @Override
    public void click(Cell cell) {
        this.gameLogic.makeMove(cell, this.currentPlayer.getSign());

        this.swapPlayers();
        ArrayList<Cell> currentMoves = this.gameLogic.generateMoves(this.currentPlayer.getSign());
        if (!currentMoves.isEmpty())
            this.board.draw(currentMoves, this.pXColor, this.pOColor);
        else {
            this.swapPlayers();
            currentMoves = this.gameLogic.generateMoves(this.currentPlayer.getSign());
            if (!currentMoves.isEmpty())
                this.board.draw(currentMoves, this.pXColor, this.pOColor);
            else
                this.endGame();
        }
        this.drawInfo();
    }

    /**
     * drawing the information of the game.
     */
    public void drawInfo() {
        this.current_player_root.getChildren().clear();
        Circle current = new Circle(this.current_player_root.getPrefHeight() / 2, this.currentPlayer.getColor());
        current.setStroke(Color.BLACK);
        this.current_player_root.getChildren().add(current);


        this.x_color_root.getChildren().clear();
        Circle x_color = new Circle(this.x_color_root.getPrefHeight() / 2, this.pXColor);
        x_color.setStroke(Color.BLACK);
        this.x_color_root.getChildren().add(x_color);


        this.o_color_root.getChildren().clear();
        Circle o_color = new Circle(this.o_color_root.getPrefHeight() / 2, this.pOColor);
        o_color.setStroke(Color.BLACK);
        this.o_color_root.getChildren().add(o_color);

        this.x_score_root.getChildren().clear();
        this.x_score_root.getChildren().add(new Text(String.valueOf(this.gameLogic.getxScore())));

        this.o_score_root.getChildren().clear();
        this.o_score_root.getChildren().add(new Text(String.valueOf(this.gameLogic.getoScore())));
    }

    /**
     * this function swap the player who need to play.
     */
    public void swapPlayers() {
        Players temp = this.currentPlayer;
        this.currentPlayer = this.nextPlayer;
        this.nextPlayer = temp;
    }

    /**
     * endGame function print the board and announce about the winner.
     */
    public void endGame() {
        this.board.draw(this.gameLogic.generateMoves(this.currentPlayer.getSign()), this.pXColor, this.pOColor);
        this.drawInfo();

        //winner
        int x_score = this.gameLogic.xScore;
        int o_score = this.gameLogic.oScore;
        this.winner.getChildren().clear();
        Text w;
        if (x_score > o_score) {
            w = new Text("X is the Winner");
            w.setFill(this.pXColor);
        } else if (x_score < o_score) {
            w = new Text("O is the Winner");
            w.setFill(this.pOColor);
        } else {
            w = new Text("Draw!");
        }
        w.setFont(new Font("system", 25));
        w.setStroke(Color.BLACK);
        this.winner.getChildren().add(w);
    }

    /**
     * set the main menu as the screen.
     */
    @FXML
    protected void returnToMenu(ActionEvent event) throws IOException {
        AnchorPane menu = FXMLLoader.load(getClass().getResource("../GUI/Menu.fxml"));
        Scene men = new Scene(menu);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(men);
        stage.show();
    }
}
