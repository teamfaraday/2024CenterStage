package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TestTeleOp")
public class TestTeleOp extends OpMode {


    public DcMotor fL;
    public DcMotor fR;
    public DcMotor bL;
    public DcMotor bR;
    public DcMotor arm;
    public DcMotor armRotate;
    public Servo clawR;
    public Servo clawL;
    public Servo drone;
    public DcMotor hang;
    public static final double MOTOR_MULTIPLIER = 0.75;
    public enum LiftState {
        LIFT_START,
        LIFT_DROP,
        LIFT_CLOSED,
        LIFT_RETRACT
    }


    LiftState liftState = LiftState.LIFT_START;


    ElapsedTime et = new ElapsedTime();




    @Override
    public void init() {


        fL = hardwareMap.get(DcMotor.class, "frontLeft");
        fR = hardwareMap.get(DcMotor.class, "frontRight");
        bL = hardwareMap.get(DcMotor.class, "backLeft");
        bR = hardwareMap.get(DcMotor.class, "backRight");
        arm = hardwareMap.get(DcMotor.class, "arm");
        armRotate = hardwareMap.get(DcMotor.class, "armRotate"); //
        clawR = hardwareMap.get(Servo.class, "clawR");
        clawL = hardwareMap.get(Servo.class, "clawL");
        hang = hardwareMap.get(DcMotor.class, "hang");
        drone = hardwareMap.get(Servo.class, "drone");


        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //


        fR.setDirection(DcMotor.Direction.REVERSE);
        bR.setDirection(DcMotor.Direction.REVERSE);
    }






    @Override
    public void loop() {


        /**
         switch (liftState) {


         case LIFT_START:


         if (gamepad1.a) {


         arm.setPower(0.86);


         }


         liftState = LiftState.LIFT_DROP;
         break;


         }**/


        double minPower = -0.8;
        double maxPower = 0.8;


        double y = gamepad1.left_stick_y;
        double x = -gamepad1.left_stick_x * 0.75;
        double rx = -gamepad1.right_stick_x * 0.75;
        int lim1 = 950;
        int lim2 = 1262;


        double denominator  = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y+x+rx) / denominator;
        double backLeftPower = (y-x+rx) / denominator;
        double frontRightPower = (y-x-rx) / denominator;
        double backRightPower = (y+x-rx) / denominator;


        fL.setPower(frontLeftPower*MOTOR_MULTIPLIER);
        bL.setPower(backLeftPower*MOTOR_MULTIPLIER);
        fR.setPower(frontRightPower*MOTOR_MULTIPLIER);
        bR.setPower(backRightPower*MOTOR_MULTIPLIER);
//        fL.setPower(Range.clip(y +x +rx,minPower,maxPower));
//        bL.setPower(Range.clip(y -x +rx,minPower,maxPower));
//        fR.setPower(Range.clip(y -x -rx,minPower,maxPower));
//        bR.setPower(Range.clip(y +x -rx,minPower,maxPower));




        //Up
        if (gamepad1.y) {
            if (arm.getCurrentPosition() > lim1) {
                arm.setPower(0.14);
                telemetry.addData("Position", arm.getCurrentPosition());
            }
            else {
                arm.setPower(0.93);
                telemetry.addData("Position", arm.getCurrentPosition());
            }
        } else if (!gamepad1.y) {


            if (arm.getCurrentPosition() > lim1 && arm.getCurrentPosition() <= lim2) {


                arm.setPower(0);


            }
            else if (arm.getCurrentPosition() > lim2) {


                arm.setPower(-0.114);


            }
            else {


                arm.setPower(0.214);
            }
        }
        //guide
        if (gamepad1.a) {


            arm.setPower(-0.78);
            telemetry.addData("Position", arm.getCurrentPosition());


        }


        //slow movement
        if (gamepad1.x) {


            arm.setPower(0.47);
            telemetry.addData("Position", arm.getCurrentPosition());


        }
        //guide
        if (gamepad1.b) {


            arm.setPower(-0.27);
            telemetry.addData("Position", arm.getCurrentPosition());


        }




        //Rotate Claw
        if (gamepad1.left_bumper) {


            armRotate.setPower(0.3);


        } else if (!gamepad1.left_bumper) {


            armRotate.setPower(0);


        }


        //Reset Claw
        if (gamepad1.right_bumper) {


            armRotate.setPower(-0.3);


        }




        //CloseRight
        if (gamepad2.dpad_right) {




            clawR.setPosition(0.45);
        }


        //OpenRight
        if (gamepad2.dpad_left) {


            clawR.setPosition(0.28);
        }
        //CloseLeft
        if (gamepad2.b) {


            clawL.setPosition(0.9);
        }


        //OpenLeft
        if (gamepad2.x) {


            clawL.setPosition(1.25);
        }
        //Drone
        if (gamepad2.a && gamepad2.right_stick_button){
            drone.setPosition(0.7);
        }
        //Hanging
        //Up
        if (gamepad2.right_bumper){
            hang.setPower(0.8);
        }
        else if (!gamepad2.right_bumper){
            hang.setPower(0);
        }
        //Down
        if (gamepad2.left_bumper){
            hang.setPower(-0.8);
        }
    }
}

