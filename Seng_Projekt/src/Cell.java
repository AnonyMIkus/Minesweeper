import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	boolean bomb = false;
	boolean flagSet = false;
	private int value = 0;
	private int[] index;
	Game game;
	ImageIcon ico;
	boolean uncovered = false;
	ArrayList<Cell> neighbourCells;
	public int dimensionX;
	public int dimensionY;

	public Cell(Game game, int[] index, ImageIcon flagItem) {	//Konstruktor der Zelle, Spiel-Obekt, Index der Zelle und Icon der Flagge wird mitgegeben

		button = new JButton();
		this.ico = flagItem;

		label = new JLabel();		//Hintergrundlabel der Zelle
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(20, 20));
		label.setBorder(new LineBorder(Color.BLACK, 1));
		// Ausrichtung der Beschriftung der Hintergrundlabels der Zelle
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(20, 20));
		button.setMargin(new Insets(0, 0, 0, 0));
		this.index = index;
		this.game = game;
		this.dimensionX = game.dimensionX;
		this.dimensionY = game.dimensionY;

		button.addMouseListener(new MouseAdapter() {		//Flagge setzen bei Rechtsklick
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {

					if (!flagSet) {
						button.setIcon(ico);
						flagSet = true;
					} else {
						button.setIcon(null);
						flagSet = false;
						//Wenn noch keine Flagge gesetzt, Flagge setzen, ansonsten Flagge wegnehmen
					}
				}
			}

		});

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if(!flagSet){
		uncover();
		
		//Bei Linksklick aufdecken, falls keine Flagge gesetzt ist

		if (bomb) {
			GameOver gameOver = new GameOver(new Minesweeper());
			gameOver.setVisible(true);
			game.frmMinesweeper.dispose();
			
			//Wenn auf dem Feld eine Miene ist, Game Over
		}
		if(this.getValue() == 0 && !this.bomb)
		uncoverNeighbours();
		
		//Wenn das Feld leer ist, Nachbarn aufdecken
		}

	}

	public void uncover() {
		if (!flagSet) {
			getButton().setVisible(false);
			uncovered = true;
		}
	}

	public void uncoverNeighbours() {

		getNeighbours();	//Befüllt die neighbourCells Liste, in der die Nachbarzellen gespeichert sind

		for (int i = 0; i < neighbourCells.size(); i++) {
			if (!neighbourCells.get(i).uncovered && (!neighbourCells.get(i).bomb || neighbourCells.get(i).flagSet)){
				neighbourCells.get(i).uncover();
			if (neighbourCells.get(i).getValue() == 0)
				neighbourCells.get(i).uncoverNeighbours();
			}
			//deckt die Nachbarzellen auf, wenn diese keine Bombe oder Flagge enthalten. Wenn diese leer sind, werden deren Nachbarzellen auch aufgedeckt
		}

	}

	public void setBomb() {
		bomb = true;
		label.setText("X");
		label.setForeground(Color.RED);
		
		//setzen einer Miene bei Spielstart
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
			//Der Wert des Labels wird bei Spielstart gesetzt
		}
	}

	public int getValue() {
		return value;
	}

	public int[] getIndex() {
		return this.index;
		//Gibt den Index der Zelle zurück
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
		
		//befüllt die neighbourCells Liste mit den Nachbarn der Zelle. Dabei werden Zellen, die am Rand des Spielfelds liegen berücksichtigt
	}

}
