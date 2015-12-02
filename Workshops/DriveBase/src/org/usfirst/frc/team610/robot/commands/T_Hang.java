package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.InputConstants;
import org.usfirst.frc.team610.robot.subsystems.Hanger;
import org.usfirst.frc.team610.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class T_Hang extends Command {

	Joystick driver;
	Joystick operator;
	Hanger hanger;
	
	boolean bunnyEarsUp = false;
	boolean hangerBarUp = false;
	boolean isPressedBunny = false;
	
    public T_Hang() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	driver = OI.getInstance().getDriver();
    	operator = OI.getInstance().getOperator();
    	hanger = Hanger.getInstance();
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    
    
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	
    	
    	
    	//CHECK INPUT CONSTANTS
    	//L1, BunnyEars
//    	if((driver.getRawButton(InputConstants.BTN_L1) 
//    			| operator.getRawButton(InputConstants.BTN_L1))
//    			&& bunnyEarsUp){
//    		bunnyEarsUp = false;
//    		hanger.setBunnyEarsDown();
//    	}
//    	else if(!(driver.getRawButton(InputConstants.BTN_L1) 
//    			| operator.getRawButton(InputConstants.BTN_L1)) && !bunnyEarsUp){
//    		bunnyEarsUp = true;
//    		hanger.setBunnyEarsUp();
//    	}
    	
    	
    	if(driver.getRawButton(InputConstants.BTN_L1) && !isPressedBunny){
    		isPressedBunny = true;
    		bunnyEarsUp = !bunnyEarsUp;
    	} else if (!driver.getRawButton(InputConstants.BTN_L1)){
    		isPressedBunny = false;
    	}
    	
    	if(bunnyEarsUp){
    		hanger.setBunnyEarsUp();
    	} else if(!bunnyEarsUp){
    		hanger.setBunnyEarsDown();
    	}
    	
    	//L2, HangerBar  	
    	if(driver.getRawButton(InputConstants.BTN_L2)){
    		hanger.setHangerBarUp();
    	} else if (driver.getRawButton(InputConstants.BTN_R2)){
    		hanger.setHangerBarDown();
    	}
    	
    	SmartDashboard.putString("Time: ", "" + DriverStation.getInstance().getMatchTime());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
