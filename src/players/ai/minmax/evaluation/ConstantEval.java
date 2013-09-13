package players.ai.minmax.evaluation;

import board.BoardState;
import board.Piece;

public class ConstantEval extends BaseEvaluator {

	@Override
	public double evaluate(BoardState board, boolean max) {

		if (max)
			return 0.5;
		else
			return -0.5;
	
	}


}
