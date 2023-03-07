public class Field {

    boolean flag;
    boolean mine;
    boolean revealed;
    int minesAround;
    int col;
    int row;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    //FIELD CONSTRUCTOR
    public Field(int col, int row, boolean flag, boolean mine, boolean revealed) {
        this.col=col;
        this.row=row;
        this.flag = flag;
        this.mine = mine;
        this.revealed = revealed;
    }
}
