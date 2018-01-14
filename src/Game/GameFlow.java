package Game;

import java.util.ArrayList;
import java.util.Scanner;

public class GameFlow {
    Board board;
    private GameLogic gl;
    private Players pX, pO;

    public GameFlow() {
        System.out.println("Please choose board size 4-20(even):");
        Scanner scanner = new Scanner(System.in);
        int size;
        while (true) {
            size = scanner.nextInt();
            if (size < 4 || size > 20 || size % 2 != 0)
                System.out.println("Wrong entry! choose again:");
            else
                break;
        }
        this.board = new Board(size);
        this.gl = new GameLogic(board);

        this.pX = new HumanPlayer('X');
        this.pO = new HumanPlayer('O');
    }

    public void run() {
        int flagX = 1, flagO = 1;
        ArrayList<Cell> moves;
        while (flagX != 0 || flagO != 0) {
            flagO = 1;
            flagX = 1;
            moves = this.gl.generateMoves('X');
            this.board.drawOn(moves, this.pX.getColor(), this.pO.getColor());
            if (!moves.isEmpty())
                this.gl.makeMove(this.pX.play(moves), 'X');
            else
                flagX--;
            moves.clear();
            moves = this.gl.generateMoves('O');
            this.board.drawOn(moves, this.pX.getColor(), this.pO.getColor());
            if (!moves.isEmpty())
                this.gl.makeMove(this.pO.play(moves), 'O');
            else
                flagO--;
        }
    }
}
