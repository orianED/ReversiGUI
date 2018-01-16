package Game;

import java.util.ArrayList;

public class GameLogic {
    private Board board;
    int xScore, oScore;

    public GameLogic(Board b) {
        this.board = b;
        this.xScore = 2;
        this.oScore = 2;
    }

    public ArrayList<Cell> generateMoves(char player_color) {
        int temp;
        boolean found;
        ArrayList<Cell> possible_moves = new ArrayList<>();

        for (int i = 0; i < this.board.getSize();
             ++i) {
            for (int j = 0; j < this.board.getSize();
                 j++) {
                if (this.board.getBoard()[i][j] == ' ') {
                    found = false;
                    for (int k = -1; k <= 1; ++k) {
                        for (int l = -1; l <= 1; ++l) {
                            temp = 1; //raised for each second player sign in a row(col/cross).
                            if ((i + (k * temp)) < this.board.getSize()
                                    && (j + (l * temp)) < this.board.getSize()
                                    && (i + (k * temp)) > 0 && (j + (l * temp)) > 0
                                    && this.board.getBoard()[i + (k * temp)][j
                                    + (l * temp)] != ' '
                                    && this.board.getBoard()[i + (k * temp)][j
                                    + (l * temp)] != player_color) {
                                while (true) {
                                    ++temp;
                                    if ((i + (k * temp)) > this.board.getSize()
                                            || (i + (k * temp)) < 0
                                            || (j + (l * temp))
                                            > this.board.getSize()
                                            || (j + (l * temp)) < 0
                                            || this.board.getBoard()[i + (k * temp)][j
                                            + (l * temp)] == ' ') {
                                        break;
                                    } else if ((i + (k * temp))
                                            <= this.board.getSize()
                                            && (i + (k * temp)) > 0
                                            && (j + (l * temp))
                                            <= this.board.getSize()
                                            && (j + (l * temp)) > 0
                                            && this.board.getBoard()[i + (k * temp)][j
                                            + (l * temp)] == player_color) {
                                        possible_moves.add(new Cell(i, j));
                                        found = true;
                                        break;
                                    }
                                }
                            }
                            if (found)
                                break;
                        } //4th for
                        if (found)
                            break;
                    } //3rd for
                }
            } //2nd for
        } //1st for
        return possible_moves;
    }

    public void makeMove(Cell cell, char player_color) {
        int temp;
        ArrayList<Cell> cells_to_change = new ArrayList<>();
        for (int k = -1; k <= 1; ++k) {
            for (int l = -1; l <= 1; ++l) {
                temp = 1;
                if ((cell.getRow() + (k * temp)) < this.board.getSize()
                        && (cell.getCol() + (l * temp)) < this.board.getSize()
                        && (cell.getRow() + (k * temp)) >= 0
                        && (cell.getCol() + (l * temp)) >= 0
                        && this.board.getBoard()[cell.getRow() + (k * temp)][cell.getCol()
                        + (l * temp)] != ' '
                        && this.board.getBoard()[cell.getRow() + (k * temp)][cell.getCol()
                        + (l * temp)] != player_color) {
                    while (true) {
                        temp++;
                        if ((cell.getRow() + (k * temp)) > this.board.getSize()
                                || (cell.getCol() + (l * temp))
                                > this.board.getSize()
                                || this.board.getBoard()[cell.getRow() + (k * temp)][cell.getCol()
                                + (l * temp)] == ' ') {
                            break;
                        } else if ((cell.getRow() + (k * temp))
                                < this.board.getSize()
                                && (cell.getCol() + (l * temp))
                                < this.board.getSize()
                                && this.board.getBoard()[cell.getRow() + (k * temp)][cell.getCol()
                                + (l * temp)] == player_color) {
                            while (temp > 1) {
                                temp--;
                                cells_to_change.add(
                                        new Cell(cell.getRow() + (k * temp),
                                                cell.getCol() + (l * temp)));
                            }
                            break;
                        }
                    }
                }
            }
        }
        if (player_color == 'X') {
            this.xScore++;
        } else if (player_color == 'O') {
            this.oScore++;
        }
        changeCells(cells_to_change, player_color);
        cells_to_change.clear();
        this.board.getBoard()[cell.getRow()][cell.getCol()] = player_color;
    }

    public void changeCells(ArrayList<Cell> cells_to_change, char sign) {
        for (int i = 0; i < cells_to_change.size(); i++) {
            this.board.getBoard()[cells_to_change.get(i).getRow()][cells_to_change.get(i).getCol()] =
                    sign;
            if (sign == 'X') {
                this.xScore++;
                this.oScore--;
            } else if (sign == 'O') {
                this.xScore--;
                this.oScore++;
            }
        }
    }
}
