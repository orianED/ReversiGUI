package Game;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer implements Players {
    private char sign;
    private Color color;

    /**
     * Constructor - create a player.
     *
     * @param sign  - player sign
     * @param color - player color
     */
    public HumanPlayer(char sign, Color color) {
        this.sign = sign;
        this.color = color;
    }

    /**
     * @return Colore - the player color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return char - the player sign.
     */
    public char getSign() {
        return this.sign;
    }

}
