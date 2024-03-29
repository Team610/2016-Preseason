package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.InputConstants;
import org.usfirst.frc.team610.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class T_Kaj extends Command {

	Joystick driver;
	OI oi;
	DriveTrain drivetrain;
	double x,y,leftSpeed,rightSpeed;

    public T_Kaj() {
    	oi = OI.getInstance();

    	driver = oi.getDriver();
    	drivetrain = DriveTrain.getInstance();
    	
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	 * TODO: CHECK AXIS
    	 * TODO: CUBE? 
    	 */
    	x = driver.getRawAxis(InputConstants.AXIS_RIGHT_X);
    	y = driver.getRawAxis(InputConstants.AXIS_LEFT_Y);
    	leftSpeed = y-x;
    	rightSpeed = y+x;
    	
    	drivetrain.setLeftSpeed(leftSpeed);
    	drivetrain.setRightSpeed(rightSpeed);
    	
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
