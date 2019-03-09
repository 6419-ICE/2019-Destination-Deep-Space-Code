package frc.robot;/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/8/19 by christopher.johnson
*/

import edu.wpi.first.wpilibj.Joystick;

public interface InputManager {

    void init();

    Joystick getJoystick(int index);
}
