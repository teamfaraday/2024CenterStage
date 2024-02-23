package org.firstinspires.ftc.teamcode.faradaycode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.checkerframework.checker.fenum.qual.FenumTop;
import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.List;

import org.firstinspires.ftc.teamcode.others.components.Hang;
import org.firstinspires.ftc.teamcode.others.components.Intake;
import org.firstinspires.ftc.teamcode.others.components.Slide;


public class LinearOpModePlusConstants extends LinearOpMode{
    protected static final boolean USE_WEBCAM = true;
    protected TfodProcessor tfod;
    protected VisionPortal visionPortal;

    Hang hang;
    Intake intake;
    Slide slide;

    public DcMotor fL;
    public DcMotor fR;
    public DcMotor bL;
    public DcMotor bR;
    public Servo drone;
    public Servo rotate;

    public boolean stopped = false;

    public int lfPos;
    public int rfPos;
    public int lbPos;
    public int rbPos;

    static final int tpi = 50; //length ratio, these is a value from last year, so it'll prob need to be tweaked
    static final double lr = 0.8535; //length ratio, these is a value from last year, so it'll prob need to be tweaked
    static final double dr = 4; //degree ratio, value needs to be tested & tweaked


    @Override
    public void runOpMode() {

        fL = hardwareMap.get(DcMotor.class, "frontLeft");
        fR = hardwareMap.get(DcMotor.class, "frontRight");
        bL = hardwareMap.get(DcMotor.class, "backLeft");
        bR = hardwareMap.get(DcMotor.class, "backRight");
        drone = hardwareMap.get(Servo.class, "drone");
        rotate = hardwareMap.get(Servo.class, "rotate");

        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fL.setDirection(DcMotor.Direction.REVERSE);
        bL.setDirection(DcMotor.Direction.REVERSE);

        hang = new Hang(hardwareMap);
        intake = new Intake(hardwareMap);
        slide = new Slide(hardwareMap);
    }

    public void moveBot(double moveSpeed, double moveInches, boolean lf, boolean rf, boolean lb, boolean rb) {
        //fetch pos
        lfPos = fL.getCurrentPosition();
        rfPos = fR.getCurrentPosition();
        lbPos = bL.getCurrentPosition();
        rbPos = bR.getCurrentPosition();

        //True if + power, false if - power
        if (lf) { lfPos += (int) (moveInches); } else { lfPos -= (int) (moveInches); }
        if (rf) { rfPos += (int) (moveInches); } else { rfPos -= (int) (moveInches); }
        if (lb) { lbPos += (int) (moveInches); } else { lbPos -= (int) (moveInches); }
        if (rb) { rbPos += (int) (moveInches); } else { rbPos -= (int) (moveInches); }

        //set target pos
        fL.setTargetPosition(lfPos);
        fR.setTargetPosition(rfPos);
        bL.setTargetPosition(lbPos);
        bR.setTargetPosition(rbPos);
        //run to pos
        fL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        fR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        bR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        //set speed
        fL.setPower(Math.abs(moveSpeed));
        fR.setPower(Math.abs(moveSpeed));
        bL.setPower(Math.abs(moveSpeed));
        bR.setPower(Math.abs(moveSpeed));
        //wait function
        while (fL.isBusy() && fR.isBusy() && bL.isBusy() && bR.isBusy()) {}
        // Stop all motion;
        fL.setPower(0);
        fR.setPower(0);
        bL.setPower(0);
        bR.setPower(0);
        //Set back to normal
        fL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void forward(double inches, double speed) {
        if (opModeIsActive()) {
            moveBot(speed,inches * tpi * lr, true, true, true, true);
        }
    }

    public void backward(double inches, double speed) {
        if (opModeIsActive()) {
            moveBot(speed,inches * tpi * lr, false, false, false, false);
        }
    }

    public void right(double inches, double speed) {
        if (opModeIsActive()) {
            moveBot(speed,inches * tpi * lr, true, false, false, true);
        }
    }

    public void left(double inches, double speed) {
        if (opModeIsActive()) {
            moveBot(speed,inches * tpi * lr, false, true, true, false);
        }
    }

    public void clockwise(double degrees, double speed) {
        if (opModeIsActive()) {
            moveBot(speed,degrees * dr, true, false, true, false);
        }
    }

    public void counterclockwise(double degrees, double speed) {
        if (opModeIsActive()) {
            moveBot(speed,degrees * dr, false, true, false, true);
        }
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
