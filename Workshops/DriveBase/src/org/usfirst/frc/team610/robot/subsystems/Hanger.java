package org.usfirst.frc.team610.robot.subsystems;

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
    
    private Hanger(){
    	
    }
    
    private static Hanger instance;
    
    private static Hanger getInstance(){
    	if (instance == null){
    		instance = new Hanger();
    	}
    	return instance;
    }
}

