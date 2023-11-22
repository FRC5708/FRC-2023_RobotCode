/*  Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.WeaponSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * A simple command that grabs a hatch with the {@link HatchSubsystem}. Written explicitly for
 * pedagogical purposes. Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 
public class CloseWeapon extends CommandBase {
  // The subsystem the command runs on
  private final WeaponSubsystem m_weaponSubsystem;

  public CloseWeapon(WeaponSubsystem subsystem) {
    m_weaponSubsystem = subsystem;
    addRequirements(m_weaponSubsystem);

    System.out.println("--Close Weapon");
  }

  @Override
  public void initialize() {
    m_weaponSubsystem.closeWeapon();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
*/