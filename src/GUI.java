import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * ************************************************** GUI frame for Yahtzee
 *
 * @version September 27 2019 *********************************************
 */

/** @author Nate Johnson */
@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
  /* Game Logic Object */
  GameLogic game;

  /* JPanel for All Dice Buttons */
  private JPanel dicePanel;
  /* JPanel for Displaying Scores */
  private JPanel scorePanel;
  /* JPanel for Selecting Scores */
  private JPanel scoreSelectPanel;
  /* JPanel for Action Buttons */
  private JPanel actionPanel;

  /* Holds all JMenus*/
  private JMenuBar menus;

  /* JMenu for file operations*/
  private JMenu fileMenu;
  /* JMenu for checking in*/
  private JMenu optionsMenu;

  /* Menu Item to Open Saved Game */
  private JMenuItem openGame;
  /* Menu Item to Save Game */
  private JMenuItem saveGame;
  /* Menu Item to exit game */
  private JMenuItem exitItem;
  /* Menu Item to add player */
  private JMenuItem addPlayer;

  /* Button to Roll Dice 1 */
  JButton diceBtn1;
  /* Button to Roll Dice 2 */
  JButton diceBtn2;
  /* Button to Roll Dice 3 */
  JButton diceBtn3;
  /* Button to Roll Dice 4 */
  JButton diceBtn4;
  /* Button to Roll Dice 5 */
  JButton diceBtn5;
  /* Button to Roll all */
  JButton rollAll;
  /* Button to Hold Dice */
  JButton holdDice;
  /* Button to Pass Dice */
  JButton passDice;

  /* Button to submit score */
  JButton submitScore;
  
  /* Label to show whose turn it is */
  JLabel turn;
  
  /* Label to show scores */
  JLabel player1Score;
  JLabel player2Score;
  JLabel player3Score;
  JLabel player4Score;
  JLabel player5Score;


  JComboBox scoreOptions;
  /** Constructor for GUI, This is where
   * I set up the layout as well as
   * all the buttons and menus. */
  public GUI() {
    // Instantiates new gamelogic object
    game = new GameLogic();

    // Instantiates menu bars
    fileMenu = new JMenu("File");
    optionsMenu = new JMenu("Options");

    // Instantiates buttons on file menu
    addPlayer = new JMenuItem("Add Player");
    openGame = new JMenuItem("Open Game");
    saveGame = new JMenuItem("Save Game");
    exitItem = new JMenuItem("Exit");

    // Adds buttons to file menu
    fileMenu.add(addPlayer);
    fileMenu.add(openGame);
    fileMenu.add(saveGame);
    fileMenu.addSeparator();
    fileMenu.add(exitItem);

    // Instantiates action listeners for file menu
    addPlayer.addActionListener(this);
    openGame.addActionListener(this);
    exitItem.addActionListener(this);
    saveGame.addActionListener(this);

    // Instantiates new menu bar
    menus = new JMenuBar();

    // adds file and options to menubar
    menus.add(fileMenu);
    menus.add(optionsMenu);

    // adds menu bar to screen
    setJMenuBar(menus);

    // sets layout for gui panel
    setLayout(new GridLayout(4, 1));

    // Adds label with current players turn
    turn = new JLabel("Turn Player  " + game.getTurn());
    add(turn);

    // Creates and adds new panel for the roll dice buttons
    dicePanel = new JPanel();
    add(dicePanel);
    dicePanel.setLayout(new GridLayout(2, 3));

    // creates and adds roll dice buttons
    diceBtn1 = new JButton("");
    diceBtn2 = new JButton("");
    diceBtn3 = new JButton("");
    diceBtn4 = new JButton("");
    diceBtn5 = new JButton("");
    rollAll = new JButton("Roll");

    dicePanel.add(diceBtn1);
    dicePanel.add(diceBtn2);
    dicePanel.add(diceBtn3);
    dicePanel.add(diceBtn4);
    dicePanel.add(diceBtn5);
    dicePanel.add(rollAll);

    // creates action listeners for dice buttons
    diceBtn1.addActionListener(this);
    diceBtn2.addActionListener(this);
    diceBtn3.addActionListener(this);
    diceBtn4.addActionListener(this);
    diceBtn5.addActionListener(this);
    rollAll.addActionListener(this);

    // ads new panel and labels to show players scores
    scorePanel = new JPanel();
    add(scorePanel);
    scorePanel.setLayout(new GridLayout(6, 1));
    scorePanel.add(new JLabel("Scores:"));
    player1Score = new JLabel("Player 1's Score:  " + game.player1.getTotalScore());
    player2Score = new JLabel("Player 2's Score:  " + game.player2.getTotalScore());
    player3Score = new JLabel("Player 3's Score:  " + game.player3.getTotalScore());
    player4Score = new JLabel("Player 4's Score:  " + game.player4.getTotalScore());
    player5Score = new JLabel("Player 5's Score:  " + game.player5.getTotalScore());
    scorePanel.add(player1Score);
    scorePanel.add(player2Score);
    scorePanel.add(player3Score);
    scorePanel.add(player4Score);
    scorePanel.add(player5Score);

    // Creates and adds new panel for the roll dice buttons
    actionPanel = new JPanel();
    add(actionPanel);
    actionPanel.setLayout(new GridLayout(1, 2));

    // creates and adds roll dice buttons
    holdDice = new JButton("Hold Dice");
    passDice = new JButton("Pass Dice");

    actionPanel.add(holdDice);
    actionPanel.add(passDice);
    holdDice.addActionListener(this);
    passDice.addActionListener(this);

    // adds new panel and combobox and button for selecting your score
    scoreSelectPanel = new JPanel();
    add(scoreSelectPanel);
    scoreSelectPanel.setLayout(new GridLayout(1, 2));

    scoreOptions = new JComboBox(game.optionChosen.values());
    scoreSelectPanel.add(scoreOptions);

    submitScore = new JButton("Submit");
    scoreSelectPanel.add(submitScore);

    // some additional parameters
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    setSize(800, 500);
  }

  /**
   * @param args
   *     <p>Instantiates a new gui
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

    // Exit button
    if (e.getSource() == exitItem) {
      System.exit(0);
    }

    // if someone presses the open button it goes here
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
			JOptionPane.showMessageDialog
			(null, "Invalid File!");
		} catch (IOException e1) {
			JOptionPane.showMessageDialog
			(null, "Invalid File!");
		} catch (Exception e1) {
			JOptionPane.showMessageDialog
			(null, "Invalid File!");
		}
      }
    }

    // if someone presses the save button it goes here
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

    // Select Button 1
    if (e.getSource() == diceBtn1) {
      if (!diceBtn1.isEnabled())
        diceBtn1.setEnabled(true);
      else
        diceBtn1.setEnabled(false);
    }

    // Select Button 2
    if (e.getSource() == diceBtn2) {
      if (!diceBtn2.isEnabled())
        diceBtn2.setEnabled(true);
      else
        diceBtn2.setEnabled(false);
    }

    // Select Button 3
    if (e.getSource() == diceBtn3) {
      if (!diceBtn3.isEnabled())
        diceBtn3.setEnabled(true);
      else
        diceBtn3.setEnabled(false);
    }

    // Select Button 4
    if (e.getSource() == diceBtn4) {
      if (!diceBtn4.isEnabled()) {
        diceBtn4.setEnabled(true);
      } else {
        diceBtn4.setEnabled(false);
        }
    }

    // Select Button 5
    if (e.getSource() == diceBtn5) {
      if (!diceBtn5.isEnabled())
        diceBtn5.setEnabled(true);
      else
        diceBtn5.setEnabled(false);
    }

    // Roll All
    if (e.getSource() == rollAll) {
      game.roll();
      if (diceBtn1.isEnabled())
        diceBtn1.setText(Integer.toString(game.die1.getRoll()));
      if (diceBtn2.isEnabled())
        diceBtn2.setText(Integer.toString(game.die2.getRoll()));
      if (diceBtn3.isEnabled())
        diceBtn3.setText(Integer.toString(game.die3.getRoll()));
      if (diceBtn4.isEnabled())
        diceBtn4.setText(Integer.toString(game.die4.getRoll()));
      if (diceBtn5.isEnabled())
        diceBtn5.setText(Integer.toString(game.die5.getRoll()));
    }

    // Submit Score Button
    if (e.getSource() == submitScore) {
      game.optionChosen = (ScoreOption) scoreOptions.getSelectedItem();
      turn.setText("Turn Player  " + game.getTurn()); //update whose turn it is
      player1Score.setText("Player 1's Score:  " + game.player1.getTotalScore());
      player2Score.setText("Player 2's Score:  " + game.player2.getTotalScore());
      player3Score.setText("Player 3's Score:  " + game.player3.getTotalScore());
      player4Score.setText("Player 4's Score:  " + game.player4.getTotalScore());
      player5Score.setText("Player 5's Score:  " + game.player5.getTotalScore());
    }

    if (e.getSource() == holdDice) {
      if (diceBtn1.isEnabled())
        game.die1.setHold(true);
      if (diceBtn2.isEnabled())
        game.die2.setHold(true);
      if (diceBtn3.isEnabled())
        game.die3.setHold(true);
      if (diceBtn4.isEnabled())
        game.die4.setHold(true);
      if (diceBtn5.isEnabled())
        game.die5.setHold(true);
    }
    if (e.getSource() == passDice) {}
    if (e.getSource() == addPlayer) {
      String playername = JOptionPane.showInputDialog("Please input a player name: ");
      game.addPlayer(playername);
    }
  }
}
