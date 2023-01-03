import java.util.Random;

public class Board {
    int col;
    int row;
    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
    Field[][] fields;
    enum GAME_DIFFICULTY{
        EASY,
        NORMAL,
        HARD,
        DEV;
    }
    GAME_DIFFICULTY GameDif;

    void setDifficulty(GAME_DIFFICULTY GameDif){
        this.GameDif=GameDif;
    }
    void generateFields(int col, int row){
        this.setCol(col);
        this.setRow(row);

        fields=new Field[getCol()][getRow()];

        for (int i = 0; i < getCol() ; i++) {
            for (int j = 0; j < getRow() ; j++) {
                fields[i][j]=new Field();
            }
        }
    }
    void fillFields(){

        generateFields(getCol(),getRow());

        for (int i = 0; i <getCol() ; i++) {
            for (int j = 0; j <getRow() ; j++) {
                fields[i][j].setFlag(false);
                fields[i][j].setRevealed(false);
                fields[i][j].setMine(false);
            }
        }
    }
    void fillFieldWithMines() {

        Random RandomCol = new Random();
        int randomCol;
        Random RandomRow = new Random();
        int randomRow;

        int bombsToPlace = 0;

        //10% BOARD COVERED IN BOMBS
        if (GameDif == GAME_DIFFICULTY.EASY) {
            bombsToPlace = (int) (0.1 * (getRow() * getCol()));
        }//20% BOARD COVERED IN BOMBS
        else if (GameDif == GAME_DIFFICULTY.NORMAL) {
            bombsToPlace = (int) (0.2 * (getRow() * getCol()));
        } //30% BOARD COVERED IN BOMBS
        else if (GameDif == GAME_DIFFICULTY.HARD) {
            bombsToPlace = (int) (0.3 * (getCol() * getRow()));
        } else if (GameDif == GAME_DIFFICULTY.DEV) {

        }

        while (bombsToPlace != 0) {

            randomCol = RandomCol.nextInt(getCol());
            randomRow = RandomRow.nextInt(getRow());

            if (!fields[randomCol][randomRow].isMine() &&
                    !fields[randomCol][randomRow].isRevealed()){
                fields[randomCol][randomRow].setMine(true);
                bombsToPlace--;
            }
        }
    }

    int bombsLeftBoard(){
        int bombsLeft=0;
        for (int i = 0; i <getCol() ; i++) {
            for (int j = 0; j <getRow() ; j++) {
                if (fields[i][j].mine){
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

        for (int i = 0; i <getCol() ; i++) {
            for (int j = 0; j <getRow() ; j++) {
                System.out.print("{");
                if (!fields[i][j].isFlag()){
                    System.out.print(".");
                }else if (fields[i][j].isFlag()){
                    System.out.print("F");

                }if (!fields[i][j].isRevealed()){
                    System.out.print(".");
                }else if (fields[i][j].isRevealed()){
                    System.out.print("R");

                }if (!fields[i][j].isMine()){
                    System.out.print(".");
                }else if (fields[i][j].isMine()){
                    System.out.print("M");
                }
                System.out.print("}");
            }
            System.out.println();
        }

    }
}
