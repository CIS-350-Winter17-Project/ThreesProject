package com.cis350.threesTwo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.Border;

/**********************************************************************
 * The Graphical User Interface for the Threes game. This creates the
 * visual board, and instantiates the ThreesTwoGame class.
 *
 * @author Scott Lumsden
 * @author Dylan Shoup
 * @author Anthony Blanton
 * @version 0.2 April 19, 2017
 *********************************************************************/

public class ThreesTwoGUI extends JPanel {

    /** The logic of the board. This dictates what appears */
    private static ThreesTwoGame game;

    /** The board pieces that visually appear on the board. */
    private JLabel[][] board;

    /** A one-to-one copy of the ThreesTwo object. */
    private Cell[][] gameBoard;

    /** Shows the player what piece will appear next after a move. */
    private JLabel nextCell;

    /** Current score that the player has accumulated in the current
     * play-through.
     */
    private JLabel cScore;

    /** The overall highScore that the player has ever achieved. */
    private JLabel hScore;

    /** The size of the board, which will always be four. */
    private final int boardSize = 4;

    /** For the Checkstyle warnings. */
    private final int bordThick = 10;

    /** Font for tiles. */
    private final int fontSize = 48;

    /** A smaller font. */
    private final int smallFont = 24;

    /** The color red in the backgrounds. */
    private final int red = 208;

    /** The color green in the backgrounds. */
    private final int green = 230;

    /** The color blue in the backgrounds. */
    private final int blue = 223;

    /** And here's the color. */
    private final Color c = new Color(red, green, blue);

    /** Random number bound. */
    private final int bound = 3;

    /** More checkstyle stuff. */
    private final int five = 5;


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

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {

                Border b = BorderFactory.createLineBorder(c, 2, true);

                board[row][col] = new JLabel();

                board[row][col].setBackground(c);
                board[row][col].setForeground(Color.WHITE);

                board[row][col].setOpaque(true);

                add(board[row][col]);

            }
        }

        // preview piece
        nextCell = new JLabel();

        // get score
        cScore = new JLabel();

        // get high score
        hScore = new JLabel();

        displayBoard();

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(final KeyEvent arg0) {

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

        cScore.setFont(new Font("Calibri", Font.ITALIC, smallFont));
        hScore.setFont(new Font("Calibri", Font.ITALIC, smallFont));

        nextCell.setFont(new Font("Calibri", Font.BOLD, fontSize));
        nextCell.setText(" ");

        if (nextValue == 1) {

            nextCell.setIcon(new ImageIcon("images/one.png"));
        }

        if (nextValue == 2) {

            nextCell.setIcon(new ImageIcon("images/two.png"));
        }

        if (nextValue >= bound) {

            nextCell.setIcon(new ImageIcon("images/three.png"));
        }

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {

                int value = gameBoard[row][col].getValue();

                board[row][col]
                        .setFont(new Font("Calibri",
                                Font.BOLD, fontSize));

                if (value == 0) {

                    board[row][col].setBackground(c);
                    board[row][col].setText(" ");
                    board[row][col].setIcon(null);

                }

                if (value == 1) {

                    board[row][col].setIcon(new ImageIcon("images/one.png"));
                    board[row][col].setText(Integer.toString(value));
                    board[row][col].setForeground(Color.WHITE);
                    board[row][col].setHorizontalTextPosition(JLabel.CENTER);
                    board[row][col].setVerticalTextPosition(JLabel.CENTER);

                } else if (value == 2) {

                    board[row][col].setIcon(new ImageIcon("images/two.png"));
                    board[row][col].setText(Integer.toString(value));
                    board[row][col].setForeground(Color.WHITE);
                    board[row][col].setHorizontalTextPosition(JLabel.CENTER);
                    board[row][col].setVerticalTextPosition(JLabel.CENTER);

                    board[row][col].setForeground(Color.WHITE);

                } else if (value >= bound) {

                    board[row][col].setIcon(new ImageIcon("images/three.png"));
                    board[row][col].setText(Integer.toString(value));
                    board[row][col].setHorizontalTextPosition(JLabel.CENTER);
                    board[row][col].setVerticalTextPosition(JLabel.CENTER);

                    board[row][col].setForeground(Color.BLACK);
                    board[row][col].setBackground(Color.WHITE);

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
    private void createMenu(final JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(e -> {
            if (!game.saveGame()) {
                JOptionPane.showMessageDialog(null,
                        "The game was not saved", "Oops",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // This can't be done with a lambda because it needs two statements.
        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!game.loadGame()) {
                    JOptionPane.showMessageDialog(null,
                            "The game cannot be loaded", "Oops",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    JOptionPane.showMessageDialog(null,
                            "Game loaded, press any key to refresh");
                }
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
    public void playGame() {

        ThreesTwoGUI gui = new ThreesTwoGUI();

        JPanel main = new JPanel(new BorderLayout(five, five));
        main.setBackground(Color.WHITE);

        // Individual panels for each section
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JPanel west = new JPanel(new GridLayout(bound, 1, five, five));
        JPanel east = new JPanel();

        top.setBackground(Color.WHITE);
        bottom.setBackground(Color.WHITE);
        west.setBackground(Color.WHITE);
        east.setBackground(Color.WHITE);

        // side panel top
        JPanel nextPanel = new JPanel(new BorderLayout(five, five));
        JPanel scorePanel = new JPanel(new BorderLayout(five, five));

        nextPanel.setBackground(c);
        nextPanel.setOpaque(true);

        JLabel next = new JLabel("Next");

        next.setFont(new Font("Calibri", Font.ITALIC, smallFont));

        next.setBackground(c);

        west.setBorder(BorderFactory.createLineBorder(Color.WHITE, five));

        scorePanel.add(hScore, BorderLayout.NORTH);
        scorePanel.add(cScore, BorderLayout.SOUTH);
        scorePanel.setBackground(c);

        nextPanel.add(next, BorderLayout.NORTH);
        nextPanel.add(nextCell, BorderLayout.CENTER);

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
