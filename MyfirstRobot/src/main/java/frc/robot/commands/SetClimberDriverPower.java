package frc.robot.commands;
/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/9/19 by christopher.johnson
*/

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetClimberDriverPower extends Command {

    private ControlMode controlMode;
    private double powah;

    public SetClimberDriverPower(ControlMode mode, double power) {
        requires(Robot.climberDriver);
        controlMode = mode;
        powah = power;
    }

    @Override
    protected void initialize() {
        Robot.climberDriver.set(controlMode, powah);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}