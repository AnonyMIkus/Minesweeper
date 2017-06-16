import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOver extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public GameOver(Minesweeper menu) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblLeiderVerloren = new JLabel("Leider verloren! :(");
		lblLeiderVerloren.setFont(new Font("Arial Black", Font.PLAIN, 36));
		lblLeiderVerloren.setBounds(10, 53, 418, 86);
		contentPanel.add(lblLeiderVerloren);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Zur\u00FCck zum Hauptmen\u00FC");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
						menu.getFrame().setVisible(true);
					}
				});
				okButton.setActionCommand("Zur\u00FCck zum Hauptmen\u00FC");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
