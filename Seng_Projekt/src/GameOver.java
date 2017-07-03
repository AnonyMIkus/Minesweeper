import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * GameOver get the player to the Game Over window where the player can choose
 * to play again or to return to main menu.
 * 
 * @author John Voronkov
 * 
 * @version 2.0
 * 
 */

public class GameOver extends JDialog {
	Game game;
	private ImageIcon bomb = new ImageIcon("img/mine.png");
	private final JPanel contentPanel = new JPanel();

	/**
	 * Default Constructor for creating window and its component. 
	 * @param menu Retrieving main menu in case of returning.
	 * @param game for disposing the recent game.
	 */
	public GameOver(Game game) {
		setTitle("Game lost!");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		bomb.setImage(bomb.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));

		JLabel lblLeiderVerloren = new JLabel("You Lost! :(");
		lblLeiderVerloren.setFont(new Font("Arial Black", Font.PLAIN, 36));
		lblLeiderVerloren.setBounds(40, 11, 364, 86);
		contentPanel.add(lblLeiderVerloren);

		JLabel lblBomb = new JLabel("");
		lblBomb.setBounds(171, 101, 90, 90);
		lblBomb.setIcon(bomb);
		contentPanel.add(lblBomb);

		JButton okButton = new JButton("Back to main menu");
		okButton.setBounds(251, 216, 153, 35);
		contentPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				game.getFrmMinesweeper().dispose();
				Minesweeper menu = new Minesweeper();
				menu.getFrame().setVisible(true);
			}
		});
		okButton.setActionCommand("Back to main menu");
		getRootPane().setDefaultButton(okButton);

		JButton btnNochmal = new JButton("Try again");
		btnNochmal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				game.getFrmMinesweeper().dispose();
				Game gm = new Game(Game.getGl().getMode(), game.getMenu());
			}
		});
		btnNochmal.setActionCommand("Try Again");
		btnNochmal.setBounds(31, 216, 153, 35);
		contentPanel.add(btnNochmal);
	}
}
