package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.subsystems.Hanger;
import org.usfirst.frc.team610.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class T_Hang extends Command {

	Joystick driver;
	Hanger hanger;
	
    public T_Hang() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	driver = OI.getInstance().getDriver();
    	hanger = Hanger.getInstance();
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//L2, BunnyEars
    	if(driver.getRawButton(7)){
    		
    	}
    	
    	//L1, HangerBar
    	if(driver.getRawButton(8)){
    		
    	}
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
