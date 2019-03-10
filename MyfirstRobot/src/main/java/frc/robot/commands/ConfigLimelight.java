package frc.robot.commands;
/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/9/19 by christopher.johnson
*/

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Limelight;

public class ConfigLimelight extends Command {

    private Limelight.LightMode lightMode;
    private Limelight.CameraMode cameraMode;

    public ConfigLimelight(Limelight.LightMode lightMode, Limelight.CameraMode cameraMode) {
        this.lightMode = lightMode;
        this.cameraMode = cameraMode;
    }

    @Override
    protected void initialize() {
        Robot.limelight.setLightMode(lightMode);
        Robot.limelight.setCameraMode(cameraMode);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {

    }
}