import static org.junit.Assert.*;

import java.awt.AWTEvent;
import java.util.ArrayList;
/**
 * Test for the Class Game and Cell
 * 
 * @version 02.07
 * @author Sandra Keim
 */
import org.junit.Test;

/**
 * 
 * @author Sandra
 * @author Philip
 * @author Chris
 * @author John
 * 
 * @version 02.07
 */
public class GameTest {
	/**
	 * assert that the number of bombs in the field is right 
	 * @bombs count the number of bombs in the field
	 */
	@Test
	public void test() {
		int bombs = 0;
		Game game = new Game(1, new Minesweeper());
		for (Cell[] c : game.getCells()) {
			for (Cell t : c) {
				if (t.isBomb())
					bombs++;
			}
		}

		assertEquals(bombs, game.getBombs());
	}

	/** 
	 * assert that the value of the cell is right
	 * @valueIsRight stay true if no value of the cells is wrong
	 * @value saves the value of the current cell
	 */
	
	@Test
	public void test2() {
		boolean valueIsRight = true;
		Game game = new Game(0, new Minesweeper());
		for (Cell[] c : game.getCells()) {
			for (Cell t : c) {
				if (!t.isBomb()) {
					int value = 0;
					ArrayList<Cell> neighbours = t.getNeighbours();
					for (int i = 0; i < neighbours.size(); i++) {
						if (neighbours.get(i).isBomb()) {
							value++;
						}
					}
					if (t.getValue() != value) {
						valueIsRight = false;

					}
				}

			}
			assertTrue(valueIsRight);
		}

	}

	/**
	 * assert that all cells are uncovered at the beginning of the game
	 */
	
	@Test
	public void test3() {
		Game game = new Game(1, new Minesweeper());
		for (Cell[] c : game.getCells()) {
			for (Cell t : c) {
				assertFalse(t.isUncovered());
			}
		}

	}

	/**
	 * assert that the game in mode 1 is lost if the opened cell contains a bomb
	 * @testCell is the cell in raw 2 column 2
	 */
	@Test
	public void test4(){
		Game game= new Game(1, new Minesweeper());
	
		Cell[][]c= game.getCells();
		Cell testCell= c[1][1];
		testCell.open();
		assert(game.isGameLost()==testCell.isBomb());
		
		
		
		
	}
	
	
	
	/**
	 * assert that the game in mode 7 (BombMode) is won when cells with bombs are opened
	 * assert that no opening of a cell opens a other cell which contains a bomb
	 */

	
	@Test
	public void test5(){
		Game game = new Game(7, new Minesweeper());
		game.getGl().setPlayerName("Player1");
		for (Cell[] c : game.getCells()) {
			for (Cell t : c) {
				if(t.isBomb())
					t.open();
			}
		}
		assertTrue(game.getWincounter()==game.getBombs());
		
	}
	
}
