package players.ai.minmax.evaluation;

import java.util.ArrayList;

import board.BoardState;
import board.Piece;

public class AdvancedEvaluator extends BaseEvaluator {

	@Override
	public int evaluate(BoardState board, boolean max) {
		// TODO Auto-generated method stub
		
		int r = super.getBoardValue(board);
		r = r + 4*(super.valueOfPositions(board));
		
		if (r >= 1000){
			r = 999;
			System.out.println("r too high");
		}
		
		if (max){
			//System.out.println(r);
			return r;
		} else {
			//System.out.println(-r);
			return -r;
		}
		
	}
	



	

}
