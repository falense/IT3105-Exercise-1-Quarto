package players.ai;

import board.BoardState;
import board.Move;
import board.Piece;
import evaluation.Evaluator;
import evaluation.CloseToQuarto;

public class AlphaBetaAI extends BaseRecursiveAI {

	final String name = AlphaBetaAI.class.getName();
	private Evaluator eval;

	public  AlphaBetaAI(boolean verboseOutput, int maxDepth) {
		super(verboseOutput);
		eval = new CloseToQuarto();
	}


	
		
	private double searchAlphaBeta(BoardState state, final Piece place,double alpha, double beta,final boolean max,final int depth){
		counter ++;

		//System.out.println(alpha + " " + beta);
		if(state.haveAWinner()){
			if (max) return -1;
			else return 1;
		}
		if (state.isDraw())
			return 0;
		if (depth <= 0){
			return eval.evaluate(state,max);
		}
		if (max){
			alpha = -Double.MAX_VALUE;
		}
		else{
			beta = Double.MAX_VALUE;
		}
		Move best = null;
		double bestScore = 0;
		for (Move m : BoardState.getAllMoves(state, place)){
			BoardState newState = state.deepCopy();
			newState.placePiece(m.getPieceToPlace(), m.getX(),m.getY());
			newState.pickPiece(m.getPieceToGiveOpponent());
			if(newState.haveAWinner()){

				if (max) return 1;
				else return -1;
			}
			if (state.isDraw())
				return 0;
			double score = searchAlphaBeta(newState,m.getPieceToGiveOpponent(),alpha,beta,!max,depth-1);
			
			if (max){
				if (best == null || score > bestScore){
					bestScore = score;
					best = m;
					alpha = score;
					if(beta < score){
						//System.out.println("Cutting search, beta: " + beta + " score: " + score);
						return score;
					}
				}
			}
			else{
				if (best == null || score < bestScore){
					bestScore = score;
					best = m;
					beta = score;
					if (alpha > score){
						//System.out.println("Cutting search, alpha: " + alpha + " score: " + score);
						return score;
					}
				}
			}
		}
		return bestScore;
	}
	
	int counter = 0;
	@Override
	public Move getNextMove(BoardState state, Piece place) {
		
		if(state.getRemainingPieces().size()>=12){
			return randomizer.getNextMove(state, place);
		}
		
		// TODO Auto-generated method stub
		Move best = null;
		double bestScore = 0;

		double alpha = -Double.MAX_VALUE;
		double beta = Double.MAX_VALUE;
		for (Move m : BoardState.getAllMoves(state, place)){
			BoardState newState = state.deepCopy();
			newState.placePiece(place, m.getX(),m.getY());
			newState.pickPiece(m.getPieceToGiveOpponent());
			double score = searchAlphaBeta(newState,m.getPieceToGiveOpponent(),alpha,beta,false,maxDepth-1);
			if (best == null || score > bestScore){
				bestScore = score;
				best = m;
				alpha = bestScore;
			}
			//System.out.println(score);
		}
		//System.out.println("Counter " + counter );
		counter = 0;
		return best;
	}


	@Override
	public String getName() {
		return this.name + "(" + maxDepth + ")";
	}
	

}
