package frc.robot.commands;
/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/10/19 by christopher.johnson
*/

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetBallIntakePower extends Command {

    private double powah;

    public SetBallIntakePower(double power) {
        requires(Robot.ballIntake);
        powah = power;
    }

    @Override
    protected void initialize() {
        DriverStation.reportWarning("Setting intake power", false);
        Robot.ballIntake.setIntakePower(powah);
    }

    @Override
    protected void execute() {
        Robot.ballIntake.setIntakePower(powah);
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