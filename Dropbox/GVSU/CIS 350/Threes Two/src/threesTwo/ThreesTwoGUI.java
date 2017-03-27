package threesTwo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

/**********************************************************************
 * The Graphical User Interface for the Threes game. This creates the
 * visual board, and instantiates the ThreesTwoGame class.
 *
 * @author Scott Lumsden
 * @author Dylan Shoup
 * @author Anthony Blanton
 * @version 0.1 March 3, 2017
 *********************************************************************/

public class ThreesTwoGUI extends JPanel {

    /** The logic of the board. This dictates what appears */
    private static ThreesTwoGame game;

    /** The board pieces that visually appear on the board. */
    private JLabel[][] board;

    /** A one-to-one copy of the ThreesTwo object. */
    private Cell[][] gameBoard;

    /** Shows the player what piece will appear next after a move. */
    private static JLabel nextCell;

    /**
     * The restart button that will restart the game and board state.
     */
    private static JButton restart;

    /** Current score that the player has accumulated in the current
     * play-through.
     */
    private static JLabel cScore;

    /** The overall highScore that the player has ever achieved. */
    private static JLabel hScore;

    /** The size of the board, which will always be four. */
    private final int boardSize = 4;

    /******************************************************************
     * Constructor which creates the JPanels, position of pieces, etc.
     *****************************************************************/
    public ThreesTwoGUI() {

        setFocusable(true);
        requestFocusInWindow();

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, 10));

        // has the board
        game = new ThreesTwoGame();

        gameBoard = game.getDisplay();

        // holds the icons
        board = new JLabel[boardSize][boardSize];
        setLayout(new GridLayout(boardSize, boardSize, 2, 2));

        // for the numbers
        Dimension d = new Dimension(48, 68);

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {

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

        // get score
        cScore = new JLabel();

        // get high score
        hScore = new JLabel();

        // restart button
        restart = new JButton("DON'T");

        restart.addActionListener(e -> {

            game.resetMain();

        });

        displayBoard();

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(final KeyEvent arg0) {
                // TODO Auto-generated method stub
                moveEvent(arg0);
                displayBoard();

                if (game.getGameStatus() == GameStatus.GAME_OVER) {

                    JOptionPane.showMessageDialog(null,
                            "Game Over: Your score was "
                                    + game.getScore() + "!");
                    game.resetMain();
                    displayBoard();

                }

            }

            @Override
            public void keyReleased(final KeyEvent arg0) { }

            @Override
            public void keyTyped(final KeyEvent arg0) { }

            private void moveEvent(final KeyEvent e) {

                int move = e.getKeyCode();

                switch (move) {
                    case KeyEvent.VK_UP:
                        game.move(Direction.UP);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.move(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_DOWN:
                        game.move(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        game.move(Direction.LEFT);
                        break;
                    default:
                        break;
                }

            }

        });

    }

    /******************************************************************
     * Method which displays the board. Shows the state of each cell,
     * and the current score, high score, and the next cell.
     *****************************************************************/
    private void displayBoard() {

        int score = game.getScore();
        int highScore = game.getHighScore();
        int nextValue = game.getNextCell().getValue();

        cScore.setText("Score: " + Integer.toString(score));
        hScore.setText("High Score: " + Integer.toString(highScore));

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
            Border b = BorderFactory.createLineBorder(Color.WHITE, 2,
                    true);

            nextCell.setBackground(Color.WHITE);

            nextCell.setBorder(b);
        }

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {

                int value = gameBoard[row][col].getValue();

                board[row][col]
                    .setHorizontalAlignment(SwingConstants.CENTER);
                board[row][col]
                        .setFont(new Font("Calibri",
                                Font.BOLD, 24));

                if (value == 0) {
                    Color c = new Color(230, 230, 255);
                    Border b = BorderFactory.createLineBorder(c,
                            2, true);

                    board[row][col].setBackground(c);
                    board[row][col].setBorder(b);
                    board[row][col].setText(" ");

                }

                if (value == 1) {
                    Color c = new Color(102, 102, 255);
                    Border b = BorderFactory.createLineBorder(c, 2,
                            true);

                    board[row][col].setText(Integer.toString(value));
                    board[row][col].setBackground(c);
                    board[row][col].setForeground(Color.WHITE);
                    board[row][col].setBorder(b);

                } else if (value == 2) {
                    Color c = new Color(255, 0, 102);
                    Border b = BorderFactory.createLineBorder(c, 2,
                            true);

                    board[row][col].setText(Integer.toString(value));
                    board[row][col].setBorder(b);
                    board[row][col].setBackground(c);
                    board[row][col].setForeground(Color.WHITE);

                } else if (value >= 3) {

                    Border b = BorderFactory
                            .createLineBorder(Color.WHITE, 2, true);

                    board[row][col].setForeground(Color.BLACK);
                    board[row][col].setBackground(Color.WHITE);

                    board[row][col].setBorder(b);

                    board[row][col].setText(Integer.toString(value));
                }
            }
        }

    }

    /******************************************************************
     * Dictates where each individual JButton, JLabel, JPanel goes and
     * what is assigned to that spot.
     *****************************************************************/
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
        JPanel scorePanel = new JPanel(new BorderLayout(5, 5));
        JLabel next = new JLabel("Next");
        JPanel nextWest = new JPanel();
        JPanel nextSouth = new JPanel();
        JPanel nextEast = new JPanel();

        west.setPreferredSize(new Dimension(75, 280));
        west.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));

        scorePanel.add(hScore, BorderLayout.NORTH);

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
