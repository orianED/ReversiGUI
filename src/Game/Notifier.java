package Game;

public interface Notifier {
    void addListener(Listener listenr);
    void clickNotify(int row, int col);
}
