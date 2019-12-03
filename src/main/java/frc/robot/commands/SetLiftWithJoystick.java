/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Lift;
import edu.wpi.first.wpilibj.Joystick;

public class SetLiftWithJoystick extends Command {
  private Lift _lift;
  private Joystick _manipulatorControl;
  private double _threshold = 0.1;
  public SetLiftWithJoystick(Lift lift, Joystick manipulatorControl) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    _lift = lift;
    _manipulatorControl = manipulatorControl;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double rate = 0-_manipulatorControl.getRawAxis(5);
    if(Math.abs(rate)<_threshold){
      rate = 0;
    }
    _lift.setLiftMotor(rate);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    _lift.setLiftMotor(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
