public class BoardActions {
    BoardPanel bp;

    public BoardActions(BoardPanel bp){
        this.bp=bp;
    }


    void toggleReveal(int col, int row) {
        if (!bp.fields[col][row].isRevealed()) {
            bp.fields[col][row].setRevealed(true);
        }
    }
    void toggleFlag(int col, int row) {
        if (!bp.fields[col][row].isFlag()) {
            bp.fields[col][row].setFlag(true);
        } else if (bp.fields[col][row].isFlag()) {
            bp.fields[col][row].setFlag(false);
        }
    }
    void showBoard() {

        for (int i = 0; i < bp.col; i++) {
            for (int j = 0; j < bp.row; j++) {
                System.out.print("{");
                if (!bp.fields[i][j].isFlag()) {
                    System.out.print(".");
                } else if (bp.fields[i][j].isFlag()) {
                    System.out.print("F");

                }
                if (!bp.fields[i][j].isRevealed()) {
                    System.out.print(".");
                } else if (bp.fields[i][j].isRevealed()) {
                    System.out.print("R");
                }
                if (!bp.fields[i][j].isMine()) {
                    System.out.print(".");
                } else if (bp.fields[i][j].isMine()) {
                    System.out.print("M");
                }
                System.out.print("}");
            }
            System.out.println();
        }
    }
    int bombsLeftBoard() {
        int bombsLeft = 0;

        for (int bCol = 0; bCol < bp.col; bCol++) {
            for (int bRow = 0; bRow < bp.row; bRow++) {
                if (bp.fields[bCol][bRow].mine) {
                    bombsLeft++;
                }
            }
        }
        return bombsLeft;
    }
}
