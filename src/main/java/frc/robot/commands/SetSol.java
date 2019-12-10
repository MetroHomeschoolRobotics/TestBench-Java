/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.OI;
import frc.robot.subsystems.SolenoidSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Joystick;

public class SetSol extends Command {
  private Joystick _driverControl;
  private SolenoidSubsystem _solenoidSubsystem;
  public SetSol(SolenoidSubsystem solenoidSubsystem) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //requires(solenoidSubsystem);
    _solenoidSubsystem = solenoidSubsystem;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double setting = _driverControl.getRawAxis(7);
    _solenoidSubsystem.solenoidControl(setting);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
