package frc.robot.commands;
/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/8/19 by christopher.johnson
*/

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetClimberPower extends Command {

    private ControlMode mode;
    private double powah;

    public SetClimberPower(ControlMode controlMode, double power) {
        requires(Robot.climber);
        mode = controlMode;
        powah = power;
    }

    @Override
    protected void initialize() {
        Robot.climber.set(mode, powah);
        System.out.println(mode);
    }

    @Override
    protected void execute() {
        System.out.println(String.format("Front: %d Back: %d", Robot.climber.getFrontVelocity(), Robot.climber.getBackVelocity()));
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