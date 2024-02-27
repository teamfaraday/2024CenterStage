package org.firstinspires.ftc.teamcode.faradaycode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

import org.firstinspires.ftc.teamcode.faradaycode.components.Hang;
import org.firstinspires.ftc.teamcode.faradaycode.components.Intake;
import org.firstinspires.ftc.teamcode.faradaycode.components.Slide;
import org.firstinspires.ftc.teamcode.faradaycode.components.Drone;
import org.firstinspires.ftc.teamcode.faradaycode.components.DriveTrain.DriveTrainAuto;
import org.firstinspires.ftc.teamcode.faradaycode.components.DriveTrain.DriveTrainTeleOp;
import org.firstinspires.ftc.teamcode.faradaycode.components.BannerBox;

public class LinearOpModePlusConstants extends LinearOpMode{
    protected static final boolean USE_WEBCAM = true;
    protected TfodProcessor tfod;
    protected VisionPortal visionPortal;

    public Hang hang;
    public Intake intake;
    public Slide slide;
    public Drone drone;
    public DriveTrainAuto driveTrainAuto;
    public DriveTrainTeleOp driveTrainTeleOp;
    public BannerBox bannerBox;

    private static final String BLUE_TFOD_MODEL_ASSET = "bb.tflite";
    private static final String[] BLUE_LABELS = {
            "b",
    }   ;

    private static final String RED_TFOD_MODEL_ASSET = "rr.tflite";
    private static final String[] RED_LABELS = {
            "r",
    }   ;

    public boolean stopped = false;
    public double nerf = 1;

    public void runOpMode() {
        hang = new Hang(hardwareMap);
        intake = new Intake(hardwareMap);
        slide = new Slide(hardwareMap);
        drone = new Drone(hardwareMap);
        driveTrainAuto = new DriveTrainAuto(hardwareMap);
        driveTrainTeleOp = new DriveTrainTeleOp(hardwareMap);
        bannerBox = new BannerBox(hardwareMap);
    }

    public void telemetryTfod() {

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

    public void initTfod(boolean isBlue) {

        // Create the TensorFlow processor by using a builder.
        if (isBlue) {
            tfod = new TfodProcessor.Builder()
                    .setModelAssetName(BLUE_TFOD_MODEL_ASSET)
                    .setModelLabels(BLUE_LABELS)
                    .build();
        } else {
            tfod = new TfodProcessor.Builder()
                    .setModelAssetName(RED_TFOD_MODEL_ASSET)
                    .setModelLabels(RED_LABELS)
                    .build();
        }

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }
        builder.addProcessor(tfod);

        visionPortal = builder.build();
    }

}
