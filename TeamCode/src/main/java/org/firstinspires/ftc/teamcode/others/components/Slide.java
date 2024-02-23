package org.firstinspires.ftc.teamcode.others.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slide {

    public double slidePower = 1;
    public double antiGravPower = -0.1;

    DcMotor slide;

    public int posSlide;

    public Slide(HardwareMap hardwareMap) {
        slide = hardwareMap.dcMotor.get("slide");
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void up() {
        slide.setPower(slidePower);
    }
    public void down() {
        slide.setPower(-slidePower);
    }
    public void antiGrav() {
        slide.setPower(antiGravPower);
    }
    public void deactivate() {
        slide.setPower(0);
    }

    //clean this up later note to self
    /*public void slide(double degrees, double speed) {
        posSlide = intake1.getCurrentPosition();

        posSlide += degrees;

        .setTargetPosition((int) pos);

        intake1.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        intake1.setPower(Math.abs(speed));

        while (intake1.isBusy()) {}

            intake1.setPower(0);

            intake1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
       } */
}
