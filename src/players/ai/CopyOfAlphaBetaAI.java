package players.ai;
/*
import java.util.ArrayList;
import java.util.ListIterator;

import board.BoardState;
import board.Move;
import board.Piece;

public class CopyOfAlphaBetaAI extends BaseAI {

	final String name = "AlphaBetaAI";
	int maxDepth = 2;
	public  CopyOfAlphaBetaAI(boolean verboseOutput, int maxDepth) {
		super(verboseOutput);
		this.maxDepth = maxDepth;
		// TODO Auto-generated constructor stub
	}


	private double evaluateState(BoardState state, boolean max){
		
		return 0;	
	}
	private double searchAlphaBeta(BoardState state, final Piece place,double alpha, double beta,final boolean max,final int depth){
		counter ++;

		//System.out.println(alpha + " " + beta);
		if(state.haveAWinner()){
			if (max) return -1;
			else return 1;
		}
		if (state.isDraw())
			return 0;
		if (depth <= 0){
			return evaluateState(state,max);
		}
		if (max){
			alpha = -Double.MAX_VALUE;
		}
		else{
			beta = Double.MAX_VALUE;
		}
		Move best = null;
		double bestScore = 0;
		for (Move m : BoardState.getAllMoves(state, place)){
			BoardState newState = state.deepCopy();
			newState.placePiece(m.getPieceToPlace(), m.getX(),m.getY());
			newState.pickPiece(m.getPieceToGiveOpponent());
			if(newState.haveAWinner()){

				if (max) return 1;
				else return -1;
			}
			if (state.isDraw())
				return 0;
			double score = searchAlphaBeta(newState,m.getPieceToGiveOpponent(),alpha,beta,!max,depth-1);
			
			if (max){
				if (best == null || score > bestScore){
					bestScore = score;
					best = m;
					alpha = score;
					if(beta < score){
						//System.out.println("Cutting search, beta: " + beta + " score: " + score);
						return score;
					}
				}
			}
			else{
				if (best == null || score < bestScore){
					bestScore = score;
					best = m;
					beta = score;
					if (alpha > score){
						//System.out.println("Cutting search, alpha: " + alpha + " score: " + score);
						return score;
					}
				}
			}
		}
		return bestScore;
	}
	
		
		  public Move getNextMove(BoardState state, Piece place) {
	          //System.out.println("Starting MINIMAX");
			  
	          Move bestMove;
	          double bestValue;
	          ListIterator li;
	          li = BoardState.getAllMoves(state, place).listIterator();
	          bestMove = (Move) BoardState.getAllMoves(state, place).get(0);
	          bestValue = Double.NEGATIVE_INFINITY;
	          
	          while(li.hasNext()) {
	        	  	  
	        	      BoardState tempBoard = state.deepCopy();
	                  Move nextMove = (Move)li.next();
	                  tempBoard.simMove(nextMove);
	                  
	                  double newBest;
	                  double maxVal;
	                  
	                  //System.out.println("Top level: Test Action");
	                  maxVal = maxValue(tempBoard, 0, );
	                  //System.out.println("Top level: recieved max of " + maxVal);
	                  if(maxVal > bestValue) {
	                          bestValue = maxVal;
	                          //System.out.println("Found better move. Value: " + newBest);
	                          bestMove = nextMove;
	                  }
	          }
	          return bestMove;
	  }
		  
		  public double maxValue (BoardState state, int depth, Piece place) {
			    double maxSoFar = Double.NEGATIVE_INFINITY;
			    ListIterator li;
			    Move move;
			    BoardState child;
			    double childValue;
			    if (state.isGameOver() || visit.getPath().size() > nodeInfo.getDepthLimit()) {
			        return evaluateState(state, true); //Eval goes here.
			    }
			    else {
			      li = state.getAllMoves(state, place).listIterator();
			      double maxValue = Double.NEGATIVE_INFINITY;
			      int count = 0;
			      while (li.hasNext()) {
			          //System.out.println("Max: Testing Action " + count);
			          arc = (Action)li.next();
			          child = (Node)visit.clone();
			          child.update(arc);
			          if(!visited.contains(child)) {
			                  childValue = minValue(child, depth+1);
			                  if(childValue > maxSoFar) maxSoFar = childValue;
			                  //System.out.println("Max: got child value: " + childValue);
			                  visited.add(child);
			          }
			          count++;
			      }
			      return maxSoFar;
			    }
			  }		  
	


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	

}
*/