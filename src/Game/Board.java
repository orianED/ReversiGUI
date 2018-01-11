package Game;

public class Board {
    private int board_size;
    private char game_board[][];

    public Board(int size) {
        this.board_size = size;
        this.game_board = new char[size][size];

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
    }

    public int getSize() {
        return board_size;
    }

    public char[][] getBoard() {
        return game_board;
    }
}
