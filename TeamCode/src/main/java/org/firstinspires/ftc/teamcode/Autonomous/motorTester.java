
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


@Autonomous(name = "motorTester")
public class motorTester extends LinearOpMode {

    DcMotor motorTest;

    static final int tpi = 50; //length ratio, these is a value from last year, so it'll prob need to be tweaked
    static final double lr = 0.8535; //length ratio, these is a value from last year, so it'll prob need to be tweaked

    public boolean stopped = false;
    public int pos;


    @Override
    public void runOpMode() {

        motorTest = hardwareMap.get(DcMotor.class,"motorTest"); //insert name here

        motorTest.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        motorTest.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorTest.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        waitForStart();


        while (opModeIsActive() && stopped == false) {
            stopped = false;

            rotateForward(10000,5);

            stopped = true;
        }


    }


    public void rotateForward(int inches, double speed) {

        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller

            // fetch motor positions
            pos = motorTest.getCurrentPosition();

            // calculate new targets
            pos += (int) (inches * tpi * lr);

            // move robot to new position
            motorTest.setTargetPosition(pos);


            motorTest.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            while (motorTest.isBusy()) {

                motorTest.setPower(Math.abs(speed));

            }

            // Stop all motion;
            motorTest.setPower(0);


            // Turn off RUN_TO_POSITION
            motorTest.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }

    public void rotateBackward(int inches, double speed) {

        if (opModeIsActive()) {
            // Determine new target position, and pass to motor controller

            // fetch motor positions
            pos = motorTest.getCurrentPosition();

            // calculate new targets
            pos -= (int) (inches * tpi * lr);

            // move robot to new position
            motorTest.setTargetPosition(pos);


            motorTest.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            while (motorTest.isBusy()) {

                motorTest.setPower(Math.abs(speed));

            }

            // Stop all motion;
            motorTest.setPower(0);


            // Turn off RUN_TO_POSITION
            motorTest.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }

}





