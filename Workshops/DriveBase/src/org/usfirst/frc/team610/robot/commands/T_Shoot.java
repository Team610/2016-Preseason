package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class T_Shoot extends Command {
	Shooter shooter;
	Counter optical;
	 private static double ff = 0.0022;
	    private static double setpoint = 0;
	    private static double current; 

    public T_Shoot() {
    	shooter = Shooter.getInstance();
    	optical = new Counter(); 
    		
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
