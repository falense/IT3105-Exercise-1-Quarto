package evaluation;

import java.util.ArrayList;

import board.BoardState;
import board.Piece;

public class EvenWinningPieces extends BaseEvaluator{
	
	double r = 0;

	@Override
	
	//this is waaayyy to slow
	public double evaluate(BoardState board, boolean max) {
		
		
		r = 0;
		ArrayList<Piece> winners = super.findWinners(board);
		
		if(!winners.isEmpty() && !board.getRemainingPieces().isEmpty()){
			int dif = board.getRemainingPieces().size() - winners.size();
			
			//If even number of winning pieces are left:
			if (dif%2==0){ 
				r = 0.5;
			}
		}
		
		
		
		
		
		if (max)
			return r;
		else
			return -r;
	}

}
