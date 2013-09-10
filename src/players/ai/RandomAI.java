package players.ai;

import java.util.ArrayList;
import java.util.Random;

import board.BoardState;
import board.Move;
import board.Piece;

public class RandomAI extends BaseAI {

	public RandomAI(boolean verboseOutput) {
		super(verboseOutput);
		// TODO Auto-generated constructor stub
	}
	protected void printMessage(String msg){
		super.printMessage(msg);
	}

	@Override
	public Move getNextMove(BoardState b, Piece place) {
		// TODO Auto-generated method stub
		ArrayList<Piece> remaining = b.getRemainingPieces();
		
		Random r = new Random(System.currentTimeMillis());
		
		if (place == null){
			printMessage(BaseAI.class.getName() + " First round picking a piece");
			place = remaining.get(r.nextInt(remaining.size()));
			remaining.remove(place);
		}

		printMessage(BaseAI.class.getName() + " Placing the piece");
		int x = r.nextInt(4);
		int y = r.nextInt(4);
		while(!b.isEmpty(x, y)){
			printMessage(BaseAI.class.getName() + " (" + x + "," + y + ") was taken, trying again");
			x = r.nextInt(4);
			y = r.nextInt(4);
		}
		

		printMessage(BaseAI.class.getName() + " Picking a piece for the opponent");
		Piece give = remaining.get(r.nextInt(remaining.size()));
		
		printMessage(b.isEmpty(x, y)+ "");
		printMessage(BaseAI.class.getName() + ": Piece " + place.getName() + " was placed at (" + x + "," + y + "). Chose " + give.getName() + " for the opponent.");
		return new Move(place,give,x,y);
	}

}
