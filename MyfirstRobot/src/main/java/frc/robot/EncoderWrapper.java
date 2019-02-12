/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Add your docs here.
 */
public class EncoderWrapper {
    private double angle = 0.0;
    private AnalogInput input;
    
private static double ARMATURE_TO_PINION = 174.9;
public static double PINION_TO_DEGREES = 360;

        // The accumulator center is probably wrong
    public EncoderWrapper(int port)
    {
        input = new AnalogInput(port);
        input.setOversampleBits(4);
        input.setAverageBits(4);
        input.setAccumulatorInitialValue(0);
        input.setAccumulatorDeadband(10);
        input.setAccumulatorCenter(2048);
    }
    public double getVoltage()
    {
        return input.getAverageVoltage();
    }
    public double getRawCounts()
    {
        return input.getAccumulatorValue();
    }
    public double getPosition()
    {
        return getRawCounts() *ARMATURE_TO_PINION;
    }
    public double getAngle()
    {
        return getPosition() *PINION_TO_DEGREES;
    }
   

    
   
    public void reset()
    {
      input.resetAccumulator();
    }
}
