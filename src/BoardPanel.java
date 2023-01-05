import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.Random;

public class BoardPanel extends JPanel implements Runnable {
    public int col=6; //COL OF GAME BOARD
    public int row=9; //ROW OF GAME BOARD
    int originalTileSize = 12;
    int scale = 3;
    int maxScreenCol = 16; //COL OF SCREEN
    int maxScreenRow = 12; //ROW OF SCREEN
    int tileSize = originalTileSize * scale; //3*12=36
    int screenWidth = tileSize * maxScreenRow; //12*36=432
    int screenHeight = tileSize * maxScreenCol; //16*36=576
    int gameScreenWitdh=tileSize*row;
    int gameScreenHeight=tileSize*col;
    Thread gameThread; //
    Field[][] fields;
    MouseInput mouseInput=new MouseInput(); //MOUSE (mouseInput) INIT

    BoardDrawingManager boardM=new BoardDrawingManager(this); //BOARDDRAWING INIT
    BoardActions boardActions=new BoardActions(this);
    enum GAME_DIFFICULTY {
        EASY,
        NORMAL,
        HARD;
    }

    GAME_DIFFICULTY GameDif;

    public BoardPanel() {
        this.setPreferredSize(new Dimension(gameScreenWitdh, gameScreenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.addMouseListener(mouseInput);
    }
    public void startGameThread() {

        GameDif = GAME_DIFFICULTY.NORMAL; //DEFAULT GAME DIFF

        generateFields(); //GENERATE BOARD ONCE
        fillFieldWithMines(); //FILL BOARD FIELDS WITH MINES ONCE

        boardM.generateDrawFieldBoard(); //GENERATE SECOOND BOARD WITH SAME SIZE AS FIRST BOARD
        boardM.setCompareBoard(); //FILLS SECOOND BOARD WITH SAME PARAMETERS AS FIRST BOARD, BUT WITH INTS

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        boardM.setCompareBoard(); //GAME ACTION -> fields BOARD -> COPY field BOARD to
                                  // fieldDraws BOARD -> setCompare FOR COPY ->
                                  // DRAW COPIED fieldDraws BOARD
    }

    @Override
    public void run() {

        repaint();


        while (gameThread!=null){
            update();
            repaint();
        }

    }

    void generateFields() {

        fields = new Field[col][row];
        //gCol - GENERATE COL, gRow = GENERATE ROW
        for (int gCol = 0; gCol < col; gCol++) {
            for (int gRow = 0; gRow < row; gRow++) {

                Field field=new Field(false, false, false);
                fields[gCol][gRow]=field;
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
            bombsToPlace = (int) (0.2 * (row * col));
        } //30% BOARD COVERED IN BOMBS
        else if (GameDif == GAME_DIFFICULTY.HARD) {
            bombsToPlace = (int) (0.3 * (row * col));
        }

        while (bombsToPlace != 0) {

            randomCol = RandomCol.nextInt(col);
            randomRow = RandomRow.nextInt(row);

            if (!fields[randomCol][randomRow].isMine() &&
                    !fields[randomCol][randomRow].isRevealed()) {
                fields[randomCol][randomRow].setMine(true);
                bombsToPlace--;
            }
        }
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;

        boardM.draw(g2);  //DRAW fieldDraws BOARD
        g2.dispose();
    }

}

