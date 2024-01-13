package org.woen.team17517.Programms.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class AutonomSample extends AutonomBaseClass {
    @Override
    public Runnable[] getBlueRight() {
        return new Runnable[]{
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 1370, 0),
                () -> robot.timer.getTimeForTimer(2.2d / 1.5d),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 0, 0),
                () -> robot.timer.getTimeForTimer(0.2),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 0, 500 * (12 / robot.voltageSensorPoint.getVol())),
                () -> robot.timer.getTimeForTimer(2),

                () -> robot.driveTrainVelocityControl.moveRobotCord(0, -1000, 0),
                () -> robot.timer.getTimeForTimer(5.2),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 1000, 0),
                () -> robot.timer.getTimeForTimer(0.6),
                () -> robot.driveTrainVelocityControl.moveRobotCord(1000, 0, 0),
                () -> robot.timer.getTimeForTimer(0.5),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 0, 0),
                () -> robot.timer.getTimeForTimer(0.1),
                () -> robot.lift.moveUP(),
                () -> robot.timer.getTimeForTimer(1.5),
                () -> robot.grabber.perekidFinish(),
                () -> robot.driveTrainVelocityControl.moveRobotCord(000, 0, 0),
                () -> robot.timer.getTimeForTimer(1),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, -1000, 0),
                () -> robot.timer.getTimeForTimer(0.5),


                () -> robot.grabber.openGraber(),
                () -> robot.timer.getTimeForTimer(1.5),
                () -> robot.grabber.perekidStart(),
                () -> robot.timer.getTimeForTimer(0.5),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 500, 0),
                () -> robot.timer.getTimeForTimer(0.1),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 0, 0),
                () -> robot.timer.getTimeForTimer(0.1)


        };
    }


    @Override
    public Runnable[] getRedLeft() {
        return new Runnable[]{
                () -> robot.driveTrainVelocityControl.moveRobotCord(1000, 0, 0),
                () -> robot.timer.getTimeForTimer(1.25),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 0, 0),
                () -> robot.timer.getTimeForTimer(0.5),
                () -> robot.lift.moveUP(),
                () -> robot.timer.getTimeForTimer(1),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, -1000, 0),
                () -> robot.timer.getTimeForTimer(1),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 0, 0),
                () -> robot.timer.getTimeForTimer(0.5),
                () -> robot.grabber.perekidFinish(),
                () -> robot.timer.getTimeForTimer(1),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, -1000, 0),
                () -> robot.timer.getTimeForTimer(1),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 0, 0),
                () -> robot.timer.getTimeForTimer(0.5),
                () -> robot.grabber.openGraber(),
                () -> robot.timer.getTimeForTimer(1.5),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 1000, 0),
                () -> robot.timer.getTimeForTimer(0.0)
        };
    }


    @Override
    public Runnable[] getRedRight() {
        return new Runnable[]{
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 1370, 0),
                () -> robot.timer.getTimeForTimer(2.2d / 1.5d),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 0, 0),
                () -> robot.timer.getTimeForTimer(0.2),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 0, -500 * (12 / robot.voltageSensorPoint.getVol())),
                () -> robot.timer.getTimeForTimer(2),

                () -> robot.driveTrainVelocityControl.moveRobotCord(0, -1000, 0),
                () -> robot.timer.getTimeForTimer(5.2),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 1000, 0),
                () -> robot.timer.getTimeForTimer(0.6),
                () -> robot.driveTrainVelocityControl.moveRobotCord(-1000, 0, 0),
                () -> robot.timer.getTimeForTimer(0.4),
                () -> robot.driveTrainVelocityControl.moveRobotCord(-800, 0, 0),
                () -> robot.timer.getTimeForTimer(2),
                () -> robot.lift.moveUP(),
                () -> robot.timer.getTimeForTimer(1.5),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, -1000, 0),
                () -> robot.timer.getTimeForTimer(1),


                () -> robot.grabber.perekidFinish(),
                () -> robot.grabber.openGraber(),
                () -> robot.timer.getTimeForTimer(1.5),
                () -> robot.grabber.perekidStart(),
                () -> robot.timer.getTimeForTimer(0.5),
                () -> robot.driveTrainVelocityControl.moveRobotCord(0, 500, 0),
                () -> robot.timer.getTimeForTimer(0.1),


        };
    }

    @Override
    public Runnable[] getBlueLeft() {
        return new Runnable[]{
                /* () -> robot.driveTrainVelocityControl.moveRobotCord(-1000, 0, 0),
                 () -> robot.timer.getTimeForTimer(1.25),
                 () -> robot.driveTrainVelocityControl.moveRobotCord(0, 0, 0),
                 () -> robot.timer.getTimeForTimer(0.5),
                 () -> robot.lift.moveUP(),
                 () -> robot.timer.getTimeForTimer(1),
                 () -> robot.driveTrainVelocityControl.moveRobotCord(0, -1000, 0),
                 () -> robot.timer.getTimeForTimer(1),
                 () -> robot.driveTrainVelocityControl.moveRobotCord(0, 0, 0),
                 () -> robot.timer.getTimeForTimer(0.5),
                 () -> robot.grabber.perekidFinish(),
                 () -> robot.timer.getTimeForTimer(1),
                 () -> robot.driveTrainVelocityControl.moveRobotCord(0, -1000, 0),
                 () -> robot.timer.getTimeForTimer(1),
                 () -> robot.driveTrainVelocityControl.moveRobotCord(0, 0, 0),
                 () -> robot.timer.getTimeForTimer(0.5),
                 () -> robot.grabber.openGraber(),
                 () -> robot.timer.getTimeForTimer(1.5),
                 () -> robot.driveTrainVelocityControl.moveRobotCord(0, 1000, 0),
                 () -> robot.timer.getTimeForTimer(0.0)

                 */
                () -> {
                    if (positionOfPixel == 1)
                        robot.driveTrainVelocityControl.moveGlobalCord(450,-900,0);
                    if (positionOfPixel == 2)
                        robot.driveTrainVelocityControl.moveGlobalCord(0,-1100,0);
                    if (positionOfPixel == 3)
                        robot.driveTrainVelocityControl.moveGlobalCord(-450,-900,0);

                },
                () -> robot.timer.getTimeForTimer(1)
        };
    }
}
