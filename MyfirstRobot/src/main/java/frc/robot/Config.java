
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Config is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class Config {

    public static boolean USING_YOKE = true;

    public static class Yoke {
        // yoke buttons
        public static final int LEFT_TRIGGER = 1,
                                RIGHT_TRIGGER = 2,
                                RIGHT_HAND_ROCKER_LEFT = 3,
                                RIGHT_HAND_ROCKER_RIGHT = 4,
                                LEFT_TOGGLE_UP = 5,
                                LEFT_TOGGLE_DOWN = 6,
                                RIGHT_TOGGLE_UP = 7,
                                RIGHT_TOGGLE_DOWN = 8,
                                LEFT_HAND_TOP_BUTTON = 9,
                                LEFT_HAND_BOTTOM_BUTTON = 10,
                                LEFT_HAND_ROCKER_UP = 11,
                                LEFT_HAND_ROCKER_DOWN = 12;
    }

    public static final int CLIMB_SPEED = 1000,
                            RETRACT_SPEED = 2000;

    //motors
    public static int FRONT_CLIMBER = 0;
    public static int BACK_CLIMBER = 1;

    //CAN devices
    public static int FRONT_LEFT_DRIVE = 1;
    public static int FRONT_RIGHT_DRIVE = 2;
    public static int BACK_LEFT_DRIVE = 3;
    public static int BACK_RIGHT_DRIVE = 4;
    public static int CLIMBER_DRIVER = 5;

    public static int INTAKE_TILT = 6;
    public static int BALL_INTAKE = 17;
    public static int WRIST = 10;

    //Sensors

    public static int BUMP_SWITCH = 0;
    public static final int LINE_SENSOR1 = 0;
    public static final int LINE_SENSOR2 = 1;
    public static final int LINE_SENSOR3 = 2;

    public static int FRONT_CLIMBER_BUMP = 3;
    public static int BACK_CLIMBER_BUMP = 4;
    public static int TILT_TOP = 5,
                      TILT_BOTTOM = 6;
    public static int WRIST_ENCODER = 1;


    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;

    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}
