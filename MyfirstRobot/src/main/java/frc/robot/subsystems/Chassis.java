/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HandleDrive;

/**
 * Add your docs here.
 */
public class Chassis extends Subsystem implements PIDOutput {
  private LineSensor lineSensorLeft, lineSensorRight, lineSensorCenter;
  private Talon flDrive, frDrive, blDrive, brDrive;
  public static double kP = 1, kI = 0, kD = 0, kF = 0;
  public static double percentTolerance = 5f;
  private ADXRS450_Gyro gyro;
  private LimitSwitch limitSwitch;

  public PIDController turnPid;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Chassis() {
    gyro = new ADXRS450_Gyro();
    lineSensorLeft = new LineSensor(RobotMap.LINE_SENSOR1);
    lineSensorCenter = new LineSensor(RobotMap.LINE_SENSOR2);
    lineSensorRight = new LineSensor(RobotMap.LINE_SENSOR3);
    flDrive = new Talon(RobotMap.FRONT_LEFT_DRIVE);
    frDrive = new Talon(RobotMap.FRONT_RIGHT_DRIVE);
    blDrive = new Talon(RobotMap.BACK_LEFT_DRIVE);
    brDrive = new Talon(RobotMap.BACK_RIGHT_DRIVE);
    limitSwitch = new LimitSwitch(RobotMap.LIMIT_SWITCH);
    turnPid = new PIDController(0, 0, 0, gyro, this);
    setPid();
    // TODO Set the direction of the motors to make driving have positive output
    // going straight.
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new HandleDrive());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setPid() {
    turnPid.setP(kP);
    turnPid.setI(kI);
    turnPid.setD(kD);
    turnPid.setF(kF);
    turnPid.setOutputRange(-1, 1);
    turnPid.setPercentTolerance(percentTolerance);
    turnPid.setContinuous(true);

  }

  public void startTurnPid(double setpoint) {
    setPid();
    turnPid.setSetpoint(setpoint);
    turnPid.enable();
  }
  public boolean touchingWall()
  {
    return limitSwitch.getPressed();
  }

  /**
   * Drives the robot using raw power.
   */
  public void drive(double left, double right) {
    flDrive.set(left);
    frDrive.set(right);
    blDrive.set(left);
    brDrive.set(right);
  }

  @Override
  public void pidWrite(double output) {
    drive(output, -output);
  }
  public boolean getLeft()
  {
    return lineSensorLeft.onTape();
  }
  public boolean getCenter()
  {
    return lineSensorCenter.onTape();
  }
  public boolean getRight()
  {
    return lineSensorRight.onTape();
  }
  public DirectionEnum directionToTurn()
  {
    boolean left = getLeft();
    boolean center = getCenter();
    boolean right = getRight();
    if(left && center && ! right)
    {
      return DirectionEnum.LEFT;
    }
    if(right && center && !left)
    {
      return DirectionEnum.RIGHT;
    }
    if(!left && center && !right)
    {
      return DirectionEnum.CENTER;
    }
    return DirectionEnum.UNKNOWN;

  }

  


}
