package org.firstinspires.ftc.teamcode.faradaycode.components.DriveTrain;

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

    public void move(double forwardSpeed,double strafeSpeed,double turnSpeed, double nerf) {
        double denominator = Math.max(Math.abs(forwardSpeed) + Math.abs(strafeSpeed) + Math.abs(turnSpeed), powerRange);
        double frontLeftPower = ((forwardSpeed + strafeSpeed + turnSpeed) )/ denominator;
        double frontRightPower = ((forwardSpeed - strafeSpeed - turnSpeed) )/ denominator;
        double backLeftPower = ((forwardSpeed - strafeSpeed + turnSpeed) ) / denominator;
        double backRightPower = ((forwardSpeed + strafeSpeed - turnSpeed)) / denominator;

        fL.setPower(frontLeftPower);
        bL.setPower(backLeftPower);
        fR.setPower(frontRightPower);
        bR.setPower(backRightPower);
    }

}