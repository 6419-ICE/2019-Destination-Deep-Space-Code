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
import frc.robot.commands.*;
import frc.robot.triggers.BetterButton;
import frc.robot.triggers.POVTrigger;

public class YokeInputManager implements InputManager {

    private Joystick yoke, weapons;

    @Override
    public void init() {
        yoke = new Joystick(0);
        if (yoke.getAxisCount() < 5) {
            DriverStation.reportWarning("You do not have a yoke plugged in!", false);
        }
        weapons = new Joystick(1);

        BetterButton raiseFront = new BetterButton(weapons, 3);
        BetterButton lowerFront = new BetterButton(weapons, 5);
        BetterButton raiseBack = new BetterButton(weapons, 4);
        BetterButton lowerBack = new BetterButton(weapons, 6);

        Command raiseFrontCommand = new RaiseFront(ControlMode.PercentOutput, 1),
                lowerFrontCommand = new RaiseFront(ControlMode.PercentOutput, -1),
                raiseBackCommand = new RaiseBack(ControlMode.PercentOutput, 1),
                lowerBackCommand = new RaiseBack(ControlMode.PercentOutput, -1);

        raiseFront.whenActive(raiseFrontCommand);
        raiseFront.cancelWhenReleased(raiseFrontCommand);
        lowerFront.whenActive(lowerFrontCommand);
        lowerFront.cancelWhenReleased(lowerFrontCommand);

        raiseBack.whenActive(raiseBackCommand);
        raiseBack.cancelWhenReleased(raiseBackCommand);
        lowerBack.whenActive(lowerBackCommand);
        lowerBack.cancelWhenReleased(lowerBackCommand);

        JoystickButton load = new JoystickButton(weapons, 2),
                       fire = new JoystickButton(weapons, 1);

        load.whileActive(new SetBallIntakePower(-1));
        fire.whileActive(new SetBallIntakePower(1));

        Trigger tiltUp = new POVTrigger(weapons, 270, 90),
                tiltDown = new POVTrigger(weapons, 90, 270);

        tiltUp.whileActive(new SetTilterPower(0.4));
        tiltDown.whileActive(new SetTilterPower(-0.3));

        JoystickButton raiseFondler = new JoystickButton(weapons, 9),
                       lowerFondler = new JoystickButton(weapons, 10);

        raiseFondler.whileActive(new SetFondlerLiftPower(0.5));
        lowerFondler.whileActive(new SetFondlerLiftPower(-0.5));
        raiseFondler.whenReleased(new AutoTensionLift());
        lowerFondler.whenReleased(new AutoTensionLift());
    }

    @Override
    public Joystick getJoystick(int index) {
        if (index < 0 || index > 1) {
            throw new IllegalArgumentException("The only valid joystick indexes are 0 and 1");
        }
        if (index == 0) {
            return yoke;
        } else {
            return weapons;
        }
    }
}
