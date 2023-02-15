// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.wpilibj.XboxController.Button;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.CheckSensor;
import frc.robot.commands.CheckTilt;
import frc.robot.commands.ComplexAuto;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.GrabHatch;
import frc.robot.commands.HalveDriveSpeed;
import frc.robot.commands.ReleaseHatch;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.WeaponSubsystem;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final WeaponSubsystem weaponSubsystem = new WeaponSubsystem();
  
  // The autonomous routines

  // A simple auto routine that drives forward a specified distance, and then stops.
  private final Command simpleAuto =
      new DriveDistance(
          AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed, m_robotDrive);

  // A chooser for autonomous commands
  SendableChooser<Command> chooser = new SendableChooser<>();

  // The driver's controller
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // Set the default drive command to split-stick arcade drive
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new DefaultDrive(
            m_robotDrive,
            () -> (m_driverController.getLeftTriggerAxis()*-1)+m_driverController.getRightTriggerAxis(),
            () -> -m_driverController.getRightX()));
    // Add commands to the autonomous command chooser
    chooser.setDefaultOption("Simple Auto", simpleAuto);
    chooser.addOption("Complex Auto", new ComplexAuto(m_robotDrive));

    // Put the chooser on the dashboard
   SmartDashboard.putData("Auton Code", chooser);

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Says if sensor is blocked when the 'A' button is pressed.
    new JoystickButton(m_driverController, Button.kA.value).onTrue(new CheckSensor(weaponSubsystem));
    //Gives tilt angle when the 'B' button is pressed.
    new JoystickButton(m_driverController, Button.kB.value).onTrue(new CheckTilt(m_robotDrive));
    // While holding the shoulder button, drive at half speed
    new JoystickButton(m_driverController, Button.kRightBumper.value)
        .whileTrue(new HalveDriveSpeed(m_robotDrive));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */

  public Command getAutonomousCommand() {
    return chooser.getSelected();
    //return new ComplexAuto(m_robotDrive, m_hatchSubsystem);
    
  }
  
}
