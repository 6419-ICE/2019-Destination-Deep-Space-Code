package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;

public class LimitSwitch extends DigitalInput
{
    /**
     * Create an instance of a LimitSwitch class. Creates a limit switch given a channel.
     *
     * @param channel the DIO channel for the digital input 0-9 are on-board, 10-25 are on the MXP
     */
    public LimitSwitch(int channel) {
        super(channel);
    }

    @Override
    public boolean get() {
        return !super.get();
    }

    @Deprecated
    public boolean getPressed() {
        return get();
    }
}