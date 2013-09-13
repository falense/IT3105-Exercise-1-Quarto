package players.ai.minmax;

import players.ai.minmax.evaluation.BaseEvaluator;
import players.ai.minmax.evaluation.CloseToQuarto;
import players.ai.minmax.evaluation.EvenWinningPieces;
import board.BoardState;
import board.Move;
import board.Piece;

public class MinMaxAI extends BaseMinMax {

	final String name = MinMaxAI.class.getName();

	public  MinMaxAI(boolean verboseOutput, int maxDepth) {
		super(verboseOutput,maxDepth);
		eval = new EvenWinningPieces();
	}

	@Override
	public String getName() {
		return this.name + "(" + maxDepth + ")";
	}
	

	
		


}
