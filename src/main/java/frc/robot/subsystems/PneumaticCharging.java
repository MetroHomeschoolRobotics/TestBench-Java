/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;

/**
 * Add your docs here.
 */
public class PneumaticCharging extends Subsystem {
  // Put methods for controlling this subsystem
  //static pneumoCharger = RobotMap.pneumoCharger.get();
  // here. Call these from Commands.

  private Compressor _compressor;

  public PneumaticCharging(Compressor compressor){
    _compressor = compressor;
  }
   
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
  public void charge() {
    _compressor.setClosedLoopControl(true);
  }
  
  public void StopCharging() {
    _compressor.setClosedLoopControl(false);
  }
  
  public boolean chargeComplete() {
    return _compressor.getPressureSwitchValue();
  }
}
