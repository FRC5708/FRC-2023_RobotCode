/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import edu.wpi.first.wpilibj.livewindow.*;
//import edu.wpi.first.wpilibj.tables.ITable;

/**
 * Class for operating the PCM (Pneumatics compressor module) The PCM
 * automatically will run in close-loop mode by default whenever a Solenoid
 * object is created. For most cases the Compressor object does not need to be
 * instantiated or used in a robot program.
 *$
 * This class is only required in cases where more detailed status or to
 * enable/disable closed loop control. Note: you cannot operate the compressor
 * directly from this class as doing so would circumvent the safety provided in
 * using the pressure switch and closed loop control. You can only turn off
 * closed loop control, thereby stopping the compressor from operating.
 */
public class Compressor {
  //private long m_pcm;

  public boolean enabled = true;
  public boolean closedLoop = true;
  public boolean pressureSwitchTriggered = false;
  //DigitalInput limitswitch;

  
  
  /**
   * Create an instance of the Compressor
   *$
   * @param pcmId The PCM CAN device ID. Most robots that use PCM will have a
   *        single module. Use this for supporting a second module other than
   *        the default.
   */
  public Compressor(int pcmId) {
    initCompressor(pcmId);
  }

  /**
   * Create an instance of the Compressor Makes a new instance of the compressor
   * using the default PCM ID (0). Additional modules can be supported by making
   * a new instance and specifying the CAN ID
   */
  public Compressor() {
    initCompressor(getDefaultSolenoidModule());

    /* 
    limitswitch = new DigitalInput(0);
  
    if (limitswitch.get()) {
      System.out.println("Photoeye Made");
        } 
        else{
          System.out.println("Photoeye Not Made");
        }
    */
  }

  private int getDefaultSolenoidModule() {
    return 0;
  }

  private void initCompressor(int module) {
    //m_table = null;
  }

  /**
   * Start the compressor running in closed loop control mode Use the method in
   * cases where you would like to manually stop and start the compressor for
   * applications such as conserving battery or making sure that the compressor
   * motor doesn't start during critical operations.
   */
  public void start() {
    setClosedLoopControl(true);
  }

  /**
   * Stop the compressor from running in closed loop control mode. Use the
   * method in cases where you would like to manually stop and start the
   * compressor for applications such as conserving battery or making sure that
   * the compressor motor doesn't start during critical operations.
   */
  public void stop() {
    setClosedLoopControl(false);
  }

  /**
   * Get the enabled status of the compressor
   *$
   * @return true if the compressor is on
   */
  public boolean enabled() {
	  return enabled;
  }

  /**
   * Get the current pressure switch value
   *$
   * @return true if the pressure is low by reading the pressure switch that is
   *         plugged into the PCM
   */
  public boolean getPressureSwitchValue() {
    return !pressureSwitchTriggered;
  }

  

  /**
   * Set the PCM in closed loop control mode
   *$
   * @param on If true sets the compressor to be in closed loop control mode
   *        otherwise normal operation of the compressor is disabled.
   */
  public void setClosedLoopControl(boolean on) {
	  closedLoop = on;
  }

  /**
   * Gets the current operating mode of the PCM
   *$
   * @return true if compressor is operating on closed-loop mode, otherwise
   *         return false.
   */
  public boolean getClosedLoopControl() {
    return closedLoop;
  }

 // @Override
  public void startLiveWindowMode() {}

//  @Override
  public void stopLiveWindowMode() {}

 // @Override
  public String getSmartDashboardType() {
    return "Compressor";
  }

  //private ITable m_table;

 // @Override
 // public void initTable(ITable subtable) {
 //   m_table = subtable;
 //   updateTable();
 // }

 // @Override
 // public ITable getTable() {
 //   return m_table;
 // }

 // @Override
 // public void updateTable() {
 //   if (m_table != null) {
 //     m_table.putBoolean("Enabled", enabled());
 //     m_table.putBoolean("Pressure Switch", getPressureSwitchValue());
 //   }
 // }
}