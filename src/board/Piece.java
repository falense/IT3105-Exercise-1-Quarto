package board;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Piece {
	boolean[] feature = new boolean[4];
	ImageIcon img;
	
	public Piece (boolean red, boolean small, boolean round, boolean hollow){
		feature[0] = red;			//red or blue
		feature[1] = small;			//small or large
		feature[2] = round;			//round or square
		feature[3] = hollow;		//hollow or solid
		String graphicsFile = "";
		if (red) graphicsFile += "R";
		else graphicsFile += "B";

		if (small) graphicsFile += "L";
		else graphicsFile += "S";

		if (round) graphicsFile += "C";
		else graphicsFile += "S";
		
		if (hollow) graphicsFile += "H";
		else graphicsFile += "N";
		
		img = loadImage(graphicsFile + ".jpg");
	}
	
	public Piece (String strFeatures){
		if (strFeatures.indexOf(0) == 'R')	feature[0] = true;
		else feature[0] = false;
		
		if (strFeatures.indexOf(1) == 'S')	feature[1] = true;
		else feature[1] = false;
		
		if (strFeatures.indexOf(2) == 'C')	feature[2] = true;
		else feature[2] = false;

		if (strFeatures.indexOf(3) == 'H')	feature[3] = true;
		else feature[3] = false;
		
		img = loadImage(strFeatures + ".jpg");
	}
	public ImageIcon getIcon(){
		return img;
	}
	ImageIcon loadImage(String filename){
		BufferedImage pict = null;
		try {
			pict = ImageIO.read(new File("./Resources/" + filename));
		} catch (IOException e) {
			e.printStackTrace();
			pict = null;
		}
		return new ImageIcon(pict);
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
