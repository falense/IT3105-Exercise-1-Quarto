package players.ai;

import java.util.ArrayList;

import board.BoardState;
import board.Move;
import board.Piece;

public class AlphaBetaAI extends BaseAI {
	
	int maxDepth = 4;
	private double evaluateState(BoardState state){
		return 0;	
	}
	private double searchAlphaBeta(BoardState state, Piece place,double alpha, double beta,boolean max,int depth){
		if (depth == 0){
			return evaluateState(state);
		}
		ArrayList<Piece> remaining = state.getRemainingPieces();
		Move best = null;
		double bestScore = 0;
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				for (int g = 0; g < remaining.size(); g++){
					BoardState newState = state.deepCopy();
					Piece give = remaining.get(g);
					double score = searchAlphaBeta(newState,give,alpha,beta,!max,depth-1);
					
					if (max){
						if (best == null || score > alpha){
							alpha = score;
							best = new Move(place,give,i,j);
							if (alpha > beta){
								return alpha;
							}
						}
					}
					else{
						if (best == null || score < beta){
							beta = score;
							best = new Move(place,give,i,j);
							if (beta < alpha){
								return beta;
							}
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
		double alpha = Double.MAX_VALUE;
		double beta = -Double.MAX_VALUE;

		Move best = null;
		double bestScore = 0;
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				for (int g = 0; g < remaining.size(); g++){
					BoardState b = state.deepCopy();
					Piece give = remaining.get(g);
					double score = searchAlphaBeta(b,give,alpha,beta,true,maxDepth);
					
					
					if (best == null || score > alpha){
						alpha = score;
						best = new Move(place,give,i,j);
					}
				
				}
				
			}
		}
		return null;
	}
	

}
