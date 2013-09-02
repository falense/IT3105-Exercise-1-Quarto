
public class Piece {
	boolean[] feature = new boolean[4];
	
	public Piece (boolean red, boolean small, boolean hollow, boolean round){
		feature[0] = red;
		feature[1] = small;
		feature[2] = hollow;
		feature[3] = round;
	}
	
	//unused so far
	public boolean sameFeature(Piece other){
		for (int i = 0; i < 4; i++){
			if (this.feature[i]==other.feature[i]){
				return true;
			}
		}
		return false;
	}
	
	public boolean[] getFeatures(){
		return feature;
	}
	
	

}
