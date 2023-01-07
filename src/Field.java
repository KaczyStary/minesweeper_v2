import java.awt.image.BufferedImage;

public class Field {

    boolean flag;
    boolean mine;
    boolean revealed;
    int bombsAround;
    int x;
    int y;
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

    public int getBombsAround() {
        return bombsAround;
    }

    public void setBombsAround(int bombsAround) {
        bombsAround=BoardActions.bombsAroundFields(x,y);
        this.bombsAround = bombsAround;
    }
    public Field() {
    }

    public Field(boolean flag, boolean mine, boolean revealed) {
        this.flag = flag;
        this.mine = mine;
        this.revealed = revealed;
    }
    public Field(boolean flag, boolean mine, boolean revealed, int bombsAround) {
        this.flag = flag;
        this.mine = mine;
        this.revealed = revealed;
        this.bombsAround = bombsAround;
    }
}
