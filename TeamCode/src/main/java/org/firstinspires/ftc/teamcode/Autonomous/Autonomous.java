package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous
public class Autonomous {

    public static DcMotor frontLeft;
    public static DcMotor frontRight;
    public static DcMotor backLeft;
    public static DcMotor backRight;

    ElapsedTime et = new ElapsedTime();


    public void init() {

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
    }


    static void front(int frontRevs) {
        frontLeft.setTargetPosition(360*frontRevs);
        frontRight.setTargetPosition(360*frontRevs);
        backLeft.setTargetPosition(360*frontRevs);
        backRight.setTargetPosition(360*frontRevs);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(0.8);
        frontRight.setPower(0.8);
        backLeft.setPower(0.8);
        backRight.setPower(0.8);
    }

    static void back(int backRevs) {
        frontLeft.setTargetPosition(360*backRevs);
        frontRight.setTargetPosition(360*backRevs);
        backLeft.setTargetPosition(360*backRevs);
        backRight.setTargetPosition(360*backRevs);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(-0.8);
        frontRight.setPower(-0.8);
        backLeft.setPower(-0.8);
        backRight.setPower(-0.8);
    }

    static void left(int leftRevs) {
        frontLeft.setTargetPosition(360*leftRevs);
        frontRight.setTargetPosition(360*leftRevs);
        backLeft.setTargetPosition(360*leftRevs);
        backRight.setTargetPosition(360*leftRevs);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(-0.8);
        frontRight.setPower(0.8);
        backLeft.setPower(0.8);
        backRight.setPower(-0.8);
    }

    static void right(int rightRevs) {
        frontLeft.setTargetPosition(360*rightRevs);
        frontRight.setTargetPosition(360*rightRevs);
        backLeft.setTargetPosition(360*rightRevs);
        backRight.setTargetPosition(360*rightRevs);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(0.8);
        frontRight.setPower(-0.8);
        backLeft.setPower(-0.8);
        backRight.setPower(0.8);
    }

    public void loop() {


        if (/*bluealliance1*/) {
            left(2); //assuming 1 rev is 1 tile on the board
        }

        if (/*bluealliance2*/) {
            front(2);
            left(3);
            back(2);
            left(1);
        }

        if (/*redalliance1*/) {
            right(2);
        }

        if (/*bluealliance2*/) {
            front(2);
            right(3);
            back(2);
            right(1);
        }
    }

}
