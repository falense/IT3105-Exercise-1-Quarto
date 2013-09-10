package players.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import board.BoardState;
import board.Move;
import board.Piece;

public class NoviceAI extends BaseAI {
	
	public NoviceAI(boolean verboseOutput) {
		super(verboseOutput);
		// TODO Auto-generated constructor stub
	}

	public Move getNextMove(BoardState b, Piece place) {
		// TODO Auto-generated method stub
		ArrayList<Piece> remaining = b.getRemainingPieces();	
		Random r = new Random(System.currentTimeMillis());
		
		ArrayList<Move> myMoves = b.getAllMoves(b, place);
		ArrayList<Move> goodMoves = new ArrayList<Move>();
		
		
		//checking for winning moves:
		for (Move move : myMoves ){
			BoardState nextBoard = b.deepCopy();
			nextBoard.placePiece(move.getPieceToPlace(), move.getX(), move.getY());
			
				if (nextBoard.isGameOver()){
					printMessage("Novice AI: Placing piece " +move.pieceToPlace.getName() +" in slot " +move.getX() + " " + move.getY() );
					printMessage("Novice AI: Giving opponent piece: " +move.getPieceToGiveOpponent().getName());
					
					return move;
				} else {
						if (!nextBoard.isWinnablePiece(move.pieceToGiveOpponent)){
							goodMoves.add(move);
						}
				}
		}
		
		
		
		Move myMove;
		
		if (goodMoves.isEmpty()){
			myMove = myMoves.get(r.nextInt(myMoves.size()));
		} else {
			//Collections.shuffle(goodMoves);
			myMove = goodMoves.get(r.nextInt(goodMoves.size()));
		}
		
		
		printMessage("Novice AI: Placing piece " +myMove.pieceToPlace.getName() +" in slot " +myMove.getX() + " " + myMove.getY() );
		printMessage("Novice AI: Giving opponent piece: " +myMove.getPieceToGiveOpponent().getName());
		
		return myMove;
		

	}

}
