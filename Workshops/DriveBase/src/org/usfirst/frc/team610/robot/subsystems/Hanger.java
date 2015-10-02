package org.usfirst.frc.team610.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hanger extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    DoubleSolenoid bunnyEars, hangerBar;
    
    //CHECK PORTS
    private Hanger(){
    	bunnyEars = new DoubleSolenoid(0,1);
    	hangerBar = new DoubleSolenoid(2,3);
    }
    
    private static Hanger instance;
    
    public static Hanger getInstance(){
    	if (instance == null){
    		instance = new Hanger();
    	}
    	return instance;
    }
    
    public void setBunnyEarsUp(){
    	bunnyEars.set(DoubleSolenoid.Value.kForward);
    }
    public void setBunnyEarsDown(){
    	bunnyEars.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void setHangerBarUp(){
    	hangerBar.set(DoubleSolenoid.Value.kForward);
    }
    public void setHangerBarDown(){
    	hangerBar.set(DoubleSolenoid.Value.kReverse);
    }
    
}

