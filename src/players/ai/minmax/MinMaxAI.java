package players.ai.minmax;

import players.ai.minmax.evaluation.BaseEvaluator;
import players.ai.minmax.evaluation.CloseToQuarto;
import board.BoardState;
import board.Move;
import board.Piece;

public class MinMaxAI extends BaseMinMax {

	final String name = MinMaxAI.class.getName();

	public  MinMaxAI(boolean verboseOutput, int maxDepth) {
		super(verboseOutput,maxDepth);
		eval = new CloseToQuarto();
	}

	@Override
	public String getName() {
		return this.name + "(" + maxDepth + ")";
	}
	

	
		


}
