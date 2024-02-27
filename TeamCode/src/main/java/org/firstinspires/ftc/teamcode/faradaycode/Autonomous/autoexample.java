package org.firstinspires.ftc.teamcode.faradaycode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.faradaycode.LinearOpModePlusConstants;

import java.util.List;

@Autonomous(name = "autoexample")
public class autoexample extends LinearOpModePlusConstants {

    public void runOpMode() {

        //initialization
        initTfod(true);
        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch Play to start OpMode");
        telemetry.update();
        while (opModeInInit()) { telemetryTfod();}
        waitForStart();
        sleep(3000);

        //acutal auto
        List<Recognition> currentRecognitions = tfod.getRecognitions();
        currentRecognitions = tfod.getRecognitions();
        if (currentRecognitions.size() != 0 && !stopped) {
            stopped = true;

        }
        else {
            currentRecognitions = tfod.getRecognitions();
            if (currentRecognitions.size() != 0 && !stopped) {
                stopped = true;

            }
        }

        telemetryTfod();

        // Push telemetry to the Driver Station.
        telemetry.update();
    }

}