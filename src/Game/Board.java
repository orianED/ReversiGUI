package Game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Board extends GridPane {
    private int board_size;
    private char game_board[][];

    public Board(int size) {
        this.board_size = size;
        this.game_board = new char[this.board_size][this.board_size];

        for (int i = 0; i < this.board_size; ++i) {
            for (int j = 0; j < this.board_size; ++j) {
                this.game_board[i][j] = ' ';
            }
        }
        this.game_board[this.board_size / 2 - 1][this.board_size / 2 - 1] = 'O';
        this.game_board[(this.board_size / 2) - 1][this.board_size / 2] = 'X';
        this.game_board[(this.board_size / 2)][(this.board_size / 2)] = 'O';
        this.game_board[this.board_size / 2][(this.board_size / 2) - 1] = 'X';

        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("Game.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
    }

    public int getSize() {
        return board_size;
    }

    public char[][] getBoard() {
        return game_board;
    }

    public void draw(ArrayList<Cell> possibles_moves, Color xColor, Color oColor) {
        this.getChildren().clear();
        int height = (int) this.getPrefHeight();
        int width = (int) this.getPrefWidth();
        int cellHeight = height / this.game_board.length;
        int cellWidth = width / this.game_board[0].length;

        for (int i = 0; i < this.board_size; i++) {
            for (int j = 0; j < this.board_size; j++) {
                Rectangle cellBack = new Rectangle(cellWidth, cellHeight, Color.GREEN);
                cellBack.setStroke(Color.BLACK);

                this.add(cellBack, j, i);
                if (this.game_board[i][j] == 'X')
                    this.add(new Circle(cellHeight / 2, xColor), j, i);
                if (this.game_board[i][j] == 'O')
                    this.add(new Circle(cellHeight / 2, oColor), j, i);
                for (int k = 0; k < possibles_moves.size(); k++) {
                    if (possibles_moves.get(k).getRow() == i && possibles_moves.get(k).getCol() == j) {
                        Rectangle possible = new Rectangle(cellWidth, cellHeight, Color.RED);
                        this.add(possible, j, i);
                        possible.setOnMouseClicked(event -> {
                            Node chosen = (Node) event.getSource();
                            int row = GridPane.getRowIndex(chosen);
                            int col = GridPane.getColumnIndex(chosen);
                            Cell chosenCell = new Cell(row, col);
                            System.exit(1);
                        });
                    }
                }
            }
        }
    }
}
