import java.awt.Color;

import com.thehowtotutorial.splashscreen.JSplash;
public class Splash {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			JSplash splash = new JSplash(Splash.class.getResource("java.png"), true, true, false, null, null, Color.RED, Color.BLACK);
			splash.splashOn();
			
			splash.setProgress(20, "Initializing");
			Thread.sleep(1100);
			splash.setProgress(40, "Getting Dependencies");
			Thread.sleep(1100);
			splash.setProgress(60, "Acquiring User Information");
			Thread.sleep(1100);
			splash.setProgress(80, "Querying for forecast");
			Thread.sleep(1100);
			splash.setProgress(90, "Establishing interface");
			Thread.sleep(1100);
			splash.splashOff();
			LogIn ll= new LogIn();
			ll.setVisible(true);
			
		
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
