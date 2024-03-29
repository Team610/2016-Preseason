package org.usfirst.frc.team610.robot.commands;

import java.io.IOError;

import org.usfirst.frc.team610.robot.OI;
import org.usfirst.frc.team610.robot.constants.InputConstants;
import org.usfirst.frc.team610.robot.constants.ShooterConstants;
import org.usfirst.frc.team610.robot.subsystems.Hanger;
import org.usfirst.frc.team610.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class T_Shoot extends Command {
	Shooter shooter;
	Counter optical;
	OI oi;
	Joystick driver;
	Joystick operator;
	SmartDashboard dashboard;

	private int retractDelay = ShooterConstants.RETRACT_DELAY;

	boolean isPressed = false;
	boolean shoot = true;
	boolean isPressedAngle = false;
	boolean angle = false;
	boolean isPressedFlap = false;
	boolean flap = false;
	double wantedSpeed = ShooterConstants.SPEED_FAR_PYRAMID;
	double currentSpeed = 0;
	double error;
	double lastError = 0;
	double diffError;
	double p = ShooterConstants.P;
	// double d = 0.00001;

	String dashAngle;
	
	double lastPower = 0;
	double power;
	double diffPower;

	boolean allowShoot = false;
	int shotIntervalCounter = 0;
	boolean isPressedP = false;

	boolean isPressedY = false;
	boolean isPressedX = false;
	
	public T_Shoot() {
		oi = OI.getInstance();
		shooter = Shooter.getInstance();
		operator = oi.getOperator();
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

		// Feed forward PID for the shooter
		currentSpeed = shooter.getSpeed();

		error = wantedSpeed - currentSpeed;

		diffError = lastError - error;

		lastError = error;

		if (currentSpeed > wantedSpeed + 1000 || currentSpeed < -10) {
			currentSpeed = wantedSpeed;
		}


		power = (wantedSpeed + 296) / 7868.4 + (error * p);
		// - (diffError * d)

		// Prevent randomly high values from affecting pid

		diffPower = Math.abs(power - lastPower);

		if (Math.abs(diffPower) > 5) {
			power = lastPower;
		}

		lastPower = power;



		// Toggle for spinning shooter
		if (driver.getRawButton(InputConstants.BTN_A) && !isPressed) {
			isPressed = true;
			shoot = !shoot;
		} else if (!driver.getRawButton(InputConstants.BTN_A)) {
			isPressed = false;
		}

		if (shoot) {
			shooter.setMotors(power);
		} else {
			shooter.setMotors(0);
		}

		// Puts speed on smart dashboard
		SmartDashboard.putNumber("Speed", shooter.getSpeed());

		// Hold down x to shoot
		if (shooter.getSpeed() < wantedSpeed - 50) {
			allowShoot = true;
		} else {
			allowShoot = false;
			// if(shotIntervalCounter <= retractDelay){
			// shotIntervalCounter ++;
			// }
		}


		if (shotIntervalCounter >= retractDelay) {
			if ((driver.getRawButton(InputConstants.BTN_X) | 
					operator.getRawButton(InputConstants.BTN_B) )
					&& Math.abs(shooter.getSpeed() - wantedSpeed) < 100) {
				shooter.feederOut();
				shotIntervalCounter = 0;
			} else {
				shooter.feederIn();
			}
		}
		if (shotIntervalCounter < retractDelay) {
			shotIntervalCounter++;
			
		}
		
			
		//OPERATOR CONTROLLS
		if((operator.getRawButton(InputConstants.BTN_R1) 
				| driver.getRawButton(InputConstants.BTN_R1)) 
				&& !isPressedAngle){
			isPressedAngle = true;
			angle = !angle;
		} else if(!(operator.getRawButton(InputConstants.BTN_R1)
				| driver.getRawButton(InputConstants.BTN_R1))){
			isPressedAngle = false;
		}
		shooter.setAngleUp(angle);
		
		if(operator.getRawButton(InputConstants.BTN_R2) && !isPressedFlap){
			flap = !flap;
			isPressedFlap = true;
		} else if(!operator.getRawButton(InputConstants.BTN_R2)){
			isPressedFlap = false;
		}
		shooter.setTrayOpen(flap);
		
		//Trimming for shooter
		if(operator.getRawButton(InputConstants.BTN_Y) && !isPressedY){
			wantedSpeed += 25;
			isPressedY = true;
		} else if (!(operator.getRawButton(InputConstants.BTN_Y))){
			isPressedY = false;
		}
		if(operator.getRawButton(InputConstants.BTN_X) && !isPressedX){
			wantedSpeed -= 25;
			isPressedX = true;
		} else if (!(operator.getRawButton(InputConstants.BTN_X))){
			isPressedX = false;
		}
		if(operator.getRawButton(InputConstants.BTN_A)){
			wantedSpeed = ShooterConstants.SPEED_FAR_PYRAMID;
		}
		
		if(angle){
			dashAngle = "Up";
		} else if (!angle){
			dashAngle = "Down";
		}
		

		SmartDashboard.putNumber("P: ", p);
		SmartDashboard.putNumber("Power: ", power);
		SmartDashboard.putString("Wanted Speed: ", "" + wantedSpeed);
		SmartDashboard.putString("Shooter Angle: ", dashAngle);
		System.out.println(operator.getRawButton(InputConstants.BTN_R1));

		// System.out.println(shooter.getOptical());
		// if (optical != null) {
		// current = (60 / (optical.getPeriod() * (8.0 / 7.0)));
		// } else {
		// current = 300;
		// }
		//
		// if (setpoint - current < -100 &&
		// (oi.getOperator().getRawButton(InputConstants.r2Button))) {
		// //This should always start off as 0 since it will reset when the
		// shooter is recovering.
		// if (extendCount == 1) {
		// //Fire and log
		// if(!shooter.isAuton()){
		// pneumatics.setFeeder(true);
		// }
		// Logger.getLogger().debug("Speed: " + current);
		// }
		// //Keep counting up with the extended count until we reach retract
		// delay.
		// if (extendCount >= retractDelay) {
		// //At that point retract.
		// if(!shooter.isAuton()){
		//
		// pneumatics.setFeeder(false);
		// }
		// extendCount = 0;
		// } else {
		// extendCount++;
		// }
		// //While we are at the right speed, use ff while the disc is going
		// through the shooter.
		// outputFinal = ff * (setpoint + 200);
		//
		//
		// } //If we are within 300 of our desired shooting speed and we are
		// holding the trigger.
		// //If we are within 300 of our desired shooting speed and we are
		// holding the trigger.
		// else if (shooterAngle == 1 && setpoint - current < 300 &&
		// (oi.getOperator().getRawButton(InputConstants.r2Button))) {
		// //settleDownCount is the counter for when we want to stop at our
		// coasting speed and then ramp up again.
		// System.out.println("BAD");
		// if (settleDownCount >= delay) {
		//
		// //Reset our extended count
		// extendCount = 0;
		// //Full blast since the delay is over. This will get us into the
		// firing if statement.
		// outputFinal = 12;
		//
		// } else {
		// //If we have not yet finished the delay, then hold at the coasting
		// speed until we are ready to fire.
		// outputFinal = ff * setpoint;
		// extendCount = 0;
		//
		// settleDownCount++;
		// }
		//
		// } //If we are not up to speed and the trigger is held (Auto mode)
		// //The shooter is recovering from a shot.
		// else if (setpoint - current > -100 &&
		// (oi.getOperator().getRawButton(InputConstants.r2Button))) {
		// //Reset our settledown counter
		// settleDownCount = 0;
		// //Even though the wheel has slowed down, it doesn't mean our shark
		// fin extend delay has not yet ended.
		// //Check if it has ended and retract when it has.
		// if (extendCount >= retractDelay) {
		// if(!shooter.isAuton()){
		// pneumatics.setFeeder(false);
		// }
		// } else {
		// extendCount++;
		// }
		// //Run the motors at full power to ensure the fastest possible
		// recovery time.
		// outputFinal = 12;
		// } //When the trigger isn't pressed or when we just want to coast.
		// else {
		// //Retract the feeder if the delay is over.
		// if (extendCount >= retractDelay) {
		// if(!shooter.isAuton()){
		// pneumatics.setFeeder(false);
		// }
		// } else {
		// extendCount++;
		// }
		// //Hold at 100 below our shooting speed
		// outputFinal = ff * setpoint;
		// }
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
