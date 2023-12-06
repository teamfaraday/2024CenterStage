package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TestTeleOp")
public class TestTeleOp extends OpMode {


    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    public DcMotor armSwivel;
    public DcMotor armRotate; //
    public Servo clawR;


    public Servo clawL;
    public Servo drone;
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


        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        armSwivel = hardwareMap.get(DcMotor.class, "armSwivel");
        armRotate = hardwareMap.get(DcMotor.class, "armRotate"); //
        clawR = hardwareMap.get(Servo.class, "clawR");
        clawL = hardwareMap.get(Servo.class, "clawL");
        drone = hardwareMap.get(Servo.class, "drone");


        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRotate.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE); //


        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);
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


        double y = -gamepad1.left_stick_y;
        double x = -gamepad1.left_stick_x * 0.75;
        double rx = gamepad1.right_stick_x * 0.5;


        double denominator  = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = (y+x+rx) / denominator;
        double backLeftPower = (y-x+rx) / denominator;
        double frontRightPower = (y-x-rx) / denominator;
        double backRightPower = (y+x-rx) / denominator;


        frontLeft.setPower(frontLeftPower*MOTOR_MULTIPLIER);
        backLeft.setPower(backLeftPower*MOTOR_MULTIPLIER);
        frontRight.setPower(frontRightPower*MOTOR_MULTIPLIER);
        backRight.setPower(backRightPower*MOTOR_MULTIPLIER);
//        frontLeft.setPower(Range.clip(y +x +rx,minPower,maxPower));
//        backLeft.setPower(Range.clip(y -x +rx,minPower,maxPower));
//        frontRight.setPower(Range.clip(y -x -rx,minPower,maxPower));
//        backRight.setPower(Range.clip(y +x -rx,minPower,maxPower));




        //Up
        if (gamepad1.y) {


            armRotate.setPower(0.86);
            telemetry.addData("Position", armRotate.getCurrentPosition());


        } else if (!gamepad1.y) {


            if (armRotate.getCurrentPosition() > 400) {


                armRotate.setPower(-0.214);


            } else {


                armRotate.setPower(0.274);
            }
        }
        //guide
        if (gamepad1.a) {




            armRotate.setPower(-0.6);


        }




        //Rotate Claw
        if (gamepad1.right_bumper) {


            armSwivel.setPower(0.2);


        } else if (!gamepad1.right_bumper) {


            armSwivel.setPower(0);


        }


        //Reset Claw
        if (gamepad1.left_bumper) {


            armSwivel.setPower(-0.2);


        }


        //Close
        if (gamepad1.right_bumper) {


            clawR.setPosition(0.32);
        }


        //Open
        if (gamepad1.left_bumper) {


            clawR.setPosition(0.23);
        }


        //Drone
      //  if gamepad1.
    }
}

