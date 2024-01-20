package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")
public class TeleOp extends OpMode {


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
    public static final double MOTOR_MULTIPLIER = 0.75;

    ElapsedTime et = new ElapsedTime();


    @Override
    public void init() {

        telemetry.update();

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



    }


    @Override
    public void loop() {
        telemetry.update();

        //telemetry.addData("drone", drone.getPosition());
        telemetry.update();


        double minPower = -0.9;
        double maxPower = 0.9;


        double y = gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x * 0.75;
        double rx = -gamepad1.right_stick_x * 0.75;
       // int lim1 = -730;
       // int lim2 = -1150;
        boolean intakeBool = false;

        fL.setPower(Range.clip(y + x + rx, minPower, maxPower));
        bL.setPower(Range.clip(y - x + rx, minPower, maxPower));
        fR.setPower(Range.clip(y - x - rx, minPower, maxPower));
        bR.setPower(Range.clip(y + x - rx, minPower, maxPower));


        telemetry.update();


        /*
        GP 1:
            Movement
            Intake
            Drone

        GP 2:
            Slides
            Hang
            Outtake

         */

        //slides

        if (gamepad2.right_bumper){
            rightSlide.setPower(-1); //up
        } else if (!gamepad2.right_bumper) {
            rightSlide.setPower(-0.1);
        }
        if (gamepad2.left_bumper) {
            rightSlide.setPower(0.9); //down
        }


        //intake
        if (gamepad1.a) {
            intake.setPower(100);
        } else if (!gamepad1.a){
            intake.setPower(0);
        }
        if (gamepad1.y) {
            intake.setPower(-100);
        }





        //Hanging
        // Up
        if (gamepad2.dpad_up) {
            actuator.setPower(0.8);
        } else if (!gamepad2.dpad_up) {
            actuator.setPower(0);
        }
        //Down
        if (gamepad2.dpad_down) {
            actuator.setPower(-0.8);
        }

        //drone

        if (gamepad1.left_trigger > 0.8 && gamepad1.right_trigger > 0.8){
            drone.setPosition(0);
        }

        //outtake

        if (gamepad2.x){
            rotate.setDirection(Servo.Direction.FORWARD);
            rotate.setPosition(0.97);

        }

        /*if (gamepad2.y){
            rotate.setDirection(Servo.Direction.FORWARD);
            rotate.setPosition(0.16);
        }*/

        if (gamepad2.b) {
            rotate.setDirection(Servo.Direction.FORWARD);
            rotate.setPosition(0.6);
        }

    }
}
