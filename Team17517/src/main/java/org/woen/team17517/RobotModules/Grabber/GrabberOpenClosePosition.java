package org.woen.team17517.RobotModules.Grabber;

import com.acmerobotics.dashboard.config.Config;

@Config
public enum GrabberOpenClosePosition {
    OPEN,CLOSE;
    public static double open;
    public static double close;
    public double get(){
        switch (this){
            case CLOSE:
                return close;
            default:
            case OPEN:
                return open;
        }
    }
}
