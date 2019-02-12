/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.ClimberDirection;
import frc.robot.RobotMap;
import frc.robot.commands.HandleBackClimber;

/**
 * Add your docs here.
 */
public class BackClimber extends Subsystem {
    private VictorSP motor;
    private boolean raised = false;
    private LimitSwitch backSwitch;
    public BackClimber()
    {
      motor = new VictorSP(RobotMap.BACK_CLIMBER);
      backSwitch = new LimitSwitch(RobotMap.BACK_CLIMBER_BUMP);
      //TODO may need to reverse the motor.
    }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void holdPosition()
  {
    motor.setSpeed(0);
  }
  public void set(double power)
  {
    motor.set(power);
  }
  @Override
  public void initDefaultCommand()
   {
    this.setDefaultCommand(new HandleBackClimber());
  }
  public boolean raiseClimber(ClimberDirection direction)
  {
    if(!backSwitch.getPressed())
    {
      motor.setSpeed(direction.getValue());
      raised = false;
    }
    else{
      raised = true;
      
    }
    return raised;
  }
  public boolean getRaised()
  {
    return raised;
  }
}
