package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.RobotMap;

public class LimitSwitch
{
    private DigitalInput raw;

    public LimitSwitch(int port)
    {
        raw = new DigitalInput(port);
        
    }
    public boolean getPressed()
    {
        return raw.get();
    }

}