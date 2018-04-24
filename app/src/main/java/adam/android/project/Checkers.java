package adam.android.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sac.game.GameState;
import sac.game.GameStateImpl;

public class Checkers extends GameStateImpl{
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
    public boolean isTurnWhite = true;
    public List<String> possibleCaptures;
    public String winningCommunicate;

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

    public Checkers(Checkers parent) {
        board = new byte[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = parent.board[i][j];
            }
        }
        whitePiecesAmount = parent.whitePiecesAmount;
        blackPiecesAmount = parent.blackPiecesAmount;
    }

    public List<GameState> generateChildren() {
        List<GameState> children = new ArrayList<GameState>();
        for (int i = 0; i < n; i++) {
            Checkers child = new Checkers(this);


            children.add(child);
        }

        return children;
    }

    byte [][] cloneBoard(byte [][] board) {
        byte [][] newBoard = new byte[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newBoard[i][j] = board[i][j];
            }
        }

        return  newBoard;
    }

    void checkBoard() {
        possibleCaptures = new ArrayList<>();
        byte [][] boardCopy = cloneBoard(board);
        int piece;
        if (isTurnWhite) {
            piece = whitePiece;
        }
        else {
            piece = blackPiece;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (boardCopy[i][j] == piece) {
                    canCapture2(cloneBoard(boardCopy), i, j, "");
                }
            }
        }
        for (int i = 0; i < possibleCaptures.size(); i++) {
            System.out.println(i + ": " + possibleCaptures.get(i));
        }
    }

    void canCapture2(byte [][] board, int x, int y, String capturePath) {
        int oppPiece;
        if (isTurnWhite)
            oppPiece = blackPiece;
        else
            oppPiece = whitePiece;

        try {

            if (board[x - 1][y + 1] == oppPiece && board[x - 2][y + 2] == empty) {
                simExecuteCapture(board, x, y, x - 1, y + 1, x - 2, y + 2);
                canCapture2(board, x - 2, y + 2, capturePath + x + y + (x - 2) + (y + 2));
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("Skip");
        }

        try {
            if (board[x - 1][y - 1] == oppPiece && board[x - 2][y - 2] == empty) {
                simExecuteCapture(board, x, y, x - 1, y - 1, x - 2, y - 2);
                canCapture2(board, x - 2, y - 2, capturePath + x + y + (x - 2) + (y - 2));
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("Skip");
        }

        try {
            if (board[x + 1][y - 1] == oppPiece && board[x + 2][y - 2] == empty) {
                simExecuteCapture(board, x, y, x + 1, y - 1, x + 2, y - 2);
                canCapture2(board, x + 2, y - 2, capturePath + x + y + (x + 2) + (y - 2));
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("Skip");
        }

        try {
            if (board[x + 1][y + 1] == oppPiece && board[x + 2][y + 2] == empty) {
                simExecuteCapture(board, x, y, x + 1, y + 1, x + 2, y + 2);
                canCapture2(board, x + 2, y + 2, capturePath + x + y + (x + 2) + (y + 2));
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            //System.out.println("Skip");
        }

        //System.out.println(" x: " +  x + " y: " + y + " inside: "+ capturePath);

        if (capturePath != "") {
            possibleCaptures.add(capturePath);
        }

        //return capturePath;
    }

    public void simExecuteCapture(byte [][] board, int x1, int y1, int x2, int y2, int x3, int y3) {
        if (isTurnWhite) {
            board[x2][y2] = empty;
            board[x3][y3] = whitePiece;;
        }
        else {
            board[x2][y2] = empty;
            board[x3][y3] = blackPiece;
        }
        board[x1][y1] = empty;
        //return board;
    }

    //deprecated
    public void canCapture(int x1, int y1, int x2, int y2) {
        /*int oppPiece;
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

        return value;*/
        if (turn == 0 && board[x1][y1] == whitePiece && board[x2][y2] == empty) {
            if (x1-x2 == 2 && y1-y2 == 2) {
                executeCapture(x1, y1, x2, y2, 1);
            }
            else if (x1-x2 == 2 && y1-y2 == -2) {
                executeCapture(x1, y1, x2, y2, -1);
            }
        }
        if (turn == 1 && board[x1][y1] == blackPiece && board[x2][y2] == empty) {
            if (x1 - x2 == -2 && y1 - y2 == 2) {
                executeCapture(x1, y1, x2, y2, 1);
            } else if (x1 - x2 == -2 && y1 - y2 == -2) {
                executeCapture(x1, y1, x2, y2, -1);
            }
        }
    }

    public void checkMove(int x1, int y1, int x2, int y2) {
        checkBoard();
        List<String> bestCaptures = new ArrayList<>();
        if (!possibleCaptures.isEmpty()) {
            int max = 0;
            for (int i = 0; i < possibleCaptures.size(); ++i) {
                if (max < possibleCaptures.get(i).length()) {
                    max = possibleCaptures.get(i).length();
                    bestCaptures.clear();
                    bestCaptures.add(possibleCaptures.get(i));
                }
                else if (max == possibleCaptures.get(i).length()) {
                    bestCaptures.add(possibleCaptures.get(i));
                }
            }

            for (int i = 0; i < bestCaptures.size(); ++i) {
                if (x1 == Character.getNumericValue(bestCaptures.get(i).charAt(0)) && y1 == Character.getNumericValue(bestCaptures.get(i).charAt(1))) {
                    if (x2 == Character.getNumericValue(bestCaptures.get(i).charAt(bestCaptures.get(i).length()-2)) && y2 == Character.getNumericValue(bestCaptures.get(i).charAt(bestCaptures.get(i).length()-1))) {
                        executeComplexCapture(bestCaptures.get(i));
                    }
                }
            }

        }
        else {
            if (isTurnWhite && board[x1][y1] == whitePiece && board[x2][y2] == empty) {
                if ((x1-1 == x2 && y1-1 == y2) || (x1-1 == x2 && y1+1 == y2)) {
                    makeMove(x1, y1, x2, y2);
                }
            }
            else if (!isTurnWhite && board[x1][y1] == blackPiece && board[x2][y2] == empty) {
                if ((x1+1 == x2 && y1-1 == y2) || (x1+1 == x2 && y1+1 == y2)) {
                    makeMove(x1, y1, x2, y2);
                }
            }
        }
    }


    public void makeMove(int x1, int y1, int x2, int y2) {
        board[x1][y1] = empty;
        if (isTurnWhite) {
            board[x2][y2] = whitePiece;
        }
        else {
            board[x2][y2] = blackPiece;
        }
        isTurnWhite = !isTurnWhite;
    }

    //deprecated
    public void executeCapture(int x1, int y1, int x2, int y2, int dir) {
        board[x1][y1] = empty;
        if (turn == 0) {
            board[x2][y2] = whitePiece;
            board[x1-1][y1-dir] = empty;
            turn = 1;
        }
        else {
            board[x2][y2] = blackPiece;
            board[x1+1][y1-dir] = empty;
            turn = 0;
        }
    }

    public void executeComplexCapture(String capture) {
        board[Character.getNumericValue(capture.charAt(0))][Character.getNumericValue(capture.charAt(1))] = empty;
        for (int i = 0; i < capture.length(); i += 4) {
            int x1, y1, x2, y2;
            x1 = Character.getNumericValue(capture.charAt(i));
            y1 = Character.getNumericValue(capture.charAt(i+1));
            x2 = Character.getNumericValue(capture.charAt(i+2));
            y2 = Character.getNumericValue(capture.charAt(i+3));

            board[x1 - ((x1-x2)/2)][y1 - ((y1-y2)/2)] = empty;
            if (isTurnWhite) {
                --blackPiecesAmount;
            }
            else {
                --whitePiecesAmount;
            }
        }
        if (isTurnWhite) {
            board[Character.getNumericValue(capture.charAt(capture.length() - 2))][Character.getNumericValue(capture.charAt(capture.length() - 1))] = whitePiece;
        }
        else {
            board[Character.getNumericValue(capture.charAt(capture.length() - 2))][Character.getNumericValue(capture.charAt(capture.length() - 1))] = blackPiece;
        }
        isTurnWhite = !isTurnWhite;

    }

    public void start() {

    }

    public int hashCode() {
        byte[] lin = new byte[m*n];
        int k = 0;
        for (int i = 0; i<n; i++)
            for (int j = 0; j<m; j++)
                lin[k++] = board[i][j];
        return Arrays.hashCode(lin);
    }

    public boolean isEnd() {
        if (whitePiecesAmount == 0) {
            winningCommunicate = "Black wins!";
            return true;
        }
        else if (blackPiecesAmount == 0) {
            winningCommunicate = "White wins!";
            return true;
        }
        return false;
    }
}

//          |
//      II  |   I
//          |
//  --------+--------
//          |
//     III  |  IV
//          |