

package frc.robot.subsystems;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WeaponConstants;


public class WeaponSubsystem extends SubsystemBase {
    public DigitalInput sensor1;
    private final DoubleSolenoid m_weaponSolenoid = 
    new DoubleSolenoid(
        PneumaticsModuleType.CTREPCM, 
        WeaponConstants.kWeaponSolenoidPorts[0], 
        WeaponConstants.kWeaponSolenoidPorts[1]);

    //will set pneumatics reverse and open
    public void openWeapon() {
        m_weaponSolenoid.set(kReverse);
    }
    
    //will set pneumatics forward and close
    public void closeWeapon() {
        m_weaponSolenoid.set(kForward);
    }

    //will have pneaumatics shut off, not open or closed
    public void offWeapon() {
        m_weaponSolenoid.set(kOff);
    }
    

    public WeaponSubsystem(){
        sensor1 = new DigitalInput(0);
    }
} 
    