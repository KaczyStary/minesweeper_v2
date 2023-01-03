import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BoardDrawingManager {

    public Field[] field;
    Board board;

    public BoardDrawingManager(Board board) {
        this.board = board;
        field = new Field[10];
    }

    public void getFileImage() {
        try {
            //BLANK
            field[0] = new Field();
            field[0].image = ImageIO.read(getClass().getResourceAsStream(""));
            //REVEALED
            field[1] = new Field();
            field[1].image = ImageIO.read(getClass().getResourceAsStream(""));
            //FLAG
            field[2] = new Field();
            field[2].image = ImageIO.read(getClass().getResourceAsStream(""));
            //MINE
            field[3] = new Field();
            field[3].image = ImageIO.read(getClass().getResourceAsStream(""));
//            field[4]=new Field();
//            field[4].image= ImageIO.read(getClass().getResourceAsStream(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBoard(){
        int col=0;
        int row=0;

    }

}

