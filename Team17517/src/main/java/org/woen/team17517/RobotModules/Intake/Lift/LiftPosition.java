package org.woen.team17517.RobotModules.Intake.Lift;

import com.acmerobotics.dashboard.config.Config;

@Config
public enum LiftPosition {
    DOWN,UP,BACKDROPDOWN;
    public static int down = 0;
    public static int up = 2500;
    public static int backdropdown = 800;
    public int get(){
        switch (this){
            default:
            case DOWN:
                return down;
            case UP:
                return up;
            case BACKDROPDOWN:
                return backdropdown;
        }
    }
}

