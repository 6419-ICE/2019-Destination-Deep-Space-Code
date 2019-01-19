
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
public static final int LINE_SENSOR1 = 4;
public static final int LINE_SENSOR2 = 5;
public static final int LINE_SENSOR3 = 6;
public static int FRONT_LEFT_DRIVE = 0;
public static int FRONT_RIGHT_DRIVE = 1;
public static int BACK_LEFT_DRIVE = 2;
public static int BACK_RIGHT_DRIVE = 3;
public static int LINE_SENSOR = 5; 
public static int BALL_INTAKE = 6;
public static int LIMIT_SWITCH = 7;


  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
