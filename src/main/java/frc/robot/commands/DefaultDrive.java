// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.WeaponSubsystem;
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
  private double m_forward;
  private double m_rotation;

  private final WeaponSubsystem m_weapon;
  private double m_horizontal;
  private double m_vertical;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public DefaultDrive(DriveSubsystem subsystem, DoubleSupplier forward, DoubleSupplier rotation,
                      WeaponSubsystem weaponSystem, DoubleSupplier horizontal, DoubleSupplier vertical) {
    m_drive = subsystem;
    m_forward = forward.getAsDouble();
    m_rotation = rotation.getAsDouble();

    m_weapon = weaponSystem;
    m_horizontal = horizontal.getAsDouble();
    m_vertical = vertical.getAsDouble();
    
    addRequirements(m_drive);
  }

  @Override
  public void execute() {
    if(OIConstants.tankSticks){

      if(!(Math.abs(m_forward) >= 0.1)){
        m_forward = 0.0;
      }
      if(!(Math.abs(m_rotation) >= 0.1)){
        m_rotation = 0.0;
      }

      if(!(Math.abs(m_horizontal) > 0.15)){
        m_forward = 0.0;
      }
      if(!(Math.abs(m_vertical) > 0.15)){
        m_rotation = 0.0;
      }

      m_drive.arcadeDrive(m_forward * DriveConstants.driveSpeed, m_rotation * DriveConstants.turnSpeed);
      m_weapon.driveWeapon(m_horizontal, m_vertical);
    }
    else{
    m_drive.arcadeDrive(m_forward, m_rotation);
    m_weapon.driveWeapon(m_horizontal, m_vertical);
    }
  }
}
