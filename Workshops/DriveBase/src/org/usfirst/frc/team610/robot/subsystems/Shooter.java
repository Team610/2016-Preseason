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
	static Shooter inst;
	private static Counter opticall;
	double speed = 0.0;
	DoubleSolenoid feeder;
	Solenoid tray;
	
	
	
	//Relay light;

	public static Shooter getInstance(){
		if(inst == null){
			inst = new Shooter();
		}
		return inst;
	}
	 
	 
	  private Shooter(){
		  shooter1 = new Victor(ElectricalConstants.shooterOne);
		  shooter2 = new Victor(ElectricalConstants.shooterTwo);
		  feeder = new DoubleSolenoid(ElectricalConstants.feedA,ElectricalConstants.feedB);
		  tray = new Solenoid(ElectricalConstants.traySolenoid);
		  opticall = new Counter(1);
		  opticall.setMaxPeriod(5);
		  opticall.setSemiPeriodMode(true);
		  
		  //light = new Relay(ElectricalConstants.LEDRelay);
	  }
	  
	  public double getSpeed(){
		  return (-7.5/opticall.getPeriod());
	  }
	  
	  //Not sure if necessary
	  public void setMotors(double v){
		  shooter1.set(v);
		  shooter2.set(v);
	  }
	  
	  
	  
	  
	  
	  //Push feeder piston out
	  public void feederOut(){
		  feeder.set(DoubleSolenoid.Value.kForward);
	  }
	  public void feederIn(){
		  feeder.set(DoubleSolenoid.Value.kReverse);
	  }
	 
	  public void setSpeed(double rpm){
		  // Made negative so that we don't shoot backwards! -Mr. Lim
		  //ShooterPIDCommand.setSetpoint(rpm);
	  }
	  
	  
	  //WHICH ONE IS WHICH, CHECK PISTONS
	  public void trayOpen(){
		  tray.set(false);
	  }
	  
	  public void trayClose(){
		  tray.set(true);
	  }

	  public boolean optical(){
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

