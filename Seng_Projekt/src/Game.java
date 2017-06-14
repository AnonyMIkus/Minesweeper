import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Game {
	private Cell[][] cells;
	int mode;
	int dimensionX;
	int dimensionY;
	JFrame frame;
	Minesweeper menu;


	
	

	/**
	 * Create the frame.
	 */
	public Game(int mode, Minesweeper menu) {
		
		this.mode = mode;
		this.menu = menu;
		dimensionX = mode == 0 ? 8 : mode == 1 ? 16 : 30;
		dimensionY = mode == 0 ? 8 : mode == 1 ? 16 : 16;
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(addCells());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(500, 200, mode == 2 ? 450 : 400, mode == 2 ? 500 : 450);
        frame.setVisible(true);
        
        JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem close = new JMenuItem("Close");
		JMenu edit = new JMenu("Edit");
	

		file.add(close);
	
		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
});
		
		bar.add(file);
		bar.add(edit);
		frame.setJMenuBar(bar);

		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Zeit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 50, 50);
		frame.getContentPane().add(panel);
		
		JLabel timer = new JLabel();
		panel.add(timer);
		timer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		timer.setText("0:00");
		
		JButton btnMenu = new JButton("zur\u00FCck zum Hauptmen\u00FC");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
				menu.getFrame().setVisible(true);
			}
		});
		btnMenu.setBounds(126, 21, 143, 31);
		frame.getContentPane().add(btnMenu);
	}
	
	public JPanel addCells(){
		
        JPanel panel = new JPanel(new GridLayout(dimensionX,dimensionY));
        panel.setBounds(49, 72, mode== 2 ? 350 : 288, mode== 2 ? 350 : 288);
        cells = new Cell[dimensionX][dimensionY];
        
        for(int j = 0; j< dimensionY; j++){
            for(int i = 0; i< dimensionX; i++){
                cells[i][j] = new Cell();
                panel.add(cells[i][j].getButton());
            }
        }
        return panel;
    }
}
