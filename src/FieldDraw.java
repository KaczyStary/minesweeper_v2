import java.awt.image.BufferedImage;

public class FieldDraw {
    int flag;
    int mine;
    int revealed;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getMine() {
        return mine;
    }

    public void setMine(int mine) {
        this.mine = mine;
    }

    public int getRevealed() {
        return revealed;
    }

    public void setRevealed(int revealed) {
        this.revealed = revealed;
    }

    public FieldDraw() {
    }

    public FieldDraw(int flag, int mine, int revealed) {
        this.flag = flag;
        this.mine = mine;
        this.revealed = revealed;
    }
}
