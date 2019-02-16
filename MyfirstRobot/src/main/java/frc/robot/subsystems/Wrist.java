/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.Util;
import frc.robot.commands.WristDefaultCommand;

/**
 * Add your docs here.
 */
public class Wrist extends Subsystem {
  private Counter motorcounter;
  private VictorSPX motor;
  public static int DOWN = 0;
  public static int up = 30;
  private int direction;
  private int position;
  private int prevPos = 0;
  private int sp = 0;
  public Wrist()
  {
    motor = new VictorSPX(RobotMap.WRIST);
    motorcounter = new Counter(new DigitalInput(RobotMap.WRIST_ENCODER));
    
  }
  @Override
  public void periodic()
  {
  //  direction = motor.getMotorOutputPercent() < 0 ? -1:1;
    int count = motorcounter.get();
   int  delta = (count - prevPos) * direction;
   delta = Math.abs(delta) > 10 ? 0:delta;
    position += delta;
    prevPos = count;
  }

  public void set(double power)
  {
    motor.set(ControlMode.PercentOutput, power);
  }
  @Override
  public void initDefaultCommand() {
    this.setDefaultCommand(new WristDefaultCommand());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void setPosition()
  {
    if(!Util.withinRange(sp - 7, sp + 7, this.position)) {
      if(sp < this.position) direction = -1;
      else direction = 1;
      this.set(direction);
    }
     else
    {
          motor.set(ControlMode.PercentOutput, 0);
    }
  }
  public void reset()
  {
    position = 0;
  }

  public int getPosition()
  {
    return position;
  }
  public void setSP(int sp)
  {
    this.sp = sp;
  }
    
}
