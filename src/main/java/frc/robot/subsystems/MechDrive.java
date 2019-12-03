/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import edu.wpi.first.wpilibj.Spark;
import com.revrobotics.CANSparkMax;

public class MechDrive extends DriveSystemBase {
  /*private Spark frontLeft;
  private Spark frontRight;
  private Spark rearLeft;
  private Spark rearRight;*/
  private CANSparkMax _frontLeft;
  private CANSparkMax _frontRight;
  private CANSparkMax _rearLeft;
  private CANSparkMax _rearRight;
  private double threshold = 0.1;

  //public MechDrive(Spark frontLeft, Spark frontRight, Spark rearLeft, Spark rearRight){
    public MechDrive(CANSparkMax frontLeft, CANSparkMax frontRight, CANSparkMax rearLeft, CANSparkMax rearRight){
    _frontLeft = frontLeft;
    _frontRight = frontRight;
    _rearLeft = rearLeft;
    _rearRight = rearRight;  
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
  
     if(Math.abs(x) < this.threshold) {
       x = 0;
     }
  
     if(Math.abs(y) < threshold) {
       y = 0;
     }
  
     if(Math.abs(z) < threshold) {
       z = 0;
     }
  
    //driveTrain.get()->DriveCartesian(x, y, z, positioning.get()->GetAngle());
    _frontLeft.set(y-x+z);
    _rearLeft.set(y+x+z);
    _frontRight.set(-y-x+z);
    _rearRight.set(-y+x+z);
  
  }
}
