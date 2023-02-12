package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;


public class CheckTilt extends CommandBase {
   private final DriveSubsystem driveSubsystem;
   public CheckTilt (DriveSubsystem subsystem){
    driveSubsystem = subsystem;
   }
   @Override 
   public void initialize(){
    System.out.println(driveSubsystem.getTilt());
   }
   @Override
   public boolean isFinished(){return true;}
}