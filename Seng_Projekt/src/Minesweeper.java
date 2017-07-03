import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

/**
 * Minsweeper creates window with game settings before starting the game.
 * 
 * @author John Voronkov
 *
 * @version 2.0
 */
public class Minesweeper {
	private JFrame frmMinesweeper;
	private boolean selectedAnf = true;
	private boolean selectedErf = false;
	private boolean selectedProf = false;
	private ImageIcon bomb = new ImageIcon("img/mine.png");
	private Game game;
	private JTextField txtPlayer;
	private JTextPane txtpnName;
	private Minesweeper menu = this;

	/**
	 * Getting current main menu.
	 * 
	 * @return active main menu window.
	 */
	JFrame getFrmMinesweeper() {
		return frmMinesweeper;
	}

	/**
	 * Set window of main menu as current main menu. To get main you @see
	 * {@link #getFrmMinesweeper()}.
	 * 
	 * @param frmMinesweeper
	 *            Current main menu of the game.
	 */
	void setFrmMinesweeper(JFrame frmMinesweeper) {
		this.frmMinesweeper = frmMinesweeper;
	}

	/**
	 * 
	 * @return
	 */
	boolean isSelectedAnf() {
		return selectedAnf;
	}

	/**
	 * 
	 * @param selectedAnf
	 */
	void setSelectedAnf(boolean selectedAnf) {
		this.selectedAnf = selectedAnf;
	}

	/**
	 * 
	 * @return
	 */
	boolean isSelectedErf() {
		return selectedErf;
	}

	/**
	 * 
	 * @param selectedErf
	 */
	void setSelectedErf(boolean selectedErf) {
		this.selectedErf = selectedErf;
	}
	/**
	 * 
	 * @return
	 */
	boolean isSelectedProf() {
		return selectedProf;
	}

	/**
	 * 
	 * @param selectedProf
	 */
	void setSelectedProf(boolean selectedProf) {
		this.selectedProf = selectedProf;
	}

	/**
	 * 
	 * @return
	 */
	Minesweeper getMenu() {
		return menu;
	}
	
	
	/**
	 * Getting current main menu.
	 * 
	 * @return active main menu window.
	 */
	public JFrame getFrame() {
		return this.frmMinesweeper;
	}

	/**
	 * Starts the game.
	 * 
	 * @param args
	 *            command line
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
	 * Starts its initialisation.
	 */
	public Minesweeper() {
		initialize();
	}

	/**
	 * Setting up text components for window.
	 */
	private void initialize() {
		frmMinesweeper = new JFrame();
		frmMinesweeper.setResizable(false);
		frmMinesweeper.setTitle("Minesweeper");
		frmMinesweeper.setBounds(500, 200, 451, 392);
		frmMinesweeper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMinesweeper.getContentPane().setLayout(null);

		JTextArea txtrSoftwareengineering = new JTextArea();
		txtrSoftwareengineering.setEditable(false);
		txtrSoftwareengineering.setText("Software-Engineering \nProjekt von \nSandra, Philipp, John, Chris");
		txtrSoftwareengineering.setBounds(36, 267, 185, 55);
		frmMinesweeper.getContentPane().add(txtrSoftwareengineering);

		JLabel lblMinesweeper = new JLabel("Minesweeper");
		lblMinesweeper.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblMinesweeper.setBounds(126, 30, 178, 23);
		frmMinesweeper.getContentPane().add(lblMinesweeper);

		txtPlayer = new JTextField();
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

		bomb.setImage(bomb.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		initializeButtons();
	}

	/**
	 * Setting up button components with their actions.
	 */
	private void initializeButtons() {
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
		bGroupGame.add(btnBomb);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Schwierigkeitsgrad",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(164, 85, 118, 166);
		frmMinesweeper.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JToggleButton btnEasy = new JToggleButton("Beginner");
		btnEasy.setSelected(true);
		btnEasy.setBounds(6, 16, 106, 46);
		panel_1.add(btnEasy);

		JToggleButton btnMedium = new JToggleButton("Amateur");
		btnMedium.setBounds(6, 64, 106, 46);
		panel_1.add(btnMedium);

		JToggleButton btnPro = new JToggleButton("Advanced");
		btnPro.setBounds(6, 113, 106, 46);
		panel_1.add(btnPro);

		bGroupDiff.add(btnEasy);
		bGroupDiff.add(btnMedium);
		bGroupDiff.add(btnPro);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMinesweeper.dispose();
				if (btnStandard.isSelected()) {
					game = new Game(btnEasy.isSelected() ? 0 : btnMedium.isSelected() ? 1 : 2, menu);
					Game.getGl().setPlayerName(txtPlayer.getText());
				}
				if (btnTreasure.isSelected()) {
					game = new Game(btnEasy.isSelected() ? 3 : btnMedium.isSelected() ? 4 : 5, menu);
					Game.getGl().setPlayerName(txtPlayer.getText());
				}
				if (btnBomb.isSelected()) {
					game = new Game(btnEasy.isSelected() ? 6 : btnMedium.isSelected() ? 7 : 8, menu);
					Game.getGl().setPlayerName(txtPlayer.getText());
				}
			}
		});
		btnStart.setBounds(309, 299, 89, 23);
		frmMinesweeper.getContentPane().add(btnStart);

		// Switch directly to Highscore lists.
		JButton justScore = new JButton("Highscore");
		justScore.addActionListener(new ActionListener() {
			HighscoreFrame hr;

			public void actionPerformed(ActionEvent e) {
				frmMinesweeper.dispose();
				if (btnStandard.isSelected()) {
					hr = new HighscoreFrame(btnEasy.isSelected() ? 0 : btnMedium.isSelected() ? 1 : 2);
				}
				if (btnTreasure.isSelected()) {
					hr = new HighscoreFrame(btnEasy.isSelected() ? 3 : btnMedium.isSelected() ? 4 : 5);
				}
				if (btnBomb.isSelected()) {
					hr = new HighscoreFrame(btnEasy.isSelected() ? 6 : btnMedium.isSelected() ? 7 : 8);
				}
			}
		});
		justScore.setBounds(309, 275, 89, 23);
		frmMinesweeper.getContentPane().add(justScore);

		JLabel lblBomb2 = new JLabel("");
		lblBomb2.setBounds(308, 27, 30, 30);
		lblBomb2.setIcon(bomb);
		frmMinesweeper.getContentPane().add(lblBomb2);

		JLabel lblBomb1 = new JLabel("");
		lblBomb1.setBounds(90, 27, 30, 30);
		lblBomb1.setIcon(bomb);
		frmMinesweeper.getContentPane().add(lblBomb1);
	}
	JTextPane getTxtpnName() {
		return txtpnName;
	}

	void setTxtpnName(JTextPane txtpnName) {
		this.txtpnName = txtpnName;
	}
}
