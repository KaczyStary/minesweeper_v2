import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        BoardPanel board = new BoardPanel();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Minesweeper");
        window.add(board);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        board.startGameThread();
    }
}