package org.firstinspires.ftc.teamcode.faradaycode.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hang {

    public double hangPower = 1;
    public double slowConst = 0.45;

    public DcMotor hang;

    public Hang(HardwareMap hardwareMap) {
        hang = hardwareMap.dcMotor.get("hang");
        hang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void up(boolean isSlow, double nerf) {
        if (!isSlow) {
            hang.setPower(hangPower);
        } else {
            hang.setPower(hangPower * slowConst);
        }
    }
    public void down(boolean isSlow, double nerf) {
        if (!isSlow) {
            hang.setPower(-hangPower);
        } else {
            hang.setPower(-hangPower * slowConst);
        }
    }
    public void deactivate() {
        hang.setPower(0);
    }
}