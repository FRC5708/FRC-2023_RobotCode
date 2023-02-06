// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
<<<<<<< Updated upstream
=======
import edu.wpi.first.wpilibj.motorcontrol.*;
>>>>>>> Stashed changes
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Motor Controller imports
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.sensors.CANCoder;
<<<<<<< Updated upstream
=======
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
>>>>>>> Stashed changes
import com.ctre.phoenixpro.hardware.CANcoder;

public class DriveSubsystem extends SubsystemBase {
  // The motors on the left side of the drive.
  private WPI_TalonFX leftMotor1 = new WPI_TalonFX(DriveConstants.kLeftMotor1Port);
  private WPI_TalonFX leftMotor2 = new WPI_TalonFX(DriveConstants.kLeftMotor2Port);
  private final MotorControllerGroup m_leftMotors =
      new MotorControllerGroup(
          leftMotor1,
          leftMotor2);

  // The motors on the right side of the drive.
  private WPI_TalonFX rightMotor1 = new WPI_TalonFX(DriveConstants.kRightMotor1Port);
  private WPI_TalonFX rightMotor2 = new WPI_TalonFX(DriveConstants.kRightMotor2Port);
  private final MotorControllerGroup m_rightMotors =
      new MotorControllerGroup(
<<<<<<< Updated upstream
          rightMotor1,
          rightMotor2);

=======
          new WPI_TalonFX(DriveConstants.kRightMotor1Port),
          new WPI_TalonFX(DriveConstants.kRightMotor2Port));

  WPI_TalonFX feedback = new WPI_TalonFX(3);
 
  
>>>>>>> Stashed changes
  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  
  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotors.setInverted(true);

    leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    leftMotor2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    
    rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    rightMotor2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
    }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {
    leftMotor1.setSelectedSensorPosition(0);
    leftMotor2.setSelectedSensorPosition(0);

    rightMotor1.setSelectedSensorPosition(0);
    rightMotor2.setSelectedSensorPosition(0);
  }

  public double getDistance(WPI_TalonFX encoder) {
    return Math.abs(encoder.getSelectedSensorPosition() / DriveConstants.ticksPerInch);
  }

  public double getEncoderPosition(){
    return rightMotor1.getSelectedSensorPosition();
  }

  /**
   * Gets the average distance of the TWO encoders.
   *
   * @return the average of the TWO encoder readings
   */
  public double getAverageEncoderDistance() {
    double average = (getDistance(leftMotor1) + getDistance(leftMotor2)
     + getDistance(rightMotor1) + getDistance(rightMotor2)) / 4.0;
    System.out.println("~~ Average Distance ~~\n" + average);
    return average;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public WPI_TalonFX getLeftEncoder() {
    return leftMotor1;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public WPI_TalonFX getRightEncoder() {
    return rightMotor1;
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }
}
