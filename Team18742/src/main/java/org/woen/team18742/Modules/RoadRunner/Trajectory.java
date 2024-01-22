package org.woen.team18742.Modules.RoadRunner;

import static java.lang.Math.PI;

import com.acmerobotics.roadrunner.Vector2d;

import org.woen.team18742.Modules.Camera.Camera;
import org.woen.team18742.Tools.Bios;

public class Trajectory {
    public static RouteManager.MyTrajectoryBuilder GetTrajectory(RouteManager.MyTrajectoryBuilder builder, Camera camera){
        return builder.splineToConstantHeading(
                new Vector2d(Bios.GetStartPosition().Position.X + 20, Bios.GetStartPosition().Position.Y - 20), -PI / 2);
    }
}
