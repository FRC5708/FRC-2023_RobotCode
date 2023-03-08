

package frc.robot.subsystems;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WeaponConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class WeaponSubsystem extends SubsystemBase {
    private boolean isClosed = false;
    public DigitalInput sensor1;

    CANSparkMax verticalNeo = new CANSparkMax(WeaponConstants.weaponVerticalPort, MotorType.kBrushless);
    CANSparkMax horizontalNeo = new CANSparkMax(WeaponConstants.weaponHorizontalPort, MotorType.kBrushless);

    private final DoubleSolenoid m_weaponSolenoid = 
    new DoubleSolenoid(
        PneumaticsModuleType.CTREPCM, 
        WeaponConstants.kWeaponSolenoidPorts[0], 
        WeaponConstants.kWeaponSolenoidPorts[1]);

    public WeaponSubsystem(){
        sensor1 = new DigitalInput(0);
    }

    //will set pneumatics reverse and open
    public void openWeapon() {
        m_weaponSolenoid.set(kReverse);
        isClosed = false;
    }
    
    //will set pneumatics forward and close
    public void closeWeapon() {
        m_weaponSolenoid.set(kForward);
        isClosed = true;
    }

    //will have pneaumatics shut off, not open or closed
    public void offWeapon() {
        m_weaponSolenoid.set(kOff);
    }

    //drives verical motor
    public void driveVertical(double direction){
        //down is go up and up is go out
        direction *= WeaponConstants.weaponVerticalSpeed;
        verticalNeo.set(-direction);
    }

    //drives horizontal motor
    public void driveHorizontal(double direction){
        direction *= WeaponConstants.weaponHorizontalSpeed;
        horizontalNeo.set(direction);
    }

    public void driveWeapon(double hDirection, double vDirection){
        driveHorizontal(hDirection);
        driveVertical(vDirection);
    }

    public void toggleOpen(){
        if(isClosed){
            openWeapon();
        }
        else{
            closeWeapon();
        }
    }
} 
    