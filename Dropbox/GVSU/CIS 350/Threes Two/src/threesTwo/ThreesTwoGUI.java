package threesTwo;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class ThreesTwoGUI extends JPanel {

	private ThreesTwoGame game;
	private int rows;
	private int columns;
	private JLabel[][] board;
	private Cell[][] gameBoard;
	private JFrame frame;
	//private String[][] visualBoard;
	//private Cell[][] board;
	
	public static void mainGame(){
		int size = 4;
		new ThreesTwoGUI(size);
		
	}
	
	public ThreesTwoGUI(int boardSize){
		
		rows = boardSize;
		columns = boardSize;
		
		game = new ThreesTwoGame();
		frame = new JFrame("ThreesTwo");
		board = new JLabel[rows][columns];
		
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(rows, columns));
		add(boardPanel);
		boardPanel.add(board[rows][columns]);
		
		JPanel topPanel = new JPanel();
		add(topPanel);
		
		game.getDisplay();
		
		frame.add(BorderLayout.CENTER, boardPanel);
		frame.add(BorderLayout.NORTH, topPanel);
		frame.setSize(500,500);
		frame.setVisible(true);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void update(){
		
		for (int row = 0; row < rows; row++) 
		    for (int col = 0; col < columns; col++) {
			gameBoard[rows][columns].getValue();
		    
	    }
	   
	}
	
	private void arrowListener(){
		addKeyListener(new KeyAdapter(){
			
			public void keyPressed(KeyEvent e){
				myKeyEvt(e);
			}
			});
	}
	
	private void myKeyEvt(KeyEvent e){
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_KP_LEFT || key == KeyEvent.VK_LEFT){
			
		}
		else if(key == KeyEvent.VK_KP_RIGHT || key == KeyEvent.VK_RIGHT){
			
		}
		else if(key == KeyEvent.VK_KP_UP || key == KeyEvent.VK_UP){
			
		}
		else if(key == KeyEvent.VK_KP_DOWN || key == KeyEvent.VK_DOWN){
			
		}
			
	}

}
