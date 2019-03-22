/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Config;
import frc.robot.commands.HandleBallIntake;

/**
 * Add your docs here.
 */
public class BallIntake extends Subsystem {
    private WPI_TalonSRX intake;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public BallIntake() {
        intake = new WPI_TalonSRX(Config.BallIntake.Motors.INTAKE_MOTOR);
        intake.setInverted(false);
    }

    public void setIntakePower(double input) {
        intake.set(ControlMode.PercentOutput, input);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        this.setDefaultCommand(new HandleBallIntake());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        setName("Ball Intake");
        intake.setName("Intake");
        intake.setSubsystem(getName());
        super.initSendable(builder);
    }
}
