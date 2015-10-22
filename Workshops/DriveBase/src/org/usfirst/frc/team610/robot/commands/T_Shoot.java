package org.usfirst.frc.team610.robot.commands;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.InputConstants;
import org.usfirst.frc.team610.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class T_Shoot extends Command {
	Shooter shooter;
	Counter optical;
	OI oi;
	Joystick driver;
	Joystick operator;
	private static double ff = 0.0022;
	private static double setpoint = 0;
	private static double current;
	private int extendCount = 0;
	private int settleDownCount = 0;
	private int retractDelay = 10;
	private static double delay = 10;
	private int frisbees = 0;

	public T_Shoot() {
		oi = OI.getInstance();
		shooter = Shooter.getInstance();
		optical = new Counter();
		driver = oi.getDriver();

		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
    	
    	if(driver.getRawButton(InputConstants.BTN_A))
    		shooter.setMotors(1);
    	else if(driver.getRawButton(InputConstants.BTN_B))
    		shooter.setMotors(-1);
    	else
    		shooter.setMotors(0);
    	
//    	if (optical != null) {
//            current = (60 / (optical.getPeriod() * (8.0 / 7.0)));
//        } else {
//            current = 300;
//        }
//    	
//    	 if (setpoint - current < -100 && (oi.getOperator().getRawButton(InputConstants.r2Button))) {
//             //This should always start off as 0 since it will reset when the shooter is recovering.
//             if (extendCount == 1) {
//                 //Fire and log
//                 if(!shooter.isAuton()){
//                 pneumatics.setFeeder(true);
//                 }
//                 Logger.getLogger().debug("Speed: " + current);
//             }
//             //Keep counting up with the extended count until we reach retract delay.
//             if (extendCount >= retractDelay) {
//                 //At that point retract.
//                 if(!shooter.isAuton()){
//                
//                 pneumatics.setFeeder(false);
//                 }
//                 extendCount = 0;
//             } else {
//                 extendCount++;
//             }
//             //While we are at the right speed, use ff while the disc is going through the shooter.
//             outputFinal = ff * (setpoint + 200);
//
//
//         } //If we are within 300 of our desired shooting speed and we are holding the trigger.
//         //If we are within 300 of our desired shooting speed and we are holding the trigger.
//         else if (shooterAngle == 1 && setpoint - current < 300 && (oi.getOperator().getRawButton(InputConstants.r2Button))) {
//             //settleDownCount is the counter for when we want to stop at our coasting speed and then ramp up again.
//             System.out.println("BAD");
//             if (settleDownCount >= delay) {
//
//                 //Reset our extended count
//                 extendCount = 0;
//                 //Full blast since the delay is over. This will get us into the firing if statement.
//                 outputFinal = 12;
//
//             } else {
//                 //If we have not yet finished the delay, then hold at the coasting speed until we are ready to fire.
//                 outputFinal = ff * setpoint;
//                 extendCount = 0;
//
//                 settleDownCount++;
//             }
//
//         } //If we are not up to speed and the trigger is held (Auto mode) 
//         //The shooter is recovering from a shot.
//         else if (setpoint - current > -100 && (oi.getOperator().getRawButton(InputConstants.r2Button))) {
//             //Reset our settledown counter
//             settleDownCount = 0;
//             //Even though the wheel has slowed down, it doesn't mean our shark fin extend delay has not yet ended.
//             //Check if it has ended and retract when it has.
//             if (extendCount >= retractDelay) {
//                   if(!shooter.isAuton()){
//                 pneumatics.setFeeder(false);
//                   }
//             } else {
//                 extendCount++;
//             }
//             //Run the motors at full power to ensure the fastest possible recovery time.
//             outputFinal = 12;
//         } //When the trigger isn't pressed or when we just want to coast.
//         else {
//             //Retract the feeder if the delay is over.
//             if (extendCount >= retractDelay) {
//                   if(!shooter.isAuton()){
//                 pneumatics.setFeeder(false);
//                   }
//             } else {
//                 extendCount++;
//             }
//             //Hold at 100 below our shooting speed
//             outputFinal = ff * setpoint;
//         }
//    	
//    	
    	
    	
    	
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
