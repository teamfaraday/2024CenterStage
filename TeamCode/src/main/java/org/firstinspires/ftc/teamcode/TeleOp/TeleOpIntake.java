package org.firstinspires.ftc.teamcode.TeleOp;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOpIntake")
public class TeleOpIntake extends OpMode {


    public DcMotor intake;


    ElapsedTime et = new ElapsedTime();


    @Override
    public void init() {

        telemetry.update();


        intake = hardwareMap.get(DcMotor.class, "intake");


        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




    }


    @Override
    public void loop() {


        telemetry.update();

        //slides


        //intake
        if (gamepad1.a) {
            intake.setPower(100);
        } else if (!gamepad1.a){
            intake.setPower(0);
        }

    }
}
