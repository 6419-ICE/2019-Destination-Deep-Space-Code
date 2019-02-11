/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HandleClimber;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {
  private VictorSP front;
  private VictorSP back;
  private LimitSwitch frontSwitch;
  private LimitSwitch backSwitch;

  public Climber()
  {
    front = new VictorSP(RobotMap.FRONT_CLIMBER);
    back = new VictorSP(RobotMap.BACK_CLIMBER);
    frontSwitch = new LimitSwitch(RobotMap.FRONT_CLIMBER_BUMP);
    backSwitch = new LimitSwitch(RobotMap.BACK_CLIMBER_BUMP);
  //TODO make the motors stay in brake mode by pushing their button.
    //TODO Set the directions of the motors
}
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HandleClimber());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void raiseFront()
  {
    if(!frontSwitch.getPressed())
      front.set(.6);

  }
  public void raiseBack()
  {
    if(!backSwitch.getPressed())
      back.set(.6);
  }
  public void holdPosition()
  {

  }
}
