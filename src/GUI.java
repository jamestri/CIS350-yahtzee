/**
 * Graphical User Interface for Yahtzee. Implements menu bar options, dice
 * panel, held dice panel, score display and score select panels. Implements
 * game logic and action listeners
 *
 * @version 2
 * @author Nate Johnson
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {

    /** Game Logic Object */
    GameLogic game;

    /** JPanel for All Dice Buttons */
    private JPanel dicePanel;
    /** JPanel for Displaying Scores */
    private JPanel scorePanel;
    /** JPanel for Selecting Scores */
    private JPanel scoreSelectPanel;
    /** JPanel for Action Buttons */
    private JPanel actionPanel;

    /** Holds all JMenus */
    private JMenuBar menus;

    /** JMenu for file operations */
    private JMenu fileMenu;
    /** JMenu for options menu */
    private JMenu optionsMenu;

    /** Menu Item to Open Saved Game */
    private JMenuItem openGame;
    /** Menu Item to Save Game */
    private JMenuItem saveGame;
    /** Menu Item to exit game */
    private JMenuItem exitItem;
    /** Menu Item to add player */
    private JMenuItem addPlayer;

    /** Blank ImageIcon */
    private ImageIcon blank;
    /** ImageIcon for Dice 1 */
    private ImageIcon one;
    /** ImageIcon for Dice 2 */
    private ImageIcon two;
    /** ImageIcon for Dice 3 */
    private ImageIcon three;
    /** ImageIcon for Dice 4 */
    private ImageIcon four;
    /** ImageIcon for Dice 5 */
    private ImageIcon five;
    /** ImageIcon for Dice 6 */
    private ImageIcon six;

    /** Button to Roll Dice 1 */
    JButton diceBtn1;
    /** Button to Roll Dice 2 */
    JButton diceBtn2;
    /** Button to Roll Dice 3 */
    JButton diceBtn3;
    /** Button to Roll Dice 4 */
    JButton diceBtn4;
    /** Button to Roll Dice 5 */
    JButton diceBtn5;
    /** Button to Roll All Dice */
    JButton rollAll;
    /** Button to Hold Dice */
    JButton holdDice;
    /** Button to Pass Dice */
    JButton passDice;

    /** Button to submit score */
    JButton submitScore;

    /** Label to show whose turn it is */
    JLabel turn;

    /** Label to show player 1's score */
    JLabel player1Score;
    /** Label to show player 2's score */
    JLabel player2Score;
    /** Label to show player 3's score */
    JLabel player3Score;
    /** Label to show player 4's score */
    JLabel player4Score;
    /** Label to show player 5's score */
    JLabel player5Score;

    /** JComboBox to display score options */
    JComboBox scoreOptions;

    /**
     * Constructor for GUI, This is where I set up the layout as well as
     * all the buttons and menus.
     */
    public GUI() {
        // Instantiates new game logic object
        game = new GameLogic();

        // Instantiates menu bars and menu items
        fileMenu = new JMenu("File");
        optionsMenu = new JMenu("Options");

        openGame = new JMenuItem("Open Game");
        saveGame = new JMenuItem("Save Game");
        exitItem = new JMenuItem("Exit");
        addPlayer = new JMenuItem("Add Player");

        // Adds menu items to file menu
        fileMenu.add(openGame);
        fileMenu.add(saveGame);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // Adds menu items to options menu
        optionsMenu.add(addPlayer);

        // Adds action listeners for menu items
        openGame.addActionListener(this);
        exitItem.addActionListener(this);
        saveGame.addActionListener(this);
        addPlayer.addActionListener(this);

        // Instantiates menu bar and adds file and options menu
        menus = new JMenuBar();
        menus.add(fileMenu);
        menus.add(optionsMenu);

        // Adds menu bar to GUI
        setJMenuBar(menus);

        // Sets layout for GUI
        setLayout(new GridLayout(3, 1));

        // Instantiates label with current players turn and adds it to GUI
        turn = new JLabel("Turn: Player  " + game.getTurn());
        add(turn);

        // create ImageIcons for the dice
        createDiceIcons();

        // Instantiates and adds panel for the dice buttons
        dicePanel = new JPanel();
        add(dicePanel);
        dicePanel.setLayout(new FlowLayout());

        // Instantiates and adds dice buttons
        diceBtn1 = new JButton("");
        diceBtn2 = new JButton("");
        diceBtn3 = new JButton("");
        diceBtn4 = new JButton("");
        diceBtn5 = new JButton("");
        diceBtn1.setPreferredSize(new Dimension(70, 70));
        diceBtn2.setPreferredSize(new Dimension(70, 70));
        diceBtn3.setPreferredSize(new Dimension(70, 70));
        diceBtn4.setPreferredSize(new Dimension(70, 70));
        diceBtn5.setPreferredSize(new Dimension(70, 70));

        // set initial dice button icons
        setDiceIcon(diceBtn1, 0);
        setDiceIcon(diceBtn2, 0);
        setDiceIcon(diceBtn3, 0);
        setDiceIcon(diceBtn4, 0);
        setDiceIcon(diceBtn5, 0);

        dicePanel.add(diceBtn1);
        dicePanel.add(diceBtn2);
        dicePanel.add(diceBtn3);
        dicePanel.add(diceBtn4);
        dicePanel.add(diceBtn5);

        // Adds action listeners for dice buttons
        diceBtn1.addActionListener(this);
        diceBtn2.addActionListener(this);
        diceBtn3.addActionListener(this);
        diceBtn4.addActionListener(this);
        diceBtn5.addActionListener(this);

        diceBtn1.setOpaque(true);
        diceBtn2.setOpaque(true);
        diceBtn3.setOpaque(true);
        diceBtn4.setOpaque(true);
        diceBtn5.setOpaque(true);

        // Instantiates and adds player score panel
        scorePanel = new JPanel();
        add(scorePanel);
        scorePanel.setLayout(new GridLayout(6, 1));
        scorePanel.add(new JLabel("Scores:"));

        // Instantiates labels for player scores and adds it to score panel
        player1Score = new JLabel("Player 1:  " + game.player1.getTotalScore());
        player2Score = new JLabel("Player 2:  " + game.player2.getTotalScore());
        player3Score = new JLabel("Player 3:  " + game.player3.getTotalScore());
        player4Score = new JLabel("Player 4:  " + game.player4.getTotalScore());
        player5Score = new JLabel("Player 5:  " + game.player5.getTotalScore());
        scorePanel.add(player1Score);
        scorePanel.add(player2Score);
        scorePanel.add(player3Score);
        scorePanel.add(player4Score);
        scorePanel.add(player5Score);

        // Instantiates and adds panel for the dice action options
        actionPanel = new JPanel();
        add(actionPanel);
        actionPanel.setLayout(new GridLayout(1, 3));

        // Instantiates and adds dice actions
        rollAll = new JButton("Roll");
        holdDice = new JButton("Hold Dice");
        passDice = new JButton("Pass Dice");
        actionPanel.add(rollAll);
        actionPanel.add(holdDice);
        actionPanel.add(passDice);

        // Adds action listeners for dice action options
        rollAll.addActionListener(this);
        holdDice.addActionListener(this);
        passDice.addActionListener(this);

        // Instantiates and adds score select panel
        scoreSelectPanel = new JPanel();
        add(scoreSelectPanel);
        scoreSelectPanel.setLayout(new GridLayout(1, 2));

        // Instantiates and adds combobox for scores to select
        scoreOptions = new JComboBox(game.optionChosen.values());
        scoreSelectPanel.add(scoreOptions);

        // Instantiates and adds button to submit score selected
        submitScore = new JButton("Submit");
        scoreSelectPanel.add(submitScore);

        // Adds action listeners for score selecting combobox and submit button
        scoreOptions.addActionListener(this);
        submitScore.addActionListener(this);

        // some additional parameters
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800, 500);
    }

    /**
     * Create ImageIcons for the Dice and
     * buffer the size to fit the Dice button size
     */
    private void createDiceIcons() {

        // instantiate ImageIcons from image files
        blank   = new ImageIcon("./src/DiceImages/blank.png");
        one     = new ImageIcon("./src/DiceImages/one.png");
        two     = new ImageIcon("./src/DiceImages/two.png");
        three   = new ImageIcon("./src/DiceImages/three.png");
        four    = new ImageIcon("./src/DiceImages/four.png");
        five    = new ImageIcon("./src/DiceImages/five.png");
        six     = new ImageIcon("./src/DiceImages/six.png");

        // buffer ImageIcons to fit the dice size
        blank   = new ImageIcon(blank.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        one     = new ImageIcon(one.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        two     = new ImageIcon(two.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        three   = new ImageIcon(three.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        four    = new ImageIcon(four.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        five    = new ImageIcon(five.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
        six     = new ImageIcon(six.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH));
    }

    /**
     * Sets the Dice Icon to what that dice was rolled to
     * @param dieButton which die button need to set icon to
     * @param rolledValue value of the die roll on that button
     */
    private void setDiceIcon(JButton dieButton, int rolledValue) {

        // switches to the value the dice was rolled to set that dice icon
        switch (rolledValue) {
            case 1:
                dieButton.setIcon(one);
                break;
            case 2:
                dieButton.setIcon(two);
                break;
            case 3:
                dieButton.setIcon(three);
                break;
            case 4:
                dieButton.setIcon(four);
                break;
            case 5:
                dieButton.setIcon(five);
                break;
            case 6:
                dieButton.setIcon(six);
                break;
            default:
                dieButton.setIcon(blank);
                break;
        }
    }
    /**
     * @param args <p>Instantiates a new gui
     */
    public static void main(String[] args) {
        new GUI();
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     * When an action is performed (ex. button is pressed) it goes in here
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // open saved game from file
        if (e.getSource() == openGame) {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showOpenDialog(null);

            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();

                try {
                    FileInputStream fileIn = new FileInputStream(filename);
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    game = (GameLogic) in.readObject();
                    in.close();
                } catch (ClassNotFoundException e1) {
                    JOptionPane.showMessageDialog(null, "Invalid File!");
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "Invalid File!");
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Invalid File!");
                }
            }
        }

        // save current game to a file
        if (e.getSource() == saveGame) {
            JFileChooser chooser = new JFileChooser();
            int status = chooser.showSaveDialog(null);

            if (status == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();

                try {
                    FileOutputStream fos = new FileOutputStream(filename);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(game);
                    oos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        // exit current game
        if (e.getSource() == exitItem) {
            System.exit(0);
        }

        // adds new player to game
        if (e.getSource() == addPlayer) {
            String playername = JOptionPane.showInputDialog("Please input a player name: ");
            game.addPlayer(playername);
        }

        // Select Button 1
        if (e.getSource() == diceBtn1) {
            if (diceBtn1.getBackground() != Color.red)
                diceBtn1.setBackground(Color.red);
            else
                diceBtn1.setBackground(new JButton().getBackground());
        }

        // Select Button 2
        if (e.getSource() == diceBtn2) {
            if (diceBtn2.getBackground() != Color.red)
                diceBtn2.setBackground(Color.red);
            else
                diceBtn2.setBackground(new JButton().getBackground());
        }

        // Select Button 3
        if (e.getSource() == diceBtn3) {
            if (diceBtn3.getBackground() != Color.red)
                diceBtn3.setBackground(Color.red);
            else
                diceBtn3.setBackground(new JButton().getBackground());
        }

        // Select Button 4
        if (e.getSource() == diceBtn4) {
            if (diceBtn4.getBackground() != Color.red) {
                diceBtn4.setBackground(Color.red);
            } else {
                diceBtn4.setBackground(new JButton().getBackground());
            }
        }

        // Select Button 5
        if (e.getSource() == diceBtn5) {
            if (diceBtn5.getBackground() != Color.red)
                diceBtn5.setBackground(Color.red);
            else
                diceBtn5.setBackground(new JButton().getBackground());
        }

        // Roll all available dice
        if (e.getSource() == rollAll) {
            game.roll();
            if (diceBtn1.isEnabled())
                setDiceIcon(diceBtn1, game.die1.getRoll());
            if (diceBtn2.isEnabled())
                setDiceIcon(diceBtn2, game.die2.getRoll());
            if (diceBtn3.isEnabled())
                setDiceIcon(diceBtn3, game.die3.getRoll());
            if (diceBtn4.isEnabled())
                setDiceIcon(diceBtn4, game.die4.getRoll());
            if (diceBtn5.isEnabled())
                setDiceIcon(diceBtn5, game.die5.getRoll());
        }

        // holds dice selected
        if (e.getSource() == holdDice) {
            if (diceBtn1.getBackground() == Color.red) {
                game.die1.setHold(true);
                diceBtn1.setEnabled(false);
            }
            if (diceBtn2.getBackground() == Color.red) {
                diceBtn2.setEnabled(false);
                game.die2.setHold(true);
            }
            if (diceBtn3.getBackground() == Color.red) {
                diceBtn3.setEnabled(false);
                game.die3.setHold(true);
            }
            if (diceBtn4.getBackground() == Color.red) {
                diceBtn4.setEnabled(false);
                game.die4.setHold(true);
            }
            if (diceBtn5.getBackground() == Color.red) {
                game.die5.setHold(true);
                diceBtn5.setEnabled(false);
            }
        }

        // pass dice selected
        if (e.getSource() == passDice) {
        }

        // score option selected
        if (e.getSource() == scoreOptions) {

        }

        // Submit Score Button
        if (e.getSource() == submitScore) {
            game.optionChosen = (ScoreOption) scoreOptions.getSelectedItem();
            turn.setText("Turn: Player  " + game.getTurn()); //update whose turn it is
            player1Score.setText("Player 1:  " + game.player1.getTotalScore());
            player2Score.setText("Player 2:  " + game.player2.getTotalScore());
            player3Score.setText("Player 3:  " + game.player3.getTotalScore());
            player4Score.setText("Player 4:  " + game.player4.getTotalScore());
            player5Score.setText("Player 5:  " + game.player5.getTotalScore());
        }



    }
}
