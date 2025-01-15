package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.Pieces.King;
import chess.Pieces.Rook;

public class ChessMatch {
    private static Board board;
    public ChessMatch(){
        board = new Board(8,8);
        InitialSetup();
    }
    public static ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()] [board.getColumns()];
        for(int i = 0; i<board.getRows(); i++){
            for(int j = 0; j<board.getColumns(); j++){
                mat [i] [j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }
    private void placeNewPiece(char column, char row, ChessPiece Piece){
        board.PlacePiece(Piece, new ChessPosition(column, row ).toPosition());
    }
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece)capturedPiece;
    }
    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.PlacePiece(p, target);
        return capturedPiece;
    }
    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("there is no piece on source");
        }
    }


    private void InitialSetup(){
      placeNewPiece('b', (char) 6, new Rook(board, Color.WHITE));
      placeNewPiece('e', (char) 1, new King(board, Color.WHITE));


    }

}

