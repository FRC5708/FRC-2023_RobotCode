// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.AutoConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.commands.BalanceOnBeamCommand;

//import frc.robot.subsystems.WeaponSubsystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


/** A complex auto command that drives forward, releases a hatch, and then drives backward. */
public class BalanceAutoBlue extends SequentialCommandGroup {
  /**
   * Creates a new BalanceAuto.
   *
   * @param drive The drive subsystem this command will run on
   * @param hatch The hatch subsystem this command will run on
   */
  public BalanceAutoBlue(DriveSubsystem drive) {
    System.out.println("***Balancing***");
    addCommands(
        // Drive forward the specified distance 
        //Drives the distance up to the balancing beam
        new DriveDistance(80.69, AutoConstants.kAutoDriveSpeedBlue, drive),

        new BalanceOnBeamCommand(drive)
        //Drives the distance up to the middle of the balancing beam (not tested if it will actually go there)
        //new DriveDistance(96.75, AutoConstants.kAutoDriveSpeed, drive)
   
    );
  }
}
