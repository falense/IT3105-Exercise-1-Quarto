import java.util.ArrayList;
import java.util.Random;

import players.BasePlayer;
import players.ai.NoviceAI;
import players.ai.RandomAI;
import players.ai.RecursiveAI;
import board.BoardState;
import board.Move;
import board.Piece;


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
			this.p1 = new RandomAI(verboseOutput);
		else
			this.p1 = p1;
		if (p2 == null)
			this.p2 = new NoviceAI(verboseOutput);
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

		ArrayList<Piece> p = state.getRemainingPieces();
		Random rand = new Random(System.currentTimeMillis());
		Piece randomPiece = p.get(0);//rand.nextInt(16));
				
		Move p1move  = new Move(null,null,-1,-1);
		Move p2move = new Move(null,randomPiece,-1,-1);
		boolean r  = false;
		
		
		while (!state.isGameOver()){
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
				
				if (state.haveAWinner()){
					winner = 1;
					printMessage(p1.getClass().getName()+" won!");
				} else	{
					winner = 0;
					printMessage("Game was a draw. (After first player");
				}
	
					
				break;
			}
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
				if (state.isDraw()){
					winner = 0;
					printMessage("Game was a draw.");
				}
				else{
					printMessage(p2.getClass().getName()+" won!");
					winner = 2;
				}
				break;
			}


			sleep(delay);
		}
		if (winner == -1) winner = 0;
		if (g != null) g.cleanup();
	}
	public static void main(String[] args)
    {


		GM g = new GM(true,false,0,new RandomAI(false),new RecursiveAI(false,1));
		Thread t = new Thread(g, "Quarto ");
		t.start();
	}
}
