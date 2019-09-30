import java.awt.GridLayout; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.EmptyStackException;
import java.util.GregorianCalendar;

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

	public GUI() {

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
		dicePanel.add(new JButton("1"));
		dicePanel.add(new JButton("2"));
		dicePanel.add(new JButton("3"));
		dicePanel.add(new JButton("4"));
		dicePanel.add(new JButton("5"));
		
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
		
		
	}


}
