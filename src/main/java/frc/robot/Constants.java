// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.filter.SlewRateLimiter;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    public static final int kLeftMotor1Port = 9;
    public static final int kLeftMotor2Port = 10;
    public static final int kRightMotor1Port = 11;
    public static final int kRightMotor2Port = 12;

    //public static final double driveSpeed = 0.6;

    public static final double ticksPerInch = 1164.23;

    public static final int[] kLeftEncoderPorts = new int[] {0, 1};
    public static final int[] kRightEncoderPorts = new int[] {2, 3};
    public static final boolean kLeftEncoderReversed = false;
    public static final boolean kRightEncoderReversed = true;

    public static final double driveSpeed = 0.5;
    public static final double turnSpeed = 0.15;
    
    public static final double fwdSkewValue = 2;
    public static final double turnSkewValue = 3;
  }

  public static final class WeaponConstants {
    public static final int weaponHorizontalPort = 13;
    public static final int weaponVerticalPort = 14;
    public static final int kWeaponSolenoidModule = 0;
    public static final int[] kWeaponSolenoidPorts = new int[] {0, 1};
    public static final double weaponHorizontalSpeed = 0.7;
    public static final double weaponVerticalSpeed = 0.3;

    public static final double horizontalSkewValueWeapon = 0.5;
    public static final double verticalSkewValueWeapon = 0.5;
  }

  public static final class AutoConstants {
    public static final double kAutoDriveDistanceInches = 36;
    public static final double kAutoBackupDistanceInches = 20;
    public static final double kAutoDriveSpeed = 0.5;
  }

  public static final class OIConstants {
    public static final boolean tankSticks = false;
    public static final int kDriverControllerPortDrive = 0;
    public static final int kDriverControllerPortWeapon = 1;
  }
}
