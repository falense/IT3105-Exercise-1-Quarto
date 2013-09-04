package board;

public class Move {
	public final Piece pieceToGiveOpponent;
	public final Piece pieceToPlace;
	public final int x,y;
	
	public Move(Piece place,Piece give, int i, int j){
		pieceToGiveOpponent = give;
		pieceToPlace = place;
		x = i;
		y = j;
	}
}
