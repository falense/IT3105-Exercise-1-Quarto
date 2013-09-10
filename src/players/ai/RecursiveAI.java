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
		if (state.isGameOver()){
			if (max){
				return -1;
			}
			else
				return 1;
		}
		return 0;	
	}
	private double searchAlphaBeta(BoardState state, Piece place,boolean max,int depth){
		if(state.isGameOver()){
			if (max) return -1;
			else return 1;
		}
		if (depth == 0){
			return evaluateState(state,max);
		}
		ArrayList<Piece> remaining = state.getRemainingPieces();
		Move best = null;
		double bestScore = 0;
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				for (int g = 0; g < remaining.size(); g++){
					BoardState newState = state.deepCopy();
					if (!newState.isEmpty(i, j)) continue;
					newState.placePiece(place, i,j);
					Piece give = remaining.get(g);
					double score = searchAlphaBeta(newState,give,!max,depth-1);
					
					if (max){
						if (best == null || score > bestScore){
							bestScore = score;
							best = new Move(place,give,i,j);
						}
					}
					else{
						if (best == null || score < bestScore){
							bestScore = score;
							best = new Move(place,give,i,j);
						}
					}
				}
			}
		}
		return 0;
	}
	
	
	@Override
	public Move getNextMove(BoardState state, Piece place) {
		// TODO Auto-generated method stub

		ArrayList<Piece> remaining = state.getRemainingPieces();

		Move best = null;
		double bestScore = 0;
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				for (int g = 0; g < remaining.size(); g++){
					BoardState newState = state.deepCopy();
					Piece give = remaining.get(g);

					if (!newState.isEmpty(i, j)) continue;
					newState.placePiece(place, i,j);
					
					double score = searchAlphaBeta(newState,give,false,maxDepth);
					
					
					if (best == null || score > bestScore){
						bestScore = score;
						best = new Move(place,give,i,j);
					}
				
				}
				
			}
		}
		return best;
	}
	

}
