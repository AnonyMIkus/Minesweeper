/**
 * Timer counting used time and change it  for display.
 * 
 * @author John Voronkov
 *
 * @version 1.0
 */
public class Timer extends Thread {
	boolean stop;

	/**
	 * Change for display while counting.
	 */
	public void run() {
		String zeit;
		stop = false;
		int c = 0;
		while (c < 9999 && !stop) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			c++;
			if (c / 60 > 9 && c % 60 > 9) {
				zeit = Integer.toString(c / 60) + ":" + Integer.toString(c % 60);
			} else {
				if (c / 60 < 10 && c % 60 > 9) {
					zeit = "0" + Integer.toString(c / 60) + ":" + Integer.toString(c % 60);
				} else {
					if (c / 60 > 9 && c % 60 < 10) {
						zeit = Integer.toString(c / 60) + ":" + "0" + Integer.toString(c % 60);
					} else {
						zeit = "0" + Integer.toString(c / 60) + ":" + "0" + Integer.toString(c % 60);
					}
				}
			}
			Game.getTimer().setText(zeit);
			Game.getGl().setTime(c);
		}
	}

	/**
	 * Resetting Timer.
	 */
	public void reset() {
		stop = true;
	}
}