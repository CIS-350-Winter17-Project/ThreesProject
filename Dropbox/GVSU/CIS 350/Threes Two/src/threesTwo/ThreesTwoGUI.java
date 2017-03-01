package threesTwo;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.Border;

public class ThreesTwoGUI extends JPanel {

	private static ThreesTwoGame game;
    private JLabel[][] board;
    private Cell[][] gameBoard;
    private static JLabel nextCell;
    private static JButton restart;
    private static JLabel cScore;
    private static JLabel hScore;

    private int BOARD_SIZE = 4;

    public ThreesTwoGUI () {
    	
    	setFocusable(true);
        requestFocusInWindow();
        
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));
    	
        // has the board
        game = new ThreesTwoGame();
        
        gameBoard = game.getDisplay();
        
        //holds the icons
        board = new JLabel[BOARD_SIZE][BOARD_SIZE];
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE, 2, 2));
                
        // for the numbers
        Dimension d = new Dimension(48, 68);
        
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
            	
            	Color c = new Color(230, 230, 255);
            	Border b = BorderFactory.createLineBorder(c, 2, true);
            	
                board[row][col] = new JLabel();
                board[row][col].setPreferredSize(d);
                
                board[row][col].setBackground(c);
                board[row][col].setForeground(Color.WHITE);
                
                board[row][col].setBorder(b);
                board[row][col].setOpaque(true);
                
                add(board[row][col]);
                
            }
        }
        
        // preview piece
        nextCell = new JLabel();
        nextCell.setPreferredSize(d);
        nextCell.setOpaque(true);
        
        //get score
        cScore = new JLabel();
        
        //get high score
        hScore = new JLabel();
        
        // restart button
    	restart = new JButton("DON'T");
    	
    	restart.addActionListener( e -> { 
    		
    		game.resetMain();
    	
    	});
      
        displayBoard();
        
        addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				moveEvent(arg0);
				displayBoard();

				if(game.getGameStatus()==GameStatus.GAME_OVER){
					
					JOptionPane.showMessageDialog(null, "Game Over: Your score was "+game.getScore()+"!");
					game.resetMain();
					displayBoard();
					
				}
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}
			
			private void moveEvent(KeyEvent e) {
	        	
				int move = e.getKeyCode();
				
				switch (move) {
					case KeyEvent.VK_UP	   : game.Move(Direction.UP); break;
					case KeyEvent.VK_RIGHT : game.Move(Direction.RIGHT); break;
					case KeyEvent.VK_DOWN  : game.Move(Direction.DOWN); break;
					case KeyEvent.VK_LEFT  : game.Move(Direction.LEFT); break;
					default: break;
				}
	        	
	        	
	        }
        	
        });
        
        

    }

    private void displayBoard() {
    	
    	int score = game.getScore();
    	int highScore = game.getHighScore();
    	int nextValue = game.getNextCell().getValue();
    	
    	
    	cScore.setText("Score: "+Integer.toString(score));
    	hScore.setText("High Score: "+Integer.toString(highScore));
    	
    	
    	nextCell.setHorizontalAlignment(SwingConstants.CENTER);
    	nextCell.setFont(new Font("Calibri", Font.BOLD, 24));
    	nextCell.setText(" ");
    	
    	if (nextValue == 1) {
    		Color c = new Color(102, 102, 255);
    		Border b = BorderFactory.createLineBorder(c, 2, true);
    		
    		nextCell.setBackground(c);
    		nextCell.setBorder(b);
    	}
    	
    	if (nextValue == 2) {
    		Color c = new Color(255, 0, 102);
    		Border b = BorderFactory.createLineBorder(c, 2, true);
    		
    		nextCell.setBorder(b);
    		nextCell.setBackground(c);
    	}
    	
    	if (nextValue >= 3) {
    		Border b = BorderFactory.createLineBorder(Color.WHITE, 2, true);
    		
    		nextCell.setBackground(Color.WHITE);
    		
    		nextCell.setBorder(b);
    	}

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
            	
            	int value = gameBoard[row][col].getValue();
            	
            	board[row][col].setHorizontalAlignment(SwingConstants.CENTER);
            	board[row][col].setFont(new Font("Calibri", Font.BOLD, 24));
            	
            	if (value == 0) {
            		Color c = new Color(230, 230, 255);
            		Border b = BorderFactory.createLineBorder(c, 2, true);
            		
            		board[row][col].setBackground(c);
            		board[row][col].setBorder(b);
            		board[row][col].setText(" ");
            		
            	}
            	
            	if (value == 1) {
            		Color c = new Color(102, 102, 255);
            		Border b = BorderFactory.createLineBorder(c, 2, true);
            		
            		board[row][col].setText(Integer.toString(value));
            		board[row][col].setBackground(c);
            		board[row][col].setForeground(Color.WHITE);
            		board[row][col].setBorder(b);
            		
            	}
            	
            	else if (value == 2) {
            		Color c = new Color(255, 0, 102);
            		Border b = BorderFactory.createLineBorder(c, 2, true);
            		
            		board[row][col].setText(Integer.toString(value));
            		board[row][col].setBorder(b);
            		board[row][col].setBackground(c);
            		board[row][col].setForeground(Color.WHITE);
            		
            	}
            	else if (value >= 3) {
            		
            		Border b = BorderFactory.createLineBorder(Color.WHITE, 2, true);
            		
            		board[row][col].setForeground(Color.BLACK);
            		board[row][col].setBackground(Color.WHITE);
            		
            		board[row][col].setBorder(b);
            		
            		board[row][col].setText(Integer.toString(value));
            	}
            }
        }

    }

    public static void playGame() {
    	
    	ThreesTwoGUI gui = new ThreesTwoGUI();
    	
    	JPanel main = new JPanel(new BorderLayout(5, 5));
    	main.setBackground(Color.WHITE);
    	JPanel top = new JPanel();
    	JPanel bottom = new JPanel();
    	JPanel west = new JPanel(new GridLayout(3, 1, 5, 5));
    	JPanel east = new JPanel();
    	
    	top.setBackground(Color.WHITE);
    	bottom.setBackground(Color.WHITE);
    	west.setBackground(Color.WHITE);
    	east.setBackground(Color.WHITE);
    	
    	// side panel top
    	JPanel nextPanel = new JPanel(new BorderLayout(5, 5));
    	JPanel scorePanel = new JPanel(new BorderLayout(5,5));
    	JLabel next = new JLabel("Next");
    	JPanel nextWest = new JPanel();
    	JPanel nextSouth = new JPanel();
    	JPanel nextEast = new JPanel();
    	
    	west.setPreferredSize(new Dimension(75, 280));
    	west.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
    	
    	scorePanel.add(hScore, BorderLayout.NORTH);;
    	scorePanel.add(cScore, BorderLayout.SOUTH);
    	
    	nextPanel.add(next, BorderLayout.NORTH);
    	nextPanel.add(nextCell, BorderLayout.CENTER);
    	nextPanel.add(nextWest, BorderLayout.WEST);
    	nextPanel.add(nextSouth, BorderLayout.SOUTH);
    	nextPanel.add(nextEast, BorderLayout.EAST);
    	
    	
    	west.add(nextPanel);
    	
    	top.add(scorePanel);
    	main.add(top, BorderLayout.NORTH);
    	main.add(west, BorderLayout.WEST);
    	main.add(gui, BorderLayout.CENTER);
    	main.add(east, BorderLayout.EAST);
    	main.add(bottom, BorderLayout.SOUTH);
    	

        JFrame frame = new JFrame("Threes!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(main);
        frame.pack();
        frame.setVisible(true);
    }
    
}
