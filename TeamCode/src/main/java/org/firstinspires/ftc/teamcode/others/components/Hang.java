package org.firstinspires.ftc.teamcode.others.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hang {

    public double hangPower;

    public DcMotor hang;

    public Hang(HardwareMap hardwareMap) {
        hang = hardwareMap.dcMotor.get("hang");
        hang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void up() {
        hang.setPower(hangPower);
    }
    public void down() {
        hang.setPower(-hangPower);
    }
    public void deactivate() {
        hang.setPower(0);
    }
}