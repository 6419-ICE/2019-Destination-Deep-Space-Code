package frc.robot;/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/8/19 by christopher.johnson
*/

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.RaiseBack;
import frc.robot.commands.RaiseFront;
import frc.robot.commands.SetClimberDriverPower;
import frc.robot.commands.SetClimberPower;
import frc.robot.triggers.BetterButton;
import frc.robot.triggers.POVTrigger;
import frc.robot.Config.Yoke;

public class YokeInputManager implements InputManager {

    private Joystick yoke;

    @Override
    public void init() {
        yoke = new Joystick(0);
        if (yoke.getAxisCount() < 5) {
            DriverStation.reportWarning("You do not have a yoke plugged in!", false);
        }

        JoystickButton raiseClimber = new JoystickButton(yoke, Yoke.LEFT_HAND_ROCKER_UP);
        JoystickButton lowerClimber = new JoystickButton(yoke, Yoke.LEFT_HAND_ROCKER_DOWN);
        raiseClimber.whileActive(new SetClimberPower(ControlMode.Velocity, Config.CLIMB_SPEED));
        lowerClimber.whileActive(new SetClimberPower(ControlMode.Velocity, -Config.CLIMB_SPEED));

        Trigger climberDriverForwards = new POVTrigger(yoke, 270, 90);
        Trigger climberDriverBackwards = new POVTrigger(yoke, 90, 270);
        climberDriverForwards.whileActive(new SetClimberDriverPower(ControlMode.PercentOutput, -0.5));
        climberDriverBackwards.whileActive(new SetClimberDriverPower(ControlMode.PercentOutput, 0.5));

        BetterButton raiseFront = new BetterButton(yoke, Yoke.LEFT_TOGGLE_UP);
        BetterButton lowerFront = new BetterButton(yoke, Yoke.LEFT_TOGGLE_DOWN);
        BetterButton raiseBack = new BetterButton(yoke, Yoke.RIGHT_TOGGLE_UP);
        BetterButton lowerBack = new BetterButton(yoke, Yoke.RIGHT_TOGGLE_DOWN);

        Command raiseFrontCommand = new RaiseFront(ControlMode.Velocity, Config.CLIMB_SPEED),
                lowerFrontCommand = new RaiseFront(ControlMode.Velocity, -Config.CLIMB_SPEED),
                raiseBackCommand = new RaiseBack(ControlMode.Velocity, -Config.CLIMB_SPEED),
                lowerBackCommand = new RaiseBack(ControlMode.Velocity, Config.CLIMB_SPEED);

        raiseFront.whenActive(raiseFrontCommand);
        raiseFront.cancelWhenReleased(raiseFrontCommand);
        lowerFront.whenActive(lowerFrontCommand);
        lowerFront.cancelWhenReleased(lowerFrontCommand);

        raiseBack.whenActive(raiseBackCommand);
        raiseBack.cancelWhenReleased(raiseBackCommand);
        lowerBack.whenActive(lowerBackCommand);
        lowerBack.cancelWhenReleased(lowerBackCommand);
    }

    @Override
    public Joystick getJoystick(int index) {
        if (index != 0) {
            throw new IllegalArgumentException("The only valid joystick index is 0");
        }
        return yoke;
    }
}
