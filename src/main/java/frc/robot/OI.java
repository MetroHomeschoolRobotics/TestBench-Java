/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.DriveSystemBase;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private DriveSystemBase _tankDrive;
  private DriveSystemBase _octaDrive;
  private DriveSystemBase _mechDrive;
  Command _autonomousCommand;
  Command _driveTank;
  Command _driveOcta;
  SendableChooser<Command> _autoChooser = new SendableChooser<>();
  public OI(DriveSystemBase tankDrive, DriveSystemBase octaDrive, DriveSystemBase mechDrive){
    _tankDrive = tankDrive;
    _octaDrive = octaDrive;
    _mechDrive = mechDrive;
  }

  public void init() {
    Joystick driverControl = new Joystick(0);
    Joystick manipulatorControl = new Joystick(1);
    _driveTank = new DriveTank(_tankDrive, driverControl, manipulatorControl);
    _driveOcta = new DriveOcta(_octaDrive, driverControl, manipulatorControl);

    SmartDashboard.putData("AutoMode", _autoChooser);
  }

  public Command getAutonomousCommand(){
    return _autoChooser.getSelected();
  }

  public Command getDriveCommand(){
    return _driveTank;
  }
    //private Joystick driveJoystick;
    // std::shared_ptr<frc::Joystick> driveJoystick;
    // std::shared_ptr<frc::Joystick> manipulatorJoystick;
    // driveJoystick.reset(new Joystick(0));
    // manipulatorJoystick.reset(new Joystick(1));
  //}
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}
