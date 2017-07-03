import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

/**
 * Cell implements function for buttons ingame.
 * 
 * @author John Voronkov
 * 
 * @version 1.0
 *
 */

public class Cell implements ActionListener {
	private JButton button;
	private JLabel label;
	private boolean bomb = false;
	private boolean treasure = false;
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

	/**
	 * 
	 * @return
	 */
	boolean isTreasure() {
		return treasure;
	}

	/**
	 * 
	 * @param bomb
	 */
	void setTreasure(boolean bomb) {
		this.treasure = bomb;
	}

	/**
	 * Value of existing bombs.
	 * 
	 * @return boolean of existing bomb
	 */
	boolean isBomb() {
		return bomb;
	}

	/**
	 * 
	 * @param bomb
	 */
	void setBomb(boolean bomb) {
		this.bomb = bomb;
	}

	/**
	 * 
	 * @return
	 */
	boolean isFlagSet() {
		return flagSet;
	}

	/**
	 * 
	 * @param flagSet
	 */
	void setFlagSet(boolean flagSet) {
		this.flagSet = flagSet;
	}

	/**
	 * 
	 * @return
	 */
	boolean isMarkedUnsure() {
		return markedUnsure;
	}

	/**
	 * 
	 * @param markedUnsure
	 */
	void setMarkedUnsure(boolean markedUnsure) {
		this.markedUnsure = markedUnsure;
	}

	/**
	 * Gatting value of a field uncovered or not.
	 * 
	 * @return boolean of uncoverd field.
	 */
	boolean isUncovered() {
		return uncovered;
	}

	/**
	 * 
	 * @param uncovered
	 */
	void setUncovered(boolean uncovered) {
		this.uncovered = uncovered;
	}

	/**
	 * 
	 * @return
	 */
	ArrayList<Cell> getNeighbourCells() {
		return neighbourCells;
	}

	/**
	 * 
	 * @param neighbourCells
	 */
	void setNeighbourCells(ArrayList<Cell> neighbourCells) {
		this.neighbourCells = neighbourCells;
	}

	/**
	 * Setting up function of the game.
	 * 
	 * @param game
	 *            Current game
	 * @param index
	 */
	public Cell(Game game, int[] index) {
		button = new JButton();
		this.flag = game.getFlag();
		this.mine = game.getMine();
		label = new JLabel();
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIconTextGap(-10);
		label.setPreferredSize(new Dimension(20, 20));
		label.setBorder(new LineBorder(Color.BLACK, 1));
		button.addActionListener(this);
		button.setPreferredSize(new Dimension(20, 20));
		button.setMargin(new Insets(0, 0, 0, 0));
		this.index = index;
		this.game = game;
		this.dimensionX = Game.getGl().getDimensionX();
		this.dimensionY = Game.getGl().getDimensionY();

		button.addMouseListener(new MouseAdapter() {
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
						markUnsure();
					} else {
						button.setIcon(null);
						flagSet = false;
						deMarkUnsure();
					}
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

	/**
	 * Overrided method. Function of clicking a field (in general),
	 */
	public void actionPerformed(ActionEvent arg0) {
		open();
	}
	
	public void open(){
		if (!flagSet && !game.isGameLost()) {
			uncover();
			if (Game.getGl().getMode() != 6 && Game.getGl().getMode() != 7 && Game.getGl().getMode() != 8) {
				if (bomb) {
					uncoverAllBombs();
					game.getTime().reset();
					GameOver gameOver = new GameOver(game);
					gameOver.setVisible(true);
					game.getBtnMenu().setVisible(false);
					game.setGameLost(true);
				}
				if (treasure) {
					win();
				}
				if (this.getValue() == 0 && !this.bomb)
					uncoverNeighbours();
				game.checkWin();
			}
			if (Game.getGl().getMode() == 6 || Game.getGl().getMode() == 7 || Game.getGl().getMode() == 8) {
				if (bomb && uncovered) {
					game.setWincounter(game.getWincounter() + 1);
					if (game.getWincounter() == game.getBombs()) {
						win();
					}
					uncoverBombNeighbours();
				}
			}
		}
	}

	/**
	 * 
	 */
	private void uncoverBombNeighbours() {
		ArrayList<Cell> neighbour = getNeighbours();
		for (int i = 0; i < neighbourCells.size(); i++) {
			if (!neighbourCells.get(i).isBomb() && neighbourCells.get(i).flagSet) {
					neighbour.get(i).checkSurroundings();
			}
		}
	}

	/**
	 * Checks surrounding cells of the current cell.
	 * 
	 * @param cell
	 *            current cell
	 */
	private void checkSurroundings() {
		if (!isBomb()) {
			ArrayList<Cell> neighbour = getNeighbours();
			int counter = 0;
			int counterOpen = 0;
			for (int i = 0; i < neighbour.size(); i++) {
				if (neighbour.get(i).isBomb() && neighbour.get(i).uncovered) {
					counter++;
				}
				if (neighbour.get(i).isBomb() || neighbour.get(i).uncovered)
					counterOpen++;
			}
			if (getValue() == counter) {
				uncover();
				uncoverAllNeighbours();
				if (counterOpen != neighbour.size())
					for (Cell c : neighbour)
						c.checkSurroundings();
			}
		}
	}

	/**
	 * 
	 */
	public void uncoverAllNeighbours() {
		ArrayList<Cell> neighbour = getNeighbours();
		for (int i = 0; i < neighbour.size(); i++) {
			if (!neighbour.get(i).isBomb())
				neighbour.get(i).uncover();
		}
	}

	/**
	 * Uncover current field.
	 */
	public void uncover() {
		if (!flagSet) {
			getButton().setVisible(false);
			uncovered = true;
		}
	}

	/**
	 * Uncovering fields around current field.
	 */
	public void uncoverNeighbours() {
		ArrayList<Cell> neighbour = getNeighbours();
		for (int i = 0; i < neighbour.size(); i++) {
			if (!neighbourCells.get(i).uncovered && (!neighbourCells.get(i).bomb || neighbourCells.get(i).flagSet)) {
				neighbourCells.get(i).uncover();
				if (neighbourCells.get(i).getValue() == 0) {
					neighbourCells.get(i).uncoverNeighbours();
				}
			}
		}
	}

	/**
	 * Setting up a bomb on a field.
	 */
	public void setBomb() {
		bomb = true;
		label.setIcon(mine);
	}

	/**
	 * Removing flag of current field.
	 */
	public void removeFlag() {
		flagSet = false;
		button.setIcon(null);
	}

	/**
	 * Uncovering all bommbs on the current field undependend of its size.
	 */
	public void uncoverAllBombs() {
		game.uncoverAllBombs();
	}

	/**
	 * 
	 * @return
	 */
	public JButton getButton() {
		return button;
	}

	/**
	 * 
	 * @return
	 */
	public JLabel getLabel() {
		return label;
	}

	/**
	 * 
	 * @param val
	 */
	public void setValue(int val) {
		if (!bomb) {
			value = val;
			label.setText(Integer.toString(value));
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Analysing environment around a field.
	 */
	ArrayList<Cell> getNeighbours() {
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
		return neighbourCells;
	}

	/**
	 * Switch from game to Highscore if player won.
	 */
	public void win() {
		game.getFrmMinesweeper().dispose();
		HighscoreFrame hf = new HighscoreFrame();
		game.getTime().reset();
	}
}
