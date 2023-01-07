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
        fieldPics=new FieldPic[15];
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
            //NO REVEAL
            fieldPics[0] = new FieldPic();
            fieldPics[0].image = ImageIO.read(getClass().getResourceAsStream("/res/no_reveal.png"));
            //REVEAL
            fieldPics[1] = new FieldPic();
            fieldPics[1].image = ImageIO.read(getClass().getResourceAsStream("/res/reveal.png"));
            //FLAG
            fieldPics[2] = new FieldPic();
            fieldPics[2].image = ImageIO.read(getClass().getResourceAsStream("/res/flag.png"));
            //NO REVEAL, BOMB
            fieldPics[3] = new FieldPic();
            fieldPics[3].image = ImageIO.read(getClass().getResourceAsStream("/res/no_reveal_bomb.png"));
            //FLAG, BOMB
            fieldPics[4] = new FieldPic();
            fieldPics[4].image = ImageIO.read(getClass().getResourceAsStream("/res/flag_bomb.png"));
            //REVEAL, BOMB
            fieldPics[5] = new FieldPic();
            fieldPics[5].image = ImageIO.read(getClass().getResourceAsStream("/res/reveal_bomb.png"));
            //0 MINE AROUNF FIELD
            fieldPics[6] = new FieldPic();
            fieldPics[6].image = ImageIO.read(getClass().getResourceAsStream("/res/0.png"));
            //1 MINE AROUNF FIELD
            fieldPics[7] = new FieldPic();
            fieldPics[7].image = ImageIO.read(getClass().getResourceAsStream("/res/1.png"));
            //2 MINE AROUNF FIELD
            fieldPics[8] = new FieldPic();
            fieldPics[8].image = ImageIO.read(getClass().getResourceAsStream("/res/2.png"));
            //3 MINE AROUNF FIELD
            fieldPics[9] = new FieldPic();
            fieldPics[9].image = ImageIO.read(getClass().getResourceAsStream("/res/3.png"));
            //4 MINE AROUNF FIELD
            fieldPics[10] = new FieldPic();
            fieldPics[10].image = ImageIO.read(getClass().getResourceAsStream("/res/4.png"));
            //5 MINE AROUNF FIELD
            fieldPics[11] = new FieldPic();
            fieldPics[11].image = ImageIO.read(getClass().getResourceAsStream("/res/5.png"));
            //6 MINE AROUNF FIELD
            fieldPics[12] = new FieldPic();
            fieldPics[12].image = ImageIO.read(getClass().getResourceAsStream("/res/6.png"));
            //7 MINE AROUNF FIELD
            fieldPics[13] = new FieldPic();
            fieldPics[13].image = ImageIO.read(getClass().getResourceAsStream("/res/7.png"));
            //8 MINE AROUNF FIELD
            fieldPics[14] = new FieldPic();
            fieldPics[14].image = ImageIO.read(getClass().getResourceAsStream("/res/8.png"));


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

        for (int sCol = 0; sCol <bp.col; sCol++) {
            for (int sRow = 0; sRow <bp.row ; sRow++) {

                if (fieldDraws[sCol][sRow].getRevealed()==1){
                    if (fieldDraws[sCol][sRow].getMine()==0){
                        if (fieldDraws[sCol][sRow].getFlag()==0){
                            //REVEALED, NO MINE, NO FLAG
                            g2.drawImage(fieldPics[1].image,dX,dY,bp.tileSize,bp.tileSize,null);
                        }else if (fieldDraws[sCol][sRow].getFlag()==1){
                            //NEVER GOING TO HAPPEN
                            //REVEALED, NO MINE, FLAG
                        }
                    } else if (fieldDraws[sCol][sRow].getMine()==1) {
                        if (fieldDraws[sCol][sRow].getFlag()==0){
                            //ENDS GAME, LOSS
                            //REVEALED, MINE, NO FLAG
                            g2.drawImage(fieldPics[5].image,dX,dY,bp.tileSize,bp.tileSize,null);
                        }else if (fieldDraws[sCol][sRow].getFlag()==1){
                            //NEVER GOING TO HAPPEN
                            //REVEALED, MINE, FLAG
                        }
                    }
                } else if (fieldDraws[sCol][sRow].getRevealed()==0) {
                    if (fieldDraws[sCol][sRow].getMine()==0){
                        if (fieldDraws[sCol][sRow].getFlag()==0){
                            //DEFAULT
                            //NO REVEALED, NO MINE, NO FLAG
                            g2.drawImage(fieldPics[0].image,dX,dY,bp.tileSize,bp.tileSize,null);
                        }else if (fieldDraws[sCol][sRow].getFlag()==1){
                            //FLAGGED
                            //NO REVEALED, NO MINE, FLAG
                            g2.drawImage(fieldPics[2].image,dX,dY,bp.tileSize,bp.tileSize,null);
                        }
                    } else if (fieldDraws[sCol][sRow].getMine()==1) {
                        if (fieldDraws[sCol][sRow].getFlag()==0){
                            //NO REVEALED, MINE, NO FLAG
                            g2.drawImage(fieldPics[3].image,dX,dY,bp.tileSize,bp.tileSize,null);
                        }else if (fieldDraws[sCol][sRow].getFlag()==1){
                            //NO REVEALED, MINE, FLAG
                            g2.drawImage(fieldPics[4].image,dX,dY,bp.tileSize,bp.tileSize,null);
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


