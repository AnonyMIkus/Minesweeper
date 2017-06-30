import static org.junit.Assert.*;

import java.awt.AWTEvent;
import java.util.ArrayList;

import org.junit.Test;

public class GameTest {

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

	// assert that the Value of the Cell is right
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

	@Test
	public void test3() {
		Game game = new Game(1, new Minesweeper());
		for (Cell[] c : game.getCells()) {
			for (Cell t : c) {
				assertFalse(t.isUncovered());
			}
		}

	}
	
	@Test
	public void test4(){
		Game game= new Game(1, new Minesweeper());
	
		Cell[][]c= game.getCells();
		Cell testCell= c[1][1];
		testCell.open();
		assert(game.isGameLost()==testCell.isBomb());
		
		
		
		
	}

}
