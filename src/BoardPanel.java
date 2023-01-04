import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class BoardPanel extends JPanel implements Runnable{
    public int col; //COL OF GAME BOARD
    public int row; //ROW OF GAME BOARD
    int originalTileSize=12;
    int scale=3;
    int maxScreenCol=16; //COL OF SCREEN
    int maxScreenRow=12; //ROW OF SCREEN
    int tileSize=originalTileSize*scale; //3*12=36
    int screenWidth=tileSize*maxScreenRow; //12*36=432
    int screenHeight=tileSize*maxScreenRow; //16*36=576
    Thread gameThread;
    Field[][] fields;
    //FieldDraw[][] fieldDraws;
    //FieldPic[] fPic;
//    BoardDrawingManager boardM=new BoardDrawingManager(this);
    enum GAME_DIFFICULTY{
        EASY,
        NORMAL,
        HARD;
    }
    GAME_DIFFICULTY GameDif;

    public BoardPanel( ){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    //void setFilePic(){
//        fPic=new FieldPic[10];
//    }

//    void getFileImage(){
//        try {
//            //NO REVEAL, NO MINE, NO FLAG
//            fPic[0] = new FieldPic();
//            fPic[0].image = ImageIO.read(getClass().getResourceAsStream("src/res/no_reveal.png"));
//            //NO REVEAL, MINE, NO FLAG
//            fPic[1] = new FieldPic();
//            fPic[1].image = ImageIO.read(getClass().getResourceAsStream("src/res/no_reveal.png"));
//            //
//            //fPic[0]=new FieldPic();
//            //fPic[0].image= ImageIO.read(getClass().getResourceAsStream(""));
//            //fPic[0]=new FieldPic();
//            //fPic[0].image= ImageIO.read(getClass().getResourceAsStream(""));
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
    public void GameStart(){

        GameDif=GAME_DIFFICULTY.NORMAL;
        generateFields(6,9);
        fillFields();
        fillFieldWithMines();
        showBoard();
        //setBoardForDraw();

    }

    public void startGameThread(){
       gameThread=new Thread(this);
       gameThread.start();
    }

    public void update(){

    }

    @Override
    public void run() {

    }

    void generateFields(int col, int row){
         this.col=col;
         this.row=row;

         //DRAW
        //fieldDraws= new FieldDraw[col][row];

        fields=new Field[col][row];
        //gCol - GENERATE COL, gRow = GENERATE ROW
        for (int gCol = 0; gCol < col ; gCol++) {
            for (int gRow = 0; gRow < row ; gRow++) {
                fields[gCol][gRow]=new Field();
                //fieldDraws[gCol][gRow]=new FieldDraw();
            }
        }
    }

    void fillFields(){
        //fCol - FILL COL, fRow - FILL ROW
        for (int fCol = 0; fCol <col ; fCol++) {
            for (int fRow = 0; fRow <row ; fRow++) {
                fields[fCol][fRow].setFlag(false);
                fields[fCol][fRow].setRevealed(false);
                fields[fCol][fRow].setMine(false);

//                fieldDraws[fCol][fRow].setFlag(0);
//                fieldDraws[fCol][fRow].setMine(0);
//                fieldDraws[fCol][fRow].setRevealed(0);
            }
        }
    }
    void fillFieldWithMines() {

        Random RandomCol = new Random();
        Random RandomRow = new Random();

        int randomCol;
        int randomRow;
        int bombsToPlace = 0;

        //10% BOARD COVERED IN BOMBS
        if (GameDif == GAME_DIFFICULTY.EASY) {
            bombsToPlace = (int) (0.1 * (row * col));
        }//20% BOARD COVERED IN BOMBS
        else if (GameDif == GAME_DIFFICULTY.NORMAL) {
            bombsToPlace = (int) (0.2 *(row * col));
        } //30% BOARD COVERED IN BOMBS
        else if (GameDif == GAME_DIFFICULTY.HARD) {
            bombsToPlace = (int) (0.3 *(row * col));
        }

        while (bombsToPlace != 0) {

            randomCol = RandomCol.nextInt(col);
            randomRow = RandomRow.nextInt(row);

            if (!fields[randomCol][randomRow].isMine() &&
                    !fields[randomCol][randomRow].isRevealed()){
                fields[randomCol][randomRow].setMine(true);
                bombsToPlace--;
            }
        }
    }

    int bombsLeftBoard(){
        int bombsLeft=0;

        for (int bCol = 0; bCol <col ; bCol++) {
            for (int bRow = 0; bRow <row ; bRow++) {
                if (fields[bCol][bRow].mine){
                    bombsLeft++;
                }
            }
        }

        return bombsLeft;
    }

    void toggleReveal(int col, int row){
        if (!fields[col][row].isRevealed()) {
            fields[col][row].setRevealed(true);
        }
    }
    void toggleFlag(int col, int row){
        if (!fields[col][row].isFlag()){
            fields[col][row].setFlag(true);
        } else if (fields[col][row].isFlag()) {
            fields[col][row].setFlag(false);
        }
    }

    void showBoard(){

        for (int i = 0; i <col ; i++) {
            for (int j = 0; j <row ; j++) {

                System.out.print("{");

                if (!fields[i][j].isFlag()){
                    System.out.print(".");
                }else if (fields[i][j].isFlag()){
                    System.out.print("F");

                }

                if (!fields[i][j].isRevealed()){
                    System.out.print(".");
                }else if (fields[i][j].isRevealed()){
                    System.out.print("R");
                }

                if (!fields[i][j].isMine()){
                    System.out.print(".");
                }else if (fields[i][j].isMine()){
                    System.out.print("M");
                }

                System.out.print("}");
            }

            System.out.println();
        }

    }

//    public void setBoardForDraw(){
//
//        for (int iCol=0; iCol < col; iCol++) {
//            for (int iRow = 0; iRow < row; iRow++) {
//                if (!fields[iCol][iRow].isRevealed()){
//                    //NO REVEALED
//                    fieldDraws[iCol][iRow].setRevealed(0);
//
//                    if (!fields[iCol][iRow].isMine()){
//                        //NO REVEALED, NO MINE
//                        fieldDraws[iCol][iRow].setMine(0);
//
//                        if (!fields[iCol][iRow].isFlag()){
//                            //NO REVEALED, NO MINE, NO FLAG
//                            fieldDraws[iCol][iRow].setFlag(0);
//
//                        }else if (fields[iCol][iRow].isFlag()){
//                            //NO REVEALED, NO MINE, FLAG
//                            fieldDraws[iCol][iRow].setFlag(1);
//                        }
//
//                    }else if (fields[iCol][iRow].isMine()){
//                        //NO REVEALED, MINE
//                        fieldDraws[iCol][iRow].setMine(1);
//
//                        if (!fields[iCol][iRow].isFlag()){
//                            //NO REVEALED, MINE, NO FLAG
//                            fieldDraws[iCol][iRow].setFlag(0);
//
//                        }else if (fields[iCol][iRow].isFlag()){
//                            //NO REVEALED, MINE, FLAG
//                            fieldDraws[iCol][iRow].setFlag(1);
//                        }
//                    }
//                }else if (fields[iCol][iRow].isRevealed()){
//                    //REVEALED
//                    fieldDraws[iCol][iRow].setRevealed(1);
//
//                    if (!fields[iCol][iRow].isMine()){
//                        //REVEALED, NO MINE
//                        fieldDraws[iCol][iRow].setMine(0);
//
//                        if (!fields[iCol][iRow].isFlag()){
//                        //REVEALED, NO MINE, NO FLAG
//                            fieldDraws[iCol][iRow].setFlag(0);
//
//                        }else if (fields[iCol][iRow].isFlag()){
//                            //REVEALED, NO MINE, FLAG
//                            fieldDraws[iCol][iRow].setFlag(1);
//                        }
//
//                    }else if (fields[iCol][iRow].isMine()){
//                        //REVEALED, MINE
//                        fieldDraws[iCol][iRow].setMine(1);
//
//                        if (!fields[iCol][iRow].isFlag()){
//                            //REVEALED, MINE, NO FLAG
//                            fieldDraws[iCol][iRow].setFlag(0);
//
//                        }else if (fields[iCol][iRow].isFlag()){
//                            //REVEALED, MINE, FLAG
//                            fieldDraws[iCol][iRow].setFlag(1);
//                        }
//                    }
//                }
//            }
//        }
//    }

//    public void draw(Graphics2D g2){
//
//        for (int sCol = 0; sCol <col ; sCol++) {
//            for (int sRow = 0; sRow <row ; sRow++) {
//                System.out.print("[");
//                if (fieldDraws[sCol][sRow].getRevealed()==1){
//                    //REVEALED
//                    System.out.print("R,");
//                    if (fieldDraws[sCol][sRow].getMine()==0){
//                        //REVEALED, NO MINE
//                        System.out.print("nM,");
//                        if (fieldDraws[sCol][sRow].getFlag()==0){
//                            //REVEALED, NO MINE, NO FLAG
//                            System.out.print("nF");
//                        }else if (fieldDraws[sCol][sRow].getFlag()==1){
//                            //REVEALED, NO MINE, FLAG
//                            System.out.print("F");
//                        }
//                    } else if (fieldDraws[sCol][sRow].getMine()==1) {
//                        //REVEALED, MINE
//                        System.out.print("M,");
//                        if (fieldDraws[sCol][sRow].getFlag()==0){
//                            //REVEALED, MINE, NO FLAG
//                            System.out.print("nF");
//                        }else if (fieldDraws[sCol][sRow].getFlag()==1){
//                            //REVEALED, NO MINE, FLAG
//                            System.out.print("F");
//                        }
//                    }
//                } else if (fieldDraws[sCol][sRow].getRevealed()==0) {
//                    //NO REVEALED
//                    System.out.print("nR,");
//                    if (fieldDraws[sCol][sRow].getMine()==0){
//                        //NO REVEALED, NO MINE
//                        System.out.print("nM,");
//                        if (fieldDraws[sCol][sRow].getFlag()==0){
//                            //NO REVEALED, NO MINE, NO FLAG
//                            //System.out.print("nF");
//                            g2.drawImage(fPic[0].image,sRow,sCol,tileSize,tileSize,null);
//                        }else if (fieldDraws[sCol][sRow].getFlag()==1){
//                            //NO REVEALED, NO MINE, FLAG
//                            System.out.print("F");
//                        }
//                    } else if (fieldDraws[sCol][sRow].getMine()==1) {
//                        //NO REVEALED, MINE
//                        System.out.print("M,");
//                        if (fieldDraws[sCol][sRow].getFlag()==0){
//                            //REVEALED, MINE, NO FLAG
//                            //System.out.print("nF");
//                            g2.drawImage(fPic[0].image,sRow,sCol,tileSize,tileSize,null);
//                        }else if (fieldDraws[sCol][sRow].getFlag()==1){
//                            //REVEALED, NO MINE, FLAG
//                            System.out.print("F");
//                        }
//                    }
//                }
//                System.out.print("]");
//            }
//            System.out.println();
//        }
//    }
//
//    public void paintComponent(Graphics g){
//
//        super.paintComponent(g);
//        Graphics2D g2 = (Graphics2D) g;
//
//        draw(g2);
//    }

}
