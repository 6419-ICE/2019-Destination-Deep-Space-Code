package frc.robot.triggers;/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/9/19 by christopher.johnson
*/

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;
import frc.robot.Util;

public class POVTrigger extends Trigger {

    private Joystick joystick;
    private int from, to;

    /**
     * Range of activation goes clockwise from the from param to the to param
     * @param joystick joystick to query for pov
     * @param from start of range
     * @param to end of range
     */
    public POVTrigger(Joystick joystick, int from, int to) {
        if (!Util.withinRange(0, 359, from)) {
            throw new IllegalArgumentException("from must be in the range [0, 360)");
        }
        if (!Util.withinRange(0, 359, to)) {
            throw new IllegalArgumentException("to must be in the range [0, 360)");
        }
        this.joystick = joystick;
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean get() {
        final int pov = joystick.getPOV();
        if (pov < 0) {
            return false;
        }
        if (from > to) {
            // crossing over 359 to 0
            return pov > from || pov < to;
        } else if (from == to) {
            // only activate on a certain angle
            return pov == from;
        } else {
            // continuous range
            return pov > from && pov < to;
        }
    }
}
