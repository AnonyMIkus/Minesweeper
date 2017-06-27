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

public class Minesweeper {

	private JFrame frmMinesweeper;
	private boolean selectedAnf = true;
	private boolean selectedErf = false;
	private boolean selectedProf = false;
	private ImageIcon bomb = new ImageIcon("img/mine.png");
	
	JFrame getFrmMinesweeper() {
		return frmMinesweeper;
	}

	void setFrmMinesweeper(JFrame frmMinesweeper) {
		this.frmMinesweeper = frmMinesweeper;
	}

	boolean isSelectedAnf() {
		return selectedAnf;
	}

	void setSelectedAnf(boolean selectedAnf) {
		this.selectedAnf = selectedAnf;
	}

	boolean isSelectedErf() {
		return selectedErf;
	}

	void setSelectedErf(boolean selectedErf) {
		this.selectedErf = selectedErf;
	}

	boolean isSelectedProf() {
		return selectedProf;
	}

	void setSelectedProf(boolean selectedProf) {
		this.selectedProf = selectedProf;
	}

	Minesweeper getMenu() {
		return menu;
	}

	void setMenu(Minesweeper menu) {
		this.menu = menu;
	}

	private Minesweeper menu = this;

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
		frmMinesweeper.setBounds(500, 200, 451, 392); //Position und Größe des Fensters
		frmMinesweeper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMinesweeper.getContentPane().setLayout(null); //Reset Layout

		//Layout für alle Text		
		JTextArea txtrSoftwareengineering = new JTextArea();
		txtrSoftwareengineering.setEditable(false); // Wird nicht mehr verändert?? Oder als Sicherheitsmaßnahme??
		txtrSoftwareengineering.setText("Software-Engineering \nProjekt von \nSandra, Philipp, John, Chris");
		txtrSoftwareengineering.setBounds(36, 267, 185, 55); //Position and Size  of TextArea
		frmMinesweeper.getContentPane().add(txtrSoftwareengineering);

		JLabel lblMinesweeper = new JLabel("Minesweeper");
		lblMinesweeper.setFont(new Font("Arial Black", Font.BOLD, 22));
		lblMinesweeper.setBounds(126, 30, 178, 23);
		frmMinesweeper.getContentPane().add(lblMinesweeper);

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
		
		bomb.setImage(bomb.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT)); // Größe der Miene, die in einem Button angezeigt wird. Abhängig von der Größe des Spielfeldes.

		//Buttons zu dem Layout hinzufügen.
		initializeButtons();
	}

	private void initializeButtons() {
		ButtonGroup bGroupGame = new ButtonGroup();
		ButtonGroup bGroupDiff = new ButtonGroup();

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Spiel", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(36, 85, 118, 166);
		frmMinesweeper.getContentPane().add(panel);
		panel.setLayout(null);

		//Buttongruppe für die Auswahl des Spiels
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

		//Buttongruppe für die Auswahl des Schwierigkeitsgrads
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
		bGroupDiff.add(btnPro);				

		//Definiton des Startbuttons
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMinesweeper.dispose();
				Game game = new Game(btnEasy.isSelected() ? 0 : btnMedium.isSelected() ? 1 : 2, menu);	//das Spielfenster wird je nach Auswahl des Schwierigkeitsgrads gestartet
			}
		});
		btnStart.setBounds(309, 299, 89, 23);
		frmMinesweeper.getContentPane().add(btnStart);
		
		JLabel lblBomb2 = new JLabel("");
		lblBomb2.setBounds(308, 27, 30, 30);
		lblBomb2.setIcon(bomb);
		frmMinesweeper.getContentPane().add(lblBomb2);
		
		JLabel lblBomb1 = new JLabel("");
		lblBomb1.setBounds(90, 27, 30, 30);
		lblBomb1.setIcon(bomb);
		frmMinesweeper.getContentPane().add(lblBomb1);
	}

	public JFrame getFrame() {
		return this.frmMinesweeper;
	}
}
