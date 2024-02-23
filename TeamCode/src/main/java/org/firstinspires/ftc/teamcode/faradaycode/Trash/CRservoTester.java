
package org.firstinspires.ftc.teamcode.faradaycode.Trash;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@Autonomous(name = "CRservoTester")
public class CRservoTester extends LinearOpMode {


    CRServo servoTest;
    //CRServo servoTest2;
    DcMotor intake;


    public boolean stopped = false;


    @Override
    public void runOpMode() {

        servoTest = hardwareMap.crservo.get("servo"); //insert servo name
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.REVERSE );
        //servoTest2 = hardwareMap.crservo.get("servo2"); //insert servo name


        waitForStart();


        while (opModeIsActive() && stopped == false) {
            stopped = false;
            servoTest.setPower(-1);
            intake.setPower(-15);
            //servoTest2.setPower(-1);
        }




    }

    public void intake(int seconds){
        servoTest.setPower(1);

        //servoTest2.setPower(-1);
       // sleep(seconds*1000);
        //servoTest.setPower(1);
        //servoTest2.setPower(-1);
    }
}








