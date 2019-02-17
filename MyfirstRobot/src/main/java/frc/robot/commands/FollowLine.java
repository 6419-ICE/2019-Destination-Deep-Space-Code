/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FollowLine extends Command {
 // private ArrayList<Double> outputs =new ArrayList<Double>(4);
//  private double kD = .8;
  public FollowLine() {
    requires(Robot.chassis);
  // for(int i = 0; i < 20; i++)
  // {
  //   outputs.add(0d);
  // }
    // outputs.add(0d);

    
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
   
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  if(!Robot.chassis.touchingWall())
  {
    double left = .2;
    double right = .2;
    double difference = Robot.chassis.directionToTurn().getPower();
    
   // System.out.println(outputs);
  //  double sum = 0;
    // for(int i = 0; i < 20; i++)
    // {
    //   sum += outputs.get(i);

    // }
    // sum += difference;

//    double newDifference = sum/21;
      double newDifference = difference;
    Robot.chassis.drive(left + newDifference, right - newDifference);///////
    

  }
  else
  {
  Robot.chassis.drive(0, 0);
  
  }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // return Robot.chassis.touchingWall();
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.chassis.drive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
