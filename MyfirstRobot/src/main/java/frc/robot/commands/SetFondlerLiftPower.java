package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetFondlerLiftPower extends Command {

    private double powah;

    public SetFondlerLiftPower(double power) {
        requires(Robot.fondlerLift);
        powah = power;
    }

    @Override
    protected void initialize() {
        Robot.fondlerLift.setPower(powah);
    }

    @Override
    protected void execute() {
        Robot.fondlerLift.setPower(powah);
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