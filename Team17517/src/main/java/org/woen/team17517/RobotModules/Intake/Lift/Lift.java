package org.woen.team17517.RobotModules.Intake.Lift;

import static java.lang.Math.abs;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.woen.team17517.RobotModules.UltRobot;
import org.woen.team17517.Service.Button;
import org.woen.team17517.Service.PID;
import org.woen.team17517.Service.RobotModule;


@Config
public class Lift implements RobotModule {
    private DcMotorEx liftMotor;
    private DigitalChannel buttonUp;
    private DigitalChannel buttonDown;
    private double voltage;
    private LiftPosition targetPosition = LiftPosition.DOWN;
    private double targetSpeed = 0;
    public LiftPosition getTargetPosition(){
        return targetPosition;
    }
    private void setTargetPosition(LiftPosition targetPosition){this.targetPosition = targetPosition;}
    private LiftMode liftMode = LiftMode.AUTO;
    public LiftMode getLiftMode(){
        return liftMode;
    }
    UltRobot robot;
    private boolean liftAtTarget = true;
    public static double kp = 0.01;
    public static double ki = 0;
    public static double kd = 0;
    public static double kg = 0.1;
    public static double maxI  = 0;
    PID pid = new PID(kp,ki,kd,0,maxI,kg);
    public void setLiftMode(LiftMode mode){
        liftMode = mode;
    }
    public Lift(UltRobot robot) {
        this.robot = robot;
        liftMotor = robot.hardware.intakeAndLiftMotors.liftMotor;
        buttonUp = robot.hardware.sensors.buttonUp;
        buttonDown = robot.hardware.sensors.buttonDown;
        voltage = 12;
    }
    Button down = new Button();
    public boolean getUpSwitch(){
        return false;
    }
    public boolean getDownSwitch(){return down.update(buttonDown.getState());}
    public void setPower(double target) {
        liftMotor.setPower(target);
    }

    public void moveUP(){
        setTargetPosition(LiftPosition.UP);
        setLiftMode(LiftMode.AUTO);
    }
    public void moveDown(){
        setTargetPosition(LiftPosition.DOWN);
        setLiftMode(LiftMode.AUTO);
    }
    public void moveBackDropDown(){
        setTargetPosition(LiftPosition.BACKDROPDOWN);
        setLiftMode(LiftMode.AUTO);
    }

    private int encoderError  = 0;
    private int encoderPosition = 0;
    public int getPosition() {return encoderPosition;}
    private int cleanPosition = 0;
    public int getCleanPosition() {return cleanPosition;}
    public void updatePosition(){
        cleanPosition = liftMotor.getCurrentPosition();
        encoderPosition = cleanPosition - encoderError;
        if (getUpSwitch()){
            encoderError = liftMotor.getCurrentPosition() - LiftPosition.UP.get();
        }if (buttonDown.getState()){
            encoderError = liftMotor.getCurrentPosition() - LiftPosition.DOWN.get();
        }
    }

    public double getPower() {
        return power;
    }

    private double power = 0;
    public void update(){
        updatePosition();
        pid.setCoeficent(kp, ki, kd, 0, maxI, kg);
        voltage = robot.voltageSensorPoint.getVol();
        if (targetPosition != LiftPosition.DOWN) {
            power = pid.pid(targetPosition.get(), getPosition(), voltage);
            liftAtTarget = abs(targetPosition.get() - getPosition()) < 5;
        } else {
            liftAtTarget = buttonDown.getState();
            power =  liftAtTarget?0:-0.25 ;
        }
        setPower(power);
    }
    public double getCurent(){
        return liftMotor.getCurrent(CurrentUnit.AMPS);
    }
    @Override
    public boolean isAtPosition() {
        return liftAtTarget;
    }
}
