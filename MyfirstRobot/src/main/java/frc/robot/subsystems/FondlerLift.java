package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.robot.Config;
import frc.robot.commands.HandleFondlerLift;

// raising is positive
public class FondlerLift extends Subsystem {

    private CANSparkMax liftMotor;
    private LimitSwitch bottomSwitch;
    private boolean lastSwitchReading;

    public FondlerLift() {
        liftMotor = new CANSparkMax(Config.FondlerLift.Motors.LIFT_MOTOR, CANSparkMaxLowLevel.MotorType.kBrushless);
        liftMotor.setOpenLoopRampRate(0.25);
        bottomSwitch = new LimitSwitch(Config.FondlerLift.Sensors.BOTTOM_SWITCH);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new HandleFondlerLift());
    }

    @Override
    public void periodic() {
        boolean currentSwitch = bottomSwitch.get();
        if (currentSwitch) {
            liftMotor.set(Math.max(0, liftMotor.get()));
        }

        if (!currentSwitch && Config.FondlerLift.TOP_POSITION > 0 && getEncoderPosition() > Config.FondlerLift.TOP_POSITION) {
            liftMotor.set(Math.min(0, liftMotor.get()));
        }

        if (!currentSwitch && lastSwitchReading) {
            // falling edge
            DriverStation.reportWarning("Falling Edge Of Lift Switch", false);
            setEncoderPosition(0);
        }

        lastSwitchReading = currentSwitch;
    }

    public void setPower(double value) {
        liftMotor.set(value);
    }

    public double getEncoderPosition() {
        return liftMotor.getEncoder().getPosition();
    }

    public void setEncoderPosition(double position) {
        liftMotor.getEncoder().setPosition(position);
    }

    public double getEncoderVelocity() {
        return liftMotor.getEncoder().getVelocity();
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        setName("Fondler Lift");
        builder.addDoubleProperty("Output", liftMotor::getAppliedOutput, null);
        builder.addDoubleProperty("Input", liftMotor::get, null);
        builder.addDoubleProperty("Encoder Position", this::getEncoderPosition, this::setEncoderPosition);
        builder.addBooleanProperty("Bottom Switch", bottomSwitch::get, null);
        builder.addDoubleProperty("Encoder Velocity", this::getEncoderVelocity, null);
        super.initSendable(builder);
    }
}