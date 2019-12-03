/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


//import edu.wpi.first.wpilibj.Spark;
import com.revrobotics.CANSparkMax;

public class TankDrive extends DriveSystemBase {
/*  private Spark frontLeft;
  private Spark frontRight;
  private Spark rearLeft;
  private Spark rearRight;*/
  private CANSparkMax _frontLeft;
  private CANSparkMax _frontRight;
  private CANSparkMax _rearLeft;
  private CANSparkMax _rearRight;

//  public TankDrive(Spark frontLeft, Spark frontRight, Spark rearLeft, Spark rearRight){
  public TankDrive(CANSparkMax frontLeft, CANSparkMax frontRight, CANSparkMax rearLeft, CANSparkMax rearRight){
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
    _frontLeft.set((y-x));
    _rearLeft.set((y-x));
    _frontRight.set((-y-x));
    _rearRight.set((-y-x));  
  }
}
