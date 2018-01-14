package Game;

import javafx.scene.paint.Color;

import java.util.ArrayList;

public interface Players {
    public Color getColor();

    public Cell play(ArrayList<Cell> possibleMoves);
}
