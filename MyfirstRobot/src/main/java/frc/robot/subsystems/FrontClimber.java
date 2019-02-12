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
import frc.robot.commands.HandleFrontClimber;

/**
 * Add your docs here.
 */
public class FrontClimber extends Subsystem {
  private VictorSP front;
  private LimitSwitch frontSwitch;

  public FrontClimber()
  {
    front = new VictorSP(RobotMap.FRONT_CLIMBER);
    frontSwitch = new LimitSwitch(RobotMap.FRONT_CLIMBER_BUMP);
  //TODO make the motors stay in brake mode by pushing their button.
    //TODO Set the directions of the motors
}
  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HandleFrontClimber());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void raiseFront(double power)
  {
    if(!frontSwitch.getPressed())
      front.set(power);

  }

  public void holdPosition()
  {
    front.setSpeed(0);
  }
  public void set(double speed)
  {
    front.set(speed);
  }
}
