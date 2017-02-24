package threesTwo;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.Border;

public class ThreesTwoGUI extends JPanel {

	private ThreesTwoGame game;
    private JLabel[][] board;
    private Cell[][] gameBoard;

    private int BOARD_SIZE = 4;
    private int range[] = {0, 1, 2, 3};

    public ThreesTwoGUI () {
    	
    	setFocusable(true);
        requestFocusInWindow();
        
        setBackground(Color.WHITE);
    	
        // has the board
        game = new ThreesTwoGame();
        
        gameBoard = game.getDisplay();
        
        //holds the icons
        board = new JLabel[BOARD_SIZE][BOARD_SIZE];
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE, 2, 2));
                
        // for the numbers
        Dimension d = new Dimension(48, 68);
        

        for (int row: range) {
            for (int col: range ) {
            	
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
        
        displayBoard();
        
        for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				
				System.out.print(String.format("%d ", game.getDisplay()[i][j].getValue()));
				
			}
			System.out.println();
		}
		
		System.out.println("\n===================\n");

        addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				moveEvent(arg0);
				displayBoard();
				
				for (int i=0; i<4; i++) {
					for (int j=0; j<4; j++) {
						
						System.out.print(String.format("%d ", game.getDisplay()[i][j].getValue()));
						
					}
					System.out.println();
				}
				
				System.out.println("\n===================\n");
				if(game.getGameStatus()==GameStatus.GAME_OVER){
					JOptionPane.showMessageDialog(null, "Game Over");
					game.resetMain();
					displayBoard();
					//for(int row = 0; row < 4; row++){
						//for(int col = 0; col < 4; col++){
							//board[row][col].setText(Integer.toString(gameBoard[row][col].getValue()));
						//}
					//}
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
            		board[row][col].setBorder(b);
            		
            	}
            	
            	else if (value == 2) {
            		Color c = new Color(255, 0, 102);
            		Border b = BorderFactory.createLineBorder(c, 2, true);
            		
            		board[row][col].setText(Integer.toString(value));
            		board[row][col].setBorder(b);
            		board[row][col].setBackground(c);
            		
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
    	
    	JPanel main = new JPanel(new BorderLayout());
    	JPanel top = new JPanel();
    	JPanel bottom = new JPanel();
    	JPanel west = new JPanel();
    	JPanel east = new JPanel();
    	
    	top.setBackground(Color.WHITE);
    	bottom.setBackground(Color.WHITE);
    	west.setBackground(Color.WHITE);
    	east.setBackground(Color.WHITE);
    	
//    	Dimension t = new Dimension(200, 90);
//    	Dimension s = new Dimension(40, 336);
//    	
//    	top.setPreferredSize(t);
//    	bottom.setPreferredSize(t);
//    	west.setPreferredSize(s);
//    	east.setPreferredSize(s);
    	
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
