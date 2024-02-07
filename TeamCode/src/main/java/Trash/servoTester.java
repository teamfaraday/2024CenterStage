
package Trash;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@Autonomous(name = "servoTester")
public class servoTester extends LinearOpMode {


    CRServo servoTest;
    CRServo servoTest2;


    public boolean stopped = false;


    @Override
    public void runOpMode() {

        servoTest = hardwareMap.crservo.get("servo"); //insert servo name
        servoTest2 = hardwareMap.crservo.get("servo2"); //insert servo name


        waitForStart();


        while (opModeIsActive() && stopped == false) {
            stopped = false;
            intake(30);
            stopped = true;

        }




    }

    public void intake(int seconds){
        servoTest.setPower(1);
        servoTest2.setPower(-1);
        sleep(seconds*1000);
        servoTest.setPower(1);
        servoTest2.setPower(-1);
    }
}








