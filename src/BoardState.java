import java.util.ArrayList;


public class BoardState {

	private Piece[][] board = new Piece[4][4];
	private ArrayList<Piece> remainingPieces;
	// add something with players.
	
	public BoardState(){
		for (int i = 0 ; i < 4 ; i++){
			for (int j = 0 ; j < 4 ; j++){
				board[j][i] = null;
			}
		}
		remainingPieces = new ArrayList<Piece>();
		//all true
		remainingPieces.add(new Piece(true, true, true, true));
		//3x true
		remainingPieces.add(new Piece(true, true, true, false));
		remainingPieces.add(new Piece(true, true, false, true));
		remainingPieces.add(new Piece(true, false, true, true));
		remainingPieces.add(new Piece(false, true, true, true));
		// 2x 2x
		remainingPieces.add(new Piece(true, true, false, false));
		remainingPieces.add(new Piece(true, false, false, true));
		remainingPieces.add(new Piece(false, false, true, true));
		remainingPieces.add(new Piece(false, true, true, false));	
		remainingPieces.add(new Piece(true, false, true, false));
		remainingPieces.add(new Piece(false, true, false, true));
		//3x false
		remainingPieces.add(new Piece(true, false, false, false));
		remainingPieces.add(new Piece(false, false, false, true));
		remainingPieces.add(new Piece(false, false, true, false));
		remainingPieces.add(new Piece(false, true, false, false));
		// all false
		remainingPieces.add(new Piece(false, false, false, false));
		//done (i hope)
		
		
	}
	
	
	private boolean hasPiece(int x, int y){
		return board[y][x]!=null;
	}
	
	
	private Piece[][] getRowsAndColumns(){
		Piece[][] returnList = new Piece[10][4];
		//
		for (int x = 0 ; x < 4 ; x++){
			//Rows:
			returnList[4+x] = board[x];
			
			for (int y = 0 ; y < 4 ; y++){
				//Cols
				returnList[x][y] = board[y][x];

				//Diags
				if (x==y){
					returnList[8][x] = board[x][x];
					returnList[9][x] = board[x][3-x];
				}
			}
		}	
		return returnList;
	}
	
	
	public boolean isGameOver(){
		if ( remainingPieces.isEmpty() ){
			return true;
		}
		Piece[][] checkList = getRowsAndColumns();
		
		
		
		
		
		
	}
	
	public boolean compareRow(Piece[] row){
		if (row == null) {
			return false;
		}
		for (int i = 0 ; i < row.length ; i++){
			
		}
		
		
		return false;
	}
	
	
	
	
	

}
