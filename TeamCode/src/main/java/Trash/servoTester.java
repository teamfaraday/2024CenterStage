
package Trash;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
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


    Servo servoTest;

    static final int tpi = 50; //length ratio, these is a value from last year, so it'll prob need to be tweaked
    static final double lr = 0.8535; //length ratio, these is a value from last year, so it'll prob need to be tweaked
    static final double dr = 16.4; //degree ratio, value needs to be tested & tweaked

    public boolean stopped = false;


    @Override
    public void runOpMode() {

        servoTest = hardwareMap.get(Servo.class, "servo"); //insert servo name


        waitForStart();


        while (opModeIsActive() && stopped == false) {
            stopped = false;

            servo(0);

        }


    }


    public void servo(int position) {

        if (opModeIsActive()) {
            servoTest.setPosition(position);
        }
    }

}





