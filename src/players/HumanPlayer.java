package players;

import java.util.ArrayList;

import board.BoardState;
import board.Move;
import board.Piece;


public class HumanPlayer implements BasePlayer{

	@Override
	public Move getNextMove(BoardState b, Piece p) {
		// TODO Auto-generated method stub
		ArrayList<Piece> remaining = b.getRemainingPieces();
		
		System.out.println("Remaining pieces are:");
		for (int i = 0; i < remaining.size();i++)
			System.out.print(remaining.get(i).getName() + " ");
		System.out.println();
		
		return null;
	}

}
