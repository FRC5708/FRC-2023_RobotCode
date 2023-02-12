package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.Constants.DriveConstants;

public class RotateDrive extends CommandBase {
  private final DriveSubsystem m_drive;
  private final double degrees;

  /**
   * Creates a new DriveDistance.
   *
   * @param inches The number of inches the robot will drive
   * @param speed The speed at which the robot will drive/
   * @param drive The drive subsystem on which this command will run
   */
  public RotateDrive(double degrees, DriveSubsystem drive) {
    this.degrees = degrees;
    m_drive = drive;
    addRequirements(m_drive);
  }

  @Override
  public void initialize() {
    //m_drive.resetEncoders();
    //System.out.println("~START~\n--Sensor Position: "+m_drive.getEncoderPosition());
    m_drive.resetSpin();
    m_drive.arcadeDrive(0, .3);
  }

  @Override
  public void execute() {
    m_drive.arcadeDrive(0, .3);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, .3);
    //System.out.println("~END~\n--Sensor Position: "+m_drive.getEncoderPosition());
  }

  @Override
  public boolean isFinished() {
    return Math.abs(m_drive.getSpin()) >= degrees;
  }
}
