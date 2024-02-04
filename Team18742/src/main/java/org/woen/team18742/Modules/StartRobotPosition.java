package org.woen.team18742.Modules;

import static java.lang.Math.PI;

import org.woen.team18742.Tools.Vector2;

public enum StartRobotPosition {
    RED_BACK(new Vector2(-98.2, -155.8), PI / 2),
    RED_FORWARD(new Vector2(40, -156.2), PI / 2),
    BLUE_BACK(new Vector2(-93.7, 156.4), -PI / 2),
    BLUE_FORWAD(new Vector2(35.0, 157.8), -PI / 2);

    private StartRobotPosition(Vector2 vector, double rotation){
        Position = vector;
        Rotation = rotation;
    }

    public Vector2 Position;
    public double Rotation;
}
