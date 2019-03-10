package frc.robot.commands;
/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/9/19 by christopher.johnson
*/

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.Robot;

public class HandleClimberDriver extends Command {

    public HandleClimberDriver() {
        requires(Robot.climberDriver);
    }

    @Override
    protected void initialize() {
        Robot.climberDriver.set(ControlMode.PercentOutput, 0);
    }

    @Override
    protected void execute() {
        if (Config.USING_YOKE) {
            Robot.climberDriver.set(ControlMode.PercentOutput, Robot.m_oi.getJoystick(1).getRawAxis(1));
        }
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