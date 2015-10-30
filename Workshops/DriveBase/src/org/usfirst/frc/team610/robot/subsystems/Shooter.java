package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Victor shooter1,shooter2;
	DigitalInput optical; 
	boolean opt = false;
	static Shooter instance;
	public static Counter opticalCounter;
	double speed = 0.0;
	Solenoid feeder;
	DoubleSolenoid tray;
	DoubleSolenoid angle;
	
	
	
	//Relay light;

	public static Shooter getInstance(){
		if(instance == null){
			instance = new Shooter();
		}
		return instance;
	}
	 
	 
	  private Shooter(){
		  shooter1 = new Victor(ElectricalConstants.SHOOTER_ONE);
		  shooter2 = new Victor(ElectricalConstants.SHOOTER_TWO);
		  feeder = new Solenoid(ElectricalConstants.FEED);
		  tray = new DoubleSolenoid(ElectricalConstants.TRAY_SOLENOID_A, ElectricalConstants.TRAY_SOLENOID_B);
		  opticalCounter = new Counter(ElectricalConstants.OPTICAL);
		  opticalCounter.setMaxPeriod(5);
		  opticalCounter.setSemiPeriodMode(true);
		  opticalCounter.reset();
		  angle = new DoubleSolenoid(ElectricalConstants.SHOOTER_ANGLE_A, ElectricalConstants.SHOOTER_ANGLE_B);
//		  optical = new DigitalInput(ElectricalConstants.OPTICAL);
		  
		  //light = new Relay(ElectricalConstants.LEDRelay);
	  }
	  
	  public double getSpeed(){
		  return (7.5/opticalCounter.getPeriod());
	  }
	  
	  public double getCounter(){
		  return (opticalCounter.get());
	  }
	  
	  public double getPeriod(){
		  return opticalCounter.getPeriod();
	  }
	  
	  //Not sure if necessary
	  public void setMotors(double speed){
		  shooter1.set(speed);
		  shooter2.set(speed);
	  }
	  
	  
	  //Push feeder piston out
	  public void feederOut(){
		  feeder.set(true);
	  }
	  public void feederIn(){
		  feeder.set(false);
	  }
	 
	  public void setSpeed(double rpm){
		  // Made negative so that we don't shoot backwards! -Mr. Lim
		  //ShooterPIDCommand.setSetpoint(rpm);
	  }
	  
	  public void setAngleUp(boolean up){
		  if(up){
			  angle.set(DoubleSolenoid.Value.kReverse);
		  } else if(!up) {
			  angle.set(DoubleSolenoid.Value.kForward);
		  }
	  }
	  
	  //WHICH ONE IS WHICH, CHECK PISTONS
	  public void trayOpen(){
		  tray.set(DoubleSolenoid.Value.kForward);
	  }
	  
	  public void trayClose(){
		  tray.set(DoubleSolenoid.Value.kReverse);
	  }

	  public boolean getOptical(){
		  return optical.get();
	  }
	  
//	  public void setLight(boolean on){
//		  if(on){
//			  light.set(Relay.Value.kForward);
//		  }
//		  else{
//			  light.set(Relay.Value.kReverse);
//		  }
//	  }
	  
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

