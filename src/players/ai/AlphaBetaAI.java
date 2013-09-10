package players.ai;

import board.BoardState;
import board.Move;
import board.Piece;

public class AlphaBetaAI extends BaseAI {

	final String name = "AlphaBetaAI";
	int maxDepth = 2;

	NoviceAI randomizer;
	public  AlphaBetaAI(boolean verboseOutput, int maxDepth) {
		super(verboseOutput);
		this.maxDepth = maxDepth;
		randomizer = new NoviceAI(verboseOutput);	
		// TODO Auto-generated constructor stub
	}


	/*private double evaluateState(BoardState state, boolean max){
		String key = state.toHash();
		if (AlphaBetaTrainer.learning.containsKey(key)) {
			int d = (int) AlphaBetaTrainer.learning.get(key);
			System.out.println("Successfull lookup" + d);
			if (max)
				return d;
			else
				return -d;
		}
		return 0;	
	}*/
	private int rowSameFeatureCount(Piece[] row){
		int features[] = new int[4];
		for (int k = 0; k < 4; k++)
			features[k] = 0;
        int empty = 0;
		
		for (int j = 0 ; j < 4 ; j++){
			if (row[j] == null) empty++;
			else{
				for (int k = 0; k < 4; k++){
					if (row[j].getFeatures()[k]){
						features[k]++;
					}
				}
			}
		}
		int max = 0;
		for (int k = 0; k < 4; k++){
			if (features[k]> max){
				max = features[k];
			}
			else if(4-features[k]-empty > max){
				max = 4-features[k]-empty;
			}
		}
		return max;
	}
	private double evaluateState(BoardState state, boolean max){
		Piece[][] checkList = state.getRowsAndColumns();
		double r = 0;
		for (int i = 0 ; i < 10 ; i++){
			int t = rowSameFeatureCount(checkList[i]);
			switch (t){
			case 0:
				break;
			case 1:
				r += 0.1*0.1;
				break;
			case 2:
				r += 0.1*0.4;
				break;
			case 3:
				r += 0.1;
				break;
			}
		}
		if (max)
			return r;
		else
			return -r;
	}
	
	private double evaluateState1(BoardState state){
		Piece[][] checkList = state.getRowsAndColumns();
		
		
		
		
		
		
		
		
		
		
		
		
		return 0;
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
			return evaluateState(state,max);
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
		// TODO Auto-generated method stub
		return this.name;
	}
	

}
