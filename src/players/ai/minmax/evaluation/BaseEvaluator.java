package players.ai.minmax.evaluation;

import java.util.ArrayList;

import board.BoardState;
import board.Piece;

public abstract class BaseEvaluator {
	
	int[][] valueOfPositions = { { 3, 2, 2, 3 }, { 2, 3, 3, 2 }, { 2, 3, 3, 2 }, { 3, 2, 2, 3 } };
	
	public BaseEvaluator(){
		
	}
	public abstract int evaluate(BoardState board, boolean max);
	
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
	
	protected ArrayList<Piece> findRowCompleters(BoardState board){
		ArrayList<Piece> pieceList = board.getRemainingPieces();
		ArrayList<Piece> outList = new ArrayList<Piece>();
		ArrayList<Piece> testList = new ArrayList<Piece>();
		
		Piece[][] checkList = board.getRowsAndColumns();
		
		for (int i = 0 ; i < checkList.length ; i++){
			int t = rowSameFeatureCount(checkList[i]);
			if (t==3){
				for (int j = 1 ; j < 4 ; j++){
					if (checkList[i][j]!=null)
						testList.add(checkList[i][j]);
				}
			}
		}
		return outList;
	}
	
	protected int getSameFeatureNumber(Piece p1, Piece p2){
		for (int i = 0 ; i < 4 ; i++){
			if (true==p1.getFeatures()[i]==p1.getFeatures()[i])
				return i;
			
		}
		return 4;
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
	
	//Adds points if positions that are a part of many winning rows are open. (Should it be taken?)
	protected double valueOfPositions(BoardState b){
		int points = 0;
        for(int x = 0; x <4; x++){
            for(int y =0; y < 4; y++){
                if(b.isEmpty(x, y)){
                	points += valueOfPositions[x][y];
                }
            }
        }
        return points;
	}

}
