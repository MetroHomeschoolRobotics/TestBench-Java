/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.*;

/**
 * Add your docs here.
 */
public class SolenoidSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  DoubleSolenoid sol1 = new DoubleSolenoid(1, 2);
  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(Command SetSol());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void solenoidControl(double value) {
    if (value==1) {
    sol1.set(DoubleSolenoid.Value.kForward);
    }
    if (value==-1) {
      sol1.set(DoubleSolenoid.Value.kReverse);
      }
    if (value==0) {
      sol1.set(DoubleSolenoid.Value.kOff);
      }
  }
}
