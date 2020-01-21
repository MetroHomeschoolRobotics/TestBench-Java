/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.pixy2.Pixy2;
import frc.robot.pixy2.Pixy2CCC;
import frc.robot.pixy2.Pixy2CCC.Block;

public class FindColorBlocks extends Command {
  private Pixy2 _pixy2;
  public FindColorBlocks(Pixy2 pixy2) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    _pixy2 = pixy2;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Block biggestBlock = getBiggestBlock(Pixy2CCC.CCC_SIG1|Pixy2CCC.CCC_SIG2|Pixy2CCC.CCC_SIG3/*|Pixy2CCC.CCC_SIG4*/|Pixy2CCC.CCC_SIG5);
   if (biggestBlock != null){ 
     //System.out.println(biggestBlock.getSignature());
    if ((biggestBlock.getSignature() /*& Pixy2CCC.CCC_SIG1*/) == 1 /*Pixy2CCC.CCC_SIG1*/){
      System.out.println("Found YELLOW");
    }
    if ((biggestBlock.getSignature() /*& Pixy2CCC.CCC_SIG2*/) == 2 /*Pixy2CCC.CCC_SIG2*/){
      System.out.println("Found RED");
    }
    if ((biggestBlock.getSignature() /*& Pixy2CCC.CCC_SIG3*/) == 3 /*Pixy2CCC.CCC_SIG3*/){
      System.out.println("Found GREEN");
    }
    if ((biggestBlock.getSignature() /*& Pixy2CCC.CCC_SIG4*/) == 5 /*Pixy2CCC.CCC_SIG5*/){
      System.out.println("Found BLUE");
    }
  }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
private Block getBiggestBlock(int signature) {
  // Gets the number of "blocks", identified targets, that match signature 1 on the Pixy2,
  // does not wait for new data if none is available,
  // and limits the number of returned blocks to 25, for a slight increase in efficiency
  Pixy2CCC tracker = _pixy2.getCCC();
  if (tracker == null){
    System.out.println("cannot find tracker");
    return null;
  }

  int blockCount = tracker.getBlocks(false, signature, 5);//wait worked on true
  //System.out.println("Found " + blockCount + " blocks!"); // Reports number of blocks found
  if (blockCount <= 0) {
    System.out.println("no blocks found");
    return null; // If blocks were not found, stop processing
  }

  ArrayList<Block> blocks = tracker.getBlocks(); // Gets a list of all blocks found by the Pixy2
  Block largestBlock = null;
  for (Block block : blocks) { // Loops through all blocks and finds the widest one
    if (largestBlock == null) {
      largestBlock = block;
    } else if (block.getWidth() > largestBlock.getWidth()) {
      largestBlock = block;
    }
  }
  return largestBlock;
}
}