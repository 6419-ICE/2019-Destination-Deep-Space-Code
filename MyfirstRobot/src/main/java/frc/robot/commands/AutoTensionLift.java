
/*
 * Copyright (c) 2019 FRC 6419 "ICE"
 *
 * This codebase is released into the public domain under an
 * open source license. Teams may use and modify this code
 * as they please.
 */

/*
 * AutoTensionLift
 * Created on 03/22/2019 by ICE
 */

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoTensionLift extends Command {

    private double startTime;

    public AutoTensionLift() {
        requires(Robot.fondlerLift);
        startTime = Timer.getFPGATimestamp();
    }

    @Override
    protected void initialize() {
        DriverStation.reportWarning("Auto-tensioning lift", false);
        Robot.fondlerLift.setPower(0.03);
    }

    @Override
    protected void execute() {
        Robot.fondlerLift.setPower(0.03);
    }

    @Override
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - startTime > 0.5 && Robot.fondlerLift.getEncoderVelocity() < 50;
    }

    @Override
    protected void end() {
        Robot.fondlerLift.setPower(0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}