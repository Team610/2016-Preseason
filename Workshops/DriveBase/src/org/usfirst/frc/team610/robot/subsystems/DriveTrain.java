package org.usfirst.frc.team610.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private static DriveTrain instance;
	Talon leftFront, leftMid, leftBack, rightFront, rightMid, rightBack;
	
	DriveTrain(){
		//CHECK PORTS
		leftFront = new Talon(0);
		leftMid = new Talon(1);
		leftBack = new Talon(2);
		rightFront = new Talon(3);
		rightMid = new Talon(4);
		rightBack = new Talon(5);
	
	}
	
	public static DriveTrain getInstance(){
		if(instance == null){
			instance = new DriveTrain();
		}
		return instance;
	}
	
	public void setLeftSpeed(double v){
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

