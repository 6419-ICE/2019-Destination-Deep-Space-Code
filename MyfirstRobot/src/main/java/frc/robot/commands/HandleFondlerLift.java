package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HandleFondlerLift extends Command {

    public HandleFondlerLift() {
        requires(Robot.fondlerLift);
    }

    @Override
    protected void initialize() {
        Robot.fondlerLift.setPower(0);
    }

    @Override
    protected void execute() {
        Robot.fondlerLift.setPower(0);
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