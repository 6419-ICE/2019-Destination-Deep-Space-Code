/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Config;
import frc.robot.Robot;
import frc.robot.Util;

public class HandleDrive extends Command {
    public HandleDrive() {
        requires(Robot.chassis);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.chassis.setTurningPidEnabled(false);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double leftPower, rightPower;
        if (Config.USING_YOKE) {
            Joystick joystick = Robot.m_oi.getJoystick(0);
            double driveInput = -joystick.getRawAxis(1);
            double turnInput = joystick.getRawAxis(0);
            double drive = Util.applyDeadband(Math.copySign(driveInput * driveInput, driveInput), 0.05),
                    turn = Util.applyDeadband(Math.copySign(turnInput * turnInput, turnInput), 0.05) + 0.2 * joystick.getRawAxis(2);
            /*if (drive < 0) {
                turn *= -1;
            }*/
            leftPower = drive + turn;
            rightPower = drive - turn;
            double maxAbs = Math.max(Math.abs(leftPower), Math.abs(rightPower));
            if (maxAbs > 1) {
                leftPower /= maxAbs;
                rightPower /= maxAbs;
            }
            /*leftPower = Math.copySign(leftPower * leftPower, leftPower);
            rightPower = Math.copySign(rightPower * rightPower, rightPower);
            leftPower = Util.applyDeadband(leftPower, 0.1);
            rightPower = Util.applyDeadband(rightPower, 0.1);*/
        } else {
            double left = -Robot.m_oi.getJoystick(0).getRawAxis(1);
            double right = -Robot.m_oi.getJoystick(1).getRawAxis(1);
            left = Util.constrain(left, -1, 1);
            right = Util.constrain(right, -1, 1);
            leftPower = Math.copySign(left * left, left);
            rightPower = Math.copySign(right * right, right);
        }
        Robot.chassis.drive(leftPower, rightPower);

        //System.out.println(String.format("Left power: %f Right Power %f", leftPower, rightPower));
        //System.out.println(String.format("Left: %f Right: %f", Robot.chassis.flDrive.getAppliedOutput(), Robot.chassis.frDrive.getAppliedOutput()));

    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
