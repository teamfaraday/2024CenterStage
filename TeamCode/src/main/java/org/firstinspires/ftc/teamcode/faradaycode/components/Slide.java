package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slide {

    public double slidePower = 1;
    public double antiGravPower = 0.1;
    public double slowConst = 0.45;
    public double slowSlidePower = -0.1;
    public double posSlide;

    public DcMotor slide;
    public DcMotor slide2;



    public Slide(HardwareMap hardwareMap) {
        slide = hardwareMap.dcMotor.get("slide");
        slide2 = hardwareMap.dcMotor.get("slide2");
        slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slide2.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void up(boolean isSlow, double nerf) {
        if (!isSlow) {
            slide.setPower(slidePower);
            slide2.setPower(slidePower);
        } else {
            slide.setPower(slidePower * slowConst);
            slide2.setPower(slidePower * slowConst);
        }
    }
    public void down(boolean isSlow, double nerf) {
        if (!isSlow) {
            slide.setPower(-slidePower);
            slide2.setPower(-slidePower);
        } else {
            slide.setPower(-slidePower * slowConst * 0.6);
            slide2.setPower(-slidePower * slowConst * 0.6);
        }
    }
    public void slowDown() {
        slide.setPower(slowSlidePower);
        slide2.setPower(slowSlidePower);
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
