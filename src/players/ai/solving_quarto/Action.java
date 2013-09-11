package players.ai.solving_quarto;

import board.Piece;

public class Action {
	public final Piece p;
	public final int x , y;
	public Action(Piece p, int x, int y){
		this.p = p;
		this.x = x;
		this.y = y;
	}
}
