package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slide {

    public double slidePower = 1;
    public double antiGravPower = 0.1;
    public int posSlide;

    public DcMotor slide;
    public DcMotor slide2;



    public Slide(HardwareMap hardwareMap) {
        slide = hardwareMap.dcMotor.get("slide");
        slide2 = hardwareMap.dcMotor.get("slide2");
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void up() {
        slide.setPower(slidePower);
        slide2.setPower(slidePower);
    }
    public void down() {
        slide.setPower(-slidePower);
        slide2.setPower(-slidePower);
    }
    public void antiGrav() {
        slide.setPower(antiGravPower);
        slide2.setPower(antiGravPower);
    }
    public void deactivate() {
        slide.setPower(0);
        slide2.setPower(0);
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
