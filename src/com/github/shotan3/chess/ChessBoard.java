package com.github.shotan3.chess;

import java.util.Arrays;

public class ChessBoard implements Board{
    private String[][] board = new String[8][8];

    public ChessBoard(){
        setUpBoard();
    }

    private void setUpBoard(){

    }

    @Override
    public void flipBoard() {

    }

    @Override
    public void draw() {

    }

    @Override
    public String toString() {
        return Arrays.deepToString(board);
    }
}
