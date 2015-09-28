
package org.usfirst.frc.team610.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
//Jack was here
public class Robot extends IterativeRobot {
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	/*
	 * 
	 * TODO: CHECK TALON NUMBERS
	 * 		 CHECK DIRECTION OF TALONS
	 * 		 WHICH PRESEASON DRIVE 
	 */
	Talon leftFront,leftMid,leftBack,rightFront,rightMid,rightBack;
	Joystick driver;
    public void robotInit() {
    	leftFront = new Talon(0);
    	leftMid = new Talon(1);
    	leftBack = new Talon(2);
    	rightFront = new Talon(3);
    	rightMid = new Talon(4);
    	rightBack = new Talon(5);
    	driver = new Joystick(0);
    	
    	
    	

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    /**
     * This function is called periodically during operator control
     */
    //TOD
    public void setLeft(double v){
    	leftFront.set(v);
    	leftMid.set(v);
    	leftBack.set(v);
    }
    public void setRight(double v){
    	rightFront.set(-v);
    	rightMid.set(-v);
    	rightBack.set(-v);
    }
    public void teleopPeriodic() {
    	double leftSpeed,rightSpeed,x,y;
    	x = driver.getRawAxis(2);
    	y = driver.getRawAxis(0);
    	leftSpeed = y+x;
    	rightSpeed = y-x;
    	setLeft(leftSpeed);
    	setRight(rightSpeed);
    	
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
