import java.awt.image.BufferedImage;

public class Field {
    public BufferedImage image;
    boolean flag;
    boolean mine;
    boolean revealed;

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

    public Field() {
    }

    public Field(boolean flag, boolean mine, boolean revealed) {
        this.flag = flag;
        this.mine = mine;
        this.revealed = revealed;
    }
}
