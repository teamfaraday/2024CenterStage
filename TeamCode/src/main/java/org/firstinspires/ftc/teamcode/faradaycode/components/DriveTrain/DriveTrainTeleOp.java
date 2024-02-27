package org.firstinspires.ftc.teamcode.faradaycode.components.DriveTrain;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrainTeleOp extends DriveTrainConstants {

    public double slowConst = 0.45;

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
        fL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void move(boolean isSlow, double forwardSpeed, double strafeSpeed, double turnSpeed, double nerf) {
        double denominator = Math.max(Math.abs(forwardSpeed) + Math.abs(strafeSpeed) + Math.abs(turnSpeed), powerRange);
        double frontLeftPower = ((forwardSpeed - strafeSpeed - turnSpeed)) / denominator;
        double frontRightPower = ((forwardSpeed + strafeSpeed + turnSpeed)) / denominator;
        double backLeftPower = ((forwardSpeed + strafeSpeed - turnSpeed)) / denominator;
        double backRightPower = ((forwardSpeed - strafeSpeed + turnSpeed)) / denominator;
        if (!isSlow) {
            fL.setPower(frontLeftPower);
            bL.setPower(backLeftPower);
            fR.setPower(frontRightPower);
            bR.setPower(backRightPower);
        } else {
            fL.setPower(frontLeftPower * slowConst);
            bL.setPower(backLeftPower * slowConst);
            fR.setPower(frontRightPower * slowConst);
            bR.setPower(backRightPower * slowConst);
        }
    }
}