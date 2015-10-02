package org.usfirst.frc.team610.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private static DriveTrain instance;
	Victor leftFront, leftMid, leftBack, rightFront, rightMid, rightBack;
	
	DriveTrain(){
		//CHECK PORTS
		leftFront = new Victor(0);
		leftMid = new Victor(1);
		leftBack = new Victor(2);
		rightFront = new Victor(3);
		rightMid = new Victor(4);
		rightBack = new Victor(5);
	
	}
	
	public static DriveTrain getInstance(){
		if(instance == null){
			instance = new DriveTrain();
		}
		return instance;
	}
	
	public void setLeftSpeed(double v){
		leftFront.set(v);
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

