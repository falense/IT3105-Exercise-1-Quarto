

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import players.BasePlayer;
import players.ai.RandomAI;
import players.ai.minmax.MinMaxAI;
import board.BoardState;
import board.Move;
import board.Piece;





public class TournamentInterface {
	
	private BasePlayer selectedAI;
	private String playerNumber;
	private int iWin = 0;
	private int youWin = 0;
	private int draws = 0;
	private boolean isRunning = true;
	private BoardState board = new BoardState();
	//private Piece myPiece;
	BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
	Socket clientSocket;
	DataOutputStream outToServer; 
	BufferedReader inFromServer;
	
	
	
	

	public TournamentInterface(BasePlayer player) {
		setAI(player);
		
		try {
			startTest();
		} catch (UnknownHostException e) {
            System.err.println("Don't know about host: GameHost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to: GameHost.");
            System.exit(1);
        }
		
		
		
	}

	
	
	
	public void run() throws IOException{
		String innCom;
		while ((innCom = inFromServer.readLine()) != null){
			serverCom(innCom);
		}
	}
	
	public void startTest() throws IOException{
  
	  inFromUser = new BufferedReader( new InputStreamReader(System.in));
	  clientSocket = new Socket("127.0.0.1", 4455);   
	  outToServer = new DataOutputStream(clientSocket.getOutputStream());   
	  inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	  
	  
	  //inFromUser.readLine();   
	  //outToServer.writeBytes("");   
	  //inFromServer.readLine();   
	  
	  

	}
	
	public void setAI(BasePlayer ai){
		selectedAI = ai;
	}
	
	private void setPlayerNumber(String s){
		String[] tempString;
		tempString = s.split(" ");
		playerNumber = tempString[1];
	}
	
	
	
	/*
	 * Player ['one'/'two']
	 * BoardUpdate [piece] [row (0-indexed)] [column (0-indexed)]
	 * Turn [piece chosen by opponent]
	 * Move [placed piece at row (0-indexed)] [placed piece at column (0-indexed)] [piece chosen for opponent]
	 * Round [round number, starts at 1] [startingPlayer ('one' or 'two')]
	 * Winner ['one' or 'two']
	 */
	
	private void serverCom(String inString) throws IOException{
		switch (inString.charAt(0)) {
			case 'P':
				setPlayerNumber(inString);
				break;
			case 'B':
				updateBoard(inString);
				break;
			case 'T':	    
				  outToServer.writeBytes(returnMove(inString));   
				break;
			case 'I':
				//Invalid piece, not sure what to do...
				break;
			case 'R':
				board = new BoardState();
				
				break;
			case 'W':
				updateWinners(inString);
				break;
			case 'G':
				//printresults or saveresults
				//disconnect the socket connection and shut down. 
				System.out.println("Our AI won "+iWin+" times");
				System.out.println("Their AI won "+youWin+" times");
				System.out.println("We drew "+draws+" times");
				inFromUser.close();
				clientSocket.close();
   			    outToServer.close();
				inFromServer.close();
				
			default:
				//some exceptions probably
				break;
		}
	}
	
	private void updateWinners(String s){
		String[] tempString;
		tempString = s.split(" ");
		if (tempString[1]==playerNumber){
			iWin++;
		} else if (tempString[1].charAt(0)=='D' || tempString[1].charAt(0)=='d'){
			draws++;
		} else
			youWin++;
		
		
	}
	
	private void updateBoard(String boardUpdate){
		//BoardUpdate [piece] [row (0-indexed)] [column (0-indexed)]
		String[] tempString;
		tempString = boardUpdate.split(" ");
		//Change this if we remove BoardUpdate:
		board.forceUsePiece(new Piece(tempString[1]), Integer.parseInt(tempString[3]), Integer.parseInt(tempString[2]));
	}
	
	private Move generateMove(Piece piece){
		board.forceRemovePiece(piece);
		return selectedAI.getNextMove(board, piece);
	}
	
	//When a client has to do a turn, it receives:
	//Turn [piece chosen by opponent]
	private String returnMove(String inData){

		String[] tempString;
		tempString = inData.split(" ");
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

	public static void main(String[] args)
    {
		TournamentInterface t = new TournamentInterface(new RandomAI(false));
		try {
			t.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	
	
}
