package evaluation;

import board.BoardState;

public interface Evaluator {
	public double evaluate(BoardState board, boolean max);
}
