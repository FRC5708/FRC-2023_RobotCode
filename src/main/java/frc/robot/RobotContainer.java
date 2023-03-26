// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.wpilibj.XboxController.Button;
import static edu.wpi.first.wpilibj.Joystick.ButtonType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.BackwardAuto;
import frc.robot.commands.CheckSensor;
import frc.robot.commands.CheckTilt;
import frc.robot.commands.BalanceAuto;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.HalveDriveSpeed;
import frc.robot.commands.OpenWeapon;
import frc.robot.commands.ToggleWeapon;
import frc.robot.commands.CloseWeapon;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.WeaponSubsystem;
//import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
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
  private final Command simpleAuto = null;
      //new DriveDistance(
      //    AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed, m_robotDrive);

  // A chooser for autonomous commands
  SendableChooser<Command> chooser = new SendableChooser<>();

  // The driver's controllers
  XboxController m_driverController;
  XboxController m_weaponController;

  Joystick m_JoystickLeft;
  Joystick m_JoystickRight;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    if(!OIConstants.tankSticks)
    {
      m_driverController =  new XboxController(OIConstants.kDriverControllerPortDrive);
      m_weaponController = new XboxController(OIConstants.kDriverControllerPortWeapon);
    }
    else
    {
      m_JoystickLeft = new Joystick(OIConstants.kDriverControllerPortDrive);
      m_JoystickRight  = new Joystick(OIConstants.kDriverControllerPortWeapon);
    }

    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
    // Set the default drive command to split-stick arcade drive
    if(!OIConstants.tankSticks){
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new DefaultDrive(
            m_robotDrive,
            () -> (m_driverController.getLeftTriggerAxis()*-1)+m_driverController.getRightTriggerAxis(),
            () -> -m_driverController.getLeftX(),
            weaponSubsystem,
            () -> m_weaponController.getLeftY(),
            () -> m_weaponController.getRightY()));
    }
    else{
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the  right.
      new DefaultDrive(
          m_robotDrive,
          () -> m_JoystickLeft.getY(),
          () -> m_JoystickRight.getY(),
          weaponSubsystem,
          () -> m_JoystickLeft.getX(),
          () -> m_JoystickRight.getX()));
    }
    // Add commands to the autonomous command chooser
    chooser.setDefaultOption("Nothing", simpleAuto);
    chooser.addOption("Balance", new BalanceAuto(m_robotDrive));
    chooser.addOption("Drives Backward", new BackwardAuto(m_robotDrive));

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
    if(OIConstants.tankSticks){
      new JoystickButton(m_JoystickLeft, ButtonType.kTrigger.value).onTrue(new HalveDriveSpeed(m_robotDrive));
      new JoystickButton(m_JoystickRight, ButtonType.kTrigger.value).onTrue(new ToggleWeapon(weaponSubsystem));
    }
    else{
    // Says if sensor is blocked when the 'A' button is pressed.
    new JoystickButton(m_driverController, Button.kA.value).onTrue(new CheckSensor(weaponSubsystem));
    //Gives tilt angle when the 'B' button is pressed.
    new JoystickButton(m_driverController, Button.kB.value).onTrue(new CheckTilt(m_robotDrive));
    // While holding the Y, drive at half speed
    new JoystickButton(m_driverController, Button.kY.value)
        .whileTrue(new HalveDriveSpeed(m_robotDrive));
    //new JoystickButton(m_driverController, )

    
    new JoystickButton(m_weaponController, Button.kRightBumper.value)
    .onTrue(new OpenWeapon(weaponSubsystem));

    new JoystickButton(m_weaponController, Button.kLeftBumper.value)
    .onTrue(new CloseWeapon(weaponSubsystem));
    }
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
