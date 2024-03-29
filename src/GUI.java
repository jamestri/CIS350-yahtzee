import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.ImageIcon;
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
 * Graphical User Interface for Yahtzee. Implements menu bar options, dice panel, held dice panel,
 * score display and score select panels. Implements game logic and action listeners
 *
 * @version 2
 * @author Nate Johnson and Logan Jaglowski
 */
@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {

  /** Game Logic Object. */
  GameLogic game;
  /** Number of players in game. */
  private int numPlayers;
  /** Number of AI in game. */
  private int numAI;
  /** JPanel for All Dice Buttons. */
  private JPanel dicePanel;
  /** JPanel for Displaying Scores. */
  private JPanel scorePanel;
  /** JPanel for Selecting Scores. */
  private JPanel scoreSelectPanel;
  /** JPanel for Action Buttons. */
  private JPanel actionPanel;
  /** Holds all JMenus. */
  private JMenuBar menus;
  /** JMenu for file operations. */
  private JMenu fileMenu;
  /** JMenu for options menu. */
  private JMenu optionsMenu;
  /** Menu Item to Open Saved Game. */
  private JMenuItem openGame;
  /** Menu Item to Save Game. */
  private JMenuItem saveGame;
  /** Menu Item to exit game. */
  private JMenuItem exitItem;
  /** Blank ImageIcon. */
  private ImageIcon blank;
  /** ImageIcon for Dice 1. */
  private ImageIcon one;
  /** ImageIcon for Dice 2. */
  private ImageIcon two;
  /** ImageIcon for Dice 3. */
  private ImageIcon three;
  /** ImageIcon for Dice 4. */
  private ImageIcon four;
  /** ImageIcon for Dice 5. */
  private ImageIcon five;
  /** ImageIcon for Dice 6. */
  private ImageIcon six;
  /** ImageIcon for Selected Dice 1. */
  private ImageIcon oneSelect;
  /** ImageIcon for Selected Dice 2. */
  private ImageIcon twoSelect;
  /** ImageIcon for Selected Dice 3. */
  private ImageIcon threeSelect;
  /** ImageIcon for Selected Dice 4. */
  private ImageIcon fourSelect;
  /** ImageIcon for Selected Dice 5. */
  private ImageIcon fiveSelect;
  /** ImageIcon for Selected Dice 6. */
  private ImageIcon sixSelect;
  /** Button to Roll Dice 1. */
  private JButton diceBtn1;
  /** Button to Roll Dice 2. */
  private JButton diceBtn2;
  /** Button to Roll Dice 3. */
  private JButton diceBtn3;
  /** Button to Roll Dice 4. */
  private JButton diceBtn4;
  /** Button to Roll Dice 5. */
  private JButton diceBtn5;
  /** Button to Roll All Dice. */
  private JButton rollDice;
  /** Button to Hold Dice. */
  private JButton holdDice;
  /** Button to Pass Dice. */
  private JButton passDice;
  /** Button to submit score. */
  private JButton submitScore;
  /** JComboBox to display score options. */
  private JComboBox scoreOptions;
  /** Label to show whose turn it is. */
  private JLabel turn;
  /** Label to show what round it is. */
  private JLabel round;
  /** Label to show player 1's score. */
  private JLabel player1Score;
  /** Label to show player 2's score. */
  private JLabel player2Score;
  /** Label to show player 3's score. */
  private JLabel player3Score;
  /** Label to show player 4's score. */
  private JLabel player4Score;
  /** Label to show player 5's score. */
  private JLabel player5Score;
  /** Menu item to change a player's name. */
  private JMenuItem changeName;
  /** Menu item to start a new game. */
  private JMenuItem newGame;
  /** Dimension of Die for GUI. */
  private static final int DIE_DIMENSION = 80;
  /**
   * Main method for running GUI.
   *
   * @param args
   *     <p>Instantiates a new gui
   */
  public static void main(String[] args) {
    new GUI();
  }

  /**
   * Constructor for GUI, This is where I set up the layout as well as all the buttons and menus.
   */
  public GUI() {

    String[] options = {"2", "3", "4", "5"};
    String playersChosen =
        (String)
            JOptionPane.showInputDialog(
                null,
                "Choose how many players you want",
                "Choose players",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    if (playersChosen == null) {
      System.exit(0);
    }
    if (playersChosen == "2") {
      numPlayers = 2;
    } else if (playersChosen == "3") {
      numPlayers = 3;
    } else if (playersChosen == "4") {
      numPlayers = 4;
    } else if (playersChosen == "5") {
      numPlayers = 5;
    } else {
      System.exit(0);
    }

    String[] aiOptions = new String[numPlayers];
    for (int i = 0; i < numPlayers; i++) {
      aiOptions[i] = "" + i;
    }
    String aiChosen =
        (String)
            JOptionPane.showInputDialog(
                null,
                "Choose how many AI players you want",
                "Choose players",
                JOptionPane.QUESTION_MESSAGE,
                null,
                aiOptions,
                aiOptions[0]);
    if (aiChosen == null) {
      System.exit(0);
    }
    if (aiChosen.equals("0")) {
      numAI = 0;
    } else if (aiChosen.equals("1")) {
      numAI = 1;
    } else if (aiChosen.equals("2")) {
      numAI = 2;
    } else if (aiChosen.equals("3")) {
      numAI = 3;
    } else if (aiChosen.equals("4")) {
      numAI = 4;
    } else {
      System.exit(0);
    }

    // Instantiates new game logic object
    game = new GameLogic();
    game.setNumPlayers(numPlayers);
    for (int i = 0; i < numAI; i++) {
      if (game.getNumPlayers() - i == 5) {
        game.player5.setAI(true);
        game.player5.setName("AI Thomas");
      }
      if (game.getNumPlayers() - i == 4) {
        game.player4.setAI(true);
        game.player4.setName("AI Jessica");
      }
      if (game.getNumPlayers() - i == 3) {
        game.player3.setAI(true);
        game.player3.setName("AI Steven");
      }
      if (game.getNumPlayers() - i == 2) {
        game.player2.setAI(true);
        game.player2.setName("AI Sean");
      }
    }

    // Sets layout for GUI
    setLayout(new GridLayout(3, 1));

    // Instantiates menus and menu items then adds
    // menu items to menus
    // Add action listeners for the menu items
    fileMenu = new JMenu("File");
    newGame = new JMenuItem("New Game");
    changeName = new JMenuItem("Change Name");
    changeName.addActionListener(this);

    openGame = new JMenuItem("Open Game");
    saveGame = new JMenuItem("Save Game");
    exitItem = new JMenuItem("Exit");

    fileMenu.add(changeName);
    fileMenu.addSeparator();
    fileMenu.add(newGame);
    fileMenu.add(openGame);
    fileMenu.add(saveGame);
    fileMenu.addSeparator();
    fileMenu.add(exitItem);

    newGame.addActionListener(this);
    openGame.addActionListener(this);
    exitItem.addActionListener(this);
    saveGame.addActionListener(this);

    // Instantiates menu bar and adds file and options menu
    // then adds menu bar to GUI
    menus = new JMenuBar();
    menus.add(fileMenu);

    setJMenuBar(menus);

    // Instantiates label to display current players turn
    // and adds the label to GUI

    turn = new JLabel("Turn " + game.getTurn().getName());
    add(turn);

    round = new JLabel("Round " + game.getNumRounds());

    // Instantiates and sets size of dice buttons
    // then adds action listeners to the dice buttons
    diceBtn1 = new JButton("");
    diceBtn2 = new JButton("");
    diceBtn3 = new JButton("");
    diceBtn4 = new JButton("");
    diceBtn5 = new JButton("");

    diceBtn1.setPreferredSize(new Dimension(DIE_DIMENSION, DIE_DIMENSION));
    diceBtn2.setPreferredSize(new Dimension(DIE_DIMENSION, DIE_DIMENSION));
    diceBtn3.setPreferredSize(new Dimension(DIE_DIMENSION, DIE_DIMENSION));
    diceBtn4.setPreferredSize(new Dimension(DIE_DIMENSION, DIE_DIMENSION));
    diceBtn5.setPreferredSize(new Dimension(DIE_DIMENSION, DIE_DIMENSION));

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

    // Creates and buffers ImageIcons for the dice to fit
    // and sets the initial dice button icons
    createDiceIcons();

    setDiceIcon(diceBtn1, 0, false);
    setDiceIcon(diceBtn2, 0, false);
    setDiceIcon(diceBtn3, 0, false);
    setDiceIcon(diceBtn4, 0, false);
    setDiceIcon(diceBtn5, 0, false);

    // Instantiates panel for the dice buttons and adds
    // the buttons then adds the panel to the GUI
    dicePanel = new JPanel();
    dicePanel.setLayout(new FlowLayout());

    dicePanel.add(diceBtn1);
    dicePanel.add(diceBtn2);
    dicePanel.add(diceBtn3);
    dicePanel.add(diceBtn4);
    dicePanel.add(diceBtn5);

    add(dicePanel);

    // Instantiates panel and labels to display player scores,
    // adds labels to panel then adds panel to GUI
    scorePanel = new JPanel();
    scorePanel.setLayout(new GridLayout(6, 1));

    turn.setText("Turn " + game.getTurn().getName()); // update whose turn it is
    round.setText("Round " + (game.getNumRounds() + 1));
    player1Score = new JLabel(game.player1.getName() + " Score:  " + game.player1.getTotalScore());
    player2Score = new JLabel(game.player2.getName() + " Score:  " + game.player2.getTotalScore());
    if (game.getNumPlayers() > 2) {
      player3Score =
          new JLabel(game.player3.getName() + " Score:  " + game.player3.getTotalScore());
    }
    if (game.getNumPlayers() > 3) {
      player4Score =
          new JLabel(game.player4.getName() + " Score:  " + game.player4.getTotalScore());
    }
    if (game.getNumPlayers() > 4) {
      player5Score =
          new JLabel(game.player5.getName() + " Score:  " + game.player5.getTotalScore());
    }
    scorePanel.add(round);
    scorePanel.add(player1Score);
    scorePanel.add(player2Score);
    if (game.getNumPlayers() > 2) {
      scorePanel.add(player3Score);
    }
    if (game.getNumPlayers() > 3) {
      scorePanel.add(player4Score);
    }
    if (game.getNumPlayers() > 4) {
      scorePanel.add(player5Score);
    }
    add(scorePanel);

    // Instantiates action buttons and adds action listeners
    rollDice = new JButton("Roll");
    holdDice = new JButton("Hold");
    passDice = new JButton("Pass");

    rollDice.addActionListener(this);
    holdDice.addActionListener(this);
    passDice.addActionListener(this);

    // Instantiates action panel and adds
    // buttons to panel then adds panel to GUI
    actionPanel = new JPanel();
    actionPanel.setLayout(new GridLayout(1, 3));

    actionPanel.add(rollDice);
    actionPanel.add(holdDice);
    actionPanel.add(passDice);

    add(actionPanel);

    // Instantiates score select panel, combobox for selection
    // and button for submitting selection, add combobox and button
    // to the panel and add panel to GUI
    scoreSelectPanel = new JPanel();
    scoreSelectPanel.setLayout(new GridLayout(1, 2));

    scoreOptions = new JComboBox(ScoreOption.values());
    submitScore = new JButton("Submit");

    scoreSelectPanel.add(scoreOptions);
    scoreSelectPanel.add(submitScore);

    submitScore.addActionListener(this);
    add(scoreSelectPanel);

    // Disable all buttons initially except roll
    holdDice.setEnabled(false);
    passDice.setEnabled(false);
    diceBtn1.setEnabled(false);
    diceBtn2.setEnabled(false);
    diceBtn3.setEnabled(false);
    diceBtn4.setEnabled(false);
    diceBtn5.setEnabled(false);
    submitScore.setEnabled(false);
    scoreOptions.setEnabled(false);

    // some additional parameters
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    setSize(800, 500);
  }

  /** Create ImageIcons for the Dice and buffer the size to fit the Dice button size. */
  private void createDiceIcons() {

    // instantiate ImageIcons from image files
    blank = new ImageIcon("./src/DiceImages/blank.png");
    one = new ImageIcon("./src/DiceImages/one.png");
    two = new ImageIcon("./src/DiceImages/two.png");
    three = new ImageIcon("./src/DiceImages/three.png");
    four = new ImageIcon("./src/DiceImages/four.png");
    five = new ImageIcon("./src/DiceImages/five.png");
    six = new ImageIcon("./src/DiceImages/six.png");
    oneSelect = new ImageIcon("./src/DiceImages/oneSelect.png");
    twoSelect = new ImageIcon("./src/DiceImages/twoSelect.png");
    threeSelect = new ImageIcon("./src/DiceImages/threeSelect.png");
    fourSelect = new ImageIcon("./src/DiceImages/fourSelect.png");
    fiveSelect = new ImageIcon("./src/DiceImages/fiveSelect.png");
    sixSelect = new ImageIcon("./src/DiceImages/sixSelect.png");

    // buffer ImageIcons to fit the dice size
    blank =
        new ImageIcon(
            blank.getImage().getScaledInstance(DIE_DIMENSION, DIE_DIMENSION, Image.SCALE_SMOOTH));
    one =
        new ImageIcon(
            one.getImage().getScaledInstance(DIE_DIMENSION, DIE_DIMENSION, Image.SCALE_SMOOTH));
    two =
        new ImageIcon(
            two.getImage().getScaledInstance(DIE_DIMENSION, DIE_DIMENSION, Image.SCALE_SMOOTH));
    three =
        new ImageIcon(
            three.getImage().getScaledInstance(DIE_DIMENSION, DIE_DIMENSION, Image.SCALE_SMOOTH));
    four =
        new ImageIcon(
            four.getImage().getScaledInstance(DIE_DIMENSION, DIE_DIMENSION, Image.SCALE_SMOOTH));
    five =
        new ImageIcon(
            five.getImage().getScaledInstance(DIE_DIMENSION, DIE_DIMENSION, Image.SCALE_SMOOTH));
    six =
        new ImageIcon(
            six.getImage().getScaledInstance(DIE_DIMENSION, DIE_DIMENSION, Image.SCALE_SMOOTH));
    oneSelect =
        new ImageIcon(
            oneSelect
                .getImage()
                .getScaledInstance(DIE_DIMENSION, DIE_DIMENSION, Image.SCALE_SMOOTH));
    twoSelect =
        new ImageIcon(
            twoSelect
                .getImage()
                .getScaledInstance(DIE_DIMENSION, DIE_DIMENSION, Image.SCALE_SMOOTH));
    threeSelect =
        new ImageIcon(
            threeSelect
                .getImage()
                .getScaledInstance(DIE_DIMENSION, DIE_DIMENSION, Image.SCALE_SMOOTH));
    fourSelect =
        new ImageIcon(
            fourSelect
                .getImage()
                .getScaledInstance(DIE_DIMENSION, DIE_DIMENSION, Image.SCALE_SMOOTH));
    fiveSelect =
        new ImageIcon(
            fiveSelect
                .getImage()
                .getScaledInstance(DIE_DIMENSION, DIE_DIMENSION, Image.SCALE_SMOOTH));
    sixSelect =
        new ImageIcon(
            sixSelect
                .getImage()
                .getScaledInstance(DIE_DIMENSION, DIE_DIMENSION, Image.SCALE_SMOOTH));
  }

  /**
   * Sets the Dice Icon to what that dice was rolled to.
   *
   * @param dieButton which die button need to set icon to
   * @param rolledValue value of the die roll on that button
   * @param selected whether the die was selected
   */
  private void setDiceIcon(JButton dieButton, int rolledValue, boolean selected) {

    // switches to the value the dice was rolled to set that dice icon
    switch (rolledValue) {
      case 1:
        if (selected) {
          dieButton.setIcon(oneSelect);
        } else {
          dieButton.setIcon(one);
        }
        break;
      case 2:
        if (selected) {
          dieButton.setIcon(twoSelect);
        } else {
          dieButton.setIcon(two);
        }
        break;
      case 3:
        if (selected) {
          dieButton.setIcon(threeSelect);
        } else {
          dieButton.setIcon(three);
        }
        break;
      case 4:
        if (selected) {
          dieButton.setIcon(fourSelect);
        } else {
          dieButton.setIcon(four);
        }
        break;
      case 5:
        if (selected) {
          dieButton.setIcon(fiveSelect);
        } else {
          dieButton.setIcon(five);
        }
        break;
      case 6:
        if (selected) {
          dieButton.setIcon(sixSelect);
        } else {
          dieButton.setIcon(six);
        }
        break;
      default:
        dieButton.setIcon(blank);
        break;
    }
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

    // Opens a saved from file
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
      player1Score.setText(game.player1.getName() + " Score:  " + game.player1.getTotalScore());
      player2Score.setText(game.player2.getName() + " Score:  " + game.player2.getTotalScore());
      if (game.getNumPlayers() > 2) {
        if (numPlayers < 3) {
          player3Score =
              new JLabel(game.player3.getName() + " Score:  " + game.player3.getTotalScore());
          scorePanel.add(player3Score);
        } else {
          player3Score.setText(game.player3.getName() + " Score:  " + game.player3.getTotalScore());
        }
      } else {
        if (numPlayers > 2) {
          player3Score.setText("");
        }
      }
      if (game.getNumPlayers() > 3) {
        if (numPlayers < 4) {
          player4Score =
              new JLabel(game.player4.getName() + " Score:  " + game.player4.getTotalScore());
          scorePanel.add(player4Score);
        } else {
          player4Score.setText(game.player4.getName() + " Score:  " + game.player4.getTotalScore());
        }
      } else {
        if (numPlayers > 3) {
          player4Score.setText("");
        }
      }
      if (game.getNumPlayers() > 4) {
        if (numPlayers < 5) {
          player5Score =
              new JLabel(game.player5.getName() + " Score:  " + game.player5.getTotalScore());
          scorePanel.add(player5Score);
        } else {
          player5Score.setText(game.player5.getName() + " Score:  " + game.player5.getTotalScore());
        }
      } else {
        if (numPlayers > 4) {
          player5Score.setText("");
        }
      }
      turn.setText("Turn " + game.getTurn().getName());
      round.setText("Round " + (game.getNumRounds() + 1));
      numPlayers = game.getNumPlayers();
    }
    // Saves current game to file
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
      if (!game.getTurn().hasAI()) {
        if (diceBtn1.getBackground() != Color.red) {
          diceBtn1.setBackground(Color.red);
          setDiceIcon(diceBtn1, game.die1.getRoll(), true);
        } else {
          diceBtn1.setBackground(new JButton().getBackground());
          setDiceIcon(diceBtn1, game.die1.getRoll(), false);
        }
      }
    }
    // Select Button 2
    if (e.getSource() == diceBtn2) {
      if (!game.getTurn().hasAI()) {
        if (diceBtn2.getBackground() != Color.red) {
          diceBtn2.setBackground(Color.red);
          setDiceIcon(diceBtn2, game.die2.getRoll(), true);
        } else {
          diceBtn2.setBackground(new JButton().getBackground());
          setDiceIcon(diceBtn2, game.die2.getRoll(), false);
        }
      }
    }
    // Select Button 3
    if (e.getSource() == diceBtn3) {
      if (!game.getTurn().hasAI()) {
        if (diceBtn3.getBackground() != Color.red) {
          diceBtn3.setBackground(Color.red);
          setDiceIcon(diceBtn3, game.die3.getRoll(), true);
        } else {
          diceBtn3.setBackground(new JButton().getBackground());
          setDiceIcon(diceBtn3, game.die3.getRoll(), false);
        }
      }
    }
    // Select Button 4
    if (e.getSource() == diceBtn4) {
      if (!game.getTurn().hasAI()) {
        if (diceBtn4.getBackground() != Color.red) {
          diceBtn4.setBackground(Color.red);
          setDiceIcon(diceBtn4, game.die4.getRoll(), true);
        } else {
          diceBtn4.setBackground(new JButton().getBackground());
          setDiceIcon(diceBtn4, game.die4.getRoll(), false);
        }
      }
    }
    // Select Button 5
    if (e.getSource() == diceBtn5) {
      if (!game.getTurn().hasAI()) {
        if (diceBtn5.getBackground() != Color.red) {
          diceBtn5.setBackground(Color.red);
          setDiceIcon(diceBtn5, game.die5.getRoll(), true);
        } else {
          diceBtn5.setBackground(new JButton().getBackground());
          setDiceIcon(diceBtn5, game.die5.getRoll(), false);
        }
      }
    }
    // Roll all available dice
    if (e.getSource() == rollDice) {
      game.roll();
      if (game.getNumRolls() == 1) {
        game.die1.setHold(false);
        game.die2.setHold(false);
        game.die3.setHold(false);
        game.die4.setHold(false);
        game.die5.setHold(false);
        diceBtn1.setBackground(new JButton().getBackground());
        diceBtn2.setBackground(new JButton().getBackground());
        diceBtn3.setBackground(new JButton().getBackground());
        diceBtn4.setBackground(new JButton().getBackground());
        diceBtn5.setBackground(new JButton().getBackground());
        diceBtn1.setEnabled(true);
        diceBtn2.setEnabled(true);
        diceBtn3.setEnabled(true);
        diceBtn4.setEnabled(true);
        diceBtn5.setEnabled(true);
        holdDice.setEnabled(true);
        submitScore.setEnabled(true);
        scoreOptions.setEnabled(true);
        rollDice.setEnabled(false);
      } else if (game.getNumRolls() == 2) {
        game.die1.setHold(false);
        game.die2.setHold(false);
        game.die3.setHold(false);
        game.die4.setHold(false);
        game.die5.setHold(false);
        diceBtn1.setBackground(new JButton().getBackground());
        diceBtn2.setBackground(new JButton().getBackground());
        diceBtn3.setBackground(new JButton().getBackground());
        diceBtn4.setBackground(new JButton().getBackground());
        diceBtn5.setBackground(new JButton().getBackground());
        holdDice.setEnabled(true);
        diceBtn1.setEnabled(true);
        diceBtn2.setEnabled(true);
        diceBtn3.setEnabled(true);
        diceBtn4.setEnabled(true);
        diceBtn5.setEnabled(true);
        rollDice.setEnabled(false);
      } else {
        game.die1.setHold(false);
        game.die2.setHold(false);
        game.die3.setHold(false);
        game.die4.setHold(false);
        game.die5.setHold(false);
        diceBtn1.setBackground(new JButton().getBackground());
        diceBtn2.setBackground(new JButton().getBackground());
        diceBtn3.setBackground(new JButton().getBackground());
        diceBtn4.setBackground(new JButton().getBackground());
        diceBtn5.setBackground(new JButton().getBackground());
        if (!submitScore.isEnabled()) {
          passDice.setEnabled(true);
        }
        diceBtn1.setEnabled(false);
        diceBtn2.setEnabled(false);
        diceBtn3.setEnabled(false);
        diceBtn4.setEnabled(false);
        diceBtn5.setEnabled(false);
        rollDice.setEnabled(false);
      }
      //            if (diceBtn1.isEnabled())
      setDiceIcon(diceBtn1, game.die1.getRoll(), false);
      //            if (diceBtn2.isEnabled())
      setDiceIcon(diceBtn2, game.die2.getRoll(), false);
      //            if (diceBtn3.isEnabled())
      setDiceIcon(diceBtn3, game.die3.getRoll(), false);
      //            if (diceBtn4.isEnabled())
      setDiceIcon(diceBtn4, game.die4.getRoll(), false);
      //            if (diceBtn5.isEnabled())
      setDiceIcon(diceBtn5, game.die5.getRoll(), false);
      game.incrementNumRolls();
    }
    // Holds the dice that were selected
    if (e.getSource() == holdDice) {
      diceBtn1.setEnabled(false);
      diceBtn2.setEnabled(false);
      diceBtn3.setEnabled(false);
      diceBtn4.setEnabled(false);
      diceBtn5.setEnabled(false);
      if (diceBtn1.getBackground() == Color.red) {
        game.die1.setHold(true);
      } else {
        game.die1.setHold(false);
      }
      if (diceBtn2.getBackground() == Color.red) {
        game.die2.setHold(true);
      } else {
        game.die2.setHold(false);
      }
      if (diceBtn3.getBackground() == Color.red) {
        game.die3.setHold(true);
      } else {
        game.die3.setHold(false);
      }
      if (diceBtn4.getBackground() == Color.red) {
        game.die4.setHold(true);
      } else {
        game.die4.setHold(false);
      }
      if (diceBtn5.getBackground() == Color.red) {
        game.die5.setHold(true);
      } else {
        game.die5.setHold(false);
      }
      if (!game.isMustPass()) {
        rollDice.setEnabled(true);
      }
      holdDice.setEnabled(false);
    }
    if (e.getSource() == changeName) {
      String[] options = new String[numPlayers];
      options[0] = game.player1.getName();
      options[1] = game.player2.getName();
      if (game.getNumPlayers() > 2) {
        options[2] = game.player3.getName();
      }
      if (game.getNumPlayers() > 3) {
        options[3] = game.player4.getName();
      }
      if (game.getNumPlayers() > 4) {
        options[4] = game.player5.getName();
      }
      String playerChosen =
          (String)
              JOptionPane.showInputDialog(
                  null,
                  "Choose which player you want to change names",
                  "Choose players",
                  JOptionPane.QUESTION_MESSAGE,
                  null,
                  options,
                  options[0]);
      String newName = JOptionPane.showInputDialog(null, "What's your name?");
      if (playerChosen == null || newName == null || newName.equals("")) {

      } else if (playerChosen.equals(game.player1.getName())) {
        game.player1.setName(newName);
      } else if (playerChosen.equals(game.player2.getName())) {
        game.player2.setName(newName);
      } else if (playerChosen.equals(game.player3.getName())) {
        game.player3.setName(newName);
      } else if (playerChosen.equals(game.player4.getName())) {
        game.player4.setName(newName);
      } else if (playerChosen.equals(game.player5.getName())) {
        game.player5.setName(newName);
      }
      player1Score.setText(game.player1.getName() + " Score:  " + game.player1.getTotalScore());
      player2Score.setText(game.player2.getName() + " Score:  " + game.player2.getTotalScore());
      if (game.getNumPlayers() > 2) {
        player3Score.setText(game.player3.getName() + " Score:  " + game.player3.getTotalScore());
      }
      if (game.getNumPlayers() > 3) {
        player4Score.setText(game.player4.getName() + " Score:  " + game.player4.getTotalScore());
      }
      if (game.getNumPlayers() > 4) {
        player5Score.setText(game.player5.getName() + " Score:  " + game.player5.getTotalScore());
      }
    }
    // Passes to next player
    if (e.getSource() == passDice) {
        game.addScore(game.getTurn());
        game.setMustPass(false);
        holdDice.setEnabled(false);
        passDice.setEnabled(false);
        diceBtn1.setEnabled(false);
        diceBtn2.setEnabled(false);
        diceBtn3.setEnabled(false);
        diceBtn4.setEnabled(false);
        diceBtn5.setEnabled(false);
        submitScore.setEnabled(false);
        scoreOptions.setEnabled(false);
        rollDice.setEnabled(true);
        diceBtn1.setText("");
        diceBtn2.setText("");
        diceBtn3.setText("");
        diceBtn4.setText("");
        diceBtn5.setText("");
        setDiceIcon(diceBtn1, 0, false);
        setDiceIcon(diceBtn2, 0, false);
        setDiceIcon(diceBtn3, 0, false);
        setDiceIcon(diceBtn4, 0, false);
        setDiceIcon(diceBtn5, 0, false);
        diceBtn1.setBackground(new JButton().getBackground());
        diceBtn2.setBackground(new JButton().getBackground());
        diceBtn3.setBackground(new JButton().getBackground());
        diceBtn4.setBackground(new JButton().getBackground());
        diceBtn5.setBackground(new JButton().getBackground());
        player1Score.setText(game.player1.getName() + " Score:  " + game.player1.getTotalScore());
        player2Score.setText(game.player2.getName() + " Score:  " + game.player2.getTotalScore());
        if (game.getNumPlayers() > 2) {
          player3Score.setText(game.player3.getName() + " Score:  " + game.player3.getTotalScore());
        }
        if (game.getNumPlayers() > 3) {
          player4Score.setText(game.player4.getName() + " Score:  " + game.player4.getTotalScore());
        }
        if (game.getNumPlayers() > 4) {
          player5Score.setText(game.player5.getName() + " Score:  " + game.player5.getTotalScore());
        }
        game.die1.setHold(false);
        game.die2.setHold(false);
        game.die3.setHold(false);
        game.die4.setHold(false);
        game.die5.setHold(false);
        scoreOptions.setSelectedIndex(0);
        turn.setText("Turn " + game.getTurn().getName());
        round.setText("Round " + game.getNumRounds() + 1);
        if (game.getTurn().hasAI()) {
          while (game.getTurn().hasAI()) {
            diceBtn1.setEnabled(true);
            diceBtn2.setEnabled(true);
            diceBtn3.setEnabled(true);
            diceBtn4.setEnabled(true);
            diceBtn5.setEnabled(true);
            rollDice.setEnabled(false);
            game.roll();
            if (game.die1.isHold()) {
              setDiceIcon(diceBtn1, game.die1.getRoll(), true);
            } else {
              setDiceIcon(diceBtn1, game.die1.getRoll(), false);
            }
            if (game.die2.isHold()) {
              setDiceIcon(diceBtn2, game.die2.getRoll(), true);
            } else {
              setDiceIcon(diceBtn2, game.die2.getRoll(), false);
            }
            if (game.die3.isHold()) {
              setDiceIcon(diceBtn3, game.die3.getRoll(), true);
            } else {
              setDiceIcon(diceBtn3, game.die3.getRoll(), false);
            }
            if (game.die4.isHold()) {
              setDiceIcon(diceBtn4, game.die4.getRoll(), true);
            } else {
              setDiceIcon(diceBtn4, game.die4.getRoll(), false);
            }
            if (game.die5.isHold()) {
              setDiceIcon(diceBtn5, game.die5.getRoll(), true);
            } else {
              setDiceIcon(diceBtn5, game.die5.getRoll(), false);
            }
            game.optionChosen = game.getAIScoringOption(game.getTurn());
            JOptionPane.showMessageDialog(
                null,
                "After their first roll, "
                    + game.getTurn().getName()
                    + " has chosen "
                    + game.optionChosen);
            game.aiChooseDice();
            game.roll();
            if (game.die1.isHold()) {
              setDiceIcon(diceBtn1, game.die1.getRoll(), true);
            } else {
              setDiceIcon(diceBtn1, game.die1.getRoll(), false);
            }
            if (game.die2.isHold()) {
              setDiceIcon(diceBtn2, game.die2.getRoll(), true);
            } else {
              setDiceIcon(diceBtn2, game.die2.getRoll(), false);
            }
            if (game.die3.isHold()) {
              setDiceIcon(diceBtn3, game.die3.getRoll(), true);
            } else {
              setDiceIcon(diceBtn3, game.die3.getRoll(), false);
            }
            if (game.die4.isHold()) {
              setDiceIcon(diceBtn4, game.die4.getRoll(), true);
            } else {
              setDiceIcon(diceBtn4, game.die4.getRoll(), false);
            }
            if (game.die5.isHold()) {
              setDiceIcon(diceBtn5, game.die5.getRoll(), true);
            } else {
              setDiceIcon(diceBtn5, game.die5.getRoll(), false);
            }
            JOptionPane.showMessageDialog(null, game.getTurn().getName() + "'s second roll!");
            game.aiChooseDice();
            game.roll();
            game.aiChooseDice();
            if (game.die1.isHold()) {
              setDiceIcon(diceBtn1, game.die1.getRoll(), true);
            } else {
              setDiceIcon(diceBtn1, game.die1.getRoll(), false);
            }
            if (game.die2.isHold()) {
              setDiceIcon(diceBtn2, game.die2.getRoll(), true);
            } else {
              setDiceIcon(diceBtn2, game.die2.getRoll(), false);
            }
            if (game.die3.isHold()) {
              setDiceIcon(diceBtn3, game.die3.getRoll(), true);
            } else {
              setDiceIcon(diceBtn3, game.die3.getRoll(), false);
            }
            if (game.die4.isHold()) {
              setDiceIcon(diceBtn4, game.die4.getRoll(), true);
            } else {
              setDiceIcon(diceBtn4, game.die4.getRoll(), false);
            }
            if (game.die5.isHold()) {
              setDiceIcon(diceBtn5, game.die5.getRoll(), true);
            } else {
              setDiceIcon(diceBtn5, game.die5.getRoll(), false);
            }
            game.addScore(game.getTurn());
            player1Score.setText(
                game.player1.getName() + " Score:  " + game.player1.getTotalScore());
            player2Score.setText(
                game.player2.getName() + " Score:  " + game.player2.getTotalScore());
            if (game.getNumPlayers() > 2) {
              player3Score.setText(
                  game.player3.getName() + " Score:  " + game.player3.getTotalScore());
            }
            if (game.getNumPlayers() > 3) {
              player4Score.setText(
                  game.player4.getName() + " Score:  " + game.player4.getTotalScore());
            }
            if (game.getNumPlayers() > 4) {
              player5Score.setText(
                  game.player5.getName() + " Score:  " + game.player5.getTotalScore());
            }
            JOptionPane.showMessageDialog(
                null,
                "That was their last roll, now it's " + game.getTurn().getName() + "'s Turn!");
            game.die1.setHold(false);
            game.die2.setHold(false);
            game.die3.setHold(false);
            game.die4.setHold(false);
            game.die5.setHold(false);
            setDiceIcon(diceBtn1, 0, false);
            setDiceIcon(diceBtn2, 0, false);
            setDiceIcon(diceBtn3, 0, false);
            setDiceIcon(diceBtn4, 0, false);
            setDiceIcon(diceBtn5, 0, false);
          }

          rollDice.setEnabled(true);
        }
        if (game.getNumRounds() == 13) {
          JOptionPane.showMessageDialog(
              null, "The game is finished! Congratulations " + game.isWinner().getName());
          rollDice.setEnabled(false);
        }
    }
    if (e.getSource() == newGame) {
      dispose();
      new GUI();
    }
    // Submits the score option selected and displays score
    if (e.getSource() == submitScore) {
      game.optionChosen = (ScoreOption) scoreOptions.getSelectedItem();
      if (game.optionChosen == ScoreOption.NONE) {
        JOptionPane.showMessageDialog(null, "Please select a scoring method");
      } else {
        if (game.checkIfCategoryUsed()) {
          JOptionPane.showMessageDialog(
              null,
              "Category already scored for current player, please choose a different category");
        } else {
          submitScore.setEnabled(false);
          scoreOptions.setEnabled(false);
          if (game.getNumRolls() == 1) {
            passDice.setEnabled(true);
          }
        }
      }
    }
  }
}
