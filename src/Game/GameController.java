package Game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class GameController implements Initializable {
    @FXML
    private HBox root;
    @FXML
    private Board board;
    @FXML
    private GameProcessInfo pro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.root = new HBox();
        this.board = new Board(8);
        root.getChildren().add(0, board);
        board.drawOn(new ArrayList<Cell>(), Color.gray(0), Color.gray(100));
    }
}
