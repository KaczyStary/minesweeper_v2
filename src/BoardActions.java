public class BoardActions {
    static BoardPanel bp;

    public BoardActions(BoardPanel bp){
        this.bp=bp;
    }


    static void toggleReveal(int col, int row) {
        //REVEAL FIELD
        if (!bp.fields[col][row].isRevealed()) {
            bp.fields[col][row].setRevealed(true);
            //CHECK IF REVEALED FIELD HAS MINE
            if (bp.fields[col][row].isMine()){
                //REVEALED FIELD HAS MINE, GAME LOSS
                bp.GameState= BoardPanel.GAME_STATE.FINISHED_LOSS;
            }
        }
    }
   static void toggleFlag(int col, int row) {
        //IF FIELD IS NOT REVEALED, SET FLAG OR UNSET FLAG
        if (!bp.fields[col][row].isRevealed()) {
            if (!bp.fields[col][row].isFlag()) {
                //SET FLAG
                bp.fields[col][row].setFlag(true);

            } else if (bp.fields[col][row].isFlag()) {
                //UNSET FLAG
                bp.fields[col][row].setFlag(false);

            }
            //CANT SET FLAG IF FIELD IS REVEALED
        } else if (bp.fields[col][row].isRevealed()) {

        }

   }
    public int bombsLeftBoard() { //COUNT MINES ON BOARD
        int bombsLeft = 0;

        for (int bCol = 0; bCol < bp.col; bCol++) {
            for (int bRow = 0; bRow < bp.row; bRow++) {
                if (bp.fields[bCol][bRow].mine&&!bp.fields[bCol][bRow].isFlag()) {
                    bombsLeft++;
                }
            }
        }
        return bombsLeft;
    }

    public static int bombsAroundFields(int col, int row) {
        int bombsAroundfield = 0;
        int fCol;
        int fRow;

        //MIDTABLE
        if (col == 0 && row == 0) { //TOP LEFT CORNER
            for (fCol = col; fCol <= col + 1; fCol++) {
                for (fRow = row; fRow <= row + 1; fRow++) {
                    if (bp.fields[fCol][fRow].isMine()) {
                        bombsAroundfield++;
                    }
                }
            }
            //TOP RIGHT CORNER
        } else if (col == 0 && row == bp.row - 1) {
            for (fCol = col; fCol <= col + 1; fCol++) {
                for (fRow = row; fRow >= row-1; fRow--) {
                    if (bp.fields[fCol][fRow].isMine()) {
                        bombsAroundfield++;
                    }
                }
            }
            //LEFT BOTTOM CORNER
        } else if (col == bp.col-1 && row == 0) {
            for (fCol = col; fCol >= col - 1; fCol--) {
                for (fRow = row; fRow <= row+1; fRow++) {
                    if (bp.fields[fCol][fRow].isMine()) {
                        bombsAroundfield++;
                    }
                }
            }
            //BOTTOM RIGHT CORNER
        }else if (col == bp.col-1 && row == bp.row-1) {
            for (fCol = col; fCol >= col-1; fCol--) {
                for (fRow = row; fRow >= row-1; fRow--) {
                    if (bp.fields[fCol][fRow].isMine()) {
                        bombsAroundfield++;
                    }
                }
            }
        }else if (col==0){
            for (fCol = col; fCol <= col + 1; fCol++) {
                for (fRow = row-1; fRow <= row + 1; fRow++) {
                    if (bp.fields[fCol][fRow].isMine()) {
                        bombsAroundfield++;
                    }
                }
            }
        } else if (col==bp.col-1) {
            for (fCol = col; fCol >= col-1; fCol--) {
                for (fRow = row-1; fRow <= row + 1; fRow++) {
                    if (bp.fields[fCol][fRow].isMine()) {
                        bombsAroundfield++;
                    }
                }
            }
        } else if (row==0) {
            for (fCol = col-1; fCol <= col+1; fCol++) {
                for (fRow = row; fRow <= row + 1; fRow++) {
                    if (bp.fields[fCol][fRow].isMine()) {
                        bombsAroundfield++;
                    }
                }
            }
        } else if (row==bp.row-1) {
            for (fCol = col-1; fCol <= col+1; fCol++) {
                for (fRow = row-1; fRow <= row; fRow++) {
                    if (bp.fields[fCol][fRow].isMine()) {
                        bombsAroundfield++;
                    }
                }
            }
        }else {
            for (fCol = col-1; fCol <= col + 1; fCol++) {
                for (fRow = row-1; fRow <= row + 1; fRow++) {
                    if (bp.fields[fCol][fRow].isMine()) {
                        bombsAroundfield++;
                    }
                }
            }
        }

        if (bp.fields[col][row].isMine()){
            bombsAroundfield--;
        }

        return bombsAroundfield;
    }

    public void checkBoard(){
        //CHECK IF THERE ARE MINES LEFT
        if (bombsLeftBoard()==0){
            //NO MINES LEFT, WIN
            bp.GameState= BoardPanel.GAME_STATE.FINISHED_WIN;
        }
    }
}
