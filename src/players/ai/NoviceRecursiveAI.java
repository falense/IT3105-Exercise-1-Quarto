package players.ai;

import java.util.ArrayList;

import board.BoardState;
import board.Move;
import board.Piece;


public class NoviceRecursiveAI extends BaseAI{


	int maxDepth = 2;
	public NoviceRecursiveAI(boolean verboseOutput, int maxDepth) {
		super(verboseOutput);
		this.maxDepth = maxDepth;
		// TODO Auto-generated constructor stub
	}


	private double evaluateState(BoardState state, boolean max){
		return 0;	
	}
	
	private double searchAlphaBeta(BoardState state, Piece place,boolean max,int depth){
		if(state.haveAWinner()){
			if (max) return 1;
				else return -1;
		}	else if (depth == 0){
			return 0;
		} else {
			double bestScore = 0;
			
			BoardState nextBoard = state.deepCopy();
			nextBoard.simulateRemovePiece(place);
			ArrayList<Move> enemyMoves = BoardState.getAllMoves(nextBoard, place);
			
			for (Move move : enemyMoves ){
				BoardState nextNextBoard = nextBoard.deepCopy();
				nextNextBoard.simulatePlacement(move.getPieceToPlace(), move.getX(), move.getY());
				double testScore = searchAlphaBeta( nextNextBoard, move.getPieceToPlace(),!max, depth-1);
				if (testScore<= bestScore)
					bestScore = testScore;
				
			}
			
			
			
			return 
		}
		
		
		
	}
	
	
	@Override
	public Move getNextMove(BoardState state, Piece place) {
		// TODO Auto-generated method stub

		ArrayList<Piece> remaining = state.getRemainingPieces();
		ArrayList<Move> myMoves = state.getAllMoves(state, place);
		ArrayList<Move> goodMoves = new ArrayList<Move>();

		Move best = null;
		double bestScore = 0;
		for (Move move : myMoves ){
			BoardState nextBoard = state.deepCopy();
			nextBoard.simulatePlacement(move.getPieceToPlace(), move.getX(), move.getY());
			
			double score = searchAlphaBeta( nextBoard, move.getPieceToPlace(),true, 1);
			if (best == null || score > bestScore){
				bestScore = score;
				best = move;
			}
		}
		return best;
	}
	

}
