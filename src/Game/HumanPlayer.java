package Game;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer implements Players {
    private char player_color;

    public HumanPlayer(char player_color) {
        this.player_color = player_color;
    }

    @Override
    public Cell play(ArrayList<Cell> possibleMoves) {
        int x, y;
        Scanner scanner = new Scanner(System.in);
        System.out.println(this.player_color + ": It's your move.");
        System.out.println("Your possible moves:");
        for (int i = 0; i < possibleMoves.size(); ++i) {
            Cell c = possibleMoves.get(i);
            System.out.println("(" + c.getRow() + "," + c.getCol() + ")");
        }


        while (true) {
            System.out.println("Please enter your move row col(i.g: x y): ");
            x = scanner.nextInt();
            y = scanner.nextInt();
            for (int i = 0; i < possibleMoves.size(); ++i) {
                if (x == possibleMoves.get(i).getRow()
                        && y == possibleMoves.get(i).getCol()) {
                    possibleMoves.clear();
                    return new Cell(x, y);
                }
            }
            System.out.println("Wrong entry!");
        }
    }
}
