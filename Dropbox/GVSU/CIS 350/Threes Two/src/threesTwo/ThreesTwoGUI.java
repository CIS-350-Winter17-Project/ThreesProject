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
    	
        // has the board
        game = new ThreesTwoGame();
        
        gameBoard = game.getDisplay();
        
        //holds the icons
        board = new JLabel[BOARD_SIZE][BOARD_SIZE];
        setLayout(new GridLayout(BOARD_SIZE, BOARD_SIZE, 2, 2));
        
        // for the numbers
        Dimension d = new Dimension(24, 24);
        Border b = BorderFactory.createLineBorder(Color.BLACK, 2);

        for (int row: range) {
            for (int col: range ) {
                board[row][col] = new JLabel();
                board[row][col].setPreferredSize(d);
                board[row][col].setBorder(b);
                board[row][col].setText("X"); //just for testing
                add(board[row][col]);
            }
        }
        
        displayBoard();

        addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				moveEvent(arg0);
				displayBoard();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				moveEvent(arg0);
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				moveEvent(arg0);
			}
			
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
            	
            	board[row][col].setText(Integer.toString(value));
            }
        }

    }

    public static void playGame() {
    	
        ThreesTwoGUI gui = new ThreesTwoGUI();

        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.getContentPane().add(gui);
        frame.pack();
        frame.setVisible(true);
    }
    
}
