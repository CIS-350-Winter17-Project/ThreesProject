package com.cis350.threesTwo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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

    /** For the Checkstyle warnings. */
    private final int bordThick = 10;

    /** For the Checkstyle warnings. Height of new dimension. */
    private final int dHeight = 48;

    /** Width of new dimension. */
    private final int dWidth = 68;

    /** Font for tiles. */
    private final int fontSize = 24;

    /** Random number bound. */
    private final int bound = 3;

    /** For the Checkstyle warnings. */
    private static final int S_BOUND = 3;

    /** More checkstyle stuff. */
    private static final int FIVE = 5;

    /** For the Checkstyle warnings. */
    private final int rColor = 230;

    /** For the Checkstyle warnings. */
    private final int gColor = 255;

    /** For the Checkstyle warnings. */
    private final int dColor = 102;

    /** For the Checkstyle warnings. */
    private static final int W_HEIGHT = 75;

    /** For the Checkstyle warnings. */
    private static final int W_WIDTH = 280;


    /******************************************************************
     * Constructor which creates the JPanels, position of pieces, etc.
     *****************************************************************/
    public ThreesTwoGUI() {

        setFocusable(true);
        requestFocusInWindow();

        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.WHITE, bordThick));

        // has the board
        game = new ThreesTwoGame();

        gameBoard = game.getDisplay();

        // holds the icons
        board = new JLabel[boardSize][boardSize];
        setLayout(new GridLayout(boardSize, boardSize, 2, 2));

        // for the numbers
        Dimension d = new Dimension(dHeight, dWidth);

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {

                Color c = new Color(rColor, rColor, gColor);
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
        nextCell.setFont(new Font("Calibri", Font.BOLD, fontSize));
        nextCell.setText(" ");

        if (nextValue == 1) {
            Color c = new Color(dColor, dColor, gColor);
            Border b = BorderFactory.createLineBorder(c, 2, true);

            nextCell.setBackground(c);
            nextCell.setBorder(b);
        }

        if (nextValue == 2) {
            Color c = new Color(gColor, 0, dColor);
            Border b = BorderFactory.createLineBorder(c, 2, true);

            nextCell.setBorder(b);
            nextCell.setBackground(c);
        }

        if (nextValue >= bound) {
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
                                Font.BOLD, fontSize));

                if (value == 0) {
                    Color c = new Color(rColor, rColor, gColor);
                    Border b = BorderFactory.createLineBorder(c,
                            2, true);

                    board[row][col].setBackground(c);
                    board[row][col].setBorder(b);
                    board[row][col].setText(" ");

                }

                if (value == 1) {
                    Color c = new Color(dColor, dColor, gColor);
                    Border b = BorderFactory.createLineBorder(c, 2,
                            true);

                    board[row][col].setText(Integer.toString(value));
                    board[row][col].setBackground(c);
                    board[row][col].setForeground(Color.WHITE);
                    board[row][col].setBorder(b);

                } else if (value == 2) {
                    Color c = new Color(gColor, 0, dColor);
                    Border b = BorderFactory.createLineBorder(c, 2,
                            true);

                    board[row][col].setText(Integer.toString(value));
                    board[row][col].setBorder(b);
                    board[row][col].setBackground(c);
                    board[row][col].setForeground(Color.WHITE);

                } else if (value >= bound) {

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

    /*****************************************************************
     * Add menu items to save and load the game.
     *
     * @param frame The frame the menu is attached to.
     ****************************************************************/
    private static void createMenu(final JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(e -> {
            game.saveGame();
        });

        // This can't be done with a lambda because it needs two statements.
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                // TODO Auto-generated method stub

            }

        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> {
            System.exit(0);
        });

        menu.add(save);
        menu.add(load);
        menu.add(exit);
        menuBar.add(menu);

        frame.setJMenuBar(menuBar);
    }

    /******************************************************************
     * Dictates where each individual JButton, JLabel, JPanel goes and
     * what is assigned to that spot.
     *
     *****************************************************************/
    public static void playGame() {

        ThreesTwoGUI gui = new ThreesTwoGUI();

        JPanel main = new JPanel(new BorderLayout(FIVE, FIVE));
        main.setBackground(Color.WHITE);
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JPanel west = new JPanel(new GridLayout(S_BOUND, 1, FIVE, FIVE));
        JPanel east = new JPanel();

        top.setBackground(Color.WHITE);
        bottom.setBackground(Color.WHITE);
        west.setBackground(Color.WHITE);
        east.setBackground(Color.WHITE);

        // side panel top
        JPanel nextPanel = new JPanel(new BorderLayout(FIVE, FIVE));
        JPanel scorePanel = new JPanel(new BorderLayout(FIVE, FIVE));
        JLabel next = new JLabel("Next");
        JPanel nextWest = new JPanel();
        JPanel nextSouth = new JPanel();
        JPanel nextEast = new JPanel();

        west.setPreferredSize(new Dimension(W_HEIGHT, W_WIDTH));
        west.setBorder(BorderFactory.createLineBorder(Color.WHITE, FIVE));

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
        createMenu(frame);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(main);
        frame.pack();
        frame.setVisible(true);
    }

}
