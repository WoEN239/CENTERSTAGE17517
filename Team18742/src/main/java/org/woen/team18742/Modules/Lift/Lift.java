package org.woen.team18742.Modules.Lift;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.woen.team18742.Collectors.BaseCollector;
import org.woen.team18742.Tools.Devices;
import org.woen.team18742.Tools.PID;
import org.woen.team18742.Tools.ToolTelemetry;

@Config
public class Lift {
    private final DcMotor _liftMotor;

    private final DigitalChannel _ending1, _ending2;

    private boolean _ending1State = false, _ending2State = false;

    public static double PCoef = 0.1, ICoef = 0, DCoef = 0.1;

    private final PID _liftPid = new PID(PCoef, ICoef, DCoef, 1, 1);
    private double _targetPoseDouble = 0;

    public Lift(BaseCollector collector) {
        _ending1 = Devices.Ending1;
        _ending2 = Devices.Ending2;

        _liftMotor = Devices.LiftMotor;
        _liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        _liftMotor.setPower(0);

        ResetLift();

        _ending1.setMode(DigitalChannel.Mode.INPUT);
        _ending2.setMode(DigitalChannel.Mode.INPUT);
    }

    private void ResetLift(){
        _liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        _liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void Update() {
        _liftPid.UpdateCoefs(PCoef, ICoef, DCoef);

        _ending1State = _ending1.getState();
        _ending2State = _ending2.getState();
        
        _liftMotor.setPower(Math.max(_liftPid.Update(_targetPoseDouble - _liftMotor.getCurrentPosition()), 0.007));

        ToolTelemetry.AddLine("Lift end1 = " + _ending1State + " end = " + _ending2State);

        /*if(_ending2State)
            ResetLift();*/
    }

    public boolean isATarget() {
        return Math.abs(_liftPid.Err) < 30;
    }

    public boolean isDown(){
        return _liftPose == LiftPose.DOWN && isATarget();
    }
    public boolean isUp(){
        return _liftPose == LiftPose.UP && isATarget();
    }
    public boolean isAverage(){return  _liftPose == LiftPose.AVERAGE && isATarget();}

    public void SetLiftPose(LiftPose pose) {
        _targetPoseDouble = pose.Pose;
        _liftPose = pose;
    }

    public void Start() {
        _liftPid.Start();
    }

    private LiftPose _liftPose = LiftPose.DOWN;
}