package org.firstinspires.ftc.teamcode.faradaycode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.others.components.Hang;
import org.firstinspires.ftc.teamcode.others.components.Intake;
import org.firstinspires.ftc.teamcode.others.components.Slide;
import org.firstinspires.ftc.teamcode.others.components.Drone;

import com.qualcomm.robotcore.util.ElapsedTime;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")
public class TeleOp extends OpMode {

    public Hang hang;
    public Intake intake;
    public Slide slide;
    public Drone drone;

    public ElapsedTime timeSpent = new ElapsedTime();

    public DcMotor fL;
    public DcMotor fR;
    public DcMotor bL;
    public DcMotor bR;
    public Servo release;
    public Servo rotate1;
    public Servo rotate2;


    public double t;
    public double fnerf = 1;
    public double bnerf = 1;

    public double powerRange = 1;


    @Override
    public void init() {
       /*
      Drivetrain: Sticks
      intake: a and b
      hanging: dpad up and down
      drone: both triggers
      outtake: x and y
       */


        fL = hardwareMap.get(DcMotor.class, "frontLeft");
        fR = hardwareMap.get(DcMotor.class, "frontRight");
        bL = hardwareMap.get(DcMotor.class, "backLeft");
        bR = hardwareMap.get(DcMotor.class, "backRight");
        release = hardwareMap.get(Servo.class, "release");
        rotate1 = hardwareMap.get(Servo.class, "rotate1");
        rotate2 = hardwareMap.get(Servo.class, "rotate2");


        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fR.setDirection(DcMotor.Direction.REVERSE);
        bR.setDirection(DcMotor.Direction.REVERSE);

        hang = new Hang(hardwareMap);
        intake = new Intake(hardwareMap);
        slide = new Slide(hardwareMap);
        drone = new Drone(hardwareMap);


    }


    public void start() {
        timeSpent.reset();
        drone.init();
    }


    @Override
    public void loop() {

        telemetry.update();
        telemetry.addData("bnerf", bnerf);
        telemetry.addData("fnerf", fnerf);
        telemetry.update();
        telemetry.update();
        telemetry.addData("fnerf", fnerf);
        telemetry.addData("bnerf", bnerf);
        telemetry.update();

        double y = gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x; // Counteract imperfect strafing
        double rx = gamepad1.right_stick_x;


        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), powerRange);
        double frontLeftPower = ((y + x - rx) * fnerf)/ denominator;
        double backLeftPower = ((y - x - rx) * bnerf) / denominator;
        double frontRightPower = ((y - x + rx) * fnerf)/ denominator;
        double backRightPower = ((y + x + rx) * bnerf) / denominator;

        fL.setPower(frontLeftPower);
        bL.setPower(backLeftPower);
        fR.setPower(frontRightPower);
        bR.setPower(backRightPower);

        telemetry.update();


        /*if (gamepad1.dpad_up) {
            bnerf += 0.005;
            telemetry.update();
            telemetry.addData("bnerf", bnerf);
            telemetry.update();
        }
        if (gamepad1.dpad_down) {
            bnerf -= 0.005;
            telemetry.update();
            telemetry.addData("bnerf", bnerf);
            telemetry.update();
        }
        if (gamepad1.dpad_right) {
            fnerf += 0.005;
            telemetry.update();
            telemetry.addData("fnerf", fnerf);
            telemetry.update();
        }
        if (gamepad1.dpad_right) {
            fnerf -= 0.005;
            telemetry.update();
            telemetry.addData("fnerf", fnerf);
            telemetry.update();
        }*/


        //slides
        if (gamepad1.left_bumper){
            slide.down();
        } else if (!gamepad2.left_bumper) {
            if (!gamepad2.a) {
                slide.antiGrav();
            }
            else {
                slide.deactivate();
            }
        }
        if (gamepad1.right_bumper) {
            slide.up();
        }

        //intake
        if (gamepad1.b) {
            intake.activate();
        } else if (!gamepad1.b){
            intake.deactivate();
        }
        if (gamepad1.a) {
            intake.reverse();
        }


        //Hanging
        if (gamepad1.dpad_up) {
            hang.up();
        } else if (!gamepad1.dpad_up) {
            hang.deactivate();
        }
        if (gamepad1.dpad_down) {
            hang.down();
        }

        //drone
        if (gamepad1.left_trigger > 0.8 && gamepad1.right_trigger > 0.8){
            drone.launch();
        }

        //outtake
        if (gamepad1.x){
            rotate1.setPosition(.8);
            rotate2.setPosition(.55);
            release.setPosition(.15);
            t = getRuntime();
            if (getRuntime() - t > 1000) {
                release.setPosition(.15);
            }
        }
        if (gamepad1.y){
            rotate1.setPosition(1.15);
            rotate2.setPosition(.20);
            release.setPosition(.5);
        }

        telemetry.update();
        telemetry.addData("bnerf", bnerf);
        telemetry.addData("fnerf", fnerf);
        telemetry.update();
        telemetry.update();
        telemetry.addData("fnerf", fnerf);
        telemetry.addData("bnerf", bnerf);
        telemetry.update();

    }
}









