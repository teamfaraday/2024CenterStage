package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.helper.HardwareSetup;

@TeleOp(name="TestTeleOp")
public class TestTeleop extends OpMode {

    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;


    @Override
    public void init() {

        HardwareSetup hs = new HardwareSetup(frontLeft, frontRight, backLeft, backRight);

        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backLeft");
    }

    @Override
    public void loop() {

        double minPower = -0.8;
        double maxPower = 0.8;

        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x * 1.25;
        double rx = gamepad1.right_stick_x * 0.5;

        frontLeft.setPower(Range.clip(y +x +rx,minPower,maxPower));
        backLeft.setPower(Range.clip(y -x +rx,minPower,maxPower));
        frontRight.setPower(Range.clip(y -x -rx,minPower,maxPower));
        backRight.setPower(Range.clip(y +x -rx,minPower,maxPower));


    }
}
