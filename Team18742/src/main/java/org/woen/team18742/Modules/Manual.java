package org.woen.team18742.Modules;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.woen.team18742.Collectors.BaseCollector;
import org.woen.team18742.Modules.Brush.Brush;
import org.woen.team18742.Modules.Brush.StaksBrush;
import org.woen.team18742.Modules.Lift.Lift;
import org.woen.team18742.Modules.Lift.LiftPose;
import org.woen.team18742.Modules.Manager.IRobotModule;
import org.woen.team18742.Modules.Manager.TeleopModule;
import org.woen.team18742.Tools.ToolTelemetry;
import org.woen.team18742.Tools.Vector2;

@TeleopModule
public class Manual implements IRobotModule {
    private boolean _gripOld = false, _brushOld = false;

    private Plane _plane;

    private Gamepad _gamepad;

    private Brush _brush;
    private Intake _intake;
    private Lift _lift;
    private Drivetrain _drivetrain;
    private Suspension _suspension;
    private StaksBrush _stacksbrush;

    @Override
    public void Init(BaseCollector collector) {
        _plane = new Plane(collector.Time);

        _gamepad = collector.Robot.gamepad1;

        _brush = collector.GetModule(Brush.class);
        _intake = collector.GetModule(Intake.class);
        _lift = collector.GetModule(Lift.class);
        _drivetrain = collector.GetModule(Drivetrain.class);
        _suspension = collector.GetModule(Suspension.class);
        _stacksbrush = collector.GetModule(StaksBrush.class);
    }

    @Override
    public void Update() {
        _plane.Update();

        _drivetrain.SimpleDriveDirection(
                new Vector2(-_gamepad.left_stick_y, -_gamepad.left_stick_x),
                -_gamepad.right_stick_x);

        boolean launchPlane = _gamepad.square;
        boolean liftUp = _gamepad.dpad_up;
        boolean liftAverage = _gamepad.dpad_right;
        boolean grip = _gamepad.triangle;
        boolean brushOn = _gamepad.cross;
        boolean brushReverseAndOff = _gamepad.circle;
        boolean planeTimerBypass = _gamepad.left_bumper;// зажать эту кнопку чтоб досрочно запустить самолетик
        boolean stacksBrush = _gamepad.right_bumper;
        double servotyaga = _gamepad.left_trigger;
        double motortyagakopka = _gamepad.right_trigger;
        boolean griperzapaa = _gamepad.dpad_right;
        if (grip && !_gripOld) {
            _intake.releaseGripper();
        }
        ToolTelemetry.AddLine("brushon = " + brushOn);
        if (brushOn) {
            if (!_brush.isBrusnOn()) {
                _brush.BrushEnable();

            }
        } else if (brushReverseAndOff) {
            _brush.BrushDisable();
            _brush.RevTimeRes();

        }
        if (stacksBrush) {
            _stacksbrush.servoSetDownPose();
        }
        if (brushReverseAndOff && _stacksbrush.brushIsDown()) {
            _stacksbrush.servoSetUpPose();
        }
        if (launchPlane)
            _plane.Launch(planeTimerBypass);
        else
            _plane.DeLaunch();

        if (liftUp)
            _lift.SetLiftPose(LiftPose.UP);
        else if (liftAverage)
            _lift.SetLiftPose(LiftPose.MIDDLE_UPPER);

        if (servotyaga > 0.2)
            _suspension.Active();
        else
            _suspension.Disable();

        _gripOld = grip;
        _brushOld = brushOn;

    }

    @Override
    public void Start() {
        _intake.setGripper(false);
    }
}