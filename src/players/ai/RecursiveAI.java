package players.ai;

import java.util.ArrayList;

import board.BoardState;
import board.Move;
import board.Piece;


public class RecursiveAI extends BaseAI{


	int maxDepth = 2;
	public RecursiveAI(boolean verboseOutput, int maxDepth) {
		super(verboseOutput);
		this.maxDepth = maxDepth;
		// TODO Auto-generated constructor stub
	}


	private double evaluateState(BoardState state, boolean max){
		
		return 0;	
	}
	private double searchAlphaBeta(BoardState state, final Piece place,final boolean max,final int depth){
		counter ++;
		if(state.isGameOver()){
			return 1;
		}
		if (depth <= 0){
			return evaluateState(state,max);
		}
		Move best = null;
		double bestScore = 0;
		for (Move m : BoardState.getAllMoves(state, place)){
			BoardState newState = state.deepCopy();
			newState.placePiece(m.getPieceToPlace(), m.getX(),m.getY());
			newState.pickPiece(m.getPieceToGiveOpponent());
			if(newState.isGameOver()){
				return 0;
			}
			double score = searchAlphaBeta(newState,m.getPieceToGiveOpponent(),!max,depth-1);
			
			if (max){
				if (best == null || score > bestScore){
					bestScore = score;
					best = m;
				}
			}
			else{
				if (best == null || score < bestScore){
					bestScore = score;
					best = m;
				}
			}
		}
		return bestScore;
	}
	
	int counter = 0;
	@Override
	public Move getNextMove(BoardState state, Piece place) {
		// TODO Auto-generated method stub
		Move best = null;
		double bestScore = 0;
		for (Move m : BoardState.getAllMoves(state, place)){
			BoardState newState = state.deepCopy();
			newState.placePiece(place, m.getX(),m.getY());
			newState.pickPiece(m.getPieceToGiveOpponent());
			double score = searchAlphaBeta(newState,m.getPieceToGiveOpponent(),false,maxDepth-1);
			if (best == null || score > bestScore){
				bestScore = score;
				best = m;
			}
			//System.out.println(score);
		}
		System.out.println("Counter " + counter );
		counter = 0;
		return best;
	}
	

}