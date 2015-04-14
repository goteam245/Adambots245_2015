package Auton;

public class ClaspContainor {
	public static int iterator = 0;
	public static void update(){
		iterator++;
		if(iterator<5){
			Exterior.Exterior.setClamps(false);
		}
		else{
			Exterior.Exterior.setClamps(true);
		}
	}
}
