package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.Compressor;
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
	Compressor comp;
	
	private DriveTrain(){
		//CHECK PORTS
		leftFront = new Victor(ElectricalConstants.LEFT_FRONT);
		leftMid = new Victor(ElectricalConstants.LEFT_MID);
		leftBack = new Victor(ElectricalConstants.LEFT_BACK);
		rightFront = new Victor(ElectricalConstants.RIGHT_FRONT);
		rightMid = new Victor(ElectricalConstants.RIGHT_MID);
		rightBack = new Victor(ElectricalConstants.RIGHT_BACK);
		//comp = new Compressor();
		//comp.start();
	
	}
	
	//Get public static instance of object DriveTrain
	public static DriveTrain getInstance(){
		if(instance == null){
			instance = new DriveTrain();
		}
		return instance;
	}
	
	//Set left speed
	public void setLeftSpeed(double speed){
	leftFront.set(speed);
		leftMid.set(speed);
		leftBack.set(speed);
	}
	
	//Set right speed
	public void setRightSpeed(double speed){
		rightFront.set(-speed);
		rightMid.set(-speed);
		rightBack.set(-speed);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

