package frc.robot.subsystems;
/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/7/19 by christopher.johnson
*/

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class MotorTest extends Subsystem {

    private WPI_TalonSRX talonSRX;

    public MotorTest() {
        talonSRX = new WPI_TalonSRX(5);
        talonSRX.configSelectedFeedbackSensor(FeedbackDevice.SoftwareEmulatedSensor);
    }

    @Override
    protected void initDefaultCommand() {

    }

    @Override
    public void initSendable(SendableBuilder builder) {
        setName("Motor test");
        builder.addDoubleProperty("Power", () -> {
            return talonSRX.getMotorOutputPercent();
        }, (double value) -> {
            talonSRX.set(ControlMode.PercentOutput, value);
        });
        builder.addDoubleProperty("Position", () -> {
            return talonSRX.getSelectedSensorPosition();
        }, null);
        super.initSendable(builder);
    }
}