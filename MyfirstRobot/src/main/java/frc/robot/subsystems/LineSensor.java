/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Add your docs here.
 */
public class LineSensor {
    private AnalogInput input;
    private int port;
    public LineSensor(int port)
    {
        this.port = port;
        input = new AnalogInput(port);
    }

    public double getAverageVoltage()
    {
        return input.getAverageVoltage();
    }
    public double getRawAverageValue()
    {
        return input.getAverageValue();
    }
    public double getNumberOfBits()
    {
        return input.getAverageBits();
    }
    public boolean onTape()
    {
        return getAverageVoltage() > 5;
    }

    public int getPort()
    {
        return port;
    }
}
