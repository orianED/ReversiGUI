package Game;

public class Cell {
    private int row, col;

    /**
     * Constructor - make a Cell (x,y) coordinates.
     */
    public Cell(int x, int y) {
        this.row = x;
        this.col = y;
    }

    /**
     * return row value of this cell.
     */
    public int getRow() {
        return row;
    }

    /**
     * return column value of this cell.
     */
    public int getCol() {
        return col;
    }
}
