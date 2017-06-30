
public class Timer extends Thread{
	
	boolean stop;
	
	 public void run(){
		String zeit;
		stop=false;
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
		Game.getTimer().setText(zeit);
		c++;
	}
	 }
	 public void reset(){
		 stop=true;
	 }
}