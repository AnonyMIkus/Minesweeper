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

public class GameOver extends JDialog {
	
	Game game;
	private ImageIcon bomb = new ImageIcon("img/mine.png");

	private final JPanel contentPanel = new JPanel();
	// Create the dialog.
	public GameOver(Minesweeper menu, Game game) {
		setTitle("Verloren!");
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		bomb.setImage(bomb.getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)); // Größe der Miene, die in einem Button angezeigt wird. Abhängig von der Größe des Spielfeldes.

		
		JLabel lblLeiderVerloren = new JLabel("Leider verloren! :(");
		lblLeiderVerloren.setFont(new Font("Arial Black", Font.PLAIN, 36));
		lblLeiderVerloren.setBounds(40, 11, 364, 86);
		contentPanel.add(lblLeiderVerloren);
		{
			JLabel lblBomb = new JLabel("");
			lblBomb.setBounds(171, 101, 90, 90);
			lblBomb.setIcon(bomb);
			contentPanel.add(lblBomb);
		}
		{
			JButton okButton = new JButton("Zur\u00FCck zum Hauptmen\u00FC");
			okButton.setBounds(251, 216, 153, 35);
			contentPanel.add(okButton);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					game.getFrmMinesweeper().dispose();
					menu.getFrame().setVisible(true);
				}
			});
			okButton.setActionCommand("Zur\u00FCck zum Hauptmen\u00FC");
			getRootPane().setDefaultButton(okButton);
		}
		
		JButton btnNochmal = new JButton("Nochmal versuchen");
		btnNochmal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				game.getFrmMinesweeper().dispose();
				Game gm = new Game(game.getGl().getMode(), game.getMenu());
			}
		});
		btnNochmal.setActionCommand("Nochmal");
		btnNochmal.setBounds(31, 216, 153, 35);
		contentPanel.add(btnNochmal);
	}
}
