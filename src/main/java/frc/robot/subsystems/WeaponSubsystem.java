

package frc.robot.subsystems;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kForward;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kReverse;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.kOff;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.WeaponConstants;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.filter.SlewRateLimiter;

public class WeaponSubsystem extends SubsystemBase {
    private boolean isClosed = false;
    public DigitalInput sensor1;
    public DigitalInput hal1;
    public DigitalInput hal2;

    CANSparkMax verticalNeo = new CANSparkMax(WeaponConstants.weaponVerticalPort, MotorType.kBrushless);
    CANSparkMax horizontalNeo = new CANSparkMax(WeaponConstants.weaponHorizontalPort, MotorType.kBrushless);

    SlewRateLimiter horizontalFilter = new SlewRateLimiter(WeaponConstants.horizontalSkewValueWeapon);
    SlewRateLimiter verticalFilter = new SlewRateLimiter(WeaponConstants.verticalSkewValueWeapon);

    private final DoubleSolenoid m_weaponSolenoid = 
    new DoubleSolenoid(
        PneumaticsModuleType.CTREPCM, 
        WeaponConstants.kWeaponSolenoidPorts[0], 
        WeaponConstants.kWeaponSolenoidPorts[1]);

    public WeaponSubsystem(){
        sensor1 = new DigitalInput(0);
        hal1 = new DigitalInput(1);
        hal2 = new DigitalInput(2);


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
       // direction *= WeaponConstants.weaponVerticalSpeed;
       // verticalNeo.set(-direction);

        if (!(hal1.get() == true)){
            System.out.println("YAY!! 1");
        }
        else if (!(hal2.get() == true)){
            System.out.println("YAY! 2");
        }

        if (hal1.get() == false && direction < 0){
            verticalNeo.set(0.0);
        }
        else if (hal2.get() == false && direction > 0){
            verticalNeo.set(0.0);
        }
        else {
            direction *= WeaponConstants.weaponVerticalSpeed;
            verticalNeo.set(-direction);
        }

        //System.out.println("Hal 1 = " + hal1.get());
        //System.out.println("Hal 2 = " + hal2.get());
    }

    //drives horizontal motor
    public void driveHorizontal(double direction){
        //moves arm in and out
        if(!sensor1.get() && direction > 0){
            horizontalNeo.set(0.0);

        }
        else {
            direction *= WeaponConstants.weaponHorizontalSpeed;
            horizontalNeo.set(direction);
        }
        ;

        
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
    