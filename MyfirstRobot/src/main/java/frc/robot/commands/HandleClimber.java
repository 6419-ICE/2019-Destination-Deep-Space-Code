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

public class HandleClimber extends Command {

    public HandleClimber() {
        requires(Robot.climber);
    }

    @Override
    protected void initialize() {
        Robot.climber.set(ControlMode.Velocity, 0);
    }

    @Override
    protected void execute() {
        //Robot.climber.set(ControlMode.Velocity, 0);
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