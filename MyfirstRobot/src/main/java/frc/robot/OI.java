/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.subsystems.Wrist;
import frc.robot.commands.SpinBallIntake;
import frc.robot.commands.FollowLine;
import frc.robot.commands.SetWristPosition;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private InputManager inputManager;

    OI(InputManager inputManager) {
        this.inputManager = inputManager;
        inputManager.init();
    }

    public Joystick getJoystick(int index) {
        return inputManager.getJoystick(index);
    }
}
