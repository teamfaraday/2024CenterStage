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

        //slides

        if (gamepad1.right_bumper){
            rightSlide.setPower(-1); //up
            leftSlide.setPower(0.93);
        } else if (!gamepad1.right_bumper) {
            rightSlide.setPower(0);
            leftSlide.setPower(0);
        }
        if (gamepad1.left_bumper) {
            rightSlide.setPower(1); //down
            leftSlide.setPower(-0.85);
        }


        //intake
        if (gamepad1.a) {
            intake.setPower(100);
        } else if (!gamepad1.a){
            intake.setPower(0);
        }





        //Hanging
        // Up
        if (gamepad1.dpad_up) {
            actuator.setPower(0.8);
        } else if (!gamepad1.dpad_up) {
            actuator.setPower(0);
        }
        //Down
        if (gamepad1.dpad_down) {
            actuator.setPower(-0.8);
        }

        if (gamepad1.left_trigger > 0.8 && gamepad1.right_trigger > 0.8){
            drone.setPosition(0);
        }

        if (gamepad1.x){
            rotate.setDirection(Servo.Direction.FORWARD);
            rotate.setPosition(0.75);

        }

        if (gamepad1.y){
            rotate.setDirection(Servo.Direction.REVERSE);
            rotate.setPosition(0.84);
        }

        if (gamepad1.b) {
            rotate.setDirection(Servo.Direction.FORWARD);
            rotate.setPosition(0.25);
        }

    }
}
