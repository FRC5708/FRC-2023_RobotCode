

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class WeaponSubsystem extends SubsystemBase {
    public DigitalInput sensor1;


    
    public WeaponSubsystem(){
        sensor1 = new DigitalInput(0);
    }
} 
    