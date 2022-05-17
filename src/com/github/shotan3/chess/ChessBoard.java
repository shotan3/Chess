package com.github.shotan3.chess;

import java.util.Arrays;

public class ChessBoard implements Board {
    private String[][] board = new String[8][8];
    private boolean flipped = false;
    private String nowMakesMove = "white";

    public ChessBoard() {
        setUpBoard();
    }


    public boolean makeMove(String from, String to){
        if(isValidMove(from, to)){
            moveChessPiece(from, to);
            if(nowMakesMove.equals("black")){
                nowMakesMove = "white";
            }else{
                nowMakesMove = "black";
            }
            return true;
        }else{
            return false;
        }
    }

    private boolean isValidMove(String from, String to){
        return true;
    }

    private void moveChessPiece(String from, String to){
        int[] start = entry(from);
        int[] end = entry(to);
        board[end[0]][end[1]] = board[start[0]][start[1]];
        board[start[0]][start[1]] = "X";
    }

    /*
    * Converts chess notation to matrix entries
    */
    public int[] entry(String input){
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
