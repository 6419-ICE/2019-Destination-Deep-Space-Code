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
import frc.robot.commands.RaiseBackClimber;
import frc.robot.commands.RaiseFrontClimber;
import frc.robot.commands.SetWristPosition;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick joystick1;
  public Joystick joystick2;
  Button followLine;
  Button raiseFront;
  Button raiseBack;
  Button lowerFront;
  Button  lowerBack;
 Button upWrist;
 Button lowerWrist;
  private Button succ;
  private Button blow;
  public OI()
  {
    joystick1 = new Joystick(0);
 //   joystick2 = new Joystick(1);
    succ = new JoystickButton(joystick1, 4);
    blow = new JoystickButton(joystick1, 6);
    succ.whileHeld(new SpinBallIntake(.7, 0));
    blow.whileHeld(new SpinBallIntake(-.7, 0));

    
    raiseFront = new JoystickButton(joystick2, 1);
    lowerFront = new JoystickButton(joystick2, 2);
    raiseBack = new JoystickButton(joystick2, 3);
    lowerBack = new JoystickButton(joystick2, 4);

    raiseFront.whileHeld(new RaiseFrontClimber(ClimberDirection.UP));
    lowerFront.whileHeld(new RaiseFrontClimber(ClimberDirection.DOWN));
    raiseBack.whileHeld(new RaiseBackClimber(ClimberDirection.UP));
    lowerBack.whileHeld(new RaiseBackClimber(ClimberDirection.DOWN));
    upWrist = new JoystickButton(joystick1, 5);
    lowerWrist = new JoystickButton(joystick1, 3);
    upWrist.whileHeld(new SetWristPosition(Wrist.up));
    lowerWrist.whileHeld(new SetWristPosition(Wrist.DOWN));

    followLine = new JoystickButton(joystick1, 2);
    followLine.whileHeld(new FollowLine());

  }

}
