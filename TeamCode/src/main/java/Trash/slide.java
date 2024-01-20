package Trash;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="slide")
public class slide extends OpMode {


    public DcMotor slide;




    @Override
    public void init() {

        telemetry.update();

        slide = hardwareMap.get(DcMotor.class, "slide");



        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


    }






    @Override
    public void loop() {


        //Up

        double slidepower = 1;




        //Rotate Claw
        if (gamepad1.right_bumper) {


            slide.setPower(slidepower);


        } else if (!gamepad1.right_bumper) {


            slide.setPower(0);


        }


        //Reset Claw
        if (gamepad1.left_bumper) {


            slide.setPower(slidepower);


        }

        }
    }
