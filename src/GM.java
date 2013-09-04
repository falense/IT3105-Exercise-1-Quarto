import players.BasePlayer;
import players.ai.AlphaBetaAI;
import board.BoardState;


public class GM {
	
	BoardState state;
	GUI g;
	BasePlayer p1,p2;
	
	
	public GM(){
		
		g = new GUI();
		state = new BoardState();
		g.updateBoard(state);
		p1 = (BasePlayer) new AlphaBetaAI();
	}
	public static void main(String[] args)
    {
		GM g = new GM();
		
	}
}
