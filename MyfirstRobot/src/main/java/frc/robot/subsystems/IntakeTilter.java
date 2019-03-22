package frc.robot.subsystems;
/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/10/19 by christopher.johnson
*/

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Config;
import frc.robot.commands.HandleTilter;

public class IntakeTilter extends Subsystem {

    private WPI_TalonSRX tilt;
    private LimitSwitch top, bottom;
    private double lastPower;

    public IntakeTilter() {
        tilt = new WPI_TalonSRX(Config.IntakeTilter.Motors.TILTER);
        tilt.setInverted(false);
        top = new LimitSwitch(Config.IntakeTilter.Sensors.TILT_TOP);
        bottom = new LimitSwitch(Config.IntakeTilter.Sensors.TILT_BOTTOM);
        tilt.setNeutralMode(NeutralMode.Brake);
        tilt.configOpenloopRamp(0.5);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new HandleTilter());
    }

    @Override
    public void periodic() {
        if (top.get()) {
            tilt.set(ControlMode.PercentOutput, Math.min(0, lastPower));
        } else if (bottom.get()) {
            tilt.set(ControlMode.PercentOutput, Math.max(-0.2, lastPower));
        } else {
            tilt.set(lastPower);
        }
    }

    public void set(double value) {
        lastPower = value;
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
        builder.addDoubleProperty("Current Draw", tilt::getOutputCurrent, null);
        builder.addBooleanProperty("Top Limit", top::get, null);
        builder.addBooleanProperty("Bottom Limit", bottom::get, null);
        super.initSendable(builder);
    }
}