import players.BasePlayer;
import players.HumanPlayer;
import players.ai.NoviceAI;
import players.ai.RandomAI;
import board.BoardState;
import board.Move;


public class GM implements Runnable {
	
	private BoardState state;
	private GUI g;
	private BasePlayer p1,p2;
	
	public int winner = -1;
	public GM(boolean guiEnabled){
		if (guiEnabled)
			g = new GUI();
		else
			g = null;
		state = new BoardState();
		/*
		System.out.println("Pick piece result: " + state.pickPiece(StaticPieces.BLCH));
		System.out.println("Place piece result: " + state.placePiece(StaticPieces.BLCH, 1,1));
		System.out.println(state.getPiece(1, 1));*/
		if (g != null)  g.updateBoard(state);
		p1 = new RandomAI();
		p2 = new NoviceAI();
	}
	private void printError(String str){
		System.err.println("GameMaster: " + str);
	}
	private static void sleep(long time){

		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run(){
		Move p1move  = new Move(null,null,-1,-1);
		Move p2move = new Move(null,null,-1,-1);
		boolean r  = false;

		System.out.println("************* PLAYER 1 TURN *************");
		p1move = p1.getNextMove(state, null);
		System.out.println(state);
		r = state.pickPiece(p1move.pieceToPlace);
		if (!r){
			printError(" Player 1 picked an invalid piece to place in first round.");
			return;
		}
		r = state.placePiece(p1move.pieceToPlace, p1move.x,p1move.y);
		if (!r){
			printError(" Player 1 placed an invalid piece to place in first round.");
			return;
		}
		r = state.pickPiece(p1move.pieceToGiveOpponent);
		if (!r){
			printError(" Player 1 picked an invalid piece for the opponent first round.");
			return;
		}
		if (g != null) g.updateBoard(state);

		System.out.println("************* END OF PLAYER 1 TURN *************");
		
		while (!state.isGameOver()){
			sleep(500);
			System.out.println("************* PLAYER 2 TURN *************");
			p2move = p2.getNextMove(state, p1move.pieceToGiveOpponent);
			r = state.placePiece(p2move.pieceToPlace, p2move.x,p2move.y);
			if (!r){
				printError(" Player 2s placing of the piece was invalid.");
				return;
			}
			r = state.pickPiece(p2move.pieceToGiveOpponent);
			if (!r){
				printError(" Player 2 picked an invalid piece for the opponent.");
				return;
			}
			if (g != null) g.updateBoard(state);
			System.out.println("************* END OF PLAYER 2 TURN *************");
			
			
			if (state.isGameOver()){
				System.out.println(p2.getClass().getName()+" won!");
				winner = 2;
				break;
			}


			sleep(500);
			System.out.println("************* PLAYER 1 TURN *************");
			p1move = p1.getNextMove(state, p2move.pieceToGiveOpponent);
			System.out.println(state);
			r = state.placePiece(p1move.pieceToPlace, p1move.x,p1move.y);
			System.out.println(state.isEmpty(p1move.x, p1move.y));
			if (!r){
				printError(" Player 1s placing of the piece was invalid.");
				return;
			}
			r = state.pickPiece(p1move.pieceToGiveOpponent);
			if (!r){
				printError(" Player 1 picked an invalid piece for the opponent.");
				return;
			}
			if (g != null) g.updateBoard(state);
			System.out.println("************* END OF PLAYER 1 TURN *************");
			
			if (state.isGameOver()){
				System.out.println(p1.getClass().getName()+" won!");
				winner = 1;
				break;
			}
		}
		if (g != null) g.cleanup();
	}
	public static void main(String[] args)
    {

		GM g,g2;
		while(true){
			System.out.println("Starting first");
			g = new GM(false);
			Thread t = new Thread(g, "Quarto 1");
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			System.out.println("Starting second");
			g = new GM(true);
			t = new Thread(g, "Quarto 1");
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/

		}
	}
}
