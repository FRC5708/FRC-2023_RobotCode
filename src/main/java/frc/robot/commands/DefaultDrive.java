// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;
//import frc.robot.subsystems.WeaponSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

import frc.robot.Constants.DriveConstants;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes - actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class DefaultDrive extends CommandBase {
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_forward;
  private final DoubleSupplier m_rotation;

  //private final WeaponSubsystem m_weapon;
  //private final DoubleSupplier m_horizontal;
  //private final DoubleSupplier m_vertical;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public DefaultDrive(DriveSubsystem subsystem, DoubleSupplier forward, DoubleSupplier rotation){
                     // WeaponSubsystem weaponSystem, DoubleSupplier horizontal, DoubleSupplier vertical) {
    m_drive = subsystem;
    m_forward = forward;
    m_rotation = rotation;

    //m_weapon = weaponSystem;
    //m_horizontal = horizontal;
    //m_vertical = vertical;
    
    addRequirements(m_drive);}
  

  @Override
  public void execute() {
    if(OIConstants.tankSticks){
      m_drive.tankDrive(m_forward.getAsDouble() * DriveConstants.driveSpeed, m_rotation.getAsDouble() * DriveConstants.turnSpeed);
     // m_weapon.driveWeapon(m_vertical.getAsDouble(), m_horizontal.getAsDouble());
    }
    else{
    m_drive.arcadeDrive(m_forward.getAsDouble(), m_rotation.getAsDouble());
    //m_weapon.driveWeapon(m_horizontal.getAsDouble(), m_vertical.getAsDouble());
    }
  }
}
