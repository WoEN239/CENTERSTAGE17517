package org.woen.team18742.Modules.Odometry;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import org.firstinspires.ftc.vision.VisionProcessor;
import org.woen.team18742.Collectors.AutonomCollector;
import org.woen.team18742.Collectors.BaseCollector;
import org.woen.team18742.Modules.Drivetrain;
import org.woen.team18742.Modules.Gyroscope;
import org.woen.team18742.Modules.Manager.AutonomModule;
import org.woen.team18742.Modules.Manager.IRobotModule;
import org.woen.team18742.Modules.OdometryHandler;
import org.woen.team18742.Tools.Configs;
import org.woen.team18742.Tools.ExponentialFilter;
import org.woen.team18742.Tools.ToolTelemetry;
import org.woen.team18742.Tools.Vector2;

@AutonomModule
public class Odometry implements IRobotModule {
    private double _oldRotate = 0, _oldOdometrXLeft, _oldOdometrXRight, _oldOdometrY;

    public Vector2 Position = new Vector2();
    private Drivetrain _drivetrain;
    private Gyroscope _gyro;
    private double _leftForwardDrive = 0, _leftBackDrive = 0, _rightForwardDrive = 0, _rightBackDrive = 0;
    private CVOdometry _CVOdometry;
    private OdometryHandler _odometrs;

    private final ExponentialFilter _filterX = new ExponentialFilter(Configs.Odometry.XCoef), _filterY = new ExponentialFilter(Configs.Odometry.YCoef);

    private AutonomCollector _collector;

    @Override
    public void Init(BaseCollector collector) {
        _CVOdometry = new CVOdometry(collector);

        _drivetrain = collector.GetModule(Drivetrain.class);
        _gyro = collector.GetModule(Gyroscope.class);
        _odometrs = collector.GetModule(OdometryHandler.class);

        if(collector instanceof AutonomCollector)
            _collector = (AutonomCollector) collector;

    }

    public VisionProcessor GetProcessor(){
        return _CVOdometry.GetProcessor();
    }

    @Override
    public void Update() {
        _filterX.UpdateCoef(Configs.Odometry.XCoef);
        _filterY.UpdateCoef(Configs.Odometry.YCoef);

        double deltaX, deltaY;

        if(Configs.GeneralSettings.IsUseOdometrs){
            double deltaRotate = _gyro.GetRadians() - _oldRotate;

            double odometrXLeft = _odometrs.GetOdometerXLeft(), odometrY = _odometrs.GetOdometerY(), odometrXRight = _odometrs.GetOdometerXRight();

            deltaX = (odometrXLeft - _oldOdometrXLeft + odometrXRight - _oldOdometrXRight) / 2;
            deltaY = (odometrY - _oldOdometrY) - Configs.Odometry.RadiusOdometrY * deltaRotate;

            _oldOdometrXLeft = odometrXLeft;
            _oldOdometrXRight = odometrXRight;
            _oldOdometrY = odometrY;

            _oldRotate = _gyro.GetRadians();
        }
        else {
            double lfd = _drivetrain.GetLeftForwardEncoder();
            double lbd = _drivetrain.GetLeftBackEncoder();
            double rfd = _drivetrain.GetRightForwardEncoder();
            double rbd = _drivetrain.GetRightBackEncoder();

            double deltaLfd = lfd - _leftForwardDrive, deltaLbd = lbd - _leftBackDrive, deltaRfd = rfd - _rightForwardDrive, deltaRbd = rbd - _rightBackDrive;

            deltaX = deltaLfd + deltaLbd + deltaRfd + deltaRbd;
            deltaY = -deltaLfd + deltaLbd + deltaRfd - deltaRbd;

            deltaY = deltaY * 0.8;

            _leftForwardDrive = lfd;
            _leftBackDrive = lbd;
            _rightBackDrive = rbd;
            _rightForwardDrive = rfd;
        }

        Position.X += deltaX * cos(_gyro.GetRadians()) + deltaY * sin(_gyro.GetRadians());
        Position.Y += -deltaX * sin(_gyro.GetRadians()) + deltaY * cos(_gyro.GetRadians());

        _CVOdometry.Update();

        if(!_CVOdometry.IsZero) {
            Position.X = _filterX.Update(Position.X, _CVOdometry.Position.X);
            Position.Y = _filterY.Update(Position.Y, _CVOdometry.Position.Y);
        }

        ToolTelemetry.DrawCircle(Position, 10, "#FFFFFF");
        ToolTelemetry.AddLine("OdometryX :" + Position);
    }

    @Override
    public void Stop() {}

    @Override
    public void Start(){
        _filterX.Reset();
        _filterY.Reset();

        if(_collector != null)
            Position = _collector.StartPosition.Position.copy();
    }
}