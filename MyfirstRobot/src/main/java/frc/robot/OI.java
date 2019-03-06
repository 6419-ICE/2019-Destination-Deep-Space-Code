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
import edu.wpi.first.wpilibj.buttons.Trigger;

import frc.robot.subsystems.Wrist;
import frc.robot.commands.SpinBallIntake;
import frc.robot.commands.FollowLine;
import frc.robot.commands.MoveClimberForward;
import frc.robot.commands.SetWristPosition;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick joystick1;
  public Joystick joystick2;
 public  Button followLine;
 public Trigger climberForward;
 public Trigger climberBackward;
 Button upWrist;
 Button lowerWrist;
  private Button succ;
  private Button blow;
  public OI()
  {
    joystick1 = new Joystick(0);
    joystick2 = new Joystick(1);

 //initialize the buttons
    succ = new JoystickButton(joystick1, 4);
    blow = new JoystickButton(joystick1, 6);

    upWrist = new JoystickButton(joystick1, 5);
    lowerWrist = new JoystickButton(joystick1, 3);
    
    followLine = new JoystickButton(joystick1, 2);

    //Set actions for buttons
    succ.whileHeld(new SpinBallIntake(.7, 0));
    blow.whileHeld(new SpinBallIntake(-.7, 0));
    followLine.whileHeld(new FollowLine());
    upWrist.whileHeld(new SetWristPosition(Wrist.up));
    lowerWrist.whileHeld(new SetWristPosition(Wrist.DOWN));


    //Custom triggers
    climberForward = new Trigger(){
    
      @Override
      public boolean get() {
        return joystick1.getPOV() == 0;
      }
    };
    climberBackward = new Trigger(){
    
      @Override
      public boolean get() {
        {
          return joystick1.getPOV() == 180;
        }
      }
    };

    // Triggers actions\
  climberForward.whileActive(new MoveClimberForward(ClimberDriveDirection.FORWARDS));
  }
}