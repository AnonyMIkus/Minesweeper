import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Game {
	private Cell[][] cells;
	private int mode;
	private int dimensionX;
	private int dimensionY;
	private JFrame frmMinesweeper;
	private Minesweeper menu;
	private JPanel panel2;
	private JLabel lblFlags;
	private ImageIcon ico = new ImageIcon("img/flag.png"); // Bild der Flagge
	private int bombs;
	private int flags;

	private int panelAnfPosX = 49;
	private int panelAnfPosY = 72;
	private int panelFortPosX = 49;
	private int panelFortPosY = 72;
	private int panelProPosX = 49;
	private int panelProPosY = 72;
	private int panelAnfWidth = 388;
	private int panelFortWidth = 488;
	private int panelProWidth = 850;

	// Positionen und Skalierungen der Spielfelder
	Cell[][] getCells() {
		return cells;
	}

	void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	int getMode() {
		return mode;
	}

	void setMode(int mode) {
		this.mode = mode;
	}

	int getDimensionX() {
		return dimensionX;
	}

	void setDimensionX(int dimensionX) {
		this.dimensionX = dimensionX;
	}

	int getDimensionY() {
		return dimensionY;
	}

	void setDimensionY(int dimensionY) {
		this.dimensionY = dimensionY;
	}

	JFrame getFrmMinesweeper() {
		return frmMinesweeper;
	}

	void setFrmMinesweeper(JFrame frmMinesweeper) {
		this.frmMinesweeper = frmMinesweeper;
	}

	Minesweeper getMenu() {
		return menu;
	}

	void setMenu(Minesweeper menu) {
		this.menu = menu;
	}

	JPanel getPanel2() {
		return panel2;
	}

	void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	JLabel getLblFlags() {
		return lblFlags;
	}

	void setLblFlags(JLabel lblFlags) {
		this.lblFlags = lblFlags;
	}

	ImageIcon getIco() {
		return ico;
	}

	void setIco(ImageIcon ico) {
		this.ico = ico;
	}

	int getBombs() {
		return bombs;
	}

	void setBombs(int bombs) {
		this.bombs = bombs;
	}

	int getFlags() {
		return flags;
	}

	void setFlags(int flags) {
		this.flags = flags;
	}

	int getPanelAnfPosX() {
		return panelAnfPosX;
	}

	void setPanelAnfPosX(int panelAnfPosX) {
		this.panelAnfPosX = panelAnfPosX;
	}

	int getPanelAnfPosY() {
		return panelAnfPosY;
	}

	void setPanelAnfPosY(int panelAnfPosY) {
		this.panelAnfPosY = panelAnfPosY;
	}

	int getPanelFortPosX() {
		return panelFortPosX;
	}

	void setPanelFortPosX(int panelFortPosX) {
		this.panelFortPosX = panelFortPosX;
	}

	int getPanelFortPosY() {
		return panelFortPosY;
	}

	void setPanelFortPosY(int panelFortPosY) {
		this.panelFortPosY = panelFortPosY;
	}

	int getPanelProPosX() {
		return panelProPosX;
	}

	void setPanelProPosX(int panelProPosX) {
		this.panelProPosX = panelProPosX;
	}

	int getPanelProPosY() {
		return panelProPosY;
	}

	void setPanelProPosY(int panelProPosY) {
		this.panelProPosY = panelProPosY;
	}

	int getPanelAnfWidth() {
		return panelAnfWidth;
	}

	void setPanelAnfWidth(int panelAnfWidth) {
		this.panelAnfWidth = panelAnfWidth;
	}

	int getPanelFortWidth() {
		return panelFortWidth;
	}

	void setPanelFortWidth(int panelFortWidth) {
		this.panelFortWidth = panelFortWidth;
	}

	int getPanelProWidth() {
		return panelProWidth;
	}

	void setPanelProWidth(int panelProWidth) {
		this.panelProWidth = panelProWidth;
	}

	// Create the frame.
	public Game(int mode, Minesweeper menu) {
		this.mode = mode;
		this.menu = menu;
		dimensionY = mode == 0 ? 8 : mode == 1 ? 16 : 30; // Dimensionen der
															// Spielfeldgröße je
															// nach
															// Schwierigkeitsgrad
		dimensionX = mode == 0 ? 8 : mode == 1 ? 16 : 16;
		bombs = mode == 0 ? 10 : mode == 1 ? 40 : 99;
		flags = bombs;
		frmMinesweeper = new JFrame();
		frmMinesweeper.setTitle("Minesweeper");
		frmMinesweeper.setResizable(false);
		frmMinesweeper.getContentPane().setLayout(null);
		frmMinesweeper.getContentPane().add(addCells());
		frmMinesweeper.getContentPane().add(panel2);
		frmMinesweeper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMinesweeper.setVisible(true);

		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem close = new JMenuItem("Close");
		JMenu edit = new JMenu("Edit");

		file.add(close);

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		bar.add(file);
		bar.add(edit);
		frmMinesweeper.setJMenuBar(bar);

		JPanel panel = new JPanel(); // Umrandung für die Zeitangabe
		panel.setBorder(new TitledBorder(null, "Zeit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 50, 50);
		frmMinesweeper.getContentPane().add(panel);

		JLabel timer = new JLabel();
		panel.add(timer);
		timer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		timer.setText("00:00"); // Zeitangabe. Wäre durch die Variable "Zeit" zu
								// ersetzen

		JButton btnMenu = new JButton("zur\u00FCck zum Hauptmen\u00FC"); // Führt
																			// zurück
																			// zum
																			// Hauptmenü
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMinesweeper.dispose();
				menu.getFrame().setVisible(true);
			}
		});
		btnMenu.setBounds(126, 21, 143, 31);
		frmMinesweeper.getContentPane().add(btnMenu);

		JLabel lblNewLabel = new JLabel("Fahnen ");
		lblNewLabel.setBounds(70, 11, 46, 14);
		frmMinesweeper.getContentPane().add(lblNewLabel);

		lblFlags = new JLabel(flags + "");
		lblFlags.setHorizontalAlignment(SwingConstants.CENTER);
		lblFlags.setBounds(70, 29, 46, 14);
		frmMinesweeper.getContentPane().add(lblFlags);

	}

	public JPanel addCells() { // Methode fügt die Zellen hinzu

		JPanel panel = new JPanel(new GridLayout(dimensionX, dimensionY)); // panel
																			// =
																			// Hier
																			// werden
																			// die
																			// Zellen
																			// reingesetzt
		panel2 = new JPanel(new GridLayout(dimensionX, dimensionY)); // panel2 =
																		// hinter
																		// den
																		// Zellen
																		// die
																		// Zahlenangaben,
																		// Bombenbildchen
																		// etc.

		int posX = 0;
		int posY = 0;
		int width = 0;
		int height = 0;

		switch (mode) { // Je nach gewähltem Schwierigkeitsgrad (mode) werden
						// die Position und Skalierung des Spielfeldes gesetzt
		case 0:
			posX = panelAnfPosX;
			posY = panelAnfPosY;
			width = panelAnfWidth;
			height = width;
			break;
		case 1:
			posX = panelFortPosX;
			posY = panelFortPosY;
			width = panelFortWidth;
			height = width;
			break;
		case 2:
			posX = panelProPosX;
			posY = panelProPosY;
			width = panelProWidth;
			height = (int) (width / (30f / 16f));
			break;
		default:
			break;
		}

		panel.setBounds(posX, posY, width, height);
		panel2.setBounds(panel.getBounds());

		frmMinesweeper.setBounds(500, 200, width + posX + 50, height + posY + 100); // die
																					// Größe
																					// des
																					// Fensters
																					// ist
																					// abhängig
																					// von
																					// der
																					// Größe
																					// des
																					// Spielfeldes

		ico.setImage(
				ico.getImage().getScaledInstance(width / dimensionY - 5, height / dimensionX - 5, Image.SCALE_DEFAULT)); // Größe
																															// der
																															// Flagge,
																															// die
																															// in
																															// einem
																															// Button
																															// angezeigt
																															// wird.
																															// Abhängig
																															// von
																															// der
																															// Größe
																															// des
																															// SPielfeldes

		cells = new Cell[dimensionX][dimensionY]; // Array "cells" wird erstellt
													// mit den Dimensionen der
													// Spielfeldgröße

		for (int i = 0; i < dimensionX; i++) {
			for (int j = 0; j < dimensionY; j++) {

				cells[i][j] = new Cell(this, new int[] { i, j }, ico); // Cell-Objekte
																		// werden
																		// erstellt,
																		// Game-Objekt,
																		// Index
																		// der
																		// Zelle
																		// und
																		// Icon
																		// für
																		// die
																		// Flagge
																		// werden
																		// mitgegeben

				panel2.add(cells[i][j].getLabel()); // Hintergrundpanel wird
													// befüllt mit den Labels
													// (Hintergrundbildern) der
													// Zellen
				panel.add(cells[i][j].getButton()); // Vordergrundpanel wird
													// befüllt mit den Buttons
													// der Zellen

			}
		}

		spreadBombs(); // Mienen werden verteilt je nach gewähltem
						// Schwierigkeitsgrad

		panel.setOpaque(false); // das Panel mit den Zellen wird auf
								// durchsichtig gesetzt, damit man die Labels
								// dahinter bei Buttondruck sehen kann
		return panel;
	}

	private void spreadBombs() {

		Random rnd = new Random();

		for (int j = 0; j < bombs; j++) {

			int x;
			int y;

			do {
				x = rnd.nextInt(dimensionX);
				y = rnd.nextInt(dimensionY);
			} while (cells[x][y].isBomb()); // Mienen werden zufällig auf die
											// Felder
			// verteilt

			cells[x][y].setBomb();
			if (x != 0)
				cells[x - 1][y].setValue(cells[x - 1][y].getValue() + 1);
			if (x != dimensionX - 1)
				cells[x + 1][y].setValue(cells[x + 1][y].getValue() + 1);
			if (x != 0 && y != dimensionY - 1)
				cells[x - 1][y + 1].setValue(cells[x - 1][y + 1].getValue() + 1);
			if (x != dimensionX - 1 && y != dimensionY - 1)
				cells[x + 1][y + 1].setValue(cells[x + 1][y + 1].getValue() + 1);
			if (y != dimensionY - 1)
				cells[x][y + 1].setValue(cells[x][y + 1].getValue() + 1);
			if (y != 0)
				cells[x][y - 1].setValue(cells[x][y - 1].getValue() + 1);
			if (x != dimensionX - 1 && y != 0)
				cells[x + 1][y - 1].setValue(cells[x + 1][y - 1].getValue() + 1);
			if (x != 0 && y != 0)
				cells[x - 1][y - 1].setValue(cells[x - 1][y - 1].getValue() + 1);
			// Die Werte (value) der Nachbarzellen einer Zelle mit Bombe werden
			// um 1 erhöht

		}
	}

	public Cell getCellbyIndex(int[] index) { // Kann von einer Zelle aufgerufen
												// werden um die Zelle anhand
												// eines Index zurück zu
												// bekommen
		return cells[index[0]][index[1]];
	}

	
	public void uncoverAllBombs() {
		for (int i = 0; i < dimensionX; i++) {
			for (int j = 0; j < dimensionY; j++) {
				cells[i][j].removeFlag();
				if (cells[i][j].isBomb())
					cells[i][j].uncover();
			}
		}
	}

	public void checkWin() {
		int counter = 0;
		for (int i = 0; i < dimensionX; i++) {
			for (int j = 0; j < dimensionY; j++) {
				if (cells[i][j].isUncovered() && !cells[i][j].isBomb()) {
					counter++;
					if (counter == (dimensionX * dimensionY) - bombs)
						cells[i][j].win();
				}
			}
		}
	}
}
