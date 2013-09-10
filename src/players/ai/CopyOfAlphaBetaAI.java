/*
package players.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import board.BoardState;
import board.Move;
import board.Piece;

public class CopyOfAlphaBetaAI extends BaseAI {
	
	private ArrayList<BoardState> haveVisited = new ArrayList<BoardState>();

	final String name = "CopyOfAlphaBetaAI";
	int maxDepth = 2;
	public  CopyOfAlphaBetaAI(boolean verboseOutput, int maxDepth) {
		super(verboseOutput);
		this.maxDepth = maxDepth;
		// TODO Auto-generated constructor stub
	}


	private double evaluateState(BoardState state, boolean max){
		if (state.haveAWinner()){
			if (max)
				return Double.MAX_VALUE;
			else
				return Double.MIN_VALUE;
			
		} else if (state.isDraw()){
			return 0;
		} else {
			return 0;
		}
		
	}
	
		
		  public Move getNextMove(BoardState state, Piece place) {
	          //System.out.println("Starting MINIMAX");
			  
			  //if (!haveVisited.isEmpty())
				//  haveVisited.clear();
			  
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
	                  
	                  //double newBest;
	                  double maxVal;
	                  
	                  //System.out.println("Top level: Test Action");
	                  maxVal = maxValue(tempBoard, maxDepth, nextMove);
	                  //System.out.println("Top level: recieved max of " + maxVal);
	                  if(maxVal > bestValue) {
	                          bestValue = maxVal;
	                          //System.out.println("Found better move. Value: " + newBest);
	                          bestMove = nextMove;
	                  }
	          }
	          return bestMove;
	  }
		  
		  private double maxValue(BoardState state,Piece place, int depth, double alpha, double beta, boolean max) {

		        if (state.isGameOver() || depth == 0) {
		            return evaluateState(state,max);
		        }
		        if (depth == 0) {
		            return evaluateState(state, max);
		        }
		        double v = Double.MIN_VALUE;
		        List<Move> successors = BoardState.getAllMoves(state, place);
		        for (Move move : successors) {
		            double minimumValueAmongSuccessors = minValue(move, depth - 1, alpha, beta, "MAX");
		            if (minimumValueAmongSuccessors > v) {
		                v = minimumValueAmongSuccessors;
		            }
		            if (v >= beta) {
		                return v;
		            }
		            alpha = Math.max(alpha, v);
		        }
		        return v;
		    }
		  
		  public double maxValue (BoardState state, int depth, Move move) {
			  	//int initialTurn = state.getTurnNumber();
			    double maxSoFar = Double.NEGATIVE_INFINITY;
			    ListIterator li;
			    Move nextMove;
			    //System.out.println(move.pieceToPlace.getName());
			    BoardState child;
			    double childValue;
			    if (state.isGameOver() || depth <= 0 ) {
			        return evaluateState(state, true); //Eval goes here.
			    }
			    else {
			      li = BoardState.getAllMoves(state, move.getPieceToGiveOpponent()).listIterator();
			      double maxValue = Double.NEGATIVE_INFINITY;
			      
			      while (li.hasNext()) {
			          //System.out.println("Max: Testing Action " + count);
			          nextMove = (Move)li.next();
			          child = state.deepCopy();
			          child.simMove(nextMove);
			          
			          //if(!haveVisited.contains(child)) {
			                  childValue = minValue(child, depth-1, nextMove);
			                  if(childValue > maxSoFar) maxSoFar = childValue;
			                  //System.out.println("Max: got child value: " + childValue);
			                  //haveVisited.add(child);
			          //}
			      }
			      return maxSoFar;
			    }
			  }

		  public double minValue (BoardState state, int depth, Move move) {
			    double minSoFar = Double.POSITIVE_INFINITY;
			    ListIterator li;
			    Move nextMove;
			    BoardState child;
			    double childValue;
			    if (state.isGameOver() || depth <= 0 ) {
			        return evaluateState(state, false); //Eval goes here.
			    }
			    else {
			    	li = BoardState.getAllMoves(state, move.getPieceToGiveOpponent()).listIterator();
			    	
			      while (li.hasNext()) {
			          //System.out.println("        Min: Testing Action " + count);
			          nextMove = (Move)li.next();
			          child = state.deepCopy();
			          child.simMove(nextMove);
			          
			          if(!haveVisited.contains(child)) {
			                  childValue = maxValue(child, depth-1, nextMove);
			                  if(minSoFar > childValue) minSoFar = childValue;
			                  haveVisited.add(child);
			          }
			      }
			      return minSoFar;
			    }




			  }
	


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}
	

}
*/