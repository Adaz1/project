package adam.android.project;

public class Checkers {
    public static final int m = 8;
    public static final int n = 8;
    public static final int whiteTile = -1;
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
                if (i <= 2 && (i+j)%2 == 1) {
                    board[i][j] = blackPiece;
                }
                else if (i >= 5 && (i+j)%2 == 1) {
                    board[i][j] = whitePiece;
                }
                else if ((i+j)%2 == 1){
                    board[i][j] = empty;
                }
                else {
                    board[i][j] = whiteTile;
                }
            }
        }
        whitePiecesAmount = 12;
        blackPiecesAmount = 12;
    }

    public int[] canCapture(int x, int y) {
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

        int oppPiece;
        if (board[x][y] == whitePiece)
            oppPiece = blackPiece;
        else
            oppPiece = whitePiece;

        int value[] = {0, 0, 0, 0};

        if (board[x+1][y+1] == oppPiece && board[x+2][y+2] == empty) {
            value[0]++;
        }
        if (board[x-1][y+1] == oppPiece && board[x-2][y+2] == empty) {
            value[1]++;
        }
        if (board[x-1][y-1] == oppPiece && board[x-2][y-2] == empty) {
            value[2]++;
        }
        if (board[x+1][y-1] == oppPiece && board[x+2][y-2] == empty) {
            value[3]++;
        }

        return value;
    }

    public void checkMove(int x1, int y1, int x2, int y2) {
        if (turn == 0 && board[x1][y1] == whitePiece && board[x2][y2] == empty) {
            if ((x1-1 == x2 && y1-1 == y2) || (x1-1 == x2 && y1+1 == y2)) {
                makeMove(x1, y1, x2, y2);
            }
        }
        else if (turn == 1 && board[x1][y1] == blackPiece && board[x2][y2] == empty) {
            if ((x1+1 == x2 && y1-1 == y2) || (x1+1 == x2 && y1+1 == y2)) {
                makeMove(x1, y1, x2, y2);
            }
        }
    }


    public void makeMove(int x1, int y1, int x2, int y2) {
        board[x1][y1] = empty;
        if (turn == 0) {
            board[x2][y2] = whitePiece;
            turn = 1;
        }
        else {
            board[x2][y2] = blackPiece;
            turn = 0;
        }
    }

    public void start() {

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