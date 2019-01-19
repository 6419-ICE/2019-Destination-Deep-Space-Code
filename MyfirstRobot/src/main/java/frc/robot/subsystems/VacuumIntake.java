/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class VacuumIntake extends Subsystem {
  private Solenoid cylinder;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public VacuumIntake()
  {
    cylinder = new Solenoid(RobotMap.SOLENOID);

  }
  public void open()
  {
    cylinder.set(true);;
  }
  public void close()
  {
    cylinder.set(false);
  }
  public void toggle()
  {
    cylinder.set(!cylinder.get());
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
