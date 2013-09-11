package players.ai.minmax.evaluation;

import java.util.ArrayList;

import board.BoardState;
import board.Piece;

public abstract class BaseEvaluator {
	
	public BaseEvaluator(){
		
	}
	public abstract double evaluate(BoardState board, boolean max);
	
	protected int rowSameFeatureCount(Piece[] row){
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
	
	protected ArrayList<Piece> findWinners(BoardState board){
		ArrayList<Piece> pieceList = board.getRemainingPieces();
		ArrayList<Piece> outList = new ArrayList<Piece>();
		
		for (Piece piece : pieceList){
			if (board.isWinnablePiece(piece)){
				outList.add(piece);
			}
		}
		
		return outList;
		
	}

}
