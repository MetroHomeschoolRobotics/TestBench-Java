/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.Spark;

public class TankDrive extends DriveSystemBase {
  private Spark frontLeft;
  private Spark frontRight;
  private Spark rearLeft;
  private Spark rearRight;

  public TankDrive(Spark frontLeft, Spark frontRight, Spark rearLeft, Spark rearRight){
    this.frontLeft = frontLeft;
    this.frontRight = frontRight;
    this.rearLeft = rearLeft;
    this.rearRight = rearRight;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  @Override
  public void move(double x, double y, double z){
    frontLeft.set((y-x));
    rearLeft.set((y-x));
    frontRight.set((-y-x));
    rearRight.set((-y-x));  
  }
}
