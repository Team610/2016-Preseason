package org.usfirst.frc.team610.robot.subsystems;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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
	DoubleSolenoid tray;

	public static Shooter getInstance(){
		if(inst == null){
			inst = new Shooter();
		}
		return inst;
	}
	 
	 
	  private Shooter(){
		  shooter1 = new Victor(1);
		  shooter2 = new Victor(2);
		  feeder = new DoubleSolenoid(0,1);
		  tray = new DoubleSolenoid(2,3);
	  }
	  
	  //Push feeder piston out
	  public void feederOut(){
		  feeder.set(DoubleSolenoid.Value.kForward);
	  }
	  public void feederIn(){
		  feeder.set(DoubleSolenoid.Value.kReverse);
	  }
	 
	  public void setSpeed(double rpm){
		  
	  }
	  
	  
	  //WHICH ONE IS WHICH, CHECK PISTONS
	  public void trayOpen(){
		  tray.set(DoubleSolenoid.Value.kForward);
	  }
	  
	  public void trayClose(){
		  tray.set(DoubleSolenoid.Value.kReverse);
	  }

	  public boolean optical(){
		  return optical.get();
	  }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

