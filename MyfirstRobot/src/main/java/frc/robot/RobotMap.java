
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

//motors
public static int FRONT_CLIMBER = 0;
public static int BACK_CLIMBER = 1;

  //CAN devices
public static int FRONT_LEFT_DRIVE = 0;
public static int FRONT_RIGHT_DRIVE = 1;
public static int BACK_LEFT_DRIVE = 2;
public static int BACK_RIGHT_DRIVE = 3;
//Sensors
public static int BALL_INTAKE = 6;
public static int BUMP_SWITCH = 0;
<<<<<<< HEAD
public static final int LINE_SENSOR1 = 0;
public static final int LINE_SENSOR2 = 1;
public static final int LINE_SENSOR3 = 2;


public static int FRONT_CLIMBER_BUMP = 3;
public static int BACK_CLIMBER_BUMP = 4;
=======
public static int WRIST = 10;
public static int WRIST_ENCODER = 0;
>>>>>>> master



  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
