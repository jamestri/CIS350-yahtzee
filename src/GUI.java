import java.awt.Dimension;
import java.awt.GridLayout; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**********************************************************************
 * GUI frame for Yahtzee
 *  
 * @version September 27 2019
 *********************************************************************/

@SuppressWarnings("serial")
public class GUI extends JFrame implements ActionListener {
	GameLogic game;
	
	private JPanel dicePanel;
	private JPanel scorePanel;
	private JPanel scoreSelectPanel;
	
	/* Holds all JMenus*/
	private JMenuBar menus;

	/* JMenu for file operations*/
	private JMenu fileMenu;
	/* JMenu for checking in*/
	private JMenu optionsMenu;

	private JMenuItem openGame;
	private JMenuItem saveGame;
	private JMenuItem exitItem;
	
	JButton diceBtn1;
	JButton diceBtn2;
	JButton diceBtn3;
	JButton diceBtn4;
	JButton diceBtn5;

	public GUI() {
		game = new GameLogic();
		
		fileMenu = new JMenu("File");
		optionsMenu = new JMenu("Options");

		openGame = new JMenuItem("Open Game");
		saveGame = new JMenuItem("Save Game");
		exitItem = new JMenuItem("Exit");

		fileMenu.add(openGame);
		fileMenu.add(saveGame);
		fileMenu.addSeparator();		
		fileMenu.add(exitItem);
		
		openGame.addActionListener(this);
		exitItem.addActionListener(this);
		saveGame.addActionListener(this);

		menus = new JMenuBar();

		menus.add(fileMenu); 
		menus.add(optionsMenu);

		setJMenuBar(menus);
		
		setLayout(new GridLayout(4,1));
		add(new JLabel("Player #'s Turn"));
		
		dicePanel = new JPanel();
		add(dicePanel);
		dicePanel.setLayout(new GridLayout(2,3));
		
		diceBtn1 = new JButton("Roll 1");
		diceBtn2 = new JButton("Roll 2");
		diceBtn3 = new JButton("Roll 3");
		diceBtn4 = new JButton("Roll 4");
		diceBtn5 = new JButton("Roll 5");
		
//		diceBtn1.setPreferredSize(new Dimension(70, 70));
		
		dicePanel.add(diceBtn1);
		dicePanel.add(diceBtn2);
		dicePanel.add(diceBtn3);
		dicePanel.add(diceBtn4);
		dicePanel.add(diceBtn5);

		
		diceBtn1.addActionListener(this);
		diceBtn2.addActionListener(this);
		diceBtn3.addActionListener(this);
		diceBtn4.addActionListener(this);
		diceBtn5.addActionListener(this);
		
		scorePanel = new JPanel();
		add(scorePanel);
		scorePanel.setLayout(new GridLayout(6,1));
		scorePanel.add(new JLabel("Scores:"));
		scorePanel.add(new JLabel("Player 1's Score:"));
		scorePanel.add(new JLabel("Player 2's Score:"));
		scorePanel.add(new JLabel("Player 3's Score:"));
		scorePanel.add(new JLabel("Player 4's Score:"));
		scorePanel.add(new JLabel("Player 5's Score:"));
		
		scoreSelectPanel = new JPanel();
		add(scoreSelectPanel);
		scoreSelectPanel.setLayout(new GridLayout(1,2));
		scoreSelectPanel.add(new JComboBox());
		scoreSelectPanel.add(new JButton("Submit"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(800,500);

	}


	public static void main (String[] args) {
		new GUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Exit button
		if(e.getSource() == exitItem) {
			System.exit(0);
		}
		
		if(e.getSource() == openGame) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile()
						.getAbsolutePath();
				//put try catch here to open the game
			}
		}

		if(e.getSource() == saveGame) {
			JFileChooser chooser = new JFileChooser();
			int status = chooser.showOpenDialog(null);
			if (status == JFileChooser.APPROVE_OPTION) {
				String filename = chooser.getSelectedFile()
						.getAbsolutePath();
				//put try catch here to save the game
			}
		}
		
		
		//Roll Button
		if(e.getSource() == diceBtn1) {
			game.roll();
			diceBtn1.setText(Integer.toString(game.die1.getRoll()));
		}
		
		//Roll Button
		if(e.getSource() == diceBtn2) {
			diceBtn2.setText(Integer.toString(game.die2.getRoll()));
		}
		
		//Roll Button
		if(e.getSource() == diceBtn3) {
			diceBtn3.setText(Integer.toString(game.die3.getRoll()));
		}
		
		//Roll Button
		if(e.getSource() == diceBtn4) {
			diceBtn4.setText(Integer.toString(game.die4.getRoll()));
		}
		
		//Roll Button
		if(e.getSource() == diceBtn5) {
			diceBtn5.setText(Integer.toString(game.die5.getRoll()));
		}
		
	}


}
