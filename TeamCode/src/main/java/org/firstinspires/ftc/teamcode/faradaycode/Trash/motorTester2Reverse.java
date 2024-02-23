
package org.firstinspires.ftc.teamcode.faradaycode.Trash;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@Autonomous(name = "motorTester2Reverse")
public class motorTester2Reverse extends LinearOpMode {

    DcMotor motorTest;
    DcMotor motorTestTwo;

    static final int tpi = 50; //length ratio, these is a value from last year, so it'll prob need to be tweaked
    static final double lr = 0.8535; //length ratio, these is a value from last year, so it'll prob need to be tweaked

    public boolean stopped = false;
    public int pos;
    public int pos2;


    @Override
    public void runOpMode() {

        motorTest = hardwareMap.get(DcMotor.class,"motorTest"); //insert name here
        motorTestTwo = hardwareMap.get(DcMotor.class,"motorTestTwo");


        motorTest.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorTestTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        motorTest.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorTestTwo.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        motorTest.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorTestTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorTestTwo.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();


        while (opModeIsActive() && stopped == false) {
            stopped = false;

            rotateBackward(10000,5);

            stopped = true;
        }


    }


    public void rotateForward(int inches, double speed) {

        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller

            // fetch motor positions
            pos = motorTest.getCurrentPosition();
            pos2 = motorTestTwo.getCurrentPosition();


            // calculate new targets
            pos -= (int) (inches * tpi * lr);
            pos2 += (int) (inches * tpi * lr);


            // move robot to new position
            motorTest.setTargetPosition(pos);
            motorTestTwo.setTargetPosition(pos2);


            motorTest.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorTestTwo.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            while (motorTest.isBusy() && motorTestTwo.isBusy()) {

                motorTest.setPower(Math.abs(speed));
                motorTestTwo.setPower(Math.abs(speed));

            }

            // Stop all motion;
            motorTest.setPower(0);
            motorTestTwo.setPower(0);


            // Turn off RUN_TO_POSITION
            motorTest.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorTestTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        }
    }

    public void rotateBackward(int inches, double speed) {

        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller

            // fetch motor positions
            pos = motorTest.getCurrentPosition();
            pos2 = motorTestTwo.getCurrentPosition();


            // calculate new targets
            pos += (int) (inches * tpi * lr);
            pos2 -= (int) (inches * tpi * lr);


            // move robot to new position
            motorTest.setTargetPosition(pos);
            motorTestTwo.setTargetPosition(pos2);


            motorTest.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorTestTwo.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            while (motorTest.isBusy() && motorTestTwo.isBusy()) {

                motorTest.setPower(Math.abs(speed));
                motorTestTwo.setPower(Math.abs(speed));

            }

            // Stop all motion;
            motorTest.setPower(0);
            motorTestTwo.setPower(0);


            // Turn off RUN_TO_POSITION
            motorTest.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorTestTwo.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        }
    }

}





