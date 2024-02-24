package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Bannerbox {

    public double rotate1Intake = 0.80; //right intake
    public double rotate2Intake = 0.55; //left intake

    public double rotate1Drop = 1.35; //right drop
    public double rotate2Drop = 0; //left drop

    public double releaseOpen = 0; //release open
    public double releaseClose = 1; //release close

    public Servo rotate1;
    public Servo rotate2;
    public Servo release;


    public Bannerbox(HardwareMap hardwareMap) {
        rotate1 = hardwareMap.get(Servo.class, "rotate1");
        rotate2 = hardwareMap.get(Servo.class, "rotate2");
        release = hardwareMap.get(Servo.class, "release");
    }

    public void intakePos() {
        rotate1.setPosition(rotate1Intake);
        rotate2.setPosition(rotate2Intake);
    }
    public void dropPos() {
        rotate1.setPosition(rotate1Drop);
        rotate2.setPosition(rotate2Drop);
    }
    public void open() {
        release.setPosition(releaseOpen);
    }
    public void close() {
        release.setPosition(releaseClose);
    }
}
