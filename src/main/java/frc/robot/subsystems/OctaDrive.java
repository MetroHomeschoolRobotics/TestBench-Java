/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import frc.robot.subsystems.*;

public class OctaDrive extends DriveSystemBase {
  private DriveSystemBase _tankDrive;
  private DriveSystemBase _mechDrive;
  private boolean isTank = true;
  
  //public OctaDrive (Spark frontLeft, Spark frontRight, Spark rearLeft, Spark rearRight, TankDrive tankDrive, MechDrive mechDrive){
    public OctaDrive (DriveSystemBase tankDrive, DriveSystemBase mechDrive){
    _tankDrive = tankDrive;
    _mechDrive = mechDrive;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void move(double x, double y, double z){
    if (isTank){
       _tankDrive.move(x,y,0);
    } else {
        _mechDrive.move(x,y,z);
    }
  }

  public void switchMode(){

    isTank = !isTank;
  
     if (isTank){
    //   RobotMap.octoDriveSwitchSol1.get().set(frc::DoubleSolenoid::Value::kReverse);
     } else {
    //   RobotMap.octoDriveSwitchSol1.get().set(frc::DoubleSolenoid::Value::kForward);
     }
  
  }

  public boolean isTankDrive() {
    return isTank;
  }

 }
