/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HandleDrive extends Command 
{
  public HandleDrive() 
  {
    requires(Robot.chassis);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute()
  {
    double left = -Robot.m_oi.joystick1.getRawAxis(1);
    double right = -Robot.m_oi.joystick2.getRawAxis(1);
    // left = Math.min(Math.max(-1, left), 1);
    // right = Math.min(Math.max(-1, right), 1);
    left = Math.max(-1, left);
    left = Math.min(1, left);
    right = Math.max(-1, right);
    right = Math.min(1, right);
    double signLeft = left < 0 ? -1:1;
    double signRight = right < 0 ? -1:1;
    left = left*left*signLeft;
    right = right*right*signRight;
    Robot.chassis.drive(left, right);
  
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished()
  {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end()
  {

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    end();
  }
}
