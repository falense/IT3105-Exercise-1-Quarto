package players.ai.minmax;

import players.ai.minmax.evaluation.BaseEvaluator;
import players.ai.minmax.evaluation.CloseToQuarto;
import board.BoardState;
import board.Move;
import board.Piece;

public class MinMaxAI2 extends BaseMinMax {

	final String name = MinMaxAI2.class.getName();
	private BaseEvaluator eval;

	@Override
	public String getName() {
		return this.name + "(" + maxDepth + ")";
	}
	
	public  MinMaxAI2(boolean verboseOutput, int maxDepth) {
		super(verboseOutput,maxDepth);
		eval = new CloseToQuarto();
	}




}
