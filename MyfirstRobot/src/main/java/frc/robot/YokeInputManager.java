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
import frc.robot.commands.SetClimberDriverPower;
import frc.robot.commands.SetClimberPower;
import frc.robot.triggers.POVTrigger;

public class YokeInputManager implements InputManager {

    private Joystick yoke;

    @Override
    public void init() {
        yoke = new Joystick(0);
        if (yoke.getAxisCount() < 5) {
            System.err.println("Warning! You do not have a yoke plugged in!");
        }

        JoystickButton raiseClimber = new JoystickButton(yoke, 11);
        JoystickButton lowerClimber = new JoystickButton(yoke, 12);
        raiseClimber.whileActive(new SetClimberPower(ControlMode.Velocity, 1500));
        lowerClimber.whileActive(new SetClimberPower(ControlMode.Velocity, -1500));

        Trigger climberDriverForwards = new POVTrigger(yoke, 270, 90);
        Trigger climberDriverBackwards = new POVTrigger(yoke, 90, 270);
        climberDriverForwards.whileActive(new SetClimberDriverPower(ControlMode.PercentOutput, -0.5));
        climberDriverBackwards.whileActive(new SetClimberDriverPower(ControlMode.PercentOutput, 0.5));
    }

    @Override
    public Joystick getJoystick(int index) {
        if (index != 0) {
            throw new IllegalArgumentException("The only valid joystick index is 0");
        }
        return yoke;
    }
}
