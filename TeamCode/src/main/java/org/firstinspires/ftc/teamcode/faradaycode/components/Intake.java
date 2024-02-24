package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    public double intakePower = 1;

    public DcMotor intake1;
    public CRServo intake2;

    public Intake(HardwareMap hardwareMap) {
        intake1 = hardwareMap.dcMotor.get("intake1");
        intake2 = hardwareMap.crservo.get("intake2");
        intake1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake2.setDirection(CRServo.Direction.REVERSE);
    }

    public void activate() {
        intake1.setPower(intakePower);
        intake2.setPower(intakePower);
    }
    public void deactivate() {
        intake1.setPower(0);
        intake2.setPower(0);
    }
    public void reverse() {
        intake1.setPower(-intakePower);
        intake2.setPower(-intakePower);
    }
}
