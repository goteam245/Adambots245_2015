package org.usfirst.frc.team245.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class Cameras {
	static int session0;
 	//static int session1;
	static Image frame;
	static int curSession;
	private static int cameraIteration = 0;
	static CameraServer server;
	private static Timer periodicTimer = new Timer();
	private static double iteration = 0;
	private static USBCamera camera;
	private static double timerCapQuality = 0.4;
	
	public static void camInit() {
//		session0 = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
//     	//session1 = NIVision.IMAQdxOpenCamera("cam2", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
//        curSession = 5;
//		setSession( session0 );
//		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
//		CameraServer.getInstance().setQuality(1);
		server = CameraServer.getInstance();
        server.setQuality(1);
        camera = new USBCamera("cam1");
        camera.setFPS(2);
        camera.setSize(160, 120);
        camera.updateSettings();
        server.startAutomaticCapture(camera);
        //the camera name (ex "camera") can be found through the roborio web interface
        //server.startAutomaticCapture("camera");
        SmartDashboard.putBoolean("DYAMIC CAMERA", false);
        
	}
	public static void setCamQuality(){
		if (iteration == 0)
			periodicTimer.start();
		else {
			SmartDashboard.putNumber("Periodic time", periodicTimer.get());
			if(periodicTimer.get()>timerCapQuality){
				server.setQuality(1);
				SmartDashboard.putBoolean("DYAMIC CAMERA", true);
			}else{
				SmartDashboard.putBoolean("DYAMIC CAMERA", false);
				//server.setQuality(50);
			}
			periodicTimer.reset();
		}
	}
	static int i = 0;
	public static void disableCam(){
		
		if(!SmartDashboard.getBoolean("CAMERA ENABLED", true)){
        	server = null;
        	i = 0;
		}
		else if(i==0){
			server = CameraServer.getInstance();
        server.setQuality(50);
        
        //the camera name (ex "camera") can be found through the roborio web interface
        server.startAutomaticCapture(camera);
        i++;
		}	
	}
	public static void setSession(int newSession) {
		if(newSession!=curSession){
			//NIVision.IMAQdxStopAcquisition(curSession);
			NIVision.IMAQdxConfigureGrab(newSession);
			NIVision.IMAQdxStartAcquisition(newSession);
			curSession = newSession;
			
		}
	}

	public static void updateCamera() {

		if ( cameraIteration % 10 == 0 ) {
			NIVision.IMAQdxGrab(curSession, frame, 1);
			
			
			CameraServer.getInstance().setImage(frame);
		}
		cameraIteration++;
	}


}
