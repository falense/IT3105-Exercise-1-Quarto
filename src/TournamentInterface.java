import players.BasePlayer;
import board.BoardState;
import board.Move;
import board.Piece;



public class TournamentInterface {
	
	private BasePlayer selectedAI;
	
	
	
	BoardState board = new BoardState();
	
	/*
	 * Player ['one'/'two']
	 * BoardUpdate [piece] [row (0-indexed)] [column (0-indexed)]
	 * Turn [piece chosen by opponent]
	 * Move [placed piece at row (0-indexed)] [placed piece at column (0-indexed)] [piece chosen for opponent]
	 */
	
	private void serverCom(String inString){
		
	}
	
	private void updateBoard(String boardUpdate){
		//BoardUpdate [piece] [row (0-indexed)] [column (0-indexed)]
		String[] tempString;
		tempString = boardUpdate.split("\\s+");	
		//Change this if we remove BoardUpdate:
		board.simulateUsePiece(new Piece(tempString[1]), Integer.parseInt(tempString[3]), Integer.parseInt(tempString[2]));
	}
	
	private Move generateMove(Piece piece){
		board.simulateRemovePiece(piece);
		return selectedAI.getNextMove(board, piece);
	}
	
	//When a client has to do a turn, it receives:
	//Turn [piece chosen by opponent]
	private String returnMove(String inData){

		String[] tempString;
		tempString = inData.split("\\s+");
		Piece myPiece = new Piece(tempString[1]);
		return moveToString(generateMove(myPiece));
	}
	
	//Move [placed piece at row (0-indexed)] [placed piece at column (0-indexed)] [piece chosen for opponent]	
	private String moveToString(Move move){
		String sMove = "";
		sMove.concat("Move ");
		sMove.concat(Integer.toString(move.getY()));
		sMove.concat(" ");
		sMove.concat(Integer.toString(move.getX()));
		sMove.concat(" ");
		sMove.concat(move.getPieceToGiveOpponent().toString());
		return sMove;
	}
	
	
	
	
	
}
