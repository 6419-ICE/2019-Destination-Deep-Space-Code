package frc.robot.subsystems;
/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/9/19 by christopher.johnson
*/

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Config;
import frc.robot.commands.HandleClimberDriver;

public class ClimberDriver extends Subsystem {

    private WPI_TalonSRX driver;

    public ClimberDriver() {
        driver = new WPI_TalonSRX(Config.ClimberDriver.Motors.CLIMBER_DRIVER);
        driver.setInverted(true);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new HandleClimberDriver());
    }

    public void set(ControlMode mode, double value) {
        driver.set(mode, value);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        setName("Climber Driver");
        driver.setName("Driver motor");
        driver.setSubsystem(getName());
        super.initSendable(builder);
    }
}