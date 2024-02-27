package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    public double intakeAPower = 0.544;
    public double intakeBPower = 1;

    public DcMotor intakeA;
    public CRServo intakeB1;
    public CRServo intakeB2;

    public Intake(HardwareMap hardwareMap) {
        intakeA = hardwareMap.dcMotor.get("intakeA");
        intakeB1 = hardwareMap.crservo.get("intakeB1");
        intakeB2 = hardwareMap.crservo.get("intakeB2");
        intakeA.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeB2.setDirection(CRServo.Direction.REVERSE);
    }

    public void activate(double nerf) {
        intakeA.setPower(intakeAPower);
        intakeB1.setPower(intakeBPower);
        intakeB2.setPower(intakeBPower);
    }
    public void deactivate() {
        intakeA.setPower(0);
        intakeB1.setPower(0);
        intakeB2.setPower(0);
    }
    public void reverse(double nerf) {
        intakeA.setPower(-intakeAPower);
        intakeB1.setPower(-intakeBPower);
        intakeB2.setPower(-intakeBPower);
    }
}
