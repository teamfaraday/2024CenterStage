package org.firstinspires.ftc.teamcode.faradaycode.TeleOp;
import org.firstinspires.ftc.teamcode.faradaycode.OpModes;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleIsOpping")
public class TeleIsOpping extends OpModes {
    public void runOpMode(){
        super.runOpMode();
        driveTrainTeleOp.reverseDir();

        waitForStart();

        drone.init();
        bannerBox.intakePos(nerf);
        while (opModeIsActive() && !stopped){
            telemetry.addData("nerf", nerf);
            telemetry.update();

            boolean left_trigger1 = gamepad1.left_trigger > 0.6;
            boolean right_trigger1 = gamepad1.right_trigger > 0.6;

            isSlow = right_trigger1;
            nerf = Nerf.iterate(gamepad1.dpad_right, gamepad1.dpad_left);

            driveTrainTeleOp.iterate(isSlow, nerf, gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
            intake.iterate(gamepad1.a, gamepad1.b, isSlow, nerf);
            drone.iterate(left_trigger1 && right_trigger1);
            bannerBox.iterate(gamepad1.x,gamepad1.y,nerf,gamepad1.left_bumper);
            hang.iterate(gamepad1.dpad_up, gamepad1.dpad_down, isSlow, nerf);
            slide.iterate(gamepad1.right_bumper,gamepad1.right_bumper,isSlow, nerf, (gamepad1.a || gamepad1.b));
            if (gamepad1.left_bumper && left_trigger1 && gamepad1.right_bumper && right_trigger1) { stopped = true;}
        }
    }
}