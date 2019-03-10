package frc.robot.commands;
/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/10/19 by christopher.johnson
*/

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetTilterPower extends Command {

    private double power;

    public SetTilterPower(double power) {
        requires(Robot.tilter);
        this.power = power;
    }

    @Override
    protected void initialize() {
        Robot.tilter.set(power);
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