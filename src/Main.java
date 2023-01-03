import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        Board board = new Board();

//        board.setDifficulty(Board.GAME_DIFFICULTY.HARD);
//        board.generateFields(5,5);
//        board.fillFields();
//        board.fillFieldWithMines();
//        board.showBoard();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Minesweeper");
        window.add(board);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}