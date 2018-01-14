package Game;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.ArrayList;

public class Board extends GridPane {
    private int board_size;
    private char game_board[][];

    public Board(int size) {
        this.board_size = size;
        this.game_board = new char[size + 1][size + 1];

        for (int i = 0; i < size + 1; ++i) {
            this.game_board[i] = new char[size + 1];
            for (int j = 0; j < size + 1; ++j) {
                this.game_board[i][j] = ' ';
            }
        }
        this.game_board[size / 2][size / 2] = 'O';
        this.game_board[(size / 2) + 1][size / 2] = 'X';
        this.game_board[(size / 2) + 1][(size / 2) + 1] = 'O';
        this.game_board[size / 2][(size / 2) + 1] = 'X';

        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("Game.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    public int getSize() {
        return board_size;
    }

    public char[][] getBoard() {
        return game_board;
    }

    public void drawOn(ArrayList<Cell> possibles_moves, Color xColor, Color oColor) {
        this.getChildren().clear();
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int cellHeight = height / this.game_board.length;
        int cellWidth = width / this.game_board[0].length;

        Rectangle cellBack = new Rectangle(cellWidth, cellHeight, Color.GREEN);
        Rectangle availableCell = new Rectangle(cellWidth, cellHeight, Color.RED);
        Circle xCircle = new Circle(cellHeight / 2, xColor);
        Circle oCircle = new Circle(cellHeight / 2, oColor);

        for (int i = 0; i < this.board_size; i++) {
            for (int j = 0; j < this.board_size; j++) {
                this.add(cellBack, j, i);
                if (this.game_board[i][j] == 'X')
                    this.add(xCircle, j, i);
                if (this.game_board[i][j] == 'O')
                    this.add(oCircle, j, i);
                for (int k = 0; k < possibles_moves.size(); k++) {
                    if (possibles_moves.get(k).getRow() == i && possibles_moves.get(k).getCol() == j)
                        this.add(availableCell, j, i);
                }
            }
        }
    }
}
