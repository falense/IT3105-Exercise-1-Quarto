package players.ai;

import board.BoardState;
import board.Move;
import board.Piece;
import players.BasePlayer;

public abstract class BaseAI implements BasePlayer{

	abstract public Move getNextMove(BoardState b, Piece p);
}
