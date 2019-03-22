/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import com.analog.adis16448.frc.ADIS16448_IMU;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Config;
import frc.robot.DirectionEnum;
import frc.robot.commands.HandleDrive;


/**
 * Add your docs here.
 */
public class Chassis extends Subsystem implements PIDOutput {
    public CANSparkMax flDrive, frDrive, blDrive, brDrive;
    public static double kP = 1, kI = 0, kD = 0, kF = 0;
    public static double percentTolerance = 5f;
    private ADIS16448_IMU gyro;
    public PIDController turnPid;
    private LineSensor lineSensorLeft, lineSensorCenter, lineSensorRight;
    private LimitSwitch bumpSwitch;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private double flStartTemp, frStartTemp, blStartTemp, brStartTemp;

    public Chassis() {
        gyro = new ADIS16448_IMU();

        flDrive = new CANSparkMax(Config.Chassis.Motors.FRONT_LEFT, MotorType.kBrushless);
        frDrive = new CANSparkMax(Config.Chassis.Motors.FRONT_RIGHT, MotorType.kBrushless);
        blDrive = new CANSparkMax(Config.Chassis.Motors.BACK_LEFT, MotorType.kBrushless);
        brDrive = new CANSparkMax(Config.Chassis.Motors.BACK_RIGHT, MotorType.kBrushless);
        flDrive.setOpenLoopRampRate(0.3);
        frDrive.setOpenLoopRampRate(0.3);
        blDrive.setOpenLoopRampRate(0.3);
        brDrive.setOpenLoopRampRate(0.3);
        turnPid = new PIDController(0, 0, 0, gyro, this);

        flStartTemp = flDrive.getMotorTemperature();
        frStartTemp = frDrive.getMotorTemperature();
        blStartTemp = blDrive.getMotorTemperature();
        brStartTemp = brDrive.getMotorTemperature();

        //Line sensors
        lineSensorLeft = new LineSensor(Config.Chassis.Sensors.LINE_SENSOR_0);
        lineSensorCenter = new LineSensor(Config.Chassis.Sensors.LINE_SENSOR_1);
        lineSensorRight = new LineSensor(Config.Chassis.Sensors.LINE_SENSOR_2);

        bumpSwitch = new LimitSwitch(Config.Chassis.Sensors.BUMP_SWITCH);

        setPid();
        // going straight.
        flDrive.setInverted(false);
        brDrive.setInverted(true);
        frDrive.setInverted(true);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new HandleDrive());
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    //gyro crap
    public double getZ() {
        return gyro.getAngleZ();
    }

    public double getY() {
        return gyro.getAngleY();
    }

    public double getX() {
        return gyro.getAngleX();
    }

    public void setPid() {
        turnPid.setInputRange(-179, 179);
        turnPid.setP(kP);
        turnPid.setI(kI);
        turnPid.setD(kD);
        turnPid.setF(kF);
        turnPid.setOutputRange(-1, 1);
        turnPid.setPercentTolerance(percentTolerance);
        turnPid.setContinuous(true);
    }

    public void startTurnPid(double setpoint) {
        setPid();
        turnPid.setSetpoint(setpoint);
        turnPid.enable();
    }

    public void setTurningPidEnabled(boolean enabled) {
        turnPid.setEnabled(enabled);
    }

    public boolean touchingWall() {
        return bumpSwitch.getPressed();
    }

    /**
     * Drives the robot using raw power.
     */
    public void drive(double left, double right) {
        flDrive.set(left);
        frDrive.set(right);
        blDrive.set(left);
        brDrive.set(right);
    }

    @Override
    public void pidWrite(double output) {
        /*drive(output, -output);*/
    }

    @Override
    public void periodic() {
        if (flDrive.getMotorTemperature() > flStartTemp + 15) {
            DriverStation.reportWarning(String.format("Front left drive motor is overheating! (%f degrees C)", flDrive.getMotorTemperature()), false);
        }
        if (frDrive.getMotorTemperature() > frStartTemp + 15) {
            DriverStation.reportWarning(String.format("Front right drive motor is overheating! (%f degrees C)", frDrive.getMotorTemperature()), false);
        }
        if (blDrive.getMotorTemperature() > blStartTemp + 15) {
            DriverStation.reportWarning(String.format("Back left drive motor is overheating! (%f degrees C)", blDrive.getMotorTemperature()), false);
        }
        if (brDrive.getMotorTemperature() > brStartTemp + 15) {
            DriverStation.reportWarning(String.format("Back right drive motor is overheating! (%f degrees C)", brDrive.getMotorTemperature()), false);
        }
    }

    public boolean getLeft() {
        return lineSensorLeft.onTape();
    }

    public boolean getCenter() {
        return lineSensorCenter.onTape();
    }

    public boolean getRight() {
        return lineSensorRight.onTape();
    }

    public DirectionEnum directionToTurn() {
        boolean left = getLeft();
        boolean center = getCenter();
        boolean right = getRight();


        if (left && !right) {
            return DirectionEnum.LEFT;
        }
        if (right && !left) {
            return DirectionEnum.RIGHT;
        }
        if (!left && center && !right) {
            return DirectionEnum.CENTER;
        }
        if (left && center && right)
            return DirectionEnum.CENTER;
        if (left && !center && !right)
            return DirectionEnum.RIGHT;
        if (right && !center && !left)
            return DirectionEnum.LEFT;

        return DirectionEnum.UNKNOWN;
    }


}
