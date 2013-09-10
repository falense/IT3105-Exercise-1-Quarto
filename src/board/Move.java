package board;

public class Move {
	private final Piece pieceToGiveOpponent;
	private final Piece pieceToPlace;
	private final int x,y;
	
	public Move(Piece place,Piece give, int i, int j){
		pieceToGiveOpponent = give;
		pieceToPlace = place;
		x = i;
		y = j;
	}

	public Piece getPieceToGiveOpponent() {
		return pieceToGiveOpponent;
	}

	public Piece getPieceToPlace() {
		return pieceToPlace;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
