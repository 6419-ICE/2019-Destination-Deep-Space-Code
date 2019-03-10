package frc.robot.subsystems;
/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/10/19 by christopher.johnson
*/

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Config;
import frc.robot.commands.HandleTilter;

public class IntakeTilter extends Subsystem {

    private WPI_TalonSRX tilt;
    private LimitSwitch top, bottom;

    public IntakeTilter() {
        tilt = new WPI_TalonSRX(Config.INTAKE_TILT);
        top = new LimitSwitch(Config.TILT_TOP);
        bottom = new LimitSwitch(Config.TILT_BOTTOM);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new HandleTilter());
    }

    @Override
    public void periodic() {

    }

    public void set(double value) {
        tilt.set(ControlMode.PercentOutput, value);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        setName("Intake Tilter");
        tilt.setName("Tilt");
        tilt.setSubsystem(getName());
        top.setName("Top");
        top.setSubsystem(getName());
        bottom.setName("Bottom");
        bottom.setSubsystem(getName());
        super.initSendable(builder);
    }
}