package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.Pieces.King;
import chess.Pieces.Rook;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private static Board board;

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessMatch(){
        turn = 1;
        currentPlayer = Color.WHITE;
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

    public boolean [][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        nextTurn();
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

        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            throw new ChessException("the chosen piece is not your");
        }

        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("there is no possible moves in this place");
        }
    }

    private void validateTargetPosition(Position source, Position target){
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("the chosen piece can't move to target position");
        }
    }

    private void nextTurn(){
        turn++;
        currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }
    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.PlacePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void InitialSetup(){
      placeNewPiece('a', 1, new Rook(board, Color.WHITE));
      placeNewPiece('b',  1, new King(board, Color.WHITE));


    }

}

