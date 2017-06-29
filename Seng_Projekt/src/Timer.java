
public class Timer extends Thread{
	
	 public void run(){
		String zeit;
	Thread thread = new Thread();
	for (int c=0; c<9999;c++){
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
		//timer.setText(zeit);
	}}
}