package players.ai;

import java.util.ArrayList;
import java.util.Random;

import board.BoardState;
import board.Move;
import board.Piece;

public class RandomAI extends BaseAI {

	@Override
	public Move getNextMove(BoardState b, Piece place) {
		// TODO Auto-generated method stub
		ArrayList<Piece> remaining = b.getRemainingPieces();

		int x, y;
		Random r = new Random(0);
		if (place == null){
			place = remaining.get(r.nextInt()%remaining.size());
			remaining.remove(place);
		}
		
		x = r.nextInt()%4;
		y = r.nextInt()%4;
		
		Piece give = remaining.get(r.nextInt()%remaining.size());
		
		
		
		return new Move(place,give,x,y);
	}

}
