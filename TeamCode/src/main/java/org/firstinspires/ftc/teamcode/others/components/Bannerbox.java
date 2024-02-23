package org.firstinspires.ftc.teamcode.others.components;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Bannerbox {

    public double rotate1Pos1 = 0.80; //right intake
    public double rotate2Pos1 = 0.55; //left intake

    public double rotate1Pos2 = 1.15; //right drop
    public double rotate2Pos2 = 0.20; //left drop

    public double releasePos1 = 0.15; //release open
    public double releasePos2 = 0.50; //release close

    public Servo rotate1;
    public Servo rotate2;
    public Servo release;

    public ElapsedTime timeSpent = new ElapsedTime();
    public double t;


    public Bannerbox(HardwareMap hardwareMap) {
        rotate1 = hardwareMap.get(Servo.class, "rotate1");
        rotate2 = hardwareMap.get(Servo.class, "rotate2");
        release = hardwareMap.get(Servo.class, "release");
    }

    public void intake() {
        rotate1.setPosition(rotate1Pos1);
        rotate2.setPosition(rotate2Pos1);
    }
    public void drop() {
        rotate1.setPosition(rotate1Pos2);
        rotate2.setPosition(rotate2Pos2);
    }
    public void open() {
        release.setPosition(releasePos1);
    }
    public void close() {
        release.setPosition(releasePos2);
    }
}
