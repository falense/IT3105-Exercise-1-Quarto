import players.BasePlayer;
import players.ai.NoviceAI;
import players.ai.RandomAI;
import board.BoardState;
import board.Move;


public class GM implements Runnable {
	private boolean verboseOutput;
	private long delay;
	private BoardState state;
	private GUI g;
	private BasePlayer p1,p2;
	
	public int winner = -1;
	public GM(boolean guiEnabled,boolean verboseOutput,long delay,BasePlayer p1, BasePlayer p2){
		this.verboseOutput = verboseOutput;
		this.delay = delay;
		if (guiEnabled)
			g = new GUI();
		else
			g = null;
		state = new BoardState();
		if (g != null)  g.updateBoard(state);
		if (p1 == null)
			this.p1 = new RandomAI();
		else
			this.p1 = p1;
		if (p2 == null)
			this.p2 = new NoviceAI();
		else
			this.p2 = p2;
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
	private void printMessage(String msg){
		if (verboseOutput) System.out.println(msg);
	}
	@Override
	public void run(){
		Move p1move  = new Move(null,null,-1,-1);
		Move p2move = new Move(null,null,-1,-1);
		boolean r  = false;

		printMessage("************* PLAYER 1 TURN *************");
		p1move = p1.getNextMove(state, null);
		printMessage(state.toString());
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

		printMessage("************* END OF PLAYER 1 TURN *************");
		
		while (!state.isGameOver()){
			sleep(delay);
			printMessage("************* PLAYER 2 TURN *************");
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
			printMessage("************* END OF PLAYER 2 TURN *************");
			
			
			if (state.isGameOver()){
				printMessage(p2.getClass().getName()+" won!");
				winner = 2;
				break;
			}


			sleep(delay);
			printMessage("************* PLAYER 1 TURN *************");
			p1move = p1.getNextMove(state, p2move.pieceToGiveOpponent);
			printMessage(state.toString());
			r = state.placePiece(p1move.pieceToPlace, p1move.x,p1move.y);
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
			printMessage("************* END OF PLAYER 1 TURN *************");
			
			if (state.isGameOver()){
				printMessage(p1.getClass().getName()+" won!");
				winner = 1;
				break;
			}
		}
		if (winner == -1) winner = 0;
		if (g != null) g.cleanup();
	}

}
