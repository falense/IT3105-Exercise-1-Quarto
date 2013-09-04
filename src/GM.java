import players.BasePlayer;
import players.HumanPlayer;
import board.BoardState;
import board.Move;
import board.StaticPieces;


public class GM implements Runnable {
	
	private final BoardState state;
	private final GUI g;
	private final BasePlayer p1,p2;
	
	
	public GM(){

		g = new GUI();
		state = new BoardState();
		System.out.println("Pick piece result: " + state.pickPiece(StaticPieces.BLCH));
		System.out.println("Place piece result: " + state.placePiece(StaticPieces.BLCH, 1,1));
		System.out.println(state.getPiece(1, 1));
		g.updateBoard(state);
		p1 = (BasePlayer) new HumanPlayer();
		p2 = (BasePlayer) new HumanPlayer();
	}
	public void run(){
		Move p1move  = new Move(null,null,-1,-1);
		Move p2move = new Move(null,null,-1,-1);
		while (!state.isGameOver()){
			
			p1move = p1.getNextMove(state, null);
			System.out.println(state);
			state.pickPiece(p1move.pieceToGiveOpponent);
			state.placePiece(p1move.pieceToPlace, p1move.x,p1move.y);
			
			if (state.isGameOver()){
				System.out.println("Player 1 won");
			}
			
			p2move = p2.getNextMove(state, null);
			state.pickPiece(p2move.pieceToGiveOpponent);
			state.placePiece(p2move.pieceToPlace, p2move.x,p2move.y);
			
			if (state.isGameOver()){
				System.out.println("Player 2 won");
			}
			
		}
	}
	public static void main(String[] args)
    {
		GM g = new GM();
		Thread t = new Thread(g, "Quarto 1");
		t.start();
	}
}
