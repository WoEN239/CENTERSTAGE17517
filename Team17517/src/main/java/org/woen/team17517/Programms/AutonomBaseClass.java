package org.woen.team17517.Programms;

import android.icu.text.Transliterator;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.woen.team17517.R;
import org.woen.team17517.Robot.Button;
import org.woen.team17517.Robot.UltRobot;

import java.nio.Buffer;
import java.util.HashMap;
@TeleOp
public class AutonomBaseClass extends LinearOpMode{
    UltRobot robot;
    DcMotor left_front_drive;
    DcMotor left_back_drive;
    DcMotor right_front_drive;
    DcMotor right_back_drive;

    boolean dpadUp = false;
    boolean dpadDown = false;
    boolean dpadRight = false;
    boolean dpadLeft = false;
    boolean rightBumper = gamepad1.right_bumper;

    Button button = new Button();
    StartTeam startTeam = StartTeam.BlUE;
    StartPosition startPosition = StartPosition.RIGHT;
    @Override
    public void waitForStart() {
        long  timeToSleep= 0;
        while (!isStarted()) {
            if (button.update(dpadDown)){
                startTeam = StartTeam.BlUE;
            }
            if (button.update(dpadUp)){
                startTeam = StartTeam.RED;
            }
            if (button.update(dpadLeft)){
                startPosition = StartPosition.LEFT;
            }
            if (button.update(dpadRight)){
                startPosition = StartPosition.RIGHT;
            }
            if (button.update(rightBumper)){
                timeToSleep += 1000;
            }
            telemetry.addData("Team",startTeam);
            telemetry.addData("Position",startPosition);
            telemetry.update();
            }
            sleep(timeToSleep);
    }


    enum StartTeam{
        BlUE,
        RED
    }
    enum StartPosition{
        RIGHT,
        LEFT
    }
    Runnable[] blueRight;
    Runnable[] blueLeft;
    Runnable[] redRight;
    Runnable[] redLeft;

    @Override
    public void runOpMode(){
        robot = new UltRobot(this);
        left_front_drive = robot.linearOpMode.hardwareMap.dcMotor.get("left_front_drive");
        left_back_drive = robot.linearOpMode.hardwareMap.dcMotor.get("left_back_drive");
        right_front_drive = robot.linearOpMode.hardwareMap.dcMotor.get("right_front_drive");
        right_back_drive = robot.linearOpMode.hardwareMap.dcMotor.get("right_back_drive");

        waitForStart();

        switch (startTeam) {
            case BlUE:
                switch (startPosition){
                    case LEFT:
                        robot.updateWhilePositionFalse(blueLeft);
                        break;
                    case RIGHT:
                        robot.updateWhilePositionFalse(blueRight);
                        break;
                }
            case RED:
                switch (startPosition) {
                    case LEFT:
                        robot.updateWhilePositionFalse(redLeft);
                        break;
                    case RIGHT:
                        robot.updateWhilePositionFalse(redRight);
                        break;
                }
        }
    }
}