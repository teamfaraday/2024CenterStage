//btw the numbers stand for which spike mark its on, starting from the left and working clockwise. The left and right is from the drivers perspective

package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@Autonomous(name = "autoTest")
public class autoTest extends LinearOpMode {

    public DcMotor fL;
    public DcMotor fR;
    public DcMotor bL;
    public DcMotor bR;
    public DcMotor leftSlide;
    public DcMotor rightSlide;
    public DcMotor actuator;
    public DcMotor intake;
    //public Servo drone;
    //public Servo rotate;

    public int lfPos;
    public int rfPos;
    public int lbPos;
    public int rbPos;
    public int aePos;
    public int arPos;
    public int cPos;
    public boolean stopped = false;

    static final int tpi = 50; //length ratio, these is a value from last year, so it'll prob need to be tweaked
    static final double lr = 0.8535; //length ratio, these is a value from last year, so it'll prob need to be tweaked
    static final double dr = 16.4; //degree ratio, value needs to be tested & tweaked

    @Override
    public void runOpMode() {

        fL = hardwareMap.get(DcMotor.class, "frontLeft");
        fR = hardwareMap.get(DcMotor.class, "frontRight");
        bL = hardwareMap.get(DcMotor.class, "backLeft");
        bR = hardwareMap.get(DcMotor.class, "backRight");
        leftSlide = hardwareMap.get(DcMotor.class, "rightSlide");
        rightSlide = hardwareMap.get(DcMotor.class, "leftSlide");
        actuator = hardwareMap.get(DcMotor.class, "actuator");
        intake = hardwareMap.get(DcMotor.class, "intake");
        //drone = hardwareMap.get(Servo.class, "drone");
        //rotate = hardwareMap.get(Servo.class, "rotate");

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

        waitForStart();


        while (opModeIsActive() && stopped == false) {
            stopped = false;
            forward(144,0.8);

            stopped = true;
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

            fL.setPower(Math.abs(speed));
            fR.setPower(Math.abs(speed));
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

            fL.setPower(Math.abs(speed));
            fR.setPower(Math.abs(speed));
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


            fL.setPower(Math.abs(speed));
            fR.setPower(Math.abs(speed));
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

    public void backward(int inches, double speed) {

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

            fL.setPower(Math.abs(speed));
            fR.setPower(Math.abs(speed));
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


            fL.setPower(Math.abs(speed));
            fR.setPower(Math.abs(speed));
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


            fL.setPower(Math.abs(speed));
            fR.setPower(Math.abs(speed));
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



}





