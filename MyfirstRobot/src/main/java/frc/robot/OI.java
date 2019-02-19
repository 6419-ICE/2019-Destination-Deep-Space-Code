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
  public Joystick joystick3;
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
    joystick2 = new Joystick(1);

 //initialize the buttons
 //ball fondler
    succ = new JoystickButton(joystick1, 4);
    blow = new JoystickButton(joystick1, 6);

    //hatch grabber

    upWrist = new JoystickButton(joystick1, 5);
    lowerWrist = new JoystickButton(joystick1, 3);

    // follow line
    followLine = new JoystickButton(joystick1, 2);

    //Climber
    raiseFront = new JoystickButton(joystick1, 7);
    raiseBack = new JoystickButton(joystick1, 8);
    lowerFront = new JoystickButton(joystick1, 9);
    lowerBack = new JoystickButton(joystick1, 10);

    //Set actions for buttons
    //ball fondler
    succ.whileHeld(new SpinBallIntake(.7, 0));
    blow.whileHeld(new SpinBallIntake(-.7, 0));
    
    //follow line

    followLine.whileHeld(new FollowLine());

    //hatch grabber

    upWrist.whileHeld(new SetWristPosition(Wrist.up));
    lowerWrist.whileHeld(new SetWristPosition(Wrist.DOWN));

    //climber
    raiseFront.whileHeld(new RaiseFrontClimber(ClimberDirection.UP));
    raiseBack.whileHeld(new RaiseBackClimber(ClimberDirection.UP));
    lowerFront.whileHeld(new RaiseFrontClimber(ClimberDirection.DOWN));
    lowerBack.whileHeld(new RaiseBackClimber(ClimberDirection.DOWN));
    



  }

}
