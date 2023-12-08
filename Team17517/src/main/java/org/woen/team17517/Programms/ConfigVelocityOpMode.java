package org.woen.team17517.Programms;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.woen.team17517.Robot.UltRobot;
import static java.lang.Math.signum;

@TeleOp
public class ConfigVelocityOpMode extends LinearOpMode {
    UltRobot robot;
    @Override
    public void runOpMode(){
        robot = new UltRobot(this);
        waitForStart();
        while(opModeIsActive()){
            double leftStickX = gamepad1.left_stick_x;
            double leftStickY = -gamepad1.left_stick_y;
            double rightStickX = gamepad1.right_stick_x;

            robot.driveTrainVelocityControl.vector.x =  leftStickX*1000;
            robot.driveTrainVelocityControl.vector.y =  leftStickY*1000;
            robot.driveTrainVelocityControl.targetH =  rightStickX*1000;
            robot.voltageSensorPoint.update();
            robot.telemetryOutput.update();
            robot.driveTrainVelocityControl.update();
        }
    }
}