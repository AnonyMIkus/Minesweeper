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

/**
 * Game is set up field and triggers nessecary components.
 * 
 * @author Sandra
 * @author Philip
 * @author Chris
 * @author John
 * 
 * @version 2.0
 */

public class Game {
	private static gameLibrary gl = new gameLibrary();
	private Cell[][] cells;
	private JFrame frmMinesweeper;
	private Minesweeper menu;
	private JPanel panel2;
	private JLabel lblFlags;
	private static JLabel timer;
	private ImageIcon flag = new ImageIcon("img/flag.png");
	private ImageIcon mine = new ImageIcon("img/mine2.png");
	private int bombs;
	private int flags;
	private Timer time = new Timer();
	private JButton btnMenu;
	private boolean gameLost = false;
	private int wincounter = 0;

	/**
	 * Getting whole game-field.
	 * 
	 * @return All buttons saved in an array.
	 */
	Cell[][] getCells() {
		return cells;
	}

	/**
	 * Setting game-field.
	 * 
	 * @param cells
	 *            Setting buttons depending on size of field.
	 */
	void setCells(Cell[][] cells) {
		this.cells = cells;
	}

	/**
	 * 
	 * @return
	 */
	public int getWincounter() {
		return wincounter;
	}

	/**
	 * 
	 * @param wincounter
	 */
	public void setWincounter(int wincounter) {
		this.wincounter = wincounter;
	}

	/**
	 * Getting value for checking if game is lost.
	 * 
	 * @return Boolean value if game lost.
	 */
	public boolean isGameLost() {
		return gameLost;
	}

	/**
	 * Setting current game as not lost or lost.
	 * 
	 * @param gameLost
	 *            Boolean for current game.
	 */
	public void setGameLost(boolean gameLost) {
		this.gameLost = gameLost;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public JButton getBtnMenu() {
		return btnMenu;
	}

	/**
	 * 
	 * @param btnMenu
	 */
	public void setBtnMenu(JButton btnMenu) {
		this.btnMenu = btnMenu;
	}

	/**
	 * Static access to gameLibrary.
	 * 
	 * @return
	 */
	public static gameLibrary getGl() {
		return gl;
	}

	/**
	 * Getting frame from the game.
	 * 
	 * @return Game frame.
	 */
	JFrame getFrmMinesweeper() {
		return frmMinesweeper;
	}

	/**
	 * Saving fram of the game.
	 * 
	 * @param frmMinesweeper
	 *            Game frame.
	 */
	void setFrmMinesweeper(JFrame frmMinesweeper) {
		this.frmMinesweeper = frmMinesweeper;
	}

	/**
	 * Getting currently main menu.
	 * 
	 * @return State of main menu.
	 */
	Minesweeper getMenu() {
		return menu;
	}

	/**
	 * Saving currently main menu.
	 * 
	 * @param menu
	 *            State of main menu.
	 */
	void setMenu(Minesweeper menu) {
		this.menu = menu;
	}

	/**
	 * 
	 * Getting panel for representing numbers.
	 * 
	 * @return Panel for numbers.
	 */
	JPanel getPanel2() {
		return panel2;
	}

	/**
	 * Saving panel for represnting numbers.
	 * 
	 * @param panel2
	 */
	void setPanel2(JPanel panel2) {
		this.panel2 = panel2;
	}

	/**
	 * Getting set flag.
	 * 
	 * @return
	 */
	JLabel getLblFlags() {
		return lblFlags;
	}

	/**
	 * Saving set flag.
	 * 
	 * @param lblFlags
	 */
	void setLblFlags(JLabel lblFlags) {
		this.lblFlags = lblFlags;
	}

	/**
	 * Getting number of bombs around a field.
	 * 
	 * @return Number of bombs.
	 */
	int getBombs() {
		return bombs;
	}

	/**
	 * Number of bombs around a specify field.
	 * 
	 * @param bombs
	 *            Number of bombs.
	 */
	void setBombs(int bombs) {
		this.bombs = bombs;
	}

	/**
	 * Getting number alls flags.
	 * 
	 * @return Number of flags.
	 */
	int getFlags() {
		return flags;
	}

	/**
	 * Setting number of all flags.
	 * 
	 * @param flags
	 *            Number of flags.
	 */
	void setFlags(int flags) {
		this.flags = flags;
	}

	/**
	 * Getting image of flag.
	 * 
	 * @return ImageIcon of flag.
	 */
	public ImageIcon getFlag() {
		return flag;
	}

	/**
	 * Setting image of flag.
	 * 
	 * @param flag
	 *            ImageIcon of flag.
	 */
	public void setFlag(ImageIcon flag) {
		this.flag = flag;
	}

	/**
	 * Getting image of mine.
	 * 
	 * @return ImageIcon of mine.
	 */
	public ImageIcon getMine() {
		return mine;
	}

	/**
	 * Setting image of mine.
	 * 
	 * @param mine
	 *            ImageIcon of mine.
	 */
	public void setMine(ImageIcon mine) {
		this.mine = mine;
	}

	/**
	 * Creating window with all components.
	 * 
	 * @param mode
	 *            setting difficulty of the game
	 * @param menu
	 *            for returning to main menu
	 */
	public Game(int mode, Minesweeper menu) {
		gl.setMenu(menu);
		gl.setMode(mode);

		// Setting size depending on mode and difficulty of the game.
		if (gl.getMode() == 0 || gl.getMode() == 1 || gl.getMode() == 2) {
			gl.setDimensionY(gl.getMode() == 0 ? 8 : gl.getMode() == 1 ? 16 : 30);
			gl.setDimensionX(gl.getMode() == 0 ? 8 : gl.getMode() == 1 ? 16 : 16);
			bombs = gl.getMode() == 0 ? 10 : gl.getMode() == 1 ? 40 : 99;
		}
		if (gl.getMode() == 3 || gl.getMode() == 4 || gl.getMode() == 5) {
			gl.setDimensionY(gl.getMode() == 3 ? 8 : gl.getMode() == 4 ? 16 : 30);
			gl.setDimensionX(gl.getMode() == 3 ? 8 : gl.getMode() == 4 ? 16 : 16);
			bombs = gl.getMode() == 3 ? 10 : gl.getMode() == 4 ? 40 : 99;
		}
		if (gl.getMode() == 6 || gl.getMode() == 7 || gl.getMode() == 8) {
			gl.setDimensionY(gl.getMode() == 6 ? 8 : gl.getMode() == 7 ? 16 : 30);
			gl.setDimensionX(gl.getMode() == 6 ? 8 : gl.getMode() == 7 ? 16 : 16);
			bombs = gl.getMode() == 6 ? 10 : gl.getMode() == 7 ? 40 : 99;
		}
		flags = bombs;

		// Creating frame with field.
		frmMinesweeper = new JFrame();
		frmMinesweeper.setTitle("Minesweeper");
		frmMinesweeper.setResizable(false);
		frmMinesweeper.getContentPane().setLayout(null);
		frmMinesweeper.getContentPane().add(addCells());
		frmMinesweeper.getContentPane().add(panel2);
		frmMinesweeper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMinesweeper.setVisible(true);

		// Creating menubar.
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem close = new JMenuItem("Close");
		JMenu edit = new JMenu("Edit");

		file.add(close);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		bar.add(file);
		bar.add(edit);
		frmMinesweeper.setJMenuBar(bar);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Time", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 50, 50);
		frmMinesweeper.getContentPane().add(panel);

		// Adding timer to gamr.
		timer = new JLabel();
		panel.add(timer);
		timer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		time.start();
		timer.setText("00:00");

		btnMenu = new JButton("Back to main menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				time.reset();
				frmMinesweeper.setVisible(false);
				gl.getMenu().getFrame().setVisible(true);
			}
		});
		btnMenu.setBounds(126, 21, 143, 31);
		frmMinesweeper.getContentPane().add(btnMenu);

		JLabel lblNewLabel = new JLabel("Flags ");
		lblNewLabel.setBounds(70, 11, 46, 14);
		frmMinesweeper.getContentPane().add(lblNewLabel);

		lblFlags = new JLabel(flags + "");
		lblFlags.setHorizontalAlignment(SwingConstants.CENTER);
		lblFlags.setBounds(70, 29, 46, 14);
		frmMinesweeper.getContentPane().add(lblFlags);
	}

	/**
	 * Getting time of Timer class.
	 * 
	 * @return
	 */
	public Timer getTime() {
		return time;
	}

	/**
	 * Setting time of Timer class.
	 * 
	 * @param time
	 */
	public void setTime(Timer time) {
		this.time = time;
	}

	/**
	 * Get time to be shown.
	 * 
	 * @return JLabel for time to be shown.
	 */
	public static JLabel getTimer() {
		return timer;
	}

	/**
	 * Setting time element.
	 * 
	 * @param timer JLabel for time element.
	 */
	public void setTimer(JLabel timer) {
		this.timer = timer;
	}

	/**
	 * Creating field of the game.
	 * 
	 * @return panel with prepared field.
	 */
	public JPanel addCells() {
		JPanel panel = new JPanel(new GridLayout(gl.getDimensionX(), gl.getDimensionY()));
		panel2 = new JPanel(new GridLayout(gl.getDimensionX(), gl.getDimensionY()));

		int posX = 0;
		int posY = 0;
		int width = 0;
		int height = 0;

		// Setting position for frame.
		switch (gl.getMode()) {
		case 0:
		case 3:
		case 6:
			posX = gl.getPanelAnfPosX();
			posY = gl.getPanelAnfPosY();
			width = gl.getPanelAnfWidth();
			height = width;
			break;
		case 1:
		case 4:
		case 7:
			posX = gl.getPanelFortPosX();
			posY = gl.getPanelFortPosY();
			width = gl.getPanelFortWidth();
			height = width;
			break;
		case 2:
		case 5:
		case 8:
			posX = gl.getPanelProPosX();
			posY = gl.getPanelProPosY();
			width = gl.getPanelProWidth();
			height = (int) (width / (30f / 16f));
			break;
		default:
			break;
		}

		panel.setBounds(posX, posY, width, height);
		panel2.setBounds(panel.getBounds());

		frmMinesweeper.setBounds(500, 200, width + posX + 50, height + posY + 100);
		flag.setImage(flag.getImage().getScaledInstance(width / gl.getDimensionY() - 5, height / gl.getDimensionX() - 5,
				Image.SCALE_DEFAULT));
		mine.setImage(mine.getImage().getScaledInstance(width / gl.getDimensionY() - 5, height / gl.getDimensionX() - 5,
				Image.SCALE_DEFAULT));

		// Setting up the game field.
		cells = new Cell[gl.getDimensionX()][gl.getDimensionY()];

		for (int i = 0; i < gl.getDimensionX(); i++) {
			for (int j = 0; j < gl.getDimensionY(); j++) {
				cells[i][j] = new Cell(this, new int[] { i, j });
				panel2.add(cells[i][j].getLabel());
				panel.add(cells[i][j].getButton());
			}
		}
		if (gl.getMode() != 3 && gl.getMode() != 4 && gl.getMode() != 5) {
			spreadBombs();
		} else {
			spreadTreasure();
		}
		panel.setOpaque(false);
		return panel;
	}

	/**
	 * Creating field for mode Tresure Hunt.
	 */
	private void spreadTreasure() {
		Random rnd = new Random();
		if (gl.getMode() == 3 || gl.getMode() == 4 || gl.getMode() == 5) {
			int k = 0;
			int x = rnd.nextInt(gl.getDimensionX());
			int y = rnd.nextInt(gl.getDimensionY());
			cells[x][y].setTreasure(true);
			if (x != 0)
				if (cells[x - 1][y] != null) {
					cells[x - 1][y].setBomb();
					adjustValue(x - 1, y);
					k++;
				}
			if (x != gl.getDimensionX() - 1)
				if (cells[x + 1][y] != null) {
					cells[x + 1][y].setBomb();
					adjustValue(x + 1, y);
					k++;
				}
			if (x != 0 && y != gl.getDimensionY() - 1)
				if (cells[x - 1][y] != null) {
					cells[x - 1][y + 1].setBomb();
					adjustValue(x - 1, y + 1);
					k++;
				}
			if (x != gl.getDimensionX() - 1 && y != gl.getDimensionY() - 1)
				if (cells[x + 1][y + 1] != null) {
					cells[x + 1][y + 1].setBomb();
					adjustValue(x + 1, y + 1);
					k++;
				}
			if (y != gl.getDimensionY() - 1)
				if (cells[x][y + 1] != null) {
					cells[x][y + 1].setBomb();
					adjustValue(x, y + 1);
					k++;
				}
			if (y != 0)
				if (cells[x][y - 1] != null) {
					cells[x][y - 1].setBomb();
					adjustValue(x, y - 1);
					k++;
				}
			if (x != gl.getDimensionX() - 1 && y != 0)
				if (cells[x + 1][y - 1] != null) {
					cells[x + 1][y - 1].setBomb();
					adjustValue(x + 1, y - 1);
					k++;
				}
			if (x != 0 && y != 0)
				if (cells[x - 1][y - 1] != null) {
					cells[x - 1][y - 1].setBomb();
					adjustValue(x - 1, y - 1);
					k++;
				}

			for (int j = 0; j < bombs - k; j++) {
				do {
					x = rnd.nextInt(gl.getDimensionX());
					y = rnd.nextInt(gl.getDimensionY());
					while (cells[x][y].isTreasure()) {
						x = rnd.nextInt(gl.getDimensionX());
						y = rnd.nextInt(gl.getDimensionY());
					}
				} while (cells[x][y].isBomb());

				cells[x][y].setBomb();
				if (x != 0)
					cells[x - 1][y].setValue(cells[x - 1][y].getValue() + 1);
				if (x != gl.getDimensionX() - 1)
					cells[x + 1][y].setValue(cells[x + 1][y].getValue() + 1);
				if (x != 0 && y != gl.getDimensionY() - 1)
					cells[x - 1][y + 1].setValue(cells[x - 1][y + 1].getValue() + 1);
				if (x != gl.getDimensionX() - 1 && y != gl.getDimensionY() - 1)
					cells[x + 1][y + 1].setValue(cells[x + 1][y + 1].getValue() + 1);
				if (y != gl.getDimensionY() - 1)
					cells[x][y + 1].setValue(cells[x][y + 1].getValue() + 1);
				if (y != 0)
					cells[x][y - 1].setValue(cells[x][y - 1].getValue() + 1);
				if (x != gl.getDimensionX() - 1 && y != 0)
					cells[x + 1][y - 1].setValue(cells[x + 1][y - 1].getValue() + 1);
				if (x != 0 && y != 0)
					cells[x - 1][y - 1].setValue(cells[x - 1][y - 1].getValue() + 1);
			}
		}
	}

	/**
	 * Setting number of bombs on certain fields.
	 * 
	 * @param x
	 *            coordinate on field.
	 * @param y
	 *            coordinate on field.
	 */
	private void adjustValue(int x, int y) {
		if (cells[x][y].isTreasure() == false) {
			cells[x][y].setBomb();
			if (x != 0)
				cells[x - 1][y].setValue(cells[x - 1][y].getValue() + 1);
			if (x != gl.getDimensionX() - 1)
				cells[x + 1][y].setValue(cells[x + 1][y].getValue() + 1);
			if (x != 0 && y != gl.getDimensionY() - 1)
				cells[x - 1][y + 1].setValue(cells[x - 1][y + 1].getValue() + 1);
			if (x != gl.getDimensionX() - 1 && y != gl.getDimensionY() - 1)
				cells[x + 1][y + 1].setValue(cells[x + 1][y + 1].getValue() + 1);
			if (y != gl.getDimensionY() - 1)
				cells[x][y + 1].setValue(cells[x][y + 1].getValue() + 1);
			if (y != 0)
				cells[x][y - 1].setValue(cells[x][y - 1].getValue() + 1);
			if (x != gl.getDimensionX() - 1 && y != 0)
				cells[x + 1][y - 1].setValue(cells[x + 1][y - 1].getValue() + 1);
			if (x != 0 && y != 0)
				cells[x - 1][y - 1].setValue(cells[x - 1][y - 1].getValue() + 1);
		}
	}

	/**
	 * 
	 * @param gl
	 */
	public void setGl(gameLibrary gl) {
		this.gl = gl;
	}

	/**
	 * Playing bombs randomly on the field.
	 */
	private void spreadBombs() {
		Random rnd = new Random();

		for (int j = 0; j < bombs; j++) {
			int x;
			int y;
			do {
				x = rnd.nextInt(gl.getDimensionX());
				y = rnd.nextInt(gl.getDimensionY());
			} while (cells[x][y].isBomb());

			cells[x][y].setBomb();
			if (x != 0)
				cells[x - 1][y].setValue(cells[x - 1][y].getValue() + 1);
			if (x != gl.getDimensionX() - 1)
				cells[x + 1][y].setValue(cells[x + 1][y].getValue() + 1);
			if (x != 0 && y != gl.getDimensionY() - 1)
				cells[x - 1][y + 1].setValue(cells[x - 1][y + 1].getValue() + 1);
			if (x != gl.getDimensionX() - 1 && y != gl.getDimensionY() - 1)
				cells[x + 1][y + 1].setValue(cells[x + 1][y + 1].getValue() + 1);
			if (y != gl.getDimensionY() - 1)
				cells[x][y + 1].setValue(cells[x][y + 1].getValue() + 1);
			if (y != 0)
				cells[x][y - 1].setValue(cells[x][y - 1].getValue() + 1);
			if (x != gl.getDimensionX() - 1 && y != 0)
				cells[x + 1][y - 1].setValue(cells[x + 1][y - 1].getValue() + 1);
			if (x != 0 && y != 0)
				cells[x - 1][y - 1].setValue(cells[x - 1][y - 1].getValue() + 1);
		}
	}

	/**
	 * Can be called from a Cell to get another Cell from its index.
	 * 
	 * @param index
	 *            of cell field.
	 * @return value of current cell.
	 */
	public Cell getCellbyIndex(int[] index) {
		return cells[index[0]][index[1]];
	}

	/**
	 * Uncover all bombs on field.
	 */
	public void uncoverAllBombs() {
		for (int i = 0; i < gl.getDimensionX(); i++) {
			for (int j = 0; j < gl.getDimensionY(); j++) {
				cells[i][j].removeFlag();
				if (cells[i][j].isBomb())
					cells[i][j].uncover();
			}
		}
	}

	/**
	 * Checks win condition.
	 */
	public void checkWin() {
		int counter = 0;
		for (int i = 0; i < gl.getDimensionX(); i++) {
			for (int j = 0; j < gl.getDimensionY(); j++) {
				if (cells[i][j].isUncovered() && !cells[i][j].isBomb()) {
					counter++;
					if (counter == (gl.getDimensionX() * gl.getDimensionY()) - bombs)
						cells[i][j].win();
				}
			}
		}
	}

}
