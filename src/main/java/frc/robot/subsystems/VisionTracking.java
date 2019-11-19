/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.pixy2.Pixy2;
import frc.robot.pixy2.Pixy2CCC;
import frc.robot.pixy2.Pixy2.LinkType;
import frc.robot.pixy2.Pixy2CCC.Block;

/**
 * Add your docs here.
 */
public class VisionTracking extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public VisionTracking(){
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public Block FindBiggestBlock(TrackingSource source){
    var pixy = buildPixy(source);
    if (pixy != null){
      return getBiggestBlock(pixy);
    }
    return null;
  }

  private Pixy2 buildPixy(TrackingSource source){
    try {
      Pixy2 pixy = null;
      if (source == TrackingSource.PIXY2I2C2){
        pixy = Pixy2.createInstance(LinkType.I2C);
      } else if (source == TrackingSource.Pixy2Spi){
        pixy = Pixy2.createInstance(LinkType.SPI);
      }
      if (pixy != null){
        pixy.init();
      }
      return pixy;
    } catch(Exception e){
      System.out.println("Unable to build pixy: " + e.getMessage());
      return null;
    }

  }

  
	private Block getBiggestBlock(Pixy2 pixy2) {
		// Gets the number of "blocks", identified targets, that match signature 1 on the Pixy2,
		// does not wait for new data if none is available,
		// and limits the number of returned blocks to 25, for a slight increase in efficiency
    Pixy2CCC tracker = pixy2.getCCC();
    if (tracker == null){
      System.out.println("cannot find tracker");
      return null;
    }
    int blockCount = tracker.getBlocks(false, Pixy2CCC.CCC_SIG1, 25);
		System.out.println("Found " + blockCount + " blocks!"); // Reports number of blocks found
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
    if (largestBlock == null){
      System.out.println("No largest block");
    } else {
      System.out.println("Largest block: " + largestBlock.toString());
    }
		return largestBlock;
	}
}
