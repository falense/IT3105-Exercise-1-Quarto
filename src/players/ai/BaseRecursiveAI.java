package players.ai;

import board.BoardState;
import board.Move;
import board.Piece;

public abstract class BaseRecursiveAI extends BaseAI{

	protected int maxDepth = 2;
	protected NoviceAI randomizer;
	
	public BaseRecursiveAI(boolean verboseOutput) {
		super(verboseOutput);
		this.maxDepth = maxDepth;
		randomizer = new NoviceAI(verboseOutput);
	}

}
