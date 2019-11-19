/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.pixy2.Pixy2;

public class SendLED extends Command {
  private Pixy2 _pixy2;
  private boolean _on;
  private int _r;
  private int _g;
  private int _b;

  public SendLED(Pixy2 pixy2, boolean on, int r, int g, int b) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    _pixy2 = pixy2;
    _on = on;
    _r = r;
    _g = g;
    _b = b;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (_on){
      _pixy2.setLED(_r, _g, _b);
    } else {
      _pixy2.setLamp((byte)0, (byte)0);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
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
