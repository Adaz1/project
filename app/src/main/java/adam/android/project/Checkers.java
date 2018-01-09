package adam.android.project;

public class Checkers {
    public static final int m = 8;
    public static final int n = 4;
    public static final int empty = 0;
    public static final int whitePiece = 1;
    public static final int blackPiece = 2;
    public static final int whiteDame = 3;
    public static final int blackDame = 4;
    public int whitePiecesAmount;
    public int blackPiecesAmount;

    public byte [][] board = null;
    public int turn = 0;    // white = 0, black = 1

    public Checkers() {
        board = new byte [m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (m <= 3) {
                    board[m][n] = blackPiece;
                }
                else if (m >= 6) {
                    board[m][n] = whitePiece;
                }
                else {
                    board[m][n] = empty;
                }
            }
        }
        whitePiecesAmount = 12;
        blackPiecesAmount = 12;
    }

    public int canCapture(int x, int y) {
        /*int piece = 0;
        int piecesAmount;

        if (turn == 0) {
            piece = 1;
            piecesAmount = whitePiecesAmount;
        }
        else {
            piece = 2;
            piecesAmount = blackPiecesAmount;
        }

        for (int i = 0; i < piecesAmount; i++) {

        }*/
        /*int oppPiece;
        if (board[x][y] == whitePiece)
            oppPiece = blackPiece;
        else
            oppPiece = whitePiece;

        int value[] = {0, 0, 0, 0};


        if (board[x+1][y+1] == oppPiece && board[x+2][y+2] == empty) {

        }
        if (board[x-1][y+1] == oppPiece && board[x-2][y+2] == empty) {
            value[2]++;
            canCapture(x-2, y+2);
        }
        if (board[x-1][y-1] == oppPiece && board[x-2][y-2] == empty) {

        }
        if (board[x+1][y-1] == oppPiece && board[x+2][y-2] == empty) {
            value++;
        }

        return value;*/
        return 0;
    }

    public void checkMove() {

    }


    public void makeMove(int x1, int y1, int x2, int y2) {
        if (turn == 0 && board[x1][y1] == whitePiece) {

        }
    }

    /*public static void main() {

    }*/
}

//          |
//      II  |   I
//          |
//  --------+--------
//          |
//     III  |  IV
//          |