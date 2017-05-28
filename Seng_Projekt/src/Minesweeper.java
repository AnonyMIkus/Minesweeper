
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Minesweeper {
	private JFrame fr = new JFrame("Minesweeper");
	private JFrame gamefr = new JFrame("Minesweeper");
	private JButton[][] mineField;

	public void greeting() {
		JPanel pa = new JPanel();
		GridLayout gl = new GridLayout();
		JButton start = new JButton("Start");
		JButton setting = new JButton("Setting");
		JTextArea ta = new JTextArea("Software-Engeneering \nProjekt Minesweeper \nby Sandra, Philipp, John");

		gl.setRows(3);

		pa.setLayout(gl);
		pa.add(start);
		pa.add(setting);
		pa.add(ta);

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fr.setVisible(false);
				game();
			}
		});

		fr.setContentPane(pa);
		fr.pack();

		fr.setVisible(true);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void game() {
		JPanel panel = new JPanel();
		JLabel hint = new JLabel("Select you Level! Choose Level in 'Edit'");
		Font fo = new Font("hi", Font.PLAIN, 10);
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem close = new JMenuItem("Close");
		JMenu edit = new JMenu("Edit");
		JMenuItem beginner = new JMenuItem("Beginner");
		JMenuItem intermediate = new JMenuItem("Intermediate");
		JMenuItem advanced = new JMenuItem("Advanced");

		file.add(close);
		edit.add(beginner);
		edit.add(intermediate);
		edit.add(advanced);

		beginner.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int setFieldSize = 10;
				mineField = new JButton[setFieldSize][setFieldSize];
				for (int i = 0; i < setFieldSize; i++) {
					for (int j = 0; j < setFieldSize; j++) {
						mineField[i][j] = new JButton();
						panel.add(mineField[i][j]);
					}
				}
			}
		});

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		bar.add(file);
		bar.add(edit);

		hint.setFont(fo);

		gamefr.setContentPane(panel);
		gamefr.setJMenuBar(bar);
		gamefr.add(hint);

		gamefr.pack();
		gamefr.setVisible(true);
		gamefr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
