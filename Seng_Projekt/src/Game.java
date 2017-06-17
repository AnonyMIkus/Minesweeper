import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

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
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import java.awt.Color;

public class Game {
	private Cell[][] cells;
	int mode;
	int dimensionX;
	int dimensionY;
	JFrame frmMinesweeper;
	Minesweeper menu;
	JPanel panel2;
	ImageIcon ico = new ImageIcon("img/flag.png"); //Bild der Flagge
	
	int panelAnfPosX = 49;
	int panelAnfPosY = 72;
	int panelFortPosX = 49;
	int panelFortPosY = 72;
	int panelProPosX = 49;
	int panelProPosY = 72;
	int panelAnfWidth = 388;
	int panelFortWidth = 488;
	int panelProWidth = 850;

//Positionen und Skalierungen der Spielfelder

	
	

	/**
	 * Create the frame.
	 */
	public Game(int mode, Minesweeper menu) {
		
		
		this.mode = mode;
		this.menu = menu;
		dimensionY = mode == 0 ? 8 : mode == 1 ? 16 : 30;		//Dimensionen der Spielfeldgr��e je nach Schwierigkeitsgrad
		dimensionX = mode == 0 ? 8 : mode == 1 ? 16 : 16;
		frmMinesweeper = new JFrame();
		frmMinesweeper.setTitle("Minesweeper");
		frmMinesweeper.setResizable(false);
		frmMinesweeper.getContentPane().setLayout(null);
		frmMinesweeper.getContentPane().add(addCells());
		frmMinesweeper.getContentPane().add(panel2);
        frmMinesweeper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMinesweeper.setVisible(true);
        
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
		frmMinesweeper.setJMenuBar(bar);

		
		JPanel panel = new JPanel();		//Umrandung f�r die Zeitangabe
		panel.setBorder(new TitledBorder(null, "Zeit", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 50, 50);
		frmMinesweeper.getContentPane().add(panel);
		
		JLabel timer = new JLabel();
		panel.add(timer);
		timer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		timer.setText("00:00");		//Zeitangabe. W�re durch die Variable "Zeit" zu ersetzen
		
		JButton btnMenu = new JButton("zur\u00FCck zum Hauptmen\u00FC"); //F�hrt zur�ck zum Hauptmen�
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMinesweeper.dispose();
				menu.getFrame().setVisible(true);
			}
		});
		btnMenu.setBounds(126, 21, 143, 31);
		frmMinesweeper.getContentPane().add(btnMenu);
		
		
	}
	
	public JPanel addCells(){ 			//Methode f�gt die Zellen hinzu
		
        JPanel panel = new JPanel(new GridLayout(dimensionX,dimensionY));	//panel = Hier werden die Zellen reingesetzt
        panel2 = new JPanel(new GridLayout(dimensionX, dimensionY));	//panel2 = hinter den Zellen die Zahlenangaben, Bombenbildchen etc.
        
        int posX = 0;
        int posY = 0;
        int width = 0;
        int height = 0;
        
        switch (mode){		// Je nach gew�hltem Schwierigkeitsgrad (mode) werden die Position und Skalierung des Spielfeldes gesetzt
        case 0:
        	posX = panelAnfPosX;
        	posY = panelAnfPosY;
        	width = panelAnfWidth;
        	height = width;
        	break;
        case 1:
        	posX = panelFortPosX;
        	posY = panelFortPosY;
        	width = panelFortWidth;
        	height = width;
        	break;
        case 2:
        	posX = panelProPosX;
        	posY = panelProPosY;
        	width = panelProWidth;
        	height = (int)(width / (30f/16f));
        	break;
        default:
        	break;
        }
        
        panel.setBounds(posX, posY, width, height);
        panel2.setBounds(panel.getBounds());
        
        frmMinesweeper.setBounds(500, 200, width + posX + 50, height + posY + 100);	//die Gr��e des Fensters ist abh�ngig von der Gr��e des Spielfeldes
        
        ico.setImage(ico.getImage().getScaledInstance(width/dimensionY -5,height/dimensionX -5,Image.SCALE_DEFAULT)); //Gr��e der Flagge, die in einem Button angezeigt wird. Abh�ngig von der Gr��e des SPielfeldes
        
        cells = new Cell[dimensionX][dimensionY];	//Array "cells" wird erstellt mit den Dimensionen der Spielfeldgr��e
        
        for(int i = 0; i< dimensionX; i++){
            for(int j = 0; j< dimensionY; j++){

            	
                cells[i][j] = new Cell(this, new int[] {i, j}, ico); //Cell-Objekte werden erstellt, Game-Objekt, Index der Zelle und Icon f�r die Flagge werden mitgegeben
                
                panel2.add(cells[i][j].getLabel());		//Hintergrundpanel wird bef�llt mit den Labels (Hintergrundbildern) der Zellen
                panel.add(cells[i][j].getButton());		//Vordergrundpanel wird bef�llt mit den Buttons der Zellen
                    
            }
        }
        
        spreadBombs(mode == 0 ? 10 : mode == 1 ? 40 : 99);	//Mienen werden verteilt je nach gew�hltem Schwierigkeitsgrad
      
        
        panel.setOpaque(false);	//das Panel mit den Zellen wird auf durchsichtig gesetzt, damit man die Labels dahinter bei Buttondruck sehen kann
        
        return panel;
    }
	
	private void spreadBombs(int number){
		
		Random rnd = new Random();
		
		for(int j = 0; j< number; j++){
        	
        	int x;
        	int y;
        	
        	do{
        	x = rnd.nextInt(dimensionX);
        	y = rnd.nextInt(dimensionY);
        	}
        	while(cells[x][y].bomb);		//Mienen werden zuf�llig auf die Felder verteilt
        			
        	cells[x][y].setBomb();
        	
        	if (x != 0)
        		cells[x-1][y].setValue(cells[x-1][y].getValue()+1);
        	if (x != dimensionX-1)
        		cells[x+1][y].setValue(cells[x+1][y].getValue()+1);
        	if (x != 0 && y != dimensionY-1)
        		cells[x-1][y+1].setValue(cells[x-1][y+1].getValue()+1);
        	if (x != dimensionX-1 && y != dimensionY-1)
        		cells[x+1][y+1].setValue(cells[x+1][y+1].getValue()+1);
        	if (y != dimensionY-1)
        		cells[x][y+1].setValue(cells[x][y+1].getValue()+1);
        	if (y != 0)
        		cells[x][y-1].setValue(cells[x][y-1].getValue()+1);
        	if (x != dimensionX-1 && y != 0)
        		cells[x+1][y-1].setValue(cells[x+1][y-1].getValue()+1);
        	if (x != 0 && y != 0)
        		cells[x-1][y-1].setValue(cells[x-1][y-1].getValue()+1);
        	//Die Werte (value) der Nachbarzellen einer Zelle mit Bombe werden um 1 erh�ht
        			
            }
	}

	
	
	public Cell getCellbyIndex(int[] index){		//Kann von einer Zelle aufgerufen werden um die Zelle anhand eines Index zur�ck zu bekommen
		
		return cells[index[0]][index[1]];
	}
}


