package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class BannerBox {

    //y
    public double rotate1Drop = 3; //right drop
    public double rotate2Drop = 0.863; //left drop
    public double releaseClose = 0.21; //release open

    //x
    public double rotate1Intake = 0.702; //right intake
    public double rotate2Intake = 0.5428; //left intake
    public double releaseOpen = 0.55; //release close

    public Servo rotate1;
    public Servo rotate2;
    public Servo release;


    public BannerBox(HardwareMap hardwareMap) {
        rotate1 = hardwareMap.get(Servo.class, "rotate1");
        rotate2 = hardwareMap.get(Servo.class, "rotate2");
        release = hardwareMap.get(Servo.class, "release");
    }

    public void intakePos(double nerf) {
        rotate1.setPosition(rotate1Intake);
        rotate2.setPosition(rotate2Intake);
        close();
    }
    public void dropPos(double nerf) {
        rotate1.setPosition(rotate1Drop);
        rotate2.setPosition(rotate2Drop);
        open();
    }
    public void close() {
        release.setPosition(releaseClose);
    }
    public void open() {
        release.setPosition(releaseOpen);
    }
}
