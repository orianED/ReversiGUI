package Game;

public class Cell {
    private int row, col;

    public Cell(int x, int y) {
        this.row = x;
        this.col = y;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
