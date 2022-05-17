package com.github.shotan3.chess;

import com.github.shotan3.chess.utils.ChessMoveValidator;

import java.util.Arrays;

public class ChessBoard implements Board {
    private String[][] board = new String[8][8];
    private boolean flipped = false;
    private String nowMakesMove = "white";

    public ChessBoard() {
        setUpBoard();
    }


    public boolean makeMove(String from, String to) {
        if (isValidMove(from, to)) {
            moveChessPiece(from, to);
            if (nowMakesMove.equals("black")) {
                nowMakesMove = "white";
            } else {
                nowMakesMove = "black";
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidMove(String from, String to) {
        if (isInBounds(from) && isInBounds(to)) {
            if (isNonEmptySquare(from)) {
                String chessPiece = getChessPiece(from);
                String chessPieceColor = getChessPieceColor(chessPiece);
                if (chessPieceColor.equals(nowMakesMove)) {
                    if (isMoveDefined(from, to, chessPiece, getChessPiece(to))) {
                        return true;
                    } else {
                        System.err.println(getChessPieceVerbose(from) + " doesn't have that kind of move defined according to chess rules!");
                        return false;
                    }
                } else {
                    System.err.println("Not " + nowMakesMove + "'s move!");
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isInBounds(String square) {
        if(square != null){
            if (square.length() == 2) {
                if (square.charAt(0) >= 'a' && square.charAt(0) <= 'h'
                        && square.charAt(1) >= '1' && square.charAt(1) <= '8') {
                    return true;
                } else {
                    System.err.println("Illegal format. Must start with characters from a to h and end with digits from 1 to 8. Example: a8");
                    return false;
                }
            } else {
                System.err.println("Illegal format. Must include only 2 characters!");
                return false;
            }
        }else{
            System.err.println("Illegal input. Null pointer exception!");
            return false;
        }
    }

    private boolean isNonEmptySquare(String square) {
        int[] entry = entry(square);
        String chessPiece = board[entry[0]][entry[1]];
        if (chessPiece.equals("X")) {
            System.err.println(square + " is empty square!");
            return false;
        } else {
            return true;
        }
    }

    private String getChessPiece(String from) {
        int[] entry = entry(from);
        String chessPiece = board[entry[0]][entry[1]];
        return chessPiece;
    }

    private String getChessPieceVerbose(String from) {
        int[] entry = entry(from);
        String chessPiece = board[entry[0]][entry[1]];
        switch (chessPiece.charAt(0)) {
            case 'R':
                return "Rook";
            case 'K':
                if (from.charAt(1) == 'n') {
                    return "Knight";
                } else {
                    return "King";
                }
            case 'B':
                return "Bishop";
            case 'Q':
                return "Queen";
            default:
                return "Pawn";
        }
    }

    private String getChessPieceColor(String chessPiece) {
        if (chessPiece.charAt(chessPiece.length() - 1) == 'w') {
            return "white";
        } else {
            return "black";
        }
    }

    //TODO: Needs implementation!
    private boolean isMoveDefined(String from, String to, String pieceName, String secondChessPiece) {
        return ChessMoveValidator.isValidChessMove(from, to, pieceName, secondChessPiece);
    }

    private void moveChessPiece(String from, String to) {
        int[] start = entry(from);
        int[] end = entry(to);
        board[end[0]][end[1]] = board[start[0]][start[1]];
        board[start[0]][start[1]] = "X";
    }

    /*
     * Converts chess notation to matrix entries
     */
    private int[] entry(String input) {
        int[] result = new int[2];
        result[0] = '8' - input.charAt(1);
        result[1] = input.charAt(0) - 'a';
        return result;
    }


    private void setUpBoard() {
        for (int i = 0; i < 8; i++) {
            board[1][i] = "Pb";
        }
        board[0][0] = board[0][7] = "Rb";
        board[0][1] = board[0][6] = "Knb";
        board[0][2] = board[0][5] = "Bb";
        board[0][3] = "Qb";
        board[0][4] = "Kb";

        for (int i = 0; i < 8; i++) {
            board[6][i] = "Pw";
        }

        board[7][0] = board[7][7] = "Rw";
        board[7][1] = board[7][6] = "Knw";
        board[7][2] = board[7][5] = "Bw";
        board[7][3] = "Qw";
        board[7][4] = "Kw";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    board[i][j] = "X";
                }
            }
        }
    }

    @Override
    public void flipBoard() {
        flipped = !flipped;
    }

    @Override
    public void draw() {
        System.out.println(toString());
    }

    @Override
    public String toString() {
        if (flipped) {
            String result = "\n";
            for (int row = 7; row >= 0; row--) {
                result += Integer.toString(8 - row) + "  ";
                for (char col = 'h'; col >= 'a'; col--) {
                    result += String.format("%-6s", board[row][col - 'a']);
                }
                result += "\n\n";
            }
            result += "   ";
            for (char col = 'h'; col >= 'a'; col--) {
                result += String.format("%-6c", col);
            }
            result += "\n";
            return result;
        } else {
            String result = "\n";
            result += "\n";
            for (int row = 0; row < 8; row++) {
                result += Integer.toString(8 - row) + "  ";
                for (char col = 'a'; col <= 'h'; col++) {
                    result += String.format("%-6s", board[row][col - 'a']);
                }
                result += "\n\n";
            }
            result += "   ";
            for (char col = 'a'; col <= 'h'; col++) {
                result += String.format("%-6c", col);
            }
            return result;
        }
    }
}
