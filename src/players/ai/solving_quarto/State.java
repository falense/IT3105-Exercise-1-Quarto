package players.ai.solving_quarto;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import board.BoardState;
import board.Move;
import board.Piece;
import board.StaticPieces;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

public class State {	
	private Piece[][] board = new Piece[4][4];
	private ArrayList<Piece> remainingPieces;
	
	public State(){
		//sets up empty board and adds all pieces to unused list
		for (int i = 0 ; i < 4 ; i++){
			for (int j = 0 ; j < 4 ; j++){
				board[i][j] = null;
			}
		}
		remainingPieces = new ArrayList<Piece>();

		remainingPieces.add(StaticPieces.RLCH);
		remainingPieces.add(StaticPieces.RLCN);
		
		remainingPieces.add(StaticPieces.RSCH);
		remainingPieces.add(StaticPieces.RSCN);

		remainingPieces.add(StaticPieces.RLSH);
		remainingPieces.add(StaticPieces.RLSN);
		
		remainingPieces.add(StaticPieces.RSSH);
		remainingPieces.add(StaticPieces.RSSN);

		remainingPieces.add(StaticPieces.BLCH);
		remainingPieces.add(StaticPieces.BLCN);
		
		remainingPieces.add(StaticPieces.BSCH);
		remainingPieces.add(StaticPieces.BSCN);

		remainingPieces.add(StaticPieces.BLSH);
		remainingPieces.add(StaticPieces.BLSN);
		
		remainingPieces.add(StaticPieces.BSSH);
		remainingPieces.add(StaticPieces.BSSN);
		
		
		
	}
	
	public State(State old) {
		//sets up empty board and adds all pieces to unused list
		for (int i = 0 ; i < 4 ; i++){
			for (int j = 0 ; j < 4 ; j++){
				board[i][j] = old.board[i][j];
			}
		}
		remainingPieces = new ArrayList<Piece>();
		
		for (int i = 0; i < old.remainingPieces.size(); i++){
			remainingPieces.add(old.remainingPieces.get(i));
		}
	}
	
	public int getTurnNumber(){
		return 16-remainingPieces.size();
	}

	public Piece getPiece(int x, int y){
		return board[x][y];
	}
	public boolean hasPiece(int x, int y){
		return !isEmpty(x,y);
	}
	public boolean isEmpty(int x, int y){
		return board[x][y] == null;
	}
	
	
	//returns a 10-by-4 list of all the 10 possible rows to win
	public Piece[][] getRowsAndColumns(){
		Piece[][] returnList = new Piece[10][4];
		//
		for (int x = 0 ; x < 4 ; x++){
			for (int y = 0 ; y < 4 ; y++){
				//Cols
				returnList[x][y] = board[y][x];
				
				//Rows:
				returnList[4+x][y] = board[x][y];
				
				
				//Diags
				if (x==y){
					returnList[8][x] = board[x][x];
					returnList[9][x] = board[x][3-x];
				}
			}
		}	
		return returnList;
	}
	
    public static ArrayList<Move> getAllMoves(BoardState b, Piece myPiece) {
        ArrayList<Move> moves = new ArrayList<Move>();;
                for(int x = 0; x < 4 ; x++)
                        for(int y = 0; y < 4; y++)
                                if(b.isEmpty(x, y))
                                        for(Piece p : b.getRemainingPieces())
                                                	moves.add(new Move(myPiece, p, x, y));  
                return moves;
    }
	
    
	public ArrayList<int[]> getOpenSlots(){
		 ArrayList<int[]> openSlots = new ArrayList<int[]>();
	        for (int i = 0; i < 4; i++) {
	            for (int j = 0; j < 4; j++) {
	                if (isEmpty(j, i)) {
	                    int[] coords = { j, i };
	                    openSlots.add(coords);
	                }
	            }
	        }
	        return openSlots;
	}
	
	
	public boolean isDraw(){
		return getOpenSlots().isEmpty() && !isQuarto();
	}
	
	public boolean isQuarto(){
		Piece[][] checkList = getRowsAndColumns();
		
		for (int i = 0 ; i < 10 ; i++){
			if (compareRow(checkList[i])) {
				return true;
			}
		}
		return false;
	}
	
	
	//check if a row of pieces has atleast one feature in common
	public boolean compareRow(Piece[] row){
		
        boolean color = true;
        boolean size = true;
        boolean squareness = true;
        boolean structure = true;
		
		if (row == null) {
			return false;
		}
		for (int i = 0 ; i < row.length ; i++){
			if (row[i] == null){
				return false;
			}
			color = color && 			(row[0].getFeatures()[0] == row[i].getFeatures()[0]);
			size = size && 				(row[0].getFeatures()[1] == row[i].getFeatures()[1]);
			squareness = squareness && 	(row[0].getFeatures()[2] == row[i].getFeatures()[2]);
			structure = structure && 	(row[0].getFeatures()[3] == row[i].getFeatures()[3]);		
		}
		return color || size || squareness || structure;
	
	}
	
	
	
	public State deepCopy(){
		State r = new State(this);
		return r;
		
	}
	public ArrayList<Action> getActions(){
		ArrayList<Action> r = new ArrayList<Action>();
		for (int i = 0; i < 4;i++)
			for (int j = 0; j < 4; j++)
				if (isEmpty(i, j))
					for (Piece p : remainingPieces)
						r.add(new Action(p,i,j));
		return r;
					
	}
	public State apply(Action a){
		return null;
		
	}
	public String toHash(){
		StringBuilder ss = new StringBuilder();
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				if (!isEmpty(i, j))
				ss.append(board[i][j].getName() + " ");
				else 
				ss.append("null ");
			}
		}
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageDigest.reset();
		messageDigest.update(ss.toString().getBytes(Charset.forName("UTF8")));
		final byte[] resultByte = messageDigest.digest();
		final String result = new String(HexBin.encode(resultByte));
		return result;
		
		
		
	}
	

}
