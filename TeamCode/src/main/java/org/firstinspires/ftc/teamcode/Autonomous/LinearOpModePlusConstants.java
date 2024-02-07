package org.firstinspires.ftc.teamcode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
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

public class LinearOpModePlusConstants extends LinearOpMode{
    protected static final boolean USE_WEBCAM = true;
    protected TfodProcessor tfod;
    protected VisionPortal visionPortal;

    public DcMotor fL;
    public DcMotor fR;
    public DcMotor bL;
    public DcMotor bR;
    public DcMotor slide;
    public DcMotor actuator;
    public DcMotor intakeL;
    public DcMotor intakeR;
    public Servo drone;
    public Servo rotate;

    public boolean stopped = false;
    public int pos;
    public int pos2;

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
        slide = hardwareMap.get(DcMotor.class, "slide");
        actuator = hardwareMap.get(DcMotor.class, "actuator");
        intakeL = hardwareMap.get(DcMotor.class, "intakeL");
        intakeR = hardwareMap.get(DcMotor.class, "intakeR");
        drone = hardwareMap.get(Servo.class, "drone");
        rotate = hardwareMap.get(Servo.class, "rotate");

        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        actuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        fL.setDirection(DcMotor.Direction.REVERSE);
        bL.setDirection(DcMotor.Direction.REVERSE);
        intakeR.setDirection(DcMotor.Direction.REVERSE);
    }

    public void fetchPos() {
        lfPos = fL.getCurrentPosition();
        rfPos = fR.getCurrentPosition();
        lbPos = bL.getCurrentPosition();
        rbPos = bR.getCurrentPosition();
    }

    public void setTargets(double moveInches, boolean lf, boolean rf, boolean lb, boolean rb) {
        //True if + power, false if - power
        if (lf) { lfPos += (int) (moveInches); } else { lfPos -= (int) (moveInches); }
        if (rf) { rfPos += (int) (moveInches); } else { rfPos -= (int) (moveInches); }
        if (lb) { lbPos += (int) (moveInches); } else { lbPos -= (int) (moveInches); }
        if (rb) { rbPos += (int) (moveInches); } else { rbPos -= (int) (moveInches); }
    }

    public void moveBot(double moveSpeed) {
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
            fetchPos();
            setTargets(inches * tpi * lr, true, true, true, true);
            moveBot(speed);
        }
    }

    public void backward(double inches, double speed) {
        if (opModeIsActive()) {
            fetchPos();
            setTargets(inches * tpi * lr, false, false, false, false);
            moveBot(speed);
        }
    }

    public void right(double inches, double speed) {
        if (opModeIsActive()) {
            fetchPos();
            setTargets(inches * tpi * lr, true, false, false, true);
            moveBot(speed);
        }
    }

    public void left(double inches, double speed) {
        if (opModeIsActive()) {
            fetchPos();
            setTargets(inches * tpi * lr, false, true, true, false);
            moveBot(speed);
        }
    }

    public void clockwise(double degrees, double speed) {
        if (opModeIsActive()) {
            fetchPos();
            setTargets(degrees * dr, true, false, true, false);
            moveBot(speed);
        }
    }

    public void counterClockwise(double degrees, double speed) {
        if (opModeIsActive()) {
            fetchPos();
            setTargets(degrees * dr, false, true, false, true);
            moveBot(speed);
        }
    }

    public void intake(int degrees, double speed) {
        if (opModeIsActive()) {
            pos = intakeL.getCurrentPosition();
            pos2 = intakeR.getCurrentPosition();

            pos += (int) (degrees * dr);
            pos2 += (int) (degrees * dr);

            intakeL.setTargetPosition(pos);
            intakeR.setTargetPosition(pos);

            intakeL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            intakeR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            intakeR.setPower(Math.abs(speed));
            intakeL.setPower(Math.abs(speed));

            while (intakeL.isBusy() && intakeR.isBusy()) {}

            intakeR.setPower(0);
            intakeL.setPower(0);

            intakeR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            intakeL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

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

    }   // end method telemetryTfod()

}
