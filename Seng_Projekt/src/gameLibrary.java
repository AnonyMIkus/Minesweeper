/**
 * gameLibrary is saving some information about game. Can be access for using for other components of the game e.g. Highscore.
 * 
 * @author Sandra
 * @author Philip
 * @author Chris
 * @author John
 *
 * @version 1.0
 */
public class gameLibrary {
	private Minesweeper menu;
	private String playerName;
	private int mode;
	private int time;
	private int panelAnfPosX = 49;
	private int panelAnfPosY = 72;
	private int panelFortPosX = 49;
	private int panelFortPosY = 72;
	private int panelProPosX = 49;
	private int panelProPosY = 72;
	private int panelAnfWidth = 388;
	private int panelFortWidth = 488;
	private int panelProWidth = 850;
	private int dimensionX;
	private int dimensionY;

	/**
	 * Get the saved information of the difficulty of the game.
	 * 
	 * @return difficulty of game.
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * Saving difficulty of game.
	 * 
	 * @param mode difficulty of game.
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}

	/**
	 * X start postion of Window for beginners.
	 * 
	 * @return int value of x position
	 */
	public int getPanelAnfPosX() {
		return panelAnfPosX;
	}

	/**
	 * Y start postion of Window for beginners.
	 * 
	 * @return int value of y position
	 */
	public int getPanelAnfPosY() {
		return panelAnfPosY;
	}

	/**
	 * X start postion of Window for amateurs.
	 * 
	 * @return int value of x position
	 */
	public int getPanelFortPosX() {
		return panelFortPosX;
	}

	/**
	 * Y start postion of Window for amateurs.
	 * 
	 * @return int value of y position
	 */
	public int getPanelFortPosY() {
		return panelFortPosY;
	}

	/**
	 * X start postion of Window for advanced.
	 * 
	 * @return int value of x position
	 */
	public int getPanelProPosX() {
		return panelProPosX;
	}

	/**
	 * Y start postion of Window for advanced.
	 * 
	 * @return int value of y position
	 */
	public int getPanelProPosY() {
		return panelProPosY;
	}

	/**
	 *  Defines the window width of beginner level.
	 * 
	 * @return int value of window width
	 */
	public int getPanelAnfWidth() {
		return panelAnfWidth;
	}

	/**
	 * Defines the window width of amateur level.
	 * 
	 * @return int value of window width
	 */
	public int getPanelFortWidth() {
		return panelFortWidth;
	}

	/**
	 * Defines the window width of advanced level.
	 * 
	 * @return int value of window width
	 */
	public int getPanelProWidth() {
		return panelProWidth;
	}

	/**
	 * Value of x Dimension.
	 * 
	 * @return Dimension  x  value.
	 */
	public int getDimensionX() {
		return dimensionX;
	}

	/**
	 * Setting up  value for x Dimension.
	 * 
	 * @param dimensionX
	 */
	public void setDimensionX(int dimensionX) {
		this.dimensionX = dimensionX;
	}

	/**
	 * Value of y Dimension.
	 * 
	 * @return Dimension  y  value.
	 */
	public int getDimensionY() {
		return dimensionY;
	}

	/**
	 * Setting up  value for y Dimension.
	 * 
	 * @param dimensionY
	 */
	public void setDimensionY(int dimensionY) {
		this.dimensionY = dimensionY;
	}

	/**
	 * Saved time of last game.
	 * 
	 * @return time in seconds
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Saving the played time(in seconds) for the last game.
	 * 
	 * @param time played time in last game.
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * Saved player name of last game.
	 * 
	 * @return String of player name last game.
	 */
	public String getPlayerName() {
		return playerName;
	}

	/**
	 * Saving player name of last game.
	 * 
	 * @param playerName String of player name last game.
	 */
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * Getting running main menu.
	 * 
	 * @return Running main menu of Minsweeper.
	 */
	public Minesweeper getMenu() {
		return menu;
	}

	/**
	 * Saving currently running main menu.
	 * 
	 * @param menu Currently running main menu of Minesweeper.
	 */
	public void setMenu(Minesweeper menu) {
		this.menu = menu;
	}
}
