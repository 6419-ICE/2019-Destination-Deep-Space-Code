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
public enum ClimberDriveDirection
 {
    FORWARDS
    {
        @Override
        public double getValue()
        {
            return .5;
        }
    },
    BACKWARDS
    {
        @Override
        public double getValue()
        {
            return -.5;
        }
    };
    public abstract double getValue();
}
