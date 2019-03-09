package frc.robot.subsystems;
/*
This code belongs to FRC Team 6419 'ICE', as part of
their codebase for the 2019 season.

Copyright (c) 2019 ICE Robotics

Created 3/7/19 by christopher.johnson
*/

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.HandleClimber;

public class Climber extends Subsystem {

    private WPI_TalonSRX frontMotor, backMotor;
    private LimitSwitch frontSwitch, backSwitch;

    public Climber() {
        frontMotor = new WPI_TalonSRX(RobotMap.FRONT_CLIMBER);
        backMotor = new WPI_TalonSRX(RobotMap.BACK_CLIMBER);
        frontSwitch = new LimitSwitch(RobotMap.FRONT_CLIMBER_BUMP);
        backSwitch = new LimitSwitch(RobotMap.BACK_CLIMBER_BUMP);

        frontMotor.setNeutralMode(NeutralMode.Brake);
        backMotor.setNeutralMode(NeutralMode.Brake);

        frontMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 100);
        backMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 100);

        frontMotor.setSelectedSensorPosition(0, 0, 100);
        backMotor.setSelectedSensorPosition(0, 0, 100);

        frontMotor.configAllowableClosedloopError(0, 100);
        backMotor.configAllowableClosedloopError(0, 100);
        frontMotor.configAllowableClosedloopError(1, 0);

        frontMotor.configPeakOutputForward(1);
        frontMotor.configPeakOutputReverse(-1);
        backMotor.configPeakOutputForward(1);
        backMotor.configPeakOutputReverse(-1);
        frontMotor.configNominalOutputForward(0.3);
        frontMotor.configNominalOutputReverse(-0.3);
        backMotor.configNominalOutputForward(0.3);
        backMotor.configNominalOutputReverse(-0.3);
        // select the back motor's encoder as RemoteSensor0
        frontMotor.configRemoteFeedbackFilter(backMotor.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor, 0);
        frontMotor.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.CTRE_MagEncoder_Relative, 100);
        frontMotor.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor0, 100);
        //frontMotor.configSelectedFeedbackCoefficient(RobotMap.CLIMBER_AUX_PID_COEF, 1, 100);
        // select the feedback sensor as difference for the auxiliary pid
        frontMotor.configSelectedFeedbackSensor(RemoteFeedbackDevice.SensorDifference, 1, 100);

        frontMotor.setSensorPhase(false);

        frontMotor.config_kP(0, 1);
        frontMotor.config_kD(0, 1);
        backMotor.config_kP(0, 1);
        backMotor.config_kD(0, 1);

        frontMotor.config_kP(1, 1);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new HandleClimber());
    }

    public void setFront(ControlMode controlMode, double value) {
        frontMotor.set(controlMode, value);
    }

    public void setBack(ControlMode controlMode, double value) {
        backMotor.set(controlMode, value);
    }

    public void set(ControlMode controlMode, double value) {
        setFront(controlMode, value);
        setBack(controlMode, value);
    }

    public int getFrontVelocity() {
        return frontMotor.getSelectedSensorVelocity();
    }

    public int getBackVelocity() {
        return backMotor.getSelectedSensorVelocity();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        setName("Climber");
        frontMotor.setName("Front motor");
        frontMotor.setSubsystem(getName());
        backMotor.setName("Back motor");
        backMotor.setSubsystem(getName());
        frontSwitch.setName("Front switch");
        frontSwitch.setSubsystem(getName());
        backSwitch.setName("Back switch");
        backSwitch.setSubsystem(getName());
        super.initSendable(builder);
    }
}