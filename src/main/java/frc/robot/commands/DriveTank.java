/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystemBase;
//import frc.robot.subsystems.TankDrive;
import edu.wpi.first.wpilibj.Joystick;

public class DriveTank extends Command {
  private DriveSystemBase _driveSystem;
  private Joystick _driverControl;
  private double threshold = 0.025;
  public DriveTank(DriveSystemBase driveSystem, Joystick driverControl) {
    //requires(tankDrive);
    _driveSystem = driveSystem;
    _driverControl = driverControl;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (/*_driverControl->GetRawButton(2)*/true) {
      //mainDrive().move(
      //mainDrive().driveVisionX(),
      //mainDrive().driveVisionY(),
        _driveSystem.move(
        0,//_driveSystem().driveVisionX(),
        0,//driveSystem().driveVisionY(),
        0
      );
    } else {
    double total = Math.abs(_driverControl.getRawAxis(0)) +
      Math.abs(_driverControl.getRawAxis(1)) +
      Math.abs(_driverControl.getRawAxis(2)) +
      Math.abs(_driverControl.getRawAxis(3));
    if (total > threshold*2) {
      double x = _driverControl.getRawAxis(0);
      double y = _driverControl.getRawAxis(1);
      if (Math.abs(x) < threshold){
        x = 0;
      } else if (Math.abs(x) < threshold * 7){
        x /= 2;
      }
      if (Math.abs(y) < threshold){
        y = 0;
      } else if (Math.abs(y) < threshold * 7){
        y /= 2;
      }
      _driveSystem.move(
          x/2,
          y/2,
          _driverControl.getRawAxis(2) - _driverControl.getRawAxis(3));
    } else {
      _driveSystem.move(
          _driverControl.getRawAxis(0)/2,
          _driverControl.getRawAxis(1)/2,
          _driverControl.getRawAxis(2) - _driverControl.getRawAxis(3));
    }
    if (_driverControl.getRawButton(6)) {
      _driveSystem.move(
          _driverControl.getRawAxis(0),
          _driverControl.getRawAxis(1),
          _driverControl.getRawAxis(2) - _driverControl.getRawAxis(3));
    }
    else if (_driverControl.getRawButton(5)) {
      _driveSystem.move(
          _driverControl.getRawAxis(0)/4,
          _driverControl.getRawAxis(1)/4,
          _driverControl.getRawAxis(2) - _driverControl.getRawAxis(3));
    }  }
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
