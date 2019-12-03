/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.DriveSystemBase;
//import frc.robot.subsystems.TankDrive;


public class DriveTank extends Command {
  private DriveSystemBase _tankDrive;
  private Joystick _driverControl;
  private Joystick _manipulatorControl;

  double _threshold = 0.01;
  public DriveTank(DriveSystemBase tankDrive, 
  Joystick driverControl, 
  Joystick manipulatorControl) {
    //requires(tankDrive);
    _tankDrive = tankDrive;
    _driverControl = driverControl;
    _manipulatorControl = manipulatorControl;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (/*_driverControl->GetRawButton(2)*//*true*/false) {
      //mainDrive().move(
      //mainDrive().driveVisionX(),
      //mainDrive().driveVisionY(),
        _tankDrive.move(
        0,//_driveSystem().driveVisionX(),
        0,//driveSystem().driveVisionY(),
        0
      );
    } else {
    double total = Math.abs(_driverControl.getRawAxis(0)) +
      Math.abs(_driverControl.getRawAxis(1)) +
      Math.abs(_driverControl.getRawAxis(2)) +
      Math.abs(_driverControl.getRawAxis(3));
    if (total > _threshold*2) {
      double x = _driverControl.getRawAxis(0);
      double y = _driverControl.getRawAxis(1);
      if (Math.abs(x) < _threshold){
        x = 0;
      } else if (Math.abs(x) < _threshold * 7){
        x /= 2;
      }
      if (Math.abs(y) < _threshold){
        y = 0;
      } else if (Math.abs(y) < _threshold * 7){
        y /= 2;
      }
      _tankDrive.move(
          x/2,
          y/2,
          _driverControl.getRawAxis(2) - _driverControl.getRawAxis(3));
    } else {
      _tankDrive.move(
          _driverControl.getRawAxis(0)/2,
          _driverControl.getRawAxis(1)/2,
          _driverControl.getRawAxis(2) - _driverControl.getRawAxis(3));
    }
    if (_driverControl.getRawButton(6)) {
      _tankDrive.move(
          _driverControl.getRawAxis(0),
          _driverControl.getRawAxis(1),
          _driverControl.getRawAxis(2) - _driverControl.getRawAxis(3));
    }
    /*else if (_driverControl.getRawButton(5)) {
      _driveSystem.move(
          _driverControl.getRawAxis(0)/4,
          _driverControl.getRawAxis(1)/4,
          _driverControl.getRawAxis(2) - _driverControl.getRawAxis(3));
    }*/  }
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
