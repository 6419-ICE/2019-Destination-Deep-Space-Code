/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;



import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HandleDrive;


/**
 * Add your docs here.
 */
public class Chassis extends Subsystem implements PIDOutput {
  private  WPI_TalonSRX flDrive, frDrive, blDrive, brDrive;
  public static double kP = 1, kI = 0, kD = 0, kF = 0;
  public static double percentTolerance = 5f;
  private ADIS16448_IMU gyro;
  public PIDController turnPid;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public Chassis() {
    gyro = new ADIS16448_IMU();
    flDrive = new WPI_TalonSRX(RobotMap.FRONT_LEFT_DRIVE);
    frDrive = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_DRIVE);
    blDrive = new WPI_TalonSRX(RobotMap.BACK_LEFT_DRIVE);
    brDrive = new WPI_TalonSRX(RobotMap.BACK_RIGHT_DRIVE);
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
    turnPid.setInputRange(-179, 179);
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

}
