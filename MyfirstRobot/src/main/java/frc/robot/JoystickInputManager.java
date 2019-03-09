package frc.robot;/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/8/19 by christopher.johnson
*/

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.FollowLine;
import frc.robot.commands.SetClimberPower;
import frc.robot.commands.SetWristPosition;
import frc.robot.commands.SpinBallIntake;
import frc.robot.subsystems.Wrist;

public class JoystickInputManager implements InputManager {

    private Joystick joy0, joy1;

    @Override
    public void init() {
        joy0 = new Joystick(0);
        joy1 = new Joystick(1);

        JoystickButton succ = new JoystickButton(joy0, 4);
        JoystickButton blow = new JoystickButton(joy0, 6);

        JoystickButton raiseWrist = new JoystickButton(joy0, 5);
        JoystickButton lowerWrist = new JoystickButton(joy0, 3);

        JoystickButton followLine = new JoystickButton(joy0, 2);

        Command raiseClimber = new SetClimberPower(ControlMode.PercentOutput, 1);
        Command lowerClimber = new SetClimberPower(ControlMode.PercentOutput, -1);
        Trigger raiseTrigger = new Trigger() {
            @Override
            public boolean get() {
                final int pov = joy0.getPOV();
                return pov > 270 || pov < 90;
            }
        };
        Trigger lowerTrigger = new Trigger() {
            @Override
            public boolean get() {
                final int pov = joy0.getPOV();
                return pov > 90 && pov < 270;
            }
        };
        raiseTrigger.whileActive(raiseClimber);
        lowerTrigger.whileActive(lowerClimber);

        succ.whileHeld(new SpinBallIntake(0.7, 0));
        blow.whileHeld(new SpinBallIntake(-0.7, 0));

        followLine.whileHeld(new FollowLine());

        raiseWrist.whileHeld(new SetWristPosition(Wrist.up));
        lowerWrist.whileHeld(new SetWristPosition(Wrist.DOWN));
    }

    @Override
    public Joystick getJoystick(int index) {
        switch (index) {
            case 0:
                return joy0;
            case 1:
                return joy1;
            default:
                throw new IllegalArgumentException(String.format("%d is an invalid index", index));
        }
    }
}
