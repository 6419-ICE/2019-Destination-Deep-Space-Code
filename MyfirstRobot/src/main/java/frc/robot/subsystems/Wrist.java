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

/**
 * Add your docs here.
 */
public class Wrist extends Subsystem {
  private double targetPosition;
  private VictorSPX motor;
  public static int DOWN = 0;
  public static int MIDDLE = 1000;
  public static int up = 2000;

  public Wrist()
  {
    motor = new VictorSPX(RobotMap.WRIST);
  }

  public void set(double power)
  {
    motor.set(ControlMode.PercentOutput, power);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void setPosition()
  {
    motor.set(ControlMode.Position, targetPosition);
  }
  public void setTargetPosition(double pos)
  {
    targetPosition = pos;
  }
  public double getTargetPosition()
  {
    return targetPosition;
  }
  
}
