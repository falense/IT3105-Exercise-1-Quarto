import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import board.BoardState;


public class GUI {
	JFrame frame;
	JPanel p;
	
	BufferedImage loadImage(String filename){
		BufferedImage pict = null;
		try {
			pict = ImageIO.read(new File(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pict = null;
		}
		return pict;
		
	}
	public GUI(){
		frame = new JFrame("QUARTO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        
        
		p = new JPanel(new GridLayout(4,4));
		
		frame.add(p);
		updateBoard(null);
		updateBoard(null);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}
	public void updateBoard(BoardState b){
		if (b == null){
			p.invalidate();
			p.add(new JLabel(new ImageIcon(loadImage("RLSN.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("RLSH.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("RLCN.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("RLCH.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("RSSN.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("RSSH.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("RSCN.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("RSCH.jpg"))));

			p.add(new JLabel(new ImageIcon(loadImage("BLSN.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("BLSH.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("BLCN.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("BLCH.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("BSSN.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("BSSH.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("BSCN.jpg"))));
			p.add(new JLabel(new ImageIcon(loadImage("BSCH.jpg"))));
		}
	}
}
