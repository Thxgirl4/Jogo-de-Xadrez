package application;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.InputMismatchException;
import java.util.Scanner;

import static chess.ChessMatch.getPieces;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ChessMatch ChessMatch = new ChessMatch();

        while(true)
        {
            try {
                UI.clearScreen();
                UI.printMatch(ChessMatch);
                System.out.println();
                System.out.println("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean [][] possibleMoves = ChessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chess.ChessMatch.getPieces(), possibleMoves);
                System.out.println();
                System.out.println("Target: ");
                ChessPosition target = UI.readChessPosition(sc);


                ChessPiece capturedPiece = ChessMatch.performChessMove(source, target);

            } catch (ChessException | InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }

        }
    }
}
