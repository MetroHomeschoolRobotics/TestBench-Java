/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;

/**
 * Add your docs here.
 */
public class MechDrive extends DriveSystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private Spark frontLeft;
  private Spark frontRight;
  private Spark rearLeft;
  private Spark rearRight;
  private double threshold = 0.1;


  public MechDrive(Spark frontLeft, Spark frontRight, Spark rearLeft, Spark rearRight){
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
    //double distance = positioning.get().getDistance();
    // Output sensor data
    //std::printf("Lidar Distance %4.2f \n", distance);
    //std::printf("Gyro Angle %4.2f \n", positioning.get()->GetAngle());
  
    // if(fabs(x) < this.threshold) {
    //   x = 0;
    // }
  
    // if(fabs(y) < threshold) {
    //   y = 0;
    // }
  
    // if(fabs(z) < threshold) {
    //   z = 0;
    // }
  
    //driveTrain.get()->DriveCartesian(x, y, z, positioning.get()->GetAngle());
    frontLeft.set(y-x+z);
    rearLeft.set(y+x+z);
    frontRight.set(-y-x+z);
    rearRight.set(-y+x+z);
  
  }
}
