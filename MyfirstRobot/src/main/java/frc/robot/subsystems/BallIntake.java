/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HandleBallIntake;

/**
 * Add your docs here.
 */
public class BallIntake extends Subsystem {
  private VictorSPX intake;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
public BallIntake()
{
  intake = new VictorSPX(RobotMap.BALL_INTAKE);
}
public void set(double input)
{
  intake.set(ControlMode.PercentOutput, input);
}
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    this.setDefaultCommand(new HandleBallIntake());
  }
}
