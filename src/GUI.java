import java.awt.Component;
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
import board.Piece;
import board.StaticPieces;


public class GUI {
	JFrame frame;
	JPanel boardPanel;
	JPanel remainingPanel;
	JPanel currentStatus;
	

	public GUI(){
		frame = new JFrame("QUARTO");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        
        GridLayout g = new GridLayout(4,4);
        g.setHgap(5);
        g.setVgap(5);
		boardPanel = new JPanel(g);
		
		frame.add(boardPanel);
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		boardPanel.add(new JLabel());
		updateBoard(null);
        frame.pack();
        //Display the window.
        frame.setVisible(true);
	}
	public void updatePiece(Piece i, int x, int y){
		if (x+y*4 >= 16 || x+y*4 < 0){
			System.err.println("Index out of bounds");
			return;
		}
		JLabel t = (JLabel) boardPanel.getComponent(x+y*4);
		if (i == null){
			try {
				BufferedImage b = ImageIO.read(new File("./Resources/Template.jpg"));
				ImageIcon j = new ImageIcon(b);
				t.setIcon(j);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			t.setIcon(i.getIcon());
			t.invalidate();
		}
	}
	public void updateBoard(BoardState b){
		if (b != null){
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4;j++)
					updatePiece(b.getPiece(i, j),i,j);
		}
		else{
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4;j++)
					updatePiece(null,i,j);
		}
	}
}
