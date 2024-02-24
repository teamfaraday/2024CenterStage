package org.firstinspires.ftc.teamcode.faradaycode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

import org.firstinspires.ftc.teamcode.faradaycode.components.Hang;
import org.firstinspires.ftc.teamcode.faradaycode.components.Intake;
import org.firstinspires.ftc.teamcode.faradaycode.components.Slide;
import org.firstinspires.ftc.teamcode.faradaycode.components.Drone;
import org.firstinspires.ftc.teamcode.faradaycode.components.DriveTrain.DriveTrainAuto;
import org.firstinspires.ftc.teamcode.faradaycode.components.Bannerbox;



public class LinearOpModePlusConstants extends LinearOpMode{
    protected static final boolean USE_WEBCAM = true;
    protected TfodProcessor tfod;
    protected VisionPortal visionPortal;

    public Hang hang;
    public Intake intake;
    public Slide slide;
    public Drone drone;
    public DriveTrainAuto driveTrain;
    public Bannerbox bannerbox;

    public boolean stopped = false;


    @Override
    public void runOpMode() {
        hang = new Hang(hardwareMap);
        intake = new Intake(hardwareMap);
        slide = new Slide(hardwareMap);
        drone = new Drone(hardwareMap);
        driveTrain = new DriveTrainAuto(hardwareMap);
        bannerbox = new Bannerbox(hardwareMap);
    }


    protected void telemetryTfod() {

        List<Recognition> currentRecognitions = tfod.getRecognitions();
        telemetry.addData("# Objects Detected", currentRecognitions.size());

        // Step through the list of recognitions and display info for each one.
        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
            double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

            telemetry.addData(""," ");
            telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
            telemetry.addData("- Position", "%.0f / %.0f", x, y);
            telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
        }   // end for() loop

    }   // end method telemetryTfod()*/

}
