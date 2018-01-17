package Game;

public interface Notifier {
    /**
     * addListener function - add the listener to the list.
     */
    void addListener(Listener listenr);

    /**
     * clickNotify function notify to the listeners about click on board.
     */
    void clickNotify(int row, int col);
}
