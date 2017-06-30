import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

public class Cell implements ActionListener {
	private JButton button;
	private JLabel label;
	private boolean bomb = false;
	private boolean treasure=false;
	private boolean flagSet = false;
	private boolean markedUnsure = false;
	private int value = 0;
	private int[] index;
	private Game game;
	private ImageIcon flag;
	private ImageIcon mine;
	private boolean uncovered = false;
	private ArrayList<Cell> neighbourCells;
	private int dimensionX;
	private int dimensionY;
	
	boolean isTreasure(){
		return treasure;
	}
	
	void setTreasure(boolean bomb){
		this.treasure=bomb;
	}

	boolean isBomb() {
		return bomb;
	}

	void setBomb(boolean bomb) {
		this.bomb = bomb;
	}

	boolean isFlagSet() {
		return flagSet;
	}

	void setFlagSet(boolean flagSet) {
		this.flagSet = flagSet;
	}

	boolean isMarkedUnsure() {
		return markedUnsure;
	}

	void setMarkedUnsure(boolean markedUnsure) {
		this.markedUnsure = markedUnsure;
	}

	boolean isUncovered() {
		return uncovered;
	}

	void setUncovered(boolean uncovered) {
		this.uncovered = uncovered;
	}

	ArrayList<Cell> getNeighbourCells() {
		return neighbourCells;
	}

	void setNeighbourCells(ArrayList<Cell> neighbourCells) {
		this.neighbourCells = neighbourCells;
	}

	public Cell(Game game, int[] index) { // Konstruktor derZelle, Spiel-Obekt, Index der Zelle und Icon der Flagge wird mitgegeben.
		button = new JButton();
		this.flag = game.getFlag();
		this.mine = game.getMine();
		label = new JLabel(); // Hintergrundlabel der Zelle
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIconTextGap(-10);
		label.setPreferredSize(new Dimension(20, 20));
		label.setBorder(new LineBorder(Color.BLACK, 1));
		// Ausrichtung der Beschriftung der Hintergrundlabels der Zelle
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(20, 20));
		button.setMargin(new Insets(0, 0, 0, 0));
		this.index = index;
		this.game = game;
		this.dimensionX = game.getGl().getDimensionX();
		this.dimensionY = game.getGl().getDimensionY();

		button.addMouseListener(new MouseAdapter() { // Flagge setzen bei
														// Rechtsklick
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) && !game.isGameLost()) {

					if (!flagSet && !markedUnsure) {
						button.setIcon(flag);
						flagSet = true;
						game.setFlags(game.getFlags() - 1);
						game.getLblFlags().setText(game.getFlags() + "");
					} else if (!markedUnsure) {
						button.setIcon(null);
						flagSet = false;
						game.setFlags(game.getFlags() + 1);
						game.getLblFlags().setText(game.getFlags() + "");
						markUnsure(); // Setzt ein Fragezeichen bei einem
										// zweiten Rechtsklick
					} else {
						button.setIcon(null);
						flagSet = false;
						deMarkUnsure(); // L�scht das Fragezeichen
					}
					// Wenn noch keine Flagge gesetzt, Flagge setzen, ansonsten
					// Flagge wegnehmen
				}
			}

			private void deMarkUnsure() {
				button.setText(null);
				markedUnsure = false;
			}

			private void markUnsure() {
				markedUnsure = true;
				button.setFont(new Font("Arial Black", Font.PLAIN, 25));
				button.setText("?");
			}
		});
	}

	
	
	public void actionPerformed(ActionEvent arg0) {
		if (!flagSet && !game.isGameLost()) {
			uncover();
			// Bei Linksklick aufdecken, falls keine Flagge gesetzt ist
			if(game.getGl().getMode()!=6 && game.getGl().getMode()!=7 && game.getGl().getMode()!=8){
			if (bomb) {
				uncoverAllBombs();
				GameOver gameOver = new GameOver(game.getMenu(), game);
				gameOver.setVisible(true);
				game.getBtnMenu().setVisible(false);
				game.setGameLost(true);
				// Wenn auf dem Feld eine Miene ist, Game Over
			}
			if (treasure){
				win();
			}
			if (this.getValue() == 0 && !this.bomb)
				uncoverNeighbours();
			// Wenn das Feld leer ist, Nachbarn aufdecken
			game.checkWin();
		}
			

			if(game.getGl().getMode()==6 || game.getGl().getMode()==7 || game.getGl().getMode()==8){
				if (!bomb) {
					uncoverAllBombs();
					GameOver gameOver = new GameOver(game.getMenu(), game);
					gameOver.setVisible(true);
					game.getBtnMenu().setVisible(false);
					game.setGameLost(true);
					// Wenn auf dem Feld eine Miene ist, Game Over
				}
				
				if (bomb){
				game.setWincounter(game.getWincounter()+1);
					if(game.getWincounter()==10 && game.getGl().getMode()==6 || game.getWincounter()==40 && game.getGl().getMode()==7
							|| game.getWincounter()==99 && game.getGl().getMode()==8){
						win();
						Timer.currentThread().stop();
											
					}
				uncoverBombNeighbours();
			}
			
		}
			}
	}

	private void uncoverBombNeighbours() {
		getNeighbours();

		for (int i = 0; i < neighbourCells.size(); i++) {
			if (!neighbourCells.get(i).uncovered && (!neighbourCells.get(i).bomb || neighbourCells.get(i).flagSet)) {
				if (neighbourCells.get(i).getValue() == 1 || neighbourCells.get(i).getValue()==0){
					neighbourCells.get(i).uncover();
					neighbourCells.get(i).uncoverNeighbours();
				}
				else{
					checkSurroundings(neighbourCells.get(i));
				}
			}
			// deckt die Nachbarzellen auf, wenn diese keine Bombe oder Flagge
			// enthalten. Wenn diese leer sind, werden deren Nachbarzellen auch
			// aufgedeckt ist das ziel......
		}

	}

	private void checkSurroundings(Cell cell) {
	cell.getNeighbours();
	int counter=0;
	for (int i=0;i<cell.neighbourCells.size();i++){
		if (cell.neighbourCells.get(i).isBomb() && cell.neighbourCells.get(i).uncovered){
			counter++;
		}
	}
	if (cell.getValue()==counter)
		cell.uncover();
	}

	public void uncover() {
		if (!flagSet) {
			getButton().setVisible(false);
			uncovered = true;
		}
	}

	public void uncoverNeighbours() {
		getNeighbours(); // Bef�llt die neighbourCells Liste, in der die Nachbarzellen gespeichert sind.
		for (int i = 0; i < neighbourCells.size(); i++) {
			if (!neighbourCells.get(i).uncovered && (!neighbourCells.get(i).bomb || neighbourCells.get(i).flagSet)) {
				neighbourCells.get(i).uncover();
				if (neighbourCells.get(i).getValue() == 0)
					neighbourCells.get(i).uncoverNeighbours();
			}
			// deckt die Nachbarzellen auf, wenn diese keine Bombe oder Flagge
			// enthalten. Wenn diese leer sind, werden deren Nachbarzellen auch
			// aufgedeckt
		}

	}

	public void setBomb() {
		bomb = true;
		label.setIcon(mine);

	
		// setzen einer Miene bei Spielstart
	}

	public void removeFlag() {
		flagSet = false;
		button.setIcon(null);
	}

	public void uncoverAllBombs() {
		game.uncoverAllBombs();
	}

	public JButton getButton() {
		return button;
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public void setValue(int val) {
		if (!bomb) {
			value = val;
			label.setText(Integer.toString(value));
			// Der Wert des Labels wird bei Spielstart gesetzt
		}
	}

	public int getValue() {
		return value;
	}

	public int[] getIndex() {
		return this.index;
		// Gibt den Index der Zelle zur�ck
	}

	private void getNeighbours() {

		int x = index[0];
		int y = index[1];

		neighbourCells = new ArrayList<Cell>();

		if (y != dimensionY - 1)
			neighbourCells.add(game.getCellbyIndex(new int[] { x, y + 1 }));
		if (y != 0)
			neighbourCells.add(game.getCellbyIndex(new int[] { x, y - 1 }));
		if (x != dimensionX - 1)
			neighbourCells.add(game.getCellbyIndex(new int[] { x + 1, y }));
		if (x != 0)
			neighbourCells.add(game.getCellbyIndex(new int[] { x - 1, y }));
		if (x != dimensionX - 1 && y != dimensionY - 1)
			neighbourCells.add(game.getCellbyIndex(new int[] { x + 1, y + 1 }));
		if (x != dimensionX - 1 && y != 0)
			neighbourCells.add(game.getCellbyIndex(new int[] { x + 1, y - 1 }));
		if (x != 0 && y != dimensionY - 1)
			neighbourCells.add(game.getCellbyIndex(new int[] { x - 1, y + 1 }));
		if (x != 0 && y != 0)
			neighbourCells.add(game.getCellbyIndex(new int[] { x - 1, y - 1 }));
		// bef�llt die neighbourCells Liste mit den Nachbarn der Zelle. Dabei
		// werden Zellen, die am Rand des Spielfelds liegen ber�cksichtigt
	}

	public void win() {
		game.getFrmMinesweeper().dispose();
		Gewonnen g = new Gewonnen();
		g.setVisible(true);
	}

}
