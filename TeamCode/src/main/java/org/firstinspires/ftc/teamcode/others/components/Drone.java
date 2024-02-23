package org.firstinspires.ftc.teamcode.others.components;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drone {

    Servo drone;

    public double initpos = 0;
    public double launchpos = 1;

    public Drone(HardwareMap hardwareMap) {
        drone = hardwareMap.servo.get("drone");
    }

    public void init() {
        drone.setPosition(initpos);
    }
    public void launch() {
        drone.setPosition(launchpos);
    }
}
