package org.woen.team18742.Collectors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.woen.team18742.Modules.Brush;
import org.woen.team18742.Modules.DriverTrain;
import org.woen.team18742.Modules.Gyroscope;
import org.woen.team18742.Modules.Intake;
import org.woen.team18742.Modules.Lift.Lift;
import org.woen.team18742.Tools.ToolTelemetry;

public class BaseCollector {
    public LinearOpMode CommandCode;
    public Gyroscope Gyro;
    public DriverTrain Driver;
    public org.woen.team18742.Modules.Lift.Lift Lift;
    public org.woen.team18742.Modules.Intake Intake;
    public ElapsedTime Time;
    public Brush Brush;

    public BaseCollector(LinearOpMode commandCode){
        CommandCode = commandCode;

        ToolTelemetry.SetTelemetry(CommandCode.telemetry);

        Time = new ElapsedTime();
        Lift = new Lift(this);
        Brush = new Brush(this);
        Gyro = new Gyroscope(this);
        Driver = new DriverTrain(this);
        Intake = new Intake(this);
    }

    public void Start(){
        Time.reset();
        Lift.Start();
        Driver.ResetIncoder();
    }

    public void Update(){
        Lift.Update();
        Gyro.Update();
        Intake.Update();

        ToolTelemetry.Update();
    }

    public void Stop(){

    }
}
