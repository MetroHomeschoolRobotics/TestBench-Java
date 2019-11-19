/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.pixy2.Pixy2CCC.Block;
import frc.robot.subsystems.DriveSystemBase;
import frc.robot.subsystems.TrackingSource;
import frc.robot.subsystems.VisionTracking;

public class FollowTheObject extends Command {
  private VisionTracking _visionTracking;
  private TrackingSource _trackingSource;
  private DriveSystemBase _driveSystem;
  private Block _foundBlock;
  private Command _driveCommand;

  public FollowTheObject(Command driveCommand, VisionTracking visionTracking, TrackingSource trackingSource, DriveSystemBase driveSystem) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    _visionTracking = visionTracking;
    _trackingSource = trackingSource;
    _driveSystem = driveSystem;
    _driveCommand = driveCommand;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    _driveCommand.cancel();
    _foundBlock = _visionTracking.FindBiggestBlock(_trackingSource);
    if (_foundBlock != null) {
      System.out.println("Follow Found Block: " + _foundBlock.toString());
      double x = 0,y = 0;
      if (_foundBlock.getWidth() > 10 && _foundBlock.getWidth() < 50){
        x = 0.12;
      } else if (_foundBlock.getWidth() > 55) {
        x = -0.12;
      }
      if (_foundBlock.getX() > 10 && _foundBlock.getX() < 230){
        y = 0.12;
      } else if (_foundBlock.getX() > 235){
        y = -0.12;
      }
      System.out.println("Follow age: " + _foundBlock.getAge() + ", x:" + x + ", y:" + y); 
      SmartDashboard.putNumber("Follow Age", _foundBlock.getAge());
      SmartDashboard.putNumber("Follow X", x);
      SmartDashboard.putNumber("Follow Y", y);

      _driveSystem.move(-x, y, 0);
    } else {
      System.out.println("Follow no block found");
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return _foundBlock == null;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    _driveCommand.start();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
