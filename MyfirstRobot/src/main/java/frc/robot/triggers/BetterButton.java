package frc.robot.triggers;/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/9/19 by christopher.johnson
*/

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;

public class BetterButton extends JoystickButton {

    private class InvertedButton extends Trigger {

        private JoystickButton button;

        private InvertedButton(JoystickButton button) {
            this.button = button;
        }

        @Override
        public boolean get() {
            return !button.get();
        }
    }

    private InvertedButton invertedButton;

    /**
     * Create a joystick button for triggering commands.
     *
     * @param joystick     The GenericHID object that has the button (e.g. Joystick, KinectStick,
     *                     etc)
     * @param buttonNumber The button number (see {@link GenericHID#getRawButton(int) }
     */
    public BetterButton(GenericHID joystick, int buttonNumber) {
        super(joystick, buttonNumber);
        invertedButton = new InvertedButton(this);
    }

    public void cancelWhenReleased(Command command) {
        invertedButton.cancelWhenActive(command);
    }
}
