
public class Timer extends Thread{
	Timer(Game game){
		stop=false;
		zeit="00:00";
		this.game=game;
	}
	Game game;
	boolean stop;
	String zeit;
	
	 public void run(){
		
	
	Thread thread = new Thread();
	int c=0;
	while (c<9999 && !stop){
		try {
			thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if (c/60>9 && c%60>9){
		zeit=Integer.toString(c/60)+":"+Integer.toString(c%60);}
		else{
		if (c/60<10 && c%60>9){
		zeit="0"+Integer.toString(c/60)+":"+Integer.toString(c%60);}
		else{
		if (c/60>9 && c%60<10){
		zeit=Integer.toString(c/60)+":"+"0"+Integer.toString(c%60);}
		else{
		zeit="0"+Integer.toString(c/60)+":"+"0"+Integer.toString(c%60);}
		}}
		game.getTimer().setText(zeit);
		c++;
	}
	 }
	 public void reset(){
		 stop=true;
	 }
}