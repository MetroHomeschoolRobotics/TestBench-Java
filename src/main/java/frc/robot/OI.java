/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.robot.subsystems.DriveSystemBase;
import frc.robot.subsystems.TrackingSource;
import frc.robot.subsystems.VisionTracking;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.pixy2.Pixy2;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private DriveSystemBase _driveSystem;
  Pixy2 _i2cPixy2 = null;
  Pixy2 _spiPixy2 = null;
  Command _autonomousCommand;
  Command _driveCommand;
  SendableChooser<Command> _autoChooser = new SendableChooser<>();
  SendableChooser<Command> _i2cPixyChooser = new SendableChooser<>();
  SendableChooser<Command> _spiPixyChooser = new SendableChooser<>();

  public OI(DriveSystemBase driveSystem, Pixy2 i2cPixy2, Pixy2 spiPixy2){
    _driveSystem = driveSystem;
    _i2cPixy2 = i2cPixy2;
    _spiPixy2 = spiPixy2;
  }

  public void init() {
    Joystick driverControl = new Joystick(0);
    Joystick manipulatorControl = new Joystick(1);

    _driveCommand = new ManualDriveCommand(_driveSystem, driverControl, manipulatorControl);

    if (_i2cPixy2 != null){
      _i2cPixyChooser.setDefaultOption("Check Version", new SendCheckVersion(_i2cPixy2));
      _i2cPixyChooser.addOption("Get Biggest Block", new SendGetBiggestBlock(_i2cPixy2));
      _i2cPixyChooser.addOption("Lamp Off", new SendLED(_i2cPixy2, false, 0, 0, 0));
      _i2cPixyChooser.addOption("Lamp On (Red)", new SendLED(_i2cPixy2, true, 255, 0, 0));
      _i2cPixyChooser.addOption("Lamp On (Green)", new SendLED(_i2cPixy2, true, 0, 255, 0));
      _i2cPixyChooser.addOption("Lamp On (Blue)", new SendLED(_i2cPixy2, true, 0, 0, 255));
      _i2cPixyChooser.addOption("Lamp On (Purple)", new SendLED(_i2cPixy2, true, 200, 30, 255));
      SmartDashboard.putData("I2C Pixy Command", _i2cPixyChooser);
      SmartDashboard.putData("Send I2C Command", new ExecuteChooser(_i2cPixyChooser));
    }

    if (_spiPixy2 != null){
      _spiPixyChooser.setDefaultOption("Check Version", new SendCheckVersion(_spiPixy2));
      _spiPixyChooser.addOption("Get Biggest Block", new SendGetBiggestBlock(_spiPixy2));
      _spiPixyChooser.addOption("Lamp Off", new SendLED(_spiPixy2, false, 0, 0, 0));
      _spiPixyChooser.addOption("Lamp On (Red)", new SendLED(_spiPixy2, true, 255, 0, 0));
      _spiPixyChooser.addOption("Lamp On (Green)", new SendLED(_spiPixy2, true, 0, 255, 0));
      _spiPixyChooser.addOption("Lamp On (Blue)", new SendLED(_spiPixy2, true, 0, 0, 255));
      _spiPixyChooser.addOption("Lamp On (Purple)", new SendLED(_spiPixy2, true, 200, 30, 255));
      SmartDashboard.putData("SPI Pixy Command", _spiPixyChooser);
      SmartDashboard.putData("Send SPI Command", new ExecuteChooser(_spiPixyChooser));
    }
    VisionTracking visionTracking = new VisionTracking();

    Command followTheObjectI2C = new FollowTheObject(
      _driveCommand,
      visionTracking, 
      TrackingSource.PIXY2I2C2, 
      _driveSystem);
      SmartDashboard.putData("I2C Follow The Object",followTheObjectI2C);
      Command followTheObjectSpi = new FollowTheObject(
        _driveCommand,
        visionTracking, 
        TrackingSource.Pixy2Spi, 
        _driveSystem);
        SmartDashboard.putData("SPI Follow The Object",followTheObjectSpi);
      
      Button btn7 = new JoystickButton(driverControl, 7);
      btn7.whenPressed(followTheObjectI2C);
      Button btn8 = new JoystickButton(driverControl, 8);
      btn8.whenPressed(followTheObjectSpi);

      SmartDashboard.putData("Auto mode", _autoChooser);

  }

  public Command getAutonmousCommand(){
    return _autoChooser.getSelected();
  }

  public Command getDriveCommand() {
    return _driveCommand;
  }

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
