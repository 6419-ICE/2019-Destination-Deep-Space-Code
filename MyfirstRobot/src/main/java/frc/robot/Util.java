/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * Add your docs here.
 */
public class Util {
    public static boolean withinRange(double min, double max, double value)
    {
        return value < max && value > min;
    }

    public static double constrain(double value, double min, double max) {
        if (value > max) {
            return max;
        }
        if (value < min) {
            return min;
        }
        return value;
    }

    public static double applyDeadband(double value, double deadband) {
        return applyDeadband(value, deadband, 0);
    }

    public static double applyDeadband(double value, double deadband, double centeredAround) {
        if (value < centeredAround + deadband && value > centeredAround - deadband) {
            return centeredAround;
        }
        return value;
    }
}
