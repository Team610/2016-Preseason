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

	
	//Toggle Button variables
	private boolean isPressed = false;
	private boolean shoot = true;
	private boolean isPressedAngle = false;
	private boolean angle = false;
	private boolean isPressedFlap = false;
	private boolean flap = false;
	
	//Shooter variables
	//Wanted RPM for shooter
	private double wantedSpeed = ShooterConstants.SPEED_FAR_PYRAMID;
	
	//Shooter PID variables
	private double currentSpeed = 0;
	private double error;
	private double lastError = 0;
	private double diffError;
	private double p = ShooterConstants.P;
	
	//Shooter interval counters to allow piston to retract
	private int retractDelay = ShooterConstants.RETRACT_DELAY;
	private int shotIntervalCounter = 0;
	
	//Shooter variables to make shooter run at a regular speed
	private double lastPower = 0;
	private double power;
	private double diffPower;

	

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
		
		//Error calculation for PID
		currentSpeed = shooter.getSpeed();
		error = wantedSpeed - currentSpeed;
		diffError = lastError - error;
		lastError = error;
		
		//If current speed is an abnormal amount, ignore the value
		if (currentSpeed > wantedSpeed + 1000 || currentSpeed < -10) {
			currentSpeed = wantedSpeed;
		}

		//Calculation to get power of the motor by entering wanted RPM
		//y=(x+296)/7868.4
		power = (wantedSpeed + 296) / 7868.4 + (error * p);

		// Prevent random errors in values from affecting PID
		diffPower = Math.abs(power - lastPower);
		//If difference between last and current power is too high, ignore the power
		if (Math.abs(diffPower) > 5) {
			power = lastPower;
		}
		lastPower = power;



		//TOGGLE FOR SHOOTER WHEEL
		//Shooter always starts off spinning
		//Press A if something is wrong
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
//		if (shooter.getSpeed() < wantedSpeed - 50) {
//			allowShoot = true;
//		} else {
//			allowShoot = false;
//			// if(shotIntervalCounter <= retractDelay){
//			// shotIntervalCounter ++;
//			// }
//		}

		//HOLD DOWN BUTTON TO SHOOT
		//x (driver) b (operator)
		
		//If the piston is back
		if (shotIntervalCounter >= retractDelay) {
			
			//If a operator/driver is holding down shoot
			//and if the speed is at the wanted speed
			if ((driver.getRawButton(InputConstants.BTN_X) | 
					operator.getRawButton(InputConstants.BTN_B) )
					&& Math.abs(shooter.getSpeed() - wantedSpeed) < 100) {
				
				//FIRE PISTON
				shooter.feederOut();
				shotIntervalCounter = 0;
				
			//Questionable line of code, don't think it's right
			} else {
				shooter.feederIn();
			}
		}
		
		//Counts the time that you can shoot again.
		//Questionable line of code should go here.
		if (shotIntervalCounter <= retractDelay) {
			shotIntervalCounter++;
		}
		
			
		//OPERATOR CONTROLLS
		
		//R1 to toggle shooter angle
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
		//Put angle up on dashboard
		SmartDashboard.putBoolean("Angle Up: ", angle);
		
		//R2 to toggle flap at the back of the robot (operator only)
		if(operator.getRawButton(InputConstants.BTN_R2) && !isPressedFlap){
			flap = !flap;
			isPressedFlap = true;
		} else if(!operator.getRawButton(InputConstants.BTN_R2)){
			isPressedFlap = false;
		}
		shooter.setTrayOpen(flap);
		

		
		//OLD 2013 CODE\\
		//WHAT'S shooter.isAuston
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
		// if(!shooter.isAuston()){
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
