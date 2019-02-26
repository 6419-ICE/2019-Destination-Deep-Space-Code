/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class ClimberDrive extends Subsystem {
  private TalonSRX frontMotor, backMotor;

  public ClimberDrive()
  {
    frontMotor = new TalonSRX(RobotMap.FRONT_CLIMBER_DRIVE);
    backMotor = new TalonSRX(RobotMap.BACK_CLIMBER_DRIVE);
  }

  public void set(double speed)
  {
    frontMotor.set(ControlMode.PercentOutput, speed);
    backMotor.set(ControlMode.PercentOutput, speed);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
