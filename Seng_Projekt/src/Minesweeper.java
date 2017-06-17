import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import java.awt.Canvas;
import java.awt.Button;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.SwingUtilities;

public class Minesweeper {

	private JFrame frmMinesweeper;
	boolean selectedAnf = true;
	boolean selectedErf = false;
	boolean selectedProf = false;
	Minesweeper menu = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Minesweeper window = new Minesweeper();
					window.frmMinesweeper.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Minesweeper() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmMinesweeper = new JFrame(); 
		frmMinesweeper.setResizable(false);
		frmMinesweeper.setTitle("Minesweeper");
		frmMinesweeper.setBounds(500, 200, 451, 392); 					//Position und Größe des Fensters
		frmMinesweeper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMinesweeper.getContentPane().setLayout(null);

		initializeButtons();

		JTextArea txtrSoftwareengineering = new JTextArea();
		txtrSoftwareengineering.setEditable(false);
		txtrSoftwareengineering.setText("Software-Engineering \nProjekt von \nSandra, Philipp, John, Chris");
		txtrSoftwareengineering.setBounds(36, 267, 185, 55);
		frmMinesweeper.getContentPane().add(txtrSoftwareengineering);

		JLabel lblMinesweeper = new JLabel("Minesweeper");
		lblMinesweeper.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblMinesweeper.setBounds(126, 30, 196, 23);
		frmMinesweeper.getContentPane().add(lblMinesweeper);

		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem close = new JMenuItem("Close");

		file.add(close);

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		bar.add(file);

		frmMinesweeper.setJMenuBar(bar);

        JTextField txtPlayer = new JTextField();
		txtPlayer.setBackground(UIManager.getColor("Button.light"));
		txtPlayer.setHorizontalAlignment(SwingConstants.CENTER);
		txtPlayer.setText("Player1");
		txtPlayer.setBounds(309, 220, 86, 20);
		frmMinesweeper.getContentPane().add(txtPlayer);
		txtPlayer.setColumns(10);
		
		JTextPane txtpnName = new JTextPane();
		txtpnName.setBackground(UIManager.getColor("CheckBox.background"));
		txtpnName.setText("Name:");
		txtpnName.setBounds(309, 189, 90, 20);
		frmMinesweeper.getContentPane().add(txtpnName);
		

	}

	private void initializeButtons() { 							//Button werden erstellt

		ButtonGroup bGroupGame = new ButtonGroup();
		ButtonGroup bGroupDiff = new ButtonGroup();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Spiel", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(36, 85, 118, 166);
		frmMinesweeper.getContentPane().add(panel);
		panel.setLayout(null);

		JToggleButton btnStandard = new JToggleButton("Standard");
		btnStandard.setSelected(true);
		btnStandard.setBounds(6, 16, 106, 46);
		panel.add(btnStandard);

		JToggleButton btnTreasure = new JToggleButton("Treasure Hunt");
		btnTreasure.setBounds(6, 64, 106, 46);
		panel.add(btnTreasure);

		JToggleButton btnBomb = new JToggleButton("Bomb Game");
		btnBomb.setBounds(6, 113, 106, 46);
		panel.add(btnBomb);

		bGroupGame.add(btnStandard);
		bGroupGame.add(btnTreasure);
		bGroupGame.add(btnBomb);					//Buttongruppe für die Auswahl des Spiels

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Schwierigkeitsgrad",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(164, 85, 118, 166);
		frmMinesweeper.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JToggleButton btnEasy = new JToggleButton("Anf\u00E4nger");
		btnEasy.setSelected(true);
		btnEasy.setBounds(6, 16, 106, 46);
		panel_1.add(btnEasy);

		JToggleButton btnMedium = new JToggleButton("Erfahren");
		btnMedium.setBounds(6, 64, 106, 46);
		panel_1.add(btnMedium);

		JToggleButton btnPro = new JToggleButton("Profi");
		btnPro.setBounds(6, 113, 106, 46);
		panel_1.add(btnPro);

		bGroupDiff.add(btnEasy);
		bGroupDiff.add(btnMedium);
		bGroupDiff.add(btnPro);				//Buttongruppe für die Auswahl des Schwierigkeitsgrads

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMinesweeper.dispose();
				Game game = new Game(btnEasy.isSelected() ? 0 : btnMedium.isSelected() ? 1 : 2, menu);	//das Spielfenster wird je nach Auswahl des Schwierigkeitsgrads gestartet

			}
		});

		btnStart.setBounds(309, 299, 89, 23);
		frmMinesweeper.getContentPane().add(btnStart);

	}

	public JFrame getFrame() {
		return this.frmMinesweeper;
	}
}
