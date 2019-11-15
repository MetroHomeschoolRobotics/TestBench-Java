/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
//import edu.wpi.first.wpilibj.command.Subsystem;

public class OctaDrive extends DriveSystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark _frontLeft;
  private Spark _frontRight;
  private Spark _rearLeft;
  private Spark _rearRight;
  private boolean isTank = true;
  public OctaDrive (){
    //tankDrive
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void move(double x, double y, double z){
    // _frontLeft.set((y-x));//may be modified later
    // _rearLeft.set((y-x));
    // _frontRight.set((-y-x));
    // _rearRight.set((-y-x));  
    // if (isTank){
    //   tankDrive.get().move(x,y);
    // } else {
    //   mechDrive.get().move(x,y,z);
    // }
  }

  public void switchMode(){

    isTank = !isTank;
  
    // if (isTank){
    //   RobotMap::octoDriveSwitchSol1.get().Set(frc::DoubleSolenoid::Value::kReverse);
    // } else {
    //   RobotMap::octoDriveSwitchSol1.get().Set(frc::DoubleSolenoid::Value::kForward);
    // }
  
  }

  public boolean isTankDrive() {
    return isTank;
  }

 }
