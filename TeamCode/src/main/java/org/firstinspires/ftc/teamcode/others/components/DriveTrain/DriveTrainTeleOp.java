package org.firstinspires.ftc.teamcode.others.components.DriveTrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrainTeleOp extends DriveTrainConstants {

    public DriveTrainTeleOp(HardwareMap hardwareMap) {
        fL = hardwareMap.get(DcMotor.class, "frontLeft");
        fR = hardwareMap.get(DcMotor.class, "frontRight");
        bL = hardwareMap.get(DcMotor.class, "backLeft");
        bR = hardwareMap.get(DcMotor.class, "backRight");
        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setDirection(DcMotor.Direction.REVERSE);
        bR.setDirection(DcMotor.Direction.REVERSE);
    }

    public void move(double y,double x,double rx,double bnerf,double fnerf) {
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), powerRange);
        double frontLeftPower = ((y + x - rx) * fnerf)/ denominator;
        double backLeftPower = ((y - x - rx) * bnerf) / denominator;
        double frontRightPower = ((y - x + rx) * fnerf)/ denominator;
        double backRightPower = ((y + x + rx) * bnerf) / denominator;

        fL.setPower(frontLeftPower);
        bL.setPower(backLeftPower);
        fR.setPower(frontRightPower);
        bR.setPower(backRightPower);
    }

}