import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BoardDrawingManager {
    BoardPanel bp;
    FieldDraw[][] fieldDraws;



    public BoardDrawingManager(BoardPanel bp) {
        this.bp = bp;
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

//    public void getFileImage() {
//        try {
//            //BLANK
//            field[0] = new Field();
//            field[0].image = ImageIO.read(getClass().getResourceAsStream(""));
//            //REVEALED
//            field[1] = new Field();
//            field[1].image = ImageIO.read(getClass().getResourceAsStream(""));
//            //FLAG
//            field[2] = new Field();
//            field[2].image = ImageIO.read(getClass().getResourceAsStream(""));
//            //MINE
//            field[3] = new Field();
//            field[3].image = ImageIO.read(getClass().getResourceAsStream(""));
////            field[4]=new Field();
////            field[4].image= ImageIO.read(getClass().getResourceAsStream(""));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void setBoard(){

        for (int col=0; col < bp.col; col++) {
            for (int row = 0; row < bp.row; row++) {
                if(bp.fields[col][row].isFlag()) {
                    //FLAG
                    //fieldDraws[col][row].setFlag(1);
                    //FLAG
                }else if(!bp.fields[col][row].isFlag()) {
                    System.out.println("noflag");
                    //NO FLAG
                    if(bp.fields[col][row].isRevealed()) {
                        //REVEALED
                        //fieldDraws[col][row].setRevealed(1);
                        //REVEALED
                        if (bp.fields[col][row].isMine()){
                            //MINE
                            //fieldDraws[col][row].setMine(1);
                            //MINE
                        }else if (!bp.fields[col][row].isMine()){
                            //NO MINE
                        }
                    }else if (!bp.fields[col][row].isRevealed()) {
                        //NO REVEALED
                    }
                    //NO FLAG
                }
            }
        }
    }

    public void soutBoard(){
        for (int col = 0; col <bp.col ; col++) {
            for (int row = 0; row <bp.row ; row++) {
                System.out.print("[");
                if (fieldDraws[col][row].getRevealed()==1){
                    //REVEALED
                    System.out.print("R,");
                    if (fieldDraws[col][row].getMine()==0){
                        //REVEALED, NO MINE
                        System.out.print("nM,");
                        if (fieldDraws[col][row].getFlag()==0){
                            //REVEALED, NO MINE, NO FLAG
                            System.out.print("nF");
                        }else if (fieldDraws[col][row].getFlag()==1){
                            //REVEALED, NO MINE, FLAG
                            System.out.print("F");
                        }
                    } else if (fieldDraws[col][row].getMine()==1) {
                        //REVEALED, MINE
                        System.out.print("M,");
                        if (fieldDraws[col][row].getFlag()==0){
                            //REVEALED, MINE, NO FLAG
                            System.out.print("nF");
                        }else if (fieldDraws[col][row].getFlag()==1){
                            //REVEALED, NO MINE, FLAG
                            System.out.print("F");
                        }
                    }
                } else if (fieldDraws[col][row].getRevealed()==0) {
                    //NO REVEALED
                    System.out.print("nR,");
                    if (fieldDraws[col][row].getMine()==0){
                        //NO REVEALED, NO MINE
                        System.out.print("nM,");
                        if (fieldDraws[col][row].getFlag()==0){
                            //NO REVEALED, NO MINE, NO FLAG
                            System.out.print("nF");
                        }else if (fieldDraws[col][row].getFlag()==1){
                            //NO REVEALED, NO MINE, FLAG
                            System.out.print("F");
                        }
                    } else if (fieldDraws[col][row].getMine()==1) {
                        //NO REVEALED, MINE
                        System.out.print("M,");
                        if (fieldDraws[col][row].getFlag()==0){
                            //REVEALED, MINE, NO FLAG
                            System.out.print("nF");
                        }else if (fieldDraws[col][row].getFlag()==1){
                            //REVEALED, NO MINE, FLAG
                            System.out.print("F");
                        }
                    }
                }
                System.out.print("]");
            }
            System.out.println();
        }
    }


}


