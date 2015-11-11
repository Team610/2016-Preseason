package org.usfirst.frc.team610.robot.subsystems;

import org.usfirst.frc.team610.robot.constants.ElectricalConstants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
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
    
    DoubleSolenoid hangerBar;
    Solenoid bunnyEars;
    
    //CHECK PORTS
    private Hanger(){
    	bunnyEars = new Solenoid(ElectricalConstants.BUNNYEARS);
    	hangerBar = new DoubleSolenoid(ElectricalConstants.HANGER_SOL_A,ElectricalConstants.HANGER_SOL_B);
    }
    
    static Hanger instance;
    //Get public static instance of object Hanger
    public static Hanger getInstance(){
    	if (instance == null){
    		instance = new Hanger();
    	}
    	return instance;
    }
    
    //Bunny ears up
    public void setBunnyEarsUp(){
    	bunnyEars.set(true);
    }
    //Bunny ears down
    public void setBunnyEarsDown(){
    	bunnyEars.set(false);
    }
    
    //Hanger up
    public void setHangerBarUp(){
    	hangerBar.set(DoubleSolenoid.Value.kReverse);
    }
    //Hanger PULL
    public void setHangerBarDown(){
    	hangerBar.set(DoubleSolenoid.Value.kForward);
    }
    
}

