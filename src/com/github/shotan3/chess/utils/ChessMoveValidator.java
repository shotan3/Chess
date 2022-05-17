package com.github.shotan3.chess.utils;

public class ChessMoveValidator {
    public static boolean isValidChessMove(String from, String to, String chessPiece, String chessPieceOnSecondSquare) {
        switch (chessPiece.charAt(0)) {
            case 'R':
                return rookCheck(from, to, chessPieceOnSecondSquare);
            case 'K':
                if(chessPiece.charAt(1) == 'n'){
                    return knightCheck(from, to, chessPieceOnSecondSquare);
                }else{
                    return kingCheck(from, to, chessPieceOnSecondSquare);
                }
            case 'B':
                return bishopColor(from, to,chessPieceOnSecondSquare);
            case 'Q':
                return queenCheck(from, to, chessPieceOnSecondSquare);
            default:
                return pawnCheck(from,to,chessPiece, chessPieceOnSecondSquare);
        }
    }

    private static boolean rookCheck(String from, String to, String chessPieceOnSecondSquare){
        return false;
    }

    private static boolean knightCheck(String from, String to, String chessPieceOnSecondSquare){
        return false;
    }

    private static boolean bishopColor(String from, String to, String chessPieceOnSecondSquare){
        return false;
    }

    private static boolean queenCheck(String from, String to, String chessPieceOnSecondSquare){
        return false;
    }

    private static boolean kingCheck(String from, String to, String chessPieceOnSecondSquare){
        return false;
    }

    private static boolean pawnCheck(String from, String to, String pawn , String chessPieceOnSecondSquare) {
        return false;
    }

    private static boolean isWhiteSquare(String square) {
        return ((square.charAt(0) - 'a') + ('8' - square.charAt(1))) % 2 == 0;
    }

    public static boolean pawnCanPromote(String from, boolean pawnIsEmpty, boolean secondSquareIsEmpty){
        return false;
    }
}
