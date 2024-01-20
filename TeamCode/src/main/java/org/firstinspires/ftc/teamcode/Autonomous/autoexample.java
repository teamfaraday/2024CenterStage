package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

@Autonomous(name = "autoexample")
public class autoexample extends LinearOpMode {





    private static final boolean USE_WEBCAM = true;  // true for webcam, false for phone camera

    private static final String TFOD_MODEL_ASSET = "bb.tflite";

    private static final String[] LABELS = {
       "b",
    };

    /**
     * The variable to store our instance of the TensorFlow Object Detection processor.
     */
    private TfodProcessor tfod;

    /**
     * The variable to store our instance of the vision portal.
     */
    private VisionPortal visionPortal;

    public DcMotor fL;
    public DcMotor fR;
    public DcMotor bL;
    public DcMotor bR;
    public DcMotor leftSlide;
    public DcMotor rightSlide;
    public DcMotor actuator;
    public DcMotor intake;
    public Servo drone;
    public Servo rotate;
   // public Servo pixel;

    public boolean stopped = false;
    public int pos;

    public int lfPos;
    public int rfPos;
    public int lbPos;
    public int rbPos;

    static final int tpi = 50; //length ratio, these is a value from last year, so it'll prob need to be tweaked
    static final double lr = 0.8535; //length ratio, these is a value from last year, so it'll prob need to be tweaked
    static final double dr = 4; //degree ratio, value needs to be tested & tweaked
    static final double nerf = 0.6;


    @Override
    public void runOpMode() {

        fL = hardwareMap.get(DcMotor.class, "frontLeft");
        fR = hardwareMap.get(DcMotor.class, "frontRight");
        bL = hardwareMap.get(DcMotor.class, "backLeft");
        bR = hardwareMap.get(DcMotor.class, "backRight");
        leftSlide = hardwareMap.get(DcMotor.class, "leftSlide");
        rightSlide = hardwareMap.get(DcMotor.class, "rightSlide");
        actuator = hardwareMap.get(DcMotor.class, "actuator");
        intake = hardwareMap.get(DcMotor.class, "intake");
        drone = hardwareMap.get(Servo.class, "drone");
        rotate = hardwareMap.get(Servo.class, "rotate");
        //pixel = hardwareMap.get(Servo.class, "pixel");

        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        actuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        fR.setDirection(DcMotor.Direction.REVERSE);
        bR.setDirection(DcMotor.Direction.REVERSE);

        initTfod();

        // Wait for the DS start button to be touched.
        telemetry.addData("DS preview on/off", "3 dots, Camera Stream");
        telemetry.addData(">", "Touch Play to start OpMode");
        telemetry.update();

        while (opModeInInit()) { telemetryTfod();}

        waitForStart();

        sleep(3000);


        forward(5,0.8);

        List<Recognition> currentRecognitions = tfod.getRecognitions();
        currentRecognitions = tfod.getRecognitions();
        telemetry.addData("Recs", currentRecognitions);
        telemetry.update();

        if (currentRecognitions.size() != 0 && !stopped) {
            stopped = false;
            forward(20, 0.8);
            stopped = true;

        }
        else {
            counterClockwise(90,0.6);
            forward(10, 0.2);
            clockwise(90,0.6);

            currentRecognitions = tfod.getRecognitions();
            telemetry.addData("Recs", currentRecognitions);
            telemetry.update();
            if (currentRecognitions.size() != 0 && !stopped) {
                forward(10,1);
            }
            else {
                forward(5,1);
                clockwise(90,1);
            }
        }


                telemetryTfod();


                // Push telemetry to the Driver Station.
                telemetry.update();
    }

            /*    // Save CPU resources; can resume streaming when needed.
                if (gamepad1.dpad_down) {
                    visionPortal.stopStreaming();
                } else if (gamepad1.dpad_up) {
                    visionPortal.resumeStreaming();
                }*/

         //   }
      // }

       /*  Save more CPU resources when camera is no longer needed.
        visionPortal.close();*/

  //  }   // end runOpMode()

    /**
     * Initialize the TensorFlow Object Detection processor.
     */


    public void pixel(double value) {
      //  pixel.setPosition(value); // test out later
    }



    public void intake(int degrees, double speed) {

        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller

            // fetch motor positions
            pos = intake.getCurrentPosition();


            // calculate new targets
            pos += (int) (degrees * dr);


            // move robot to new position
            intake.setTargetPosition(pos);


            intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            intake.setPower(Math.abs(speed));



            while (intake.isBusy()) {



            }

            // Stop all motion;
            intake.setPower(0);

            // Turn off RUN_TO_POSITION
            intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }


    public void clockwise(int degrees, double speed) {

        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller

            // fetch motor positions
            lfPos = fL.getCurrentPosition();
            rfPos = fR.getCurrentPosition();
            lbPos = bL.getCurrentPosition();
            rbPos = bR.getCurrentPosition();

            // calculate new targets
            lfPos -= (int) (degrees * dr);
            rfPos += (int) (degrees * dr);
            lbPos -= (int) (degrees * dr);
            rbPos += (int) (degrees * dr);

            // move robot to new position
            fL.setTargetPosition(lfPos);
            fR.setTargetPosition(rfPos);
            bL.setTargetPosition(lbPos);
            bR.setTargetPosition(rbPos);

            fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            fL.setPower(Math.abs(speed * nerf * nerf));
            fR.setPower(Math.abs(speed * nerf));
            bL.setPower(Math.abs(speed * nerf));
            bR.setPower(Math.abs(speed));


            while (fL.isBusy() && fR.isBusy() && bL.isBusy() && bR.isBusy()) {



            }

            // Stop all motion;
            fL.setPower(0);
            fR.setPower(0);
            bL.setPower(0);
            bR.setPower(0);

            // Turn off RUN_TO_POSITION
            fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void counterClockwise(int degrees, double speed) {

        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller

            // fetch motor positions
            lfPos = fL.getCurrentPosition();
            rfPos = fR.getCurrentPosition();
            lbPos = bL.getCurrentPosition();
            rbPos = bR.getCurrentPosition();

            // calculate new targets
            lfPos += (int) (degrees * dr);
            rfPos -= (int) (degrees * dr);
            lbPos += (int) (degrees * dr);
            rbPos -= (int) (degrees * dr);


            // move robot to new position
            fL.setTargetPosition(lfPos);
            fR.setTargetPosition(rfPos);
            bL.setTargetPosition(lbPos);
            bR.setTargetPosition(rbPos);

            fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            fL.setPower(Math.abs(speed * nerf));
            fR.setPower(Math.abs(speed * nerf));
            bL.setPower(Math.abs(speed * nerf));
            bR.setPower(Math.abs(speed));


            while (fL.isBusy() && fR.isBusy() && bL.isBusy() && bR.isBusy()) {



            }

            // Stop all motion;
            fL.setPower(0);
            fR.setPower(0);
            bL.setPower(0);
            bR.setPower(0);

            // Turn off RUN_TO_POSITION
            fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void forward(int inches, double speed) {

        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller

            // fetch motor positions
            lfPos = fL.getCurrentPosition();
            rfPos = fR.getCurrentPosition();
            lbPos = bL.getCurrentPosition();
            rbPos = bR.getCurrentPosition();

            // calculate new targets
            lfPos -= (int) (inches * tpi * lr);
            rfPos -= (int) (inches * tpi * lr);
            lbPos -= (int) (inches * tpi * lr);
            rbPos -= (int) (inches * tpi * lr);

            // move robot to new position
            fL.setTargetPosition(lfPos);
            fR.setTargetPosition(rfPos);
            bL.setTargetPosition(lbPos);
            bR.setTargetPosition(rbPos);

            fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            fL.setPower(Math.abs(speed * nerf));
            fR.setPower(Math.abs(speed * nerf));
            bL.setPower(Math.abs(speed * nerf));
            bR.setPower(Math.abs(speed));

            while (fL.isBusy() && fR.isBusy() && bL.isBusy() && bR.isBusy()) {



            }

            // Stop all motion;
            fL.setPower(0);
            fR.setPower(0);
            bL.setPower(0);
            bR.setPower(0);

            // Turn off RUN_TO_POSITION
            fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void backward(int inches, double speed) {

        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller

            // fetch motor positions
            lfPos = fL.getCurrentPosition();
            rfPos = fR.getCurrentPosition();
            lbPos = bL.getCurrentPosition();
            rbPos = bR.getCurrentPosition();

            // calculate new targets
            lfPos = (int) (inches * tpi * lr);
            rfPos = (int) (inches * tpi * lr);
            lbPos = (int) (inches * tpi * lr);
            rbPos = (int) (inches * tpi * lr);

            // move robot to new position
            fL.setTargetPosition(lfPos);
            fR.setTargetPosition(rfPos);
            bL.setTargetPosition(lbPos);
            bR.setTargetPosition(rbPos);

            fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            fL.setPower(Math.abs(speed * nerf));
            fR.setPower(Math.abs(speed * nerf));
            bL.setPower(Math.abs(speed * nerf));
            bR.setPower(Math.abs(speed));

            while (fL.isBusy() && fR.isBusy() && bL.isBusy() && bR.isBusy()) {



            }

            // Stop all motion;
            fL.setPower(0);
            fR.setPower(0);
            bL.setPower(0);
            bR.setPower(0);

            // Turn off RUN_TO_POSITION
            fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void right(int inches, double speed) {

        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller

            // fetch motor positions
            lfPos = fL.getCurrentPosition();
            rfPos = fR.getCurrentPosition();
            lbPos = bL.getCurrentPosition();
            rbPos = bR.getCurrentPosition();

            // calculate new targets
            lfPos -= (int) (inches * tpi * lr);
            rfPos += (int) (inches * tpi * lr);
            lbPos += (int) (inches * tpi * lr);
            rbPos -= (int) (inches * tpi * lr);

            // move robot to new position
            fL.setTargetPosition(lfPos);
            fR.setTargetPosition(rfPos);
            bL.setTargetPosition(lbPos);
            bR.setTargetPosition(rbPos);

            fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            fL.setPower(Math.abs(speed * nerf));
            fR.setPower(Math.abs(speed * nerf));
            bL.setPower(Math.abs(speed));
            bR.setPower(Math.abs(speed));

            while (fL.isBusy() && fR.isBusy() && bL.isBusy() && bR.isBusy()) {



            }

            // Stop all motion;
            fL.setPower(0);
            fR.setPower(0);
            bL.setPower(0);
            bR.setPower(0);

            // Turn off RUN_TO_POSITION
            fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void left(int inches, double speed) {

        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller

            // fetch motor positions
            lfPos = fL.getCurrentPosition();
            rfPos = fR.getCurrentPosition();
            lbPos = bL.getCurrentPosition();
            rbPos = bR.getCurrentPosition();

            // calculate new targets
            lfPos += (int) (inches * tpi * lr);
            rfPos -= (int) (inches * tpi * lr);
            lbPos -= (int) (inches * tpi * lr);
            rbPos += (int) (inches * tpi * lr);

            // move robot to new position
            fL.setTargetPosition(lfPos);
            fR.setTargetPosition(rfPos);
            bL.setTargetPosition(lbPos);
            bR.setTargetPosition(rbPos);

            fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            fL.setPower(Math.abs(speed * nerf));
            fR.setPower(Math.abs(speed * nerf));
            bL.setPower(Math.abs(speed * nerf));
            bR.setPower(Math.abs(speed));

            while (fL.isBusy() && fR.isBusy() && bL.isBusy() && bR.isBusy()) {



            }

            // Stop all motion;
            fL.setPower(0);
            fR.setPower(0);
            bL.setPower(0);
            bR.setPower(0);

            // Turn off RUN_TO_POSITION
            fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            bR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    private void initTfod() {

        // Create the TensorFlow processor by using a builder.
        tfod = new TfodProcessor.Builder()

            // With the following lines commented out, the default TfodProcessor Builder
            // will load the default model for the season. To define a custom model to load, 
            // choose one of the following:
            //   Use setModelAssetName() if the custom TF Model is built in as an asset (AS only).
            //   Use setModelFileName() if you have downloaded a custom team model to the Robot Controller.
            .setModelAssetName(TFOD_MODEL_ASSET)
            //.setModelFileName(TFOD_MODEL_FILE)

            // The following default settings are available to un-comment and edit as needed to 
            // set parameters for custom models.
            .setModelLabels(LABELS)
            //.setIsModelTensorFlow2(true)
            //.setIsModelQuantized(true)
            //.setModelInputSize(300)
            //.setModelAspectRatio(16.0 / 9.0)

            .build();

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }

        // Choose a camera resolution. Not all cameras support all resolutions.
        //builder.setCameraResolution(new Size(640, 480));

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        //builder.enableLiveView(true);

        // Set the stream format; MJPEG uses less bandwidth than default YUY2.
        //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

        // Choose whether or not LiveView stops if no processors are enabled.
        // If set "true", monitor shows solid orange screen if no processors enabled.
        // If set "false", monitor shows camera view without annotations.
        //builder.setAutoStopLiveView(false);

        // Set and enable the processor.
        builder.addProcessor(tfod);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();

        // Set confidence threshold for TFOD recognitions, at any time.
        //tfod.setMinResultConfidence(0.75f);

        // Disable or re-enable the TFOD processor at any time.
        //visionPortal.setProcessorEnabled(tfod, true);

    }   // end method initTfod()

    /**
     * Add telemetry about TensorFlow Object Detection (TFOD) recognitions.
     */
    private void telemetryTfod() {

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

    }   // end method telemetryTfod()


}