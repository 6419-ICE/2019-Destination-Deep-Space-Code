/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.HandleDrive;

/**
 * Add your docs here.
 */
public class MecchanumChassis extends Subsystem implements PIDOutput {
  private Talon flDrive, frDrive, blDrive, brDrive;
  public static double kP = 1, kI = 0, kD = 0, kF = 0;
  public static double percentTolerance = 5f;
  private ADXRS450_Gyro gyro;

  public PIDController turnPid;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public MecchanumChassis() {
    
    gyro = new ADXRS450_Gyro();
    flDrive = new Talon(RobotMap.FRONT_LEFT_DRIVE);
    frDrive = new Talon(RobotMap.FRONT_RIGHT_DRIVE);
    blDrive = new Talon(RobotMap.BACK_LEFT_DRIVE);
    brDrive = new Talon(RobotMap.BACK_RIGHT_DRIVE);
    turnPid = new PIDController(0, 0, 0, gyro, this);
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
    turnPid.setSetpoint(setpoint);
    turnPid.enable();
  }

  /**
   * Drives the robot using raw power.
   */
  public void drive(double x, double y, double z) {
    double flPower = 0, frPower = 0, blPower = 0, brPower = 0;
    flPower = y - x + z;
    frPower = y + x - z;
    blPower = y + x + z;
    brPower = y -x -z;
    flDrive.set(flPower);
    frDrive.set(frPower);
    blDrive.set(blPower);
    brDrive.set(brPower);
  }

  @Override
  public void pidWrite(double output) {
    drive(0, 0, output);
  }

}
