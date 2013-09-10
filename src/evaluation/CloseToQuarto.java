package evaluation;

import board.BoardState;
import board.Piece;

public class CloseToQuarto extends BaseEvaluator implements Evaluator {

	@Override
	public double evaluate(BoardState board, boolean max) {
		Piece[][] checkList = board.getRowsAndColumns();
		double r = 0;
		for (int i = 0 ; i < 10 ; i++){
			int t = super.rowSameFeatureCount(checkList[i]);
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


}
