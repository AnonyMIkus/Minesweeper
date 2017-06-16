import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;

public class Cell implements ActionListener{
	private JButton button;
	private JLabel label;
	boolean bomb = false;
	boolean flagSet = false;
	private int value = 0;
	private int[] index;
	Game game;
	ImageIcon ico;
	boolean uncovered = false;
	Cell[] neighbourCells;
	public int dimensionX;
	public int dimensionY;
	
	
	public Cell(Game game, int[] index, ImageIcon flagItem){
		
        button = new JButton();
        this.ico = flagItem;
       
        
        label = new JLabel();
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(20,20));
        label.setBorder(new LineBorder(Color.BLACK, 1));
        button.addActionListener(this);
        button.setPreferredSize(new Dimension(20,20));
        button.setMargin(new Insets(0,0,0,0));
        this.index = index;
        this.game = game;
        this.dimensionX = game.dimensionX;
        this.dimensionY = game.dimensionY;
        
        button.addMouseListener(new MouseAdapter(){
      	  public void mouseReleased(MouseEvent e){
      		if (SwingUtilities.isRightMouseButton(e)){
      			
	      	    if(!flagSet){
	      	    	button.setIcon(ico);
	      	    	flagSet = true;
	      	    }
	      	    else{
	      	    	button.setIcon(null);
	      	    	flagSet = false;
	      	    }
	      	    }
      	  }
      	  
      });
    
  
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		uncover();
		
		if(bomb){
			GameOver gameOver = new GameOver(new Minesweeper());
			gameOver.setVisible(true);
			game.frmMinesweeper.dispose();
		}
		//if(this.getValue() == 0 && !this.bomb)
			//uncoverNeighbours();
		game.uncover(index);
		
	}
	
	public void uncover(){
		if(!flagSet){
			getButton().setVisible(false);
			uncovered = true;
		}
	}
	
	public void uncoverNeighbours(){
		
		getNeighbours();

		
		for(int i = 0; i < neighbourCells.length; i++){
			if(neighbourCells[i] != null){
				if(!neighbourCells[i].uncovered && (!neighbourCells[i].bomb || neighbourCells[i].flagSet))
					neighbourCells[i].uncover();
				if(neighbourCells[i].getValue() == 0)
					neighbourCells[i].uncoverNeighbours();
			}
		}
		
	}
	
	
	
	public void setBomb(){
		bomb = true;
		label.setText("X");
		label.setForeground(Color.RED);
	}
	
	 public JButton getButton() {
	        return button;
	    }

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}
	
	public void setValue(int val){
		if(!bomb){
			value = val;
			label.setText(Integer.toString(value));
			}
	}
	
	public int getValue(){
		return value;
	}
	
	public int[] getIndex(){
		return this.index;
	}
	
	private void getNeighbours(){
		
		int x = index[0];
		int y = index[1];
		
		neighbourCells = new Cell[] {null, null, null, null, null, null, null, null};
		
		if(y != dimensionY-1)
			neighbourCells[0] = game.getCellbyIndex(new int[]{x, y+1});
		if(y != 0)
			neighbourCells[1] = game.getCellbyIndex(new int[]{x, y-1});
		if(x != dimensionX-1)
			neighbourCells[2] = game.getCellbyIndex(new int[]{x+1, y});
		if(x != 0)
			neighbourCells[3] = game.getCellbyIndex(new int[]{x-1, y});
		if(x != dimensionX-1 && y != dimensionY-1)
			neighbourCells[4] = game.getCellbyIndex(new int[]{x+1, y+1});
		if(x != dimensionX-1 && y != 0)
			neighbourCells[5] = game.getCellbyIndex(new int[]{x+1, y-1});
		if(x != 0 && y != dimensionY-1)
			neighbourCells[6] = game.getCellbyIndex(new int[]{x-1, y+1});
		if(x != 0 && y != 0)
			neighbourCells[7] = game.getCellbyIndex(new int[]{x-1, y-1});
	}
	
	

	
	
}


