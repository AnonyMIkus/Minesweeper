import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * HighscoreFrame is loading the Highscore for the gamemodes and their levels
 * beginner, amateur and advanced.
 * 
 * @author John Voronkov
 *
 * @version 2.0
 */

public class HighscoreFrame {

	private static String[] fileName = { "beginnerstandard.txt", "amateurstandard.txt", "advancedstandard.txt",
			"beginnertreasure.txt", "amateurtreasure.txt", "advancedtreasure.txt", "beginnerbomb.txt",
			"amateurbomb.txt", "advancedbomb.txt" };
	private static String[] Title = { "Highscore Beginner Standard", "Highscore Amateur Standard",
			"Highscore Advanced Standard", "Highscore Beginner Treasure Hunt", "Highscore Amateur Treasure Hunt",
			"Highscore Advanced Treasure Hunt", "Highscore Beginner Bomb Hunt", "Highscore Amateur Bomb Hunt",
			"Highscore Advanced Bomb Hunt" };
	private final int topNumber = 25;
	gameLibrary gl;
	private String seperator = ",";
	private ArrayList<String> playerList = new ArrayList<>();
	private ArrayList<Integer> timeList = new ArrayList<>();
	private ArrayList<JLabel> rangText = new ArrayList<>();
	private ArrayList<JLabel> playerText = new ArrayList<>();
	private ArrayList<JLabel> timeText = new ArrayList<>();
	private JFrame highscoreFrame = new JFrame("Highscore");
	private JPanel listPanel = new JPanel(new GridLayout(1, 4));
	private JPanel postionPanel = new JPanel(new GridLayout(topNumber, 1));
	private JPanel playerPanel = new JPanel(new GridLayout(topNumber, 1));
	private JPanel timerPanel = new JPanel(new GridLayout(topNumber, 1));
	private JPanel framePanel = new JPanel();
	private int usedTime = Game.getGl().getTime();
	private String playerName = Game.getGl().getPlayerName();
	private int mode = Game.getGl().getMode();

	/**
	 * Default Constructor for the process after a winning game.
	 */
	public HighscoreFrame() {
		gl = Game.getGl();
		load();
		addScore();
		save();
		loadingList();
		createFrame();
	}

	/**
	 * Overloaded Constructor for calling Highscore from main menu.
	 * 
	 * @param mode
	 *            is important to load the right document for score.
	 */
	public HighscoreFrame(int mode) {
		highscoreFrame.setTitle(HighscoreFrame.Title[mode]);
		load(mode);
		loadingList();
		createFrame();
	}

	/**
	 * Saving Highscore into right document.
	 * 
	 * @return boolen for success of saving.
	 */
	public boolean save() {
		try {
			if (mode < 0 || mode > 8) {
				return false;
			}
			File saving = new File(fileName[mode]);
			FileWriter fw = new FileWriter(saving);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < playerList.size(); i++) {
				bw.write(playerList.get(i));
				bw.write(seperator);
				bw.write(String.valueOf(timeList.get(i)));
				bw.newLine();
			}
			bw.close();
			fw.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Adding Score to Highscore if player did a Highscore in last game.
	 */
	public void addScore() {
		int i = 0;
		if (timeList.size() == 0) {
			playerList.add(playerName);
			timeList.add(usedTime);
		} else {
			while (i < timeList.size() &&  usedTime > timeList.get(i)  ) {
				i++;
			}
     		playerList.add(i,playerName);
	    	timeList.add(i,usedTime);
			while (timeList.size() > topNumber) {
				timeList.remove(topNumber);
				timeList.trimToSize();
				playerList.remove(topNumber);
				playerList.trimToSize();
			}
		}
	}

	/**
	 * Loading nessecary files depending on gamemode and difficulty.
	 * 
	 * @return boolen for success of loading.
	 */
	public boolean load() {
		try {
			if (mode < 0 || mode > 8) {
				return false;
			}
			File saving = new File(fileName[mode]);
			if (saving.exists()) {
				FileReader fr = new FileReader(saving);
				BufferedReader br = new BufferedReader(fr);
				String bufferedReader = null;
				if (br.ready()) {
					bufferedReader = br.readLine();
				}
				String[] valueList;
				while (bufferedReader!=null) {
					valueList = bufferedReader.split(seperator);
					playerList.add(valueList[0]);
					timeList.add(Integer.parseInt(valueList[1]));
					if (br.ready()) {
						bufferedReader = br.readLine();
					} else {
						break;
					}
				}
				br.close();
				fr.close();
				return true;
			} else {
				if (saving.createNewFile()) {
					return true;
				}
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Preparing List for window.
	 */
	public void loadingList() {
		for (int i = 0; i < topNumber; i++) {
			rangText.add(new JLabel(String.valueOf(i + 1)));
			if (i < playerList.size() && i < topNumber) {
				playerText.add(new JLabel(playerList.get(i)));
			} else {
				playerText.add(new JLabel("No player found"));
			}
			if (i < timeList.size() && i < topNumber) {
				timeText.add(new JLabel(String.valueOf(timeList.get(i))));
			} else {
				timeText.add(new JLabel("No time found"));
			}
		}

		for (int i = 0; i < topNumber; i++) {
			postionPanel.add(rangText.get(i));
			playerPanel.add(playerText.get(i));
			timerPanel.add(timeText.get(i));
		}
	}

	/**
	 * Creating Window with given information.
	 */
	public void createFrame() {
		JButton backButton = new JButton("Back to Menu");
		backButton.setBounds(510, 20, 150, 30);
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				highscoreFrame.dispose();
				highscoreFrame.setVisible(false);
				Minesweeper ms = new Minesweeper();
				ms.getFrame().setVisible(true);
			}
		});

		Border rangBorder = BorderFactory.createTitledBorder("Rang");
		Border playerBorder = BorderFactory.createTitledBorder("Player Name");
		Border timeBorder = BorderFactory.createTitledBorder("Time");

		postionPanel.setBorder(rangBorder);
		playerPanel.setBorder(playerBorder);
		timerPanel.setBorder(timeBorder);

		listPanel.add(postionPanel);
		listPanel.add(playerPanel);
		listPanel.add(timerPanel);

		framePanel.add(listPanel);

		highscoreFrame.add(backButton);
		highscoreFrame.setSize(700, 500);
		highscoreFrame.add(framePanel);
		highscoreFrame.setVisible(true);
		highscoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		highscoreFrame.setTitle(Title[Game.getGl().getMode()]);
	}

	/**
	 * Loading nessecary files depending on gamemode and difficulty.
	 * 
	 * @param mode
	 *            for loading correct file.
	 * @return boolen for success of loading.
	 */

	public boolean load(int mode) {
		try {
			if (mode < 0 || mode > 8) {
				return false;
			}
			File saving = new File(fileName[mode]);
			if (saving.exists()) {
				FileReader fr = new FileReader(saving);
				BufferedReader br = new BufferedReader(fr);
				String bufferedReader = null;
				if (br.ready()) {
					bufferedReader = br.readLine();
				}
				String[] valueList;
				while (bufferedReader!=null) {
					valueList = bufferedReader.split(seperator);
					playerList.add(valueList[0]);
					timeList.add(Integer.parseInt(valueList[1]));
					if (br.ready()) {
						bufferedReader = br.readLine();
					} else {
						break;
					}
				}
				br.close();
				fr.close();
				return true;
			} else {
				if (saving.createNewFile()) {
					return true;
				}
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
