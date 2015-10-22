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
		leftFront = new Victor(ElectricalConstants.leftFront);
		leftMid = new Victor(ElectricalConstants.leftMid);
		leftBack = new Victor(ElectricalConstants.leftBack);
		rightFront = new Victor(ElectricalConstants.rightFront);
		rightMid = new Victor(ElectricalConstants.rightMid);
		rightBack = new Victor(ElectricalConstants.rightBack);
		//comp = new Compressor();
		//comp.start();
	
	}
	
	public static DriveTrain getInstance(){
		if(instance == null){
			instance = new DriveTrain();
		}
		return instance;
	}
	
	public void setLeftSpeed(double v){
	leftFront.set(v);
		leftMid.set(v);
		leftBack.set(v);
	}
	
	public void setRightSpeed(double v){
		rightFront.set(-v);
		rightMid.set(-v);
		rightBack.set(-v);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

