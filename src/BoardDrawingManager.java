import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BoardDrawingManager {
    BoardPanel bp;
    FieldDraw[][] fieldDraws;
    FieldPic[] fieldPics;


    public BoardDrawingManager(BoardPanel bp) {
        this.bp = bp;
        fieldPics=new FieldPic[10];
        getFileImage();
    }

    void generateDrawFieldBoard(){
        fieldDraws=new FieldDraw[bp.col][bp.row];

        for (int gCol = 0; gCol < bp.col; gCol++) {
            for (int gRow = 0; gRow < bp.row; gRow++){

                FieldDraw fieldDraw=new FieldDraw(0,0,0);

                fieldDraws[gCol][gRow]=fieldDraw;
            }
        }
    }

    public void getFileImage() {
        try {
            fieldPics[0] = new FieldPic();
            fieldPics[0].image = ImageIO.read(getClass().getResourceAsStream("/res/no_reveal.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCompareBoard(){

        for (int col=0; col < bp.col; col++) {
            for (int row = 0; row < bp.row; row++) {

                if (!bp.fields[col][row].isRevealed()){
                    fieldDraws[col][row].setRevealed(0);
                } else if (bp.fields[col][row].isRevealed()) {
                    fieldDraws[col][row].setRevealed(1);
                }

                if (!bp.fields[col][row].isFlag()){
                    fieldDraws[col][row].setFlag(0);
                } else if (bp.fields[col][row].isFlag()) {
                    fieldDraws[col][row].setFlag(1);
                }

                if (!bp.fields[col][row].isMine()){
                    fieldDraws[col][row].setMine(0);
                } else if (bp.fields[col][row].isMine()) {
                    fieldDraws[col][row].setMine(1);
                }

            }
        }
    }

    public void draw(Graphics2D g2){
        int dX=0;
        int dY=0;

        for (int sCol = 0; sCol <bp.col ; sCol++) {
            for (int sRow = 0; sRow <bp.row ; sRow++) {
                if (fieldDraws[sCol][sRow].getRevealed()==1){
                    if (fieldDraws[sCol][sRow].getMine()==0){
                        if (fieldDraws[sCol][sRow].getFlag()==0){
                            //REVEALED, NO MINE, NO FLAG
                        }else if (fieldDraws[sCol][sRow].getFlag()==1){
                            //REVEALED, NO MINE, FLAG
                        }
                    } else if (fieldDraws[sCol][sRow].getMine()==1) {
                        if (fieldDraws[sCol][sRow].getFlag()==0){
                            //REVEALED, MINE, NO FLAG
                        }else if (fieldDraws[sCol][sRow].getFlag()==1){
                            //REVEALED, MINE, FLAG
                        }
                    }
                } else if (fieldDraws[sCol][sRow].getRevealed()==0) {
                    if (fieldDraws[sCol][sRow].getMine()==0){
                        if (fieldDraws[sCol][sRow].getFlag()==0){
                            //NO REVEALED, NO MINE, NO FLAG
                            g2.drawImage(fieldPics[0].image,dX,dY,bp.tileSize,bp.tileSize,null);
                            //System.out.println("g");
                        }else if (fieldDraws[sCol][sRow].getFlag()==1){
                            //NO REVEALED, NO MINE, FLAG
                        }
                    } else if (fieldDraws[sCol][sRow].getMine()==1) {
                        if (fieldDraws[sCol][sRow].getFlag()==0){
                            //NO REVEALED, MINE, NO FLAG
                            g2.drawImage(fieldPics[0].image,dX,dY,bp.tileSize,bp.tileSize,null);
                        }else if (fieldDraws[sCol][sRow].getFlag()==1){
                            //NO REVEALED, MINE, FLAG
                        }
                    }
                }
                dX+=bp.tileSize;
            }
            dY+=bp.tileSize;
            dX=0;
        }
    }
}


