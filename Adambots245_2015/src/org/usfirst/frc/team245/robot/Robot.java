package org.usfirst.frc.team245.robot;

import Auton.PushForward;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
	private static final String AUTON_MODE_SELECTOR_INPUT = "Auton Mode selector input";
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	private double autonMode = 0;
	private double iteration = 0;

	@Override
	public void robotInit() {

		// SensorsAndActuators.compressor.start();

		SmartDashboard.putString("Init Completed", " ");
		// Auton.LandmarkPlacement.init();
		// SensorsAndActuators.initialize();
		SensorsAndActuators.initialize();
		//SensorsAndActuators.compressor.start();
		
		 Drive.Drive.init();
		// Drive.Drive.roiInit();
		 ControlsTest.timer.start();
		//PneumaticsTest.init();
		 Cameras.camInit();
	}

	@Override
	public void autonomousInit() {
		// TODO Auto-generated method stub
		super.autonomousInit();
		PushForward.iterator = 0;
		Auton.Containor.iterator=0;
		Auton.PushForward.iterator = 0;
	}

	@Override
	public void autonomousPeriodic() {

		SmartDashboard.putNumber("Auton Mode selected", autonMode);

		// Only one of the two is called, eventually smartdash will decide
		// LandmarkPlacement.update();
		
			//Auton.PushForward.update();
		
		
			Auton.Containor.update();
		
		//Auton.Containor.update();
		 SmartDashboard.putNumber("auton iterator", PushForward.iterator);
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	
	public void teleopPeriodic() {
		 try{
		Teleop.doWerk();
		// Auton.Test.update();
		//ControlsTest.update();
		// SmartDashboard.putNumber("Halleffect encoder",
		// SensorsAndActuators.exteriorRotationEncoder.get());
		SmartDashboard.putNumber("exterior motor value", SensorsAndActuators.exteriorLiftMotor.get());
		
		 }
		catch(Exception e){
		SmartDashboard.putString("the exception was:", e.toString());
		
		SmartDashboard.putString("the class of the exception was", e.getStackTrace()[0].getClassName());
		SmartDashboard.putNumber("the line number exception was", e.getStackTrace()[0].getLineNumber());
		// e.printStackTrace();
		}
		//PneumaticsTest.update();
		//ControlsTest.update();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {

	}
	@Override
	public void disabledInit() {
		// TODO Auto-generated method stub
		super.disabledInit();
		
	}
	@Override
	public void disabledPeriodic() {
		// TODO Auto-generated method stub
		super.disabledPeriodic();
		
		/*SmartDashboard.putString("Writing x", Drive.Drive.xValues.toString());
		SmartDashboard.putString("Writing y", Drive.Drive.yValues.toString());
		SmartDashboard.putString("Writing z",
				Drive.Drive.rotationValues.toString());
		SmartDashboard.putBoolean("TOP LIMIT INIT",
				SensorsAndActuators.exteriorTopLimit.get());
		SmartDashboard.putBoolean("BOTTOM LIMIT INIT",
				SensorsAndActuators.exteriorBottomLimit.get());*/
	//SensorsAndActuators.interiorManipulator.reset();	
		SmartDashboard.putString("Writing x", Drive.Drive.xAxis.toString());
		SmartDashboard.putString("Writing y", Drive.Drive.yAxis.toString());
		SmartDashboard.putString("Writing rotation",
				Drive.Drive.rotationAxis.toString());
		SmartDashboard.putString("Writing interiorSpeed", Interior.Interior.interiorSpeed.toString());
		SmartDashboard.putString("interior ratchet", Interior.Interior.interiorRatchet.toString());
		SmartDashboard.putString("interior clamp", Interior.Interior.interiorClamp.toString());
		SmartDashboard.putString("exterior speed", Exterior.Exterior.exteriorSpeed.toString());
		SmartDashboard.putString("exterior clamp", Exterior.Exterior.exteriorClamp.toString());
		Drive.Drive.MAX_ACCEL=SmartDashboard.getNumber("Max acceleration input", .05);
	}

}