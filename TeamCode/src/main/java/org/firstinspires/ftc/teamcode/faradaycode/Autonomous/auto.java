package org.firstinspires.ftc.teamcode.faradaycode.Autonomous;
import org.firstinspires.ftc.teamcode.faradaycode.OpModes;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import java.util.List;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name = "auto")
public class auto extends OpModes {

    public void runOpMode() {
        super.runOpMode();

        //initialization
        tFod.initTfod(true);
        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch Play to start OpMode");
        telemetry.update();
        while (opModeInInit()) { tFod.telemetryTfod();}
        waitForStart();
        sleep(3000);

        //actual auto
        List<Recognition> currentRecognitions = tFod.tfod.getRecognitions();
        currentRecognitions = tFod.tfod.getRecognitions();
        if (currentRecognitions.size() != 0 && !stopped) {
            stopped = true;

        }
        else {
            currentRecognitions = tFod.tfod.getRecognitions();
            if (currentRecognitions.size() != 0 && !stopped) {
                stopped = true;

            }
        }

        tFod.telemetryTfod();

        // Push telemetry to the Driver Station.
        telemetry.update();
    }

}