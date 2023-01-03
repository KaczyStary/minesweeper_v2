import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        board.setDifficulty(Board.GAME_DIFFICULTY.HARD);
        board.generateFields(5,5);
        board.fillFields();
        board.fillFieldWithMines();
        board.showBoard();
    }
}