package org.woen.team17517.RobotModules.Transport.Lift;

public enum LiftPosition {
    DOWN(50), UP(950), UNKNOWN(0), FORAUTONOM(1000), YELLOWPIXEL(500), WHITEPIXEL(750);
    public int value;

    LiftPosition(int value) {
        this.value = value;
    }
}

