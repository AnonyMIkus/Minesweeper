import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class HighscoreFrame {
	private final int topNumber = 25;

	// Listen
	private ArrayList<String> playerName = new ArrayList<>();
	private ArrayList<Integer> playedTime = new ArrayList<>();

	private ArrayList<JLabel> rangText = new ArrayList<>();
	private ArrayList<JLabel> playerText = new ArrayList<>();
	private ArrayList<JLabel> timeText = new ArrayList<>();

	// Intinitalisierung des Fensters.
	private JFrame highscoreFrame = new JFrame("Highscore");
	private JPanel listPanel = new JPanel(new GridLayout(1, 3));
	private JPanel postionPanel = new JPanel(new GridLayout(topNumber, 1));
	private JPanel playerPanel = new JPanel(new GridLayout(topNumber, 1));
	private JPanel timerPanel = new JPanel(new GridLayout(topNumber, 1));
	private JPanel framePanel = new JPanel();

	// Ininitalisierung der Elemente.
	private JTextField inputName = new JTextField("Player 1");
	private int used = 935; // Hier kommt die gespielte Zeit hin. Vorerste Werte
							// zum Testen.
	private JLabel receivedTime = new JLabel(String.valueOf(used));
	private JLabel sec = new JLabel("sec");
	private JButton submitter = new JButton("Submit");

	public void creatingHighscoreFrame() {
		for (int i = 0; i < topNumber; i++) {
			rangText.add(new JLabel(String.valueOf(i + 1)));
			if (i < playerName.size()) {
				playerText.add(new JLabel(playerName.get(i)));
			} else {
				playerText.add(new JLabel("No Player found"));
			}
			if (i < playedTime.size()) {
				timeText.add(new JLabel(String.valueOf(playedTime.get(i)) + " sec"));
			} else {
				timeText.add(new JLabel("999 sec"));
			}
		}
		for (int i = 0; i < topNumber; i++) {
			postionPanel.add(rangText.get(i));
			playerPanel.add(playerText.get(i));
			timerPanel.add(timeText.get(i));
		}

		submitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (check()) {
					refresh();
				}
			}
		});

		// Aussehen der Listen definieren und hinzufügen.
		Border rangBorder = BorderFactory.createTitledBorder("Rang");
		Border playerBorder = BorderFactory.createTitledBorder("Player Name");
		Border timeBorder = BorderFactory.createTitledBorder("Time");

		postionPanel.setBorder(rangBorder);
		playerPanel.setBorder(playerBorder);
		timerPanel.setBorder(timeBorder);

		listPanel.add(postionPanel);
		listPanel.add(playerPanel);
		listPanel.add(timerPanel);

		// Elemente dem Fenster hinzufügen.
		framePanel.add(listPanel);
		framePanel.add(inputName);
		framePanel.add(receivedTime);
		framePanel.add(sec);
		framePanel.add(submitter);

		highscoreFrame.add(framePanel);
		highscoreFrame.setVisible(true);
		highscoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		highscoreFrame.pack();
	}

	public boolean check() {
		boolean checkScore = false;
		int i = 0;
		while (i <= playedTime.size() && i < topNumber) {
			if (playedTime.size() != 0) {
				if (used <= playedTime.get(i)) {
					playedTime.add(i, used);
					playerName.add(i, inputName.getText());
					if (playedTime.size() >= topNumber) {
						while (playedTime.size() > topNumber) {
							playedTime.remove(topNumber);
							playerName.remove(topNumber);
						}
					}
					return checkScore;
				}
			} else {
				playedTime.add(i, used);
				playerName.add(i, inputName.getText());
				return checkScore;
			}
			i++;
		}
		return checkScore;
	}

	public void refresh() {
		clearing();
		addScores();
	}

	public void addScores() {
		for (int i = 0; i < topNumber; i++) {
			rangText.add(new JLabel(String.valueOf(i + 1)));
			if (i < playerName.size()) {
				playerText.add(new JLabel(playerName.get(i)));
			} else {
				playerText.add(new JLabel("No Player found"));
			}
			if (i < playedTime.size()) {
				timeText.add(new JLabel(String.valueOf(playedTime.get(i)) + " sec"));
			} else {
				timeText.add(new JLabel("999 sec"));
			}
		}
		for (int i = 0; i < topNumber; i++) {
			postionPanel.add(rangText.get(i));
			playerPanel.add(playerText.get(i));
			timerPanel.add(timeText.get(i));
		}
	}

	public void clearing() {
		postionPanel.removeAll();
		playerPanel.removeAll();
		timerPanel.removeAll();
		for (int i = 0; i < playerText.size();) {
			rangText.remove(i);
			rangText.trimToSize();
		}
		for (int i = 0; i < playerText.size();) {
			playerText.remove(i);
			playerText.trimToSize();
		}
		for (int i = 0; i < playerText.size();) {
			timeText.remove(i);
			timeText.trimToSize();
		}
	}

	public void save() {

	}

	public void load() {

	}
}
