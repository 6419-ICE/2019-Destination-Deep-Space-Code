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
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Config;
import frc.robot.commands.HandleClimber;

public class Climber extends PIDSubsystem {

    private WPI_TalonSRX frontMotor, backMotor;
    private LimitSwitch frontSwitch, backSwitch;

    private double pidOutput;

    public Climber() {
        super(0.1, 0, 0);
        setSetpoint(0);
        enable();
        setOutputRange(-100, 100);
        frontMotor = new WPI_TalonSRX(Config.FRONT_CLIMBER);
        backMotor = new WPI_TalonSRX(Config.BACK_CLIMBER);
        frontSwitch = new LimitSwitch(Config.FRONT_CLIMBER_BUMP);
        backSwitch = new LimitSwitch(Config.BACK_CLIMBER_BUMP);

        frontMotor.setNeutralMode(NeutralMode.Brake);
        backMotor.setNeutralMode(NeutralMode.Brake);

        frontMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 100);
        backMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 100);

        resetEncoders();

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
        /*frontMotor.configRemoteFeedbackFilter(backMotor.getDeviceID(), RemoteSensorSource.TalonSRX_SelectedSensor, 0);
        frontMotor.configSensorTerm(SensorTerm.Diff0, FeedbackDevice.CTRE_MagEncoder_Relative, 100);
        frontMotor.configSensorTerm(SensorTerm.Diff1, FeedbackDevice.RemoteSensor0, 100);
        //frontMotor.configSelectedFeedbackCoefficient(Config.CLIMBER_AUX_PID_COEF, 1, 100);
        // select the feedback sensor as difference for the auxiliary pid
        frontMotor.configSelectedFeedbackSensor(RemoteFeedbackDevice.SensorDifference, 1, 100);
        frontMotor.selectProfileSlot(1, 1);
        frontMotor.configAuxPIDPolarity(false);*/

        frontMotor.setSensorPhase(false);

        frontMotor.config_kP(0, 1);
        frontMotor.config_kD(0, 1);
        backMotor.config_kP(0, 1);
        backMotor.config_kD(0, 1);

        //frontMotor.config_kP(1, 1);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new HandleClimber());
    }

    public void resetEncoders() {
        frontMotor.setSelectedSensorPosition(0, 0, 100);
        backMotor.setSelectedSensorPosition(0, 0, 100);
    }

    public void setFront(ControlMode controlMode, double value) {
        // away from switch is negative velocity
        if (frontSwitch.get() && (controlMode == ControlMode.PercentOutput || controlMode == ControlMode.Velocity)) {
            frontMotor.set(controlMode, Math.max(0, value));
        } else {
            frontMotor.set(controlMode, value + ((controlMode == ControlMode.Velocity) ? pidOutput : 0));
        }
    }

    public void setBack(ControlMode controlMode, double value) {
        // away from switch is negative velocity
        if (backSwitch.get() && (controlMode == ControlMode.PercentOutput || controlMode == ControlMode.Velocity)) {
            backMotor.set(controlMode, Math.min(0, value));
        } else {
            backMotor.set(controlMode, value);
        }
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

    @Override
    protected double returnPIDInput() {
        return backMotor.getSelectedSensorPosition() - frontMotor.getSelectedSensorPosition();
    }

    @Override
    protected void usePIDOutput(double output) {
        System.out.println(output);
        SmartDashboard.putNumber("Sync Pid output", output);
        pidOutput = output;
    }

    @Override
    public void disable() {
        super.disable();
        pidOutput = 0;
    }
}