package players.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import board.BoardState;
import board.Move;
import board.Piece;

public class NoviceAI extends BaseAI {

	public Move getNextMove(BoardState b, Piece place) {
		// TODO Auto-generated method stub
		ArrayList<Piece> remaining = b.getRemainingPieces();	
		Random r = new Random(System.currentTimeMillis());
		
		if (place == null){
			System.out.println(BaseAI.class.getName() + " First round picking a piece");
			place = remaining.get(r.nextInt(remaining.size()));
			remaining.remove(place);
		}
		
		ArrayList<Move> myMoves = b.getAllMoves(b, place);
		ArrayList<Move> goodMoves = new ArrayList<Move>();
		Collections.shuffle(myMoves);
		
		
		for (Move move : myMoves ){
			BoardState nextBoard = b.deepCopy();
			nextBoard.simulatePlacement(move.getPieceToPlace(), move.getX(), move.getY());
			
				if (nextBoard.isGameOver()){
					return move;
				}		
				
		}
		
		
		Move myMove = myMoves.get(r.nextInt(myMoves.size()));
		
		System.out.println("Novice AI: Placing piece " +myMove.pieceToPlace.getName() +" in slot " +myMove.getX() + " " + myMove.getY() );
		System.out.println("Novice AI: Giving opponent piece: " +myMove.getPieceToGiveOpponent().getName());
		
		return myMove;
		

	}

}
